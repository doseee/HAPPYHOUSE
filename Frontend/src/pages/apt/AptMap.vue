/* eslint-disable prettier/prettier */
<template>
  <div class="content">
    <b-row>
      <b-col>
        <b-list-group horizontal>
          <b-list-group-item
            class="category"
            :class="{ selected: martSelected }"
            @click="selectMarket">
            편의점
          </b-list-group-item>
          <b-list-group-item
            class="category"
            :class="{ selected: bankSelected }"
            @click="selectBank(0)">
            은행
            <!-- <b-button type="button">은행</b-button -->
          </b-list-group-item>

          <b-list-group-item
            class="category"
            :class="{ selected: hpSelected }"
            @click="selectHospital">
            병원
          </b-list-group-item>
          <b-list-group-item
            class="category"
            :class="{ selected: subwaySelected }"
            @click="selectSubway">
            지하철
          </b-list-group-item>
          <button
            class="category1"
            id="popover-button-event"
            ref="button"
            style="margin-left: 408px">
            <i class="input-group-text now-ui-icons design_bullet-list-67"></i>
          </button>
        </b-list-group>
        <b-popover
          ref="popover"
          target="popover-button-event"
          title="필터링"
          triggers="focus"
          :show.sync="popoverShow"
          placement="top"
          class="popstyle">
          <template #title>
            <div class="filtertitle">
              <b-button
                @click="onClose"
                class="close m-0"
                aria-label="Close"
                style="background-color: gray">
                <span class="d-inline-block" aria-hidden="true">&times;</span>
              </b-button>
              <h6>아파트 필터</h6>
            </div>
          </template>

          <b-container class="flex-direction-col mx-2">
            <div class="my-4">
              거래 금액 (단위 : 만원)
              <vue-slider
                v-model="pricevalue"
                :data="pricedata"
                :tooltip="'always'"
                :tooltip-placement="['bottom', 'bottom']"
                :tooltip-formatter="format1"
                @dragging="onSubmit"></vue-slider>
            </div>
            <div class="my-4">
              건설연도 (단위 : 년)
              <vue-slider
                v-model="buildrange"
                :tooltip="'always'"
                :tooltip-placement="['bottom', 'bottom']"
                :tooltip-formatter="format2"
                :min="1961"
                :max="2022"
                @dragging="onSubmit"></vue-slider>
            </div>
            <b-row class="mt-4 d-flex justify-content-end">
              <button
                @click="onReset"
                size="sm"
                variant="outline-danger"
                class="btn wh btn-sm btn-secondary"
                style="background: rgba(201, 138, 195, 0.6)">
                초기화
              </button>
            </b-row>
          </b-container>
        </b-popover>
      </b-col>
    </b-row>

    <div id="map" style="width: 750px; height: 400px"></div>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";
