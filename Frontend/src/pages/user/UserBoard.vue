<template>
  <div class="container clear-filter">
    <div class="row">
      <div class="col-1"></div>
      <div class="col-10">
        <card type="login" plain>
          <h3 style="color: black">내 글</h3>
          <b-table-simple sticky-header class="con">
            <colgroup style="padding: 0px">
              <col style="width: 60px" />
              <col style="width: 150px" />
              <col style="width: 100px" />
              <col style="width: 10px" />
              <col style="width: 10px" />
            </colgroup>
            <b-thead>
              <b-tr>
                <b-th>번호</b-th>
                <b-th>제목</b-th>
                <b-th>작성일자</b-th>
                <b-th></b-th>
                <b-th></b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <user-board-item
                v-for="(region, idx) in boardList"
                :key="idx"
                v-bind="region"
                :idx="idx + 1">
              </user-board-item>
            </tbody>
          </b-table-simple>
        </card>
      </div>
    </div>
  </div>
</template>
<script>
import { Card } from "@/components";
import { mapState } from "vuex";
import UserBoardItem from "@/pages/user/UserBoardItem";
const userStore = "userStore";

export default {
  name: "UserBoard",
  bodyClass: "user-info-view",
  data() {
    return {
      userId: null,
    };
  },
  computed: {
    ...mapState(userStore, ["boardList", "userInfo"]),
  },

  components: {
    Card,
    UserBoardItem,
  },
};
</script>
<style>
.user-page-title {
  padding: 10px;
  background-color: gray;
}
.con {
  overflow-y: auto;
  max-height: 300px;
}
.con::-webkit-scrollbar {
  width: 5px;
}
.con::-webkit-scrollbar-thumb {
  background-color: black;
  border-radius: 10px;
}
.con::-webkit-scrollbar-track {
  border-radius: 10px;
}
</style>
