<template>
  <div class="page-header clear-filter" filter-color="orange">
    <b-container class="bv-example-row md-3">
      <div class="content">
        <div
          class="row col-md-12 justify-content-center mb-4"
          style="padding-bottom: 0px">
          <div class="col-md-2"></div>
          <div class="col-md-8">
            <apt-search-form></apt-search-form>
          </div>
        </div>
        <div class="row">
          <b-row class="mb-5">
            <b-col cols="4" class="p-0" style="margin-top: 20px">
              <apt-list> </apt-list>
            </b-col>
            <b-col class="p-0">
              <apt-map v-on:show-modal="showModal"></apt-map>
            </b-col>
          </b-row>
        </div>
      </div>
      <b-modal
        id="modal-lg"
        size="lg"
        v-model="modalShow"
        title="아파트 상세 조회">
        <apt-detail></apt-detail>
      </b-modal>
    </b-container>
  </div>
</template>
<script>
import AptSearchForm from "@/pages/apt/AptSearchForm";
import AptMap from "@/pages/apt/AptMap";
import AptList from "@/pages/apt/AptList";
import AptDetail from "@/pages/apt/AptDetail";
import { mapActions } from "vuex";
const userStore = "userStore";
export default {
  name: "AptPage",
  data() {
    return {
      modalShow: false,
    };
  },
  components: {
    AptSearchForm,
    AptMap,
    AptList,
    AptDetail,
  },
  created() {
    this.clearHouse();
  },
  methods: {
    ...mapActions(userStore, ["clearHouse"]),
    showModal() {
      this.modalShow = true;
    },
  },
};
</script>
<style></style>