import VueSlider from "vue-slider-component";
import "vue-slider-component/theme/default.css";
const aptStore = "aptStore";
export default {
  name: "AptMap",
  data() {
    return {
      initPostion: [],
      markers: [], //마커를 담는 배열
      map: null,
      places: null,
      ps: null, //장소 검색 객체
      infoWindow: null,
      customOverlay: null,
      sideMarkers: [[], [], [], []], //순서대로 은행, 병원, 마트 마커를 담을 배열
      bankSelected: false,
      martSelected: false,
      hpSelected: false,
      subwaySelected: false,
      imgSrcs: [
        "img/bank.png",
        "img/store.png",
        "img/hospital.png",
        "img/subway.png",
      ],
      codes: ["BK9", "CS2", "HP8", "SW8"],
      popoverShow: false,
      pricevalue: "1200",
      pricedata: ["1", "5", "10", "20", "50", "100", "500", "1000", "1200"],
      buildrange: [1961, 2022],
      format1: (v) => `${v / 10.0}억`,
      format2: `{value}년`,
    };
  },
  computed: {
    ...mapState(aptStore, [
      "houses",
      "house",
      "housesfilter",
      "sidoCode",
      "gugunCode",
      "dongCode",
    ]),
    // listUpdate: function () {
    //   return displayMarkers(this.housesfilter);
    // },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=137733fdbf835c5c41c957dd14e3d699&libraries=services";
      document.head.appendChild(script);
    }
  },
  watch: {
    housesfilter: function () {
      if (this.housesfilter.length != 0 && this.housesfilter) {
        this.displayMarkers();
      }
    },
    house: function () {
      this.setMapCenter();
    },
    houses: function () {
      this.pricevalue = "1200";
      this.buildrange = [1961, 2022];
      this.bankSelected = false;
      this.martSelected = false;
      this.hpSelected = false;
      this.subwaySelected = false;
      for (let i = 0; i < 4; i++) {
        this.removeMarkerSide(i);
      }
    },
  },
  methods: {
    ...mapActions(aptStore, [
      "clearHouses",
      "clearHousesFilter",
      "setHousesFilter",
      "detailHouse",
    ]),
    setMapCenter() {
      let moveLatLon = new kakao.maps.LatLng(this.house.lat, this.house.lng);
      this.map.setCenter(moveLatLon);
      this.map.setLevel(2);
    },
    onClose() {
      this.popoverShow = false;
    },
    onSubmit() {
      this.clearHousesFilter();
      //필터 적용하기
      let hlist = this.houses;
      let filterHouse = hlist.filter((h) => {
        return (
          parseInt(h.avgPrice) <= this.pricevalue * 1000 &&
          h.buildYear >= this.buildrange[0] &&
          h.buildYear <= this.buildrange[1]
        );
      });
      this.setHousesFilter(filterHouse);
    },
    setDetail(house) {
      this.detailHouse(house);
      this.$emit("show-modal");
    },
    onReset() {
      //필터 해제
      let origin = this.houses;
      this.setHousesFilter(origin);
      this.pricevalue = "1200";
      this.buildrange = [1961, 2022];
    },
    initMap() {
      const container = document.getElementById("map"); // 지도를 표시할 div let
      let Options = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
      this.map = new kakao.maps.Map(container, Options);
      this.infoWindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      this.ps = new kakao.maps.services.Places(this.map);
      //지도를 이동할때마다 편의시설 조회해서 지도에 표기
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
    },
    removeMarker() {
      for (let i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    addMarker(position) {
      let imageSrc = "img/apt.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(30, 30), // 마커 이미지의 크기
        imgOptions = {
          // offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          // eslint-disable-next-line prettier/prettier
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });
      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다
      return marker;
    },
    selectBank() {
      if (this.bankSelected) {
        this.bankSelected = false;
        //마커에 은행 제거하기
        this.removeMarkerSide(0);
      } else {
        this.bankSelected = true;
        this.findSide(0, this.codes[0]);
      }
    },
    //편의점
    selectMarket() {
      if (this.martSelected) {
        this.martSelected = false;
        //마커에 은행 제거하기
        this.removeMarkerSide(1);
      } else {
        this.martSelected = true;
        this.findSide(1, this.codes[1]);
      }
    },
    //병원
    selectHospital() {
      if (this.hpSelected) {
        this.hpSelected = false;
        //마커에 은행 제거하기
        this.removeMarkerSide(2);
      } else {
        this.hpSelected = true;
        this.findSide(2, this.codes[2]);
      }
    },
    //지하철
    selectSubway() {
      if (this.subwaySelected) {
        this.subwaySelected = false;
        //마커에 은행 제거하기
        this.removeMarkerSide(3);
      } else {
        this.subwaySelected = true;
        this.findSide(3, this.codes[3]);
      }
    },
    //idle함수
    searchPlaces() {
      if (this.bankSelected) this.findSide(0, this.codes[0]);
      if (this.martSelected) this.findSide(1, this.codes[1]);
      if (this.hpSelected) this.findSide(2, this.codes[2]);
      if (this.subwaySelected) this.findSide(3, this.codes[3]);
    },
    //시설 찾기
    findSide(idx, code) {
      this.ps.categorySearch(
        code,
        (data, status) => {
          if (status === kakao.maps.services.Status.OK) {
            this.displayMarkerSide(idx, data);
          }
        },
        // eslint-disable-next-line prettier/prettier
        { useMapBounds: true }
      );
    },
    displayMarkers() {
      let map = this.map;
      let bounds = new kakao.maps.LatLngBounds();
      this.removeMarker();
      this.housesfilter.map((house) => {
        let placePosition = new kakao.maps.LatLng(
          house.lat,
          // eslint-disable-next-line prettier/prettier
          house.lng
        );
        let mk = this.addMarker(placePosition);
        //지도 범위를 재설정하기 위해 LatLngBounds에 좌표를 추가
        bounds.extend(placePosition);
        //마커와 검색 결과 항목에 mouseover 했을때 해당 장소에 인포윈도우에 장소명 표시
        let content = `<div class="infowindow" style="color: black">
                          <span class="title">
                            ${house.apartmentName}
                            </span>
                      </div>`;
        let overlay = new kakao.maps.CustomOverlay({
          content: content,
          map: map,
          position: mk.getPosition(),
        });
        overlay.setMap(null);
        //마커 클릭시 vuex에 해당 아파트 정보 저장하고, 모달창 띄우기
        kakao.maps.event.addListener(mk, "click", () => {
          this.setDetail(house);
        });
        kakao.maps.event.addListener(mk, "mouseover", function () {
          overlay.setMap(map);
        });
        kakao.maps.event.addListener(mk, "mouseout", function () {
          overlay.setMap(null);
        });
      });
      //검색된 장소 위치를 기준으로 지도 범위 재설정
      this.map.setBounds(bounds);
    },
    //편의시설 관련 마커 추가
    displayMarkerSide(idx, data) {
      this.removeMarkerSide(idx);
      // idle함수가 실행되는 동안에 초기화 시키기 때문에 한번더 체크 해야함
      if (
        (idx == 0 && !this.bankSelected) ||
        (idx == 1 && !this.martSelected) ||
        (idx == 2 && !this.hpSelected) ||
        (idx == 3 && !this.subwaySelected)
      )
        return;
      let imgSrc = this.imgSrcs[idx],
        imgSize = new kakao.maps.Size(30, 30),
        markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);
      data.map((item) => {
        let itemPositon = new kakao.maps.LatLng(item.y, item.x);
        let mk = new kakao.maps.Marker({
          position: itemPositon, // 마커의 위치
          image: markerImage,
        });
        mk.setMap(this.map);
        this.sideMarkers[idx].push(mk);
      });
    },
    removeMarkerSide(idx) {
      for (let i = 0; i < this.sideMarkers[idx].length; i++) {
        this.sideMarkers[idx][i].setMap(null);
      }
      this.sideMarkers[idx] = [];
    },
  },
  components: {
    VueSlider,
  },
};
</script>
<style>
.category {
  margin-top: 20px;
  height: 40px;
  color: black !important;
  display: flex !important;
  align-items: center !important;
}
.category1 {
  border: none;
  background-color: rgba(0, 0, 0, 0);
  margin-top: 20px;
  height: 40px;
  display: flex !important;
  align-items: center !important;
}
.infowindow {
  padding: 5px 10px;
  background-color: white;
  position: relative;
  bottom: 70px;
  border-radius: 10px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.selected {
  background-color: gray !important;
  color: whitesmoke;
}
.filtertitle {
  color: black;
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-around;
  align-items: baseline;
}
.popover.b-popover {
  border: none;
}
.con {
  position: relative;
  overflow-y: auto;
  height: 600px;
}
.con::-webkit-scrollbar {
  width: 10px;
}
.con::-webkit-scrollbar-thumb {
  background-color: black;
  border-radius: 10px;
}
.con::-webkit-scrollbar-track {
  border-radius: 10px;
}
.center {
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: center;
}
.right {
  display: flex;
  align-items: flex-end;
}
.wd {
  width: 100%;
}
</style>
