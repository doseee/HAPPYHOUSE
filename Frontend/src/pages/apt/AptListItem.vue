<template>
  <b-list-group-item
    class="m-2 rd-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="[
      { 'mouse-over-bgcolor': isColor },
      { 'house-selected': isSelected },
    ]">
    <b-row
      class="d-flex"
      style="color: black; font-size: 14px; font-weigth: bold">
      <p>{{ houseItem.apartmentName }} | &nbsp;</p>
      <p style="color: blue">
        평균 {{ houseItem.avgPrice | price | toprice }}만원
      </p>
    </b-row>
    <b-row style="color: rgba(201, 138, 195, 0.6)">
      {{ houseItem.sidoname }} {{ houseItem.gugunname }} {{ houseItem.dong }}
      {{ houseItem.jibun }}
    </b-row>
  </b-list-group-item>
</template>

<script>
import { mapState, mapActions } from "vuex";
const aptStore = "aptStore";
export default {
  name: "AptListItem",
  data() {
    return {
      isColor: false,
      isSelected: false,
    };
  },
  props: {
    houseItem: Object,
  },
  computed: {
    ...mapState(aptStore, ["house"]),
  },
  filters: {
    toInt(val) {
      if (!val) return "";
      let num = parseInt(val);
      if (num == 0) return "";
      else return num;
    },
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    toprice(value) {
      let front = value.substr(0, value.length - 5);
      let back = value.substr(value.length - 5, value.length);
      if (back.charAt(0) == "0") {
        back = back.substr(2);
      }
      return front == "" ? `${back}` : `${front}억 ${back}`;
    },
  },
  watch: {
    house: function () {
      if (this.house && this.house.aptCode == this.houseItem.aptCode) {
        this.isSelected = true;
      } else {
        this.isSelected = false;
      }
    },
  },
  methods: {
    ...mapActions(aptStore, ["detailHouse"]),
    selectHouse() {
      if (this.house && this.house.aptCode == this.houseItem.aptCode) {
        this.isSelected = true;
      }
      this.detailHouse(this.houseItem);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  font-size: 1rem;
}
.mouse-over-bgcolor {
  background-color: gray;
}
.rd-2 {
  border-radius: 10px !important;
}
.house-selected {
  background-color: gray;
}
</style>
