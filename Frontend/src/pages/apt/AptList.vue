<template>
  <b-container
    class="row align-items-end"
    style="padding-top: 30px; padding-bottom: 0px; margin-left: 10px">
    <div
      v-if="houses && houses.length != 0"
      class="bv-example-row wd"
      style="padding-top: 30px">
      <b-row></b-row>
      <b-list-group class="con" style="padding-top: 10px; max-height: 400px">
        <apt-list-item
          v-for="(house, index) in housesfilter"
          v-on:show-modal="showModal"
          :key="index"
          :houseItem="house" />
      </b-list-group>
    </div>
    <div v-else class="bv-example-row mt-3 center" style="margin-right: 5px">
      <b-row>
        <b-col>
          <div class="d-flex">
            <h3>아파트 목록이 없습니다.</h3>
          </div>
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import AptListItem from "@/pages/apt/AptListItem";
import { mapActions, mapState } from "vuex";
const aptStore = "aptStore";
export default {
  name: "AptList",
  components: {
    AptListItem,
  },
  data() {
    return {
      popoverShow: false,
      pricevalue: "1200",
      pricedata: ["1", "5", "10", "20", "50", "100", "500", "1000", "1200"],
      buildrange: [1961, 2022],
      format1: (v) => `${v / 10.0}억`,
      format2: `{value}년`,
    };
  },
  watch: {
    houses: function () {
      this.pricevalue = "1200";
      this.buildrange = [1961, 2022];
    },
  },
  created() {
    this.clearHouses();
  },
  computed: {
    ...mapState(aptStore, ["houses", "housesfilter"]),
  },
  methods: {
    ...mapActions(aptStore, [
      "clearHouses",
      "clearHousesFilter",
      "setHousesFilter",
    ]),
    showModal() {
      this.$emit("show-modal");
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
    onReset() {
      //필터 해제
      let origin = this.houses;
      this.setHousesFilter(origin);
      this.pricevalue = "1200";
      this.buildrange = [1961, 2022];
    },
  },
};
</script>

<style>
.con2 {
  position: relative;
  overflow-y: auto;
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

.filtertitle {
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-around;
  align-items: baseline;
}
.popover.b-popover {
  border: none;
}
.right {
  display: flex;
  align-items: flex-end;
}
.wd {
  width: 100%;
}
</style>
