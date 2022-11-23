<template>
  <b-container>
    <div class="tit">
      <div>
        <h3>{{ this.house.apartmentName }}</h3>
      </div>
      <b-button type="button" variant="warning" @click="linkMap" class="wd"
        >자세히보기</b-button
      >
    </div>
    <b-list-group class="my-3">
      <b-list-group-item>
        <span class="title"> 도로명 </span>
        {{ this.house.sidoname }} {{ this.house.gugunname }}
        {{ this.house.roadName }} {{ this.house.roadNameBonbun | toInt }}
        {{ this.house.roadNameBubun | toInt }}
      </b-list-group-item>
      <b-list-group-item>
        <span class="title">지번 </span>{{ this.house.sidoname }}
        {{ this.house.gugunname }} {{ this.house.jibun }}
      </b-list-group-item>
      <b-list-group-item>
        <b-row>
          <b-col>
            <span class="title">건설연도</span> {{ this.house.buildYear }}년
          </b-col>
          <b-col>
            <span class="title">평균 거래 금액</span>
            {{ this.house.avgPrice | price | toprice }}만원
          </b-col>
        </b-row>
      </b-list-group-item>
    </b-list-group>
    <h4 class="my-3">거래 목록</h4>
    <b-table-simple sticky-header class="deal-list">
      <b-thead head-variant="dark">
        <b-tr>
          <b-th v-for="(field, i) in fields" :key="i" sortable>
            {{ field }}
          </b-th>
        </b-tr>
      </b-thead>
      <b-tbody>
        <b-tr v-for="(deal, idx) in deals" :key="idx">
          <b-td
            >{{ deal.dealYear }}년 {{ deal.dealMonth }}월
            {{ deal.dealDay }}일</b-td
          >
          <b-td>{{ deal.area | areaChange }}</b-td>
          <b-td>{{ deal.dealAmount | toInt | price | toprice }}만원</b-td>
          <b-td>{{ deal.floor }}층</b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { dealsByCode } from "@/api/apt.js";
const aptStore = "aptStore";
export default {
  name: "AptDetail",
  computed: {
    ...mapState(aptStore, ["house"]),
  },
  data() {
    return {
      //해당 아파트의 거래 내역을 조회
      deals: [],
      fields: ["거래일", "면적", "금액", "층수"],
    };
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
    areaChange(value) {
      let ch = Math.round(parseInt(value) * 0.3025);
      return `${value}m² (${ch}평)`;
    },
  },
  created() {
    dealsByCode({ aptCode: this.house.aptCode }, (res) => {
      this.deals = res.data;
    });
  },
  methods: {
    linkMap() {
      window.open(
        `https://map.kakao.com/link/map/${this.house.apartmentName},${this.house.lat},${this.house.lng}`,
        "_blank",
      );
    },
  },
};
</script>

<style scoped>
.title {
  font-weight: bold;
}
.deal-list::-webkit-scrollbar {
  width: 15px;
}
.deal-list::-webkit-scrollbar-thumb {
  background-color: #181817;
  border-radius: 10px;
}
.deal-list::-webkit-scrollbar-track {
  border-radius: 10px;
}
.b-table-sticky-header {
  max-height: 300px !important;
}
.center {
  text-align: center;
}
.wd {
  width: 120px;
}
.tit {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
