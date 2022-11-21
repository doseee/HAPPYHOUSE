/* eslint-disable prettier/prettier */
<template>
  <div class="content">
    <b-row>
      <b-col>
        <b-list-group horizontal id="category">
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
        </b-list-group>
      </b-col>
    </b-row>
    <div id="map" style="width: 750px; height: 400px"></div>
  </div>
</template>
<script>
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
    };
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
  methods: {
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
      let imageSrc =
          "https://images.vexels.com/media/users/3/142675/isolated/lists/84e468a8fff79b66406ef13d3b8653e2-house-location-marker-icon.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(50, 50), // 마커 이미지의 크기
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
        imgSize = new kakao.maps.Size(40, 40),
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
};
</script>
<style>
.category {
  height: 40px;
  color: black !important;
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
</style>
