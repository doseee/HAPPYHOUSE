<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="content">
      <div class="row">
        <div class="col-3">
          <user-func-list v-on:func-select="changeView"></user-func-list>
        </div>
        <div class="col-lg-6">
          <user-info-view v-if="routeData == 'mypage'"> </user-info-view>
          <user-modify v-if="routeData == 'modify'" :propsData="userInfo">
          </user-modify>
          <user-like-apt
            v-if="routeData == 'likeapt'"
            :propsData="userInfo"></user-like-apt>
          <user-pwd-change
            v-if="routeData == 'pwdchange'"
            :propsData="userInfo"
            v-on:gotomypage="changeView"></user-pwd-change>
          <user-board
            v-if="routeData == 'myboard'"
            :propsData="userInfo"></user-board>
          <user-comment
            v-if="routeData == 'mycomment'"
            :propsData="userInfo"></user-comment>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Button } from "@/components";
import UserInfoView from "@/pages/user/UserInfoView";
import UserModify from "@/pages/user/UserModify";
import UserLikeApt from "@/pages/user/UserLikeApt";
import UserFuncList from "@/pages/user/UserFuncList";
import { mapState, mapActions } from "vuex";
import UserPwdChange from "@/pages/user/UserPwdChange";
import UserComment from "@/pages/user/UserComment";
import UserBoard from "@/pages/user/UserBoard";
const userStore = "userStore";
export default {
  name: "UserPage",
  data() {
    return {
      modals: {
        mini: false,
      },
      routeData: "mypage",
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  components: {
    [Button.name]: Button,
    UserInfoView,
    UserModify,
    UserLikeApt,
    UserFuncList,
    UserPwdChange,
    UserBoard,
    UserComment,
  },
  methods: {
    ...mapActions(userStore, [
      "userRemove",
      "setLikeList",
      "setBoardList",
      "setCommentList",
    ]),
    changeView(data) {
      this.routeData = data;
      if (this.routeData === "likeapt") {
        this.setLikeList(this.userInfo.userId);
      } else if (this.routeData === "myboard") {
        this.setBoardList(this.userInfo.userId);
      } else if (this.routeData === "mycomment") {
        this.setCommentList(this.userInfo.userId);
      }
    },
  },
};
</script>
<style>
#sidebar {
  width: 350px;
  height: 100%;
  padding: 5px 15px;
  float: left;
  background-color: rgba(201, 138, 195, 0.00001);
}
</style>
