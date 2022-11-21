<template>
  <div class="container" style="padding-left: 24px">
    <div style="height: 70px"></div>
    <div
      class="row col-md-12 justify-content-center"
      style="paddding-top: 40px; padding-bottom: 0px margin-bottom: 0px">
      <div class="form-group col-md-2">
        <b-form-select
          v-model="selectSido"
          :options="sidos"
          @change="getSidoList"></b-form-select>
      </div>
      <div class="form-group col-md-2">
        <b-form-select
          v-model="selectGugun"
          :options="guguns"
          @change="getGugunList"></b-form-select>
      </div>
      <div class="form-group col-md-2">
        <b-form-select
          v-model="selectDong"
          :options="dongs"
          @change="getDongList"></b-form-select>
      </div>
      <div style="margin-right: 10px">
        <b-button variant="outline-primary">검색</b-button>
      </div>
      <div>
        <b-button variant="outline-primary">관심지역추가</b-button>
      </div>
    </div>
  </div>
</template>
<script>
import { sidoList, gugunList, dongList } from "@/api/apt.js";
import { mapActions, mapState } from "vuex";

const houseStore = "houseStore";
export default {
  name: "AptSearchForm",
  data() {
    return {
      selectSido: null,
      selectGugun: null,
      selectDong: null,
      sidos: [{ value: null, text: "시/도 선택" }],
      guguns: [{ value: null, text: "구/군 선택" }],
      dongs: [{ value: null, text: "동 선택" }],
    };
  },
  computed: {
    ...mapState(houseStore, ["sidoCode", "gugunCode", "dongCode"]),
  },
  created() {
    this.selectSido = this.sidoCode;
    this.selectGugun = this.gugunCode;
    this.selectDong = this.dongCode;
    this.getSidoList();
    let params = this.$route.params;
    if (params.sidoCodeParam) {
      this.currentSido = params.sidoCodeParam.substr(0, 2); //시도 설정
      //구군 불러오기
      this.getGugunList();
      this.currentGugun = params.gugunCodeParam.substr(0, 5); //구군 설정
      //동 불러오기
      this.getDongList();
      this.currentDong = params.dongCodeParam; //동 설정
      this.getHouseList();
    }
  },
  methods: {
    ...mapActions(houseStore, [
      "setHouses",
      "setHousesFilter",
      "setSidoCode",
      "setGugunCode",
      "setDongCode",
      "clearSidoCode",
      "clearGugunCode",
      "clearDongCode",
    ]),
    getSidoList() {
      sidoList((res) => {
        res.data.forEach((sido) => {
          this.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
        });
      });
    },
    getGugunList() {
      this.setSidoCode(this.currentSido);
      this.guguns = [{ value: null, text: "선택" }];
      gugunList({ sidoCode: this.currentSido }, (res) => {
        res.data.forEach((gugun) => {
          if (gugun.gugunName != null) {
            if (!gugun.gugunCode) gugun.gugunCode = null;
            this.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
          }
        });
      });
    },
    getDongList() {
      this.setGugunCode(this.currentGugun);
      this.dongs = [{ value: null, text: "선택" }];
      dongList({ sidoCode: this.currentGugun }, (res) => {
        res.data.forEach((dong) => {
          if (dong.dongName != null) {
            if (!dong.dongCode) dong.dongCode = null;
            this.dongs.push({ value: dong.dongCode, text: dong.dongName });
          }
        });
      });
    },
    getHouseList() {
      this.setDongCode(this.currentDong);
      this.isLikeDong();
      houseListByDong(
        {
          dongCode: this.currentDong,
        },
        (res) => {
          this.setHouses(res.data);
          this.setHousesFilter(res.data);
          // eslint-disable-next-line prettier/prettier
        }
      );
    },
  },
  destroyed() {
    this.clearSidoCode();
    this.clearGugunCode();
    this.clearDongCode();
  },
};
</script>
<style scoped></style>
