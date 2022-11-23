<template>
  <div class="container clear-filter">
    <div class="row">
      <div class="col-1"></div>
      <div class="col-10">
        <card type="login" plain>
          <h3 style="color: black">
            {{ userInfo.userName }}님의 관심지역 페이지
          </h3>
          <b-table-simple sticky-header class="con">
            <colgroup style="padding: 0px">
              <col style="width: 10%" />
              <col style="width: 60%" />
              <col style="width: 10%" />
              <col style="width: 10%" />
            </colgroup>
            <b-thead>
              <b-tr>
                <b-th>번호</b-th>
                <b-th>지역</b-th>
                <b-th>조회</b-th>
                <b-th>삭제</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <user-like-apt-item
                v-for="(region, idx) in likeList"
                :key="idx"
                v-bind="region"
                :idx="idx + 1">
              </user-like-apt-item>
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
import UserLikeAptItem from "@/pages/user/UserLikeAptItem";
const userStore = "userStore";

export default {
  name: "UserLikeApt",
  bodyClass: "user-info-view",
  data() {
    return {
      userId: null,
    };
  },
  computed: {
    ...mapState(userStore, ["likeList", "userInfo"]),
  },

  components: {
    Card,
    UserLikeAptItem,
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
