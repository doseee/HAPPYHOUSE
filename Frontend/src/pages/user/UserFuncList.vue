<template>
  <div class="content">
    <v-list id="sidebar" class="clear-filter nav-link">
      <v-list-group :value="true" no-action sub-group>
        <template v-slot:activator>
          <v-list-item-title style="color: white">
            회원관리
            <i class="now-ui-icons arrows-1_minimal-down"></i>
          </v-list-item-title>
        </template>

        <v-list-item style="color: white">
          <v-list-item-title class="mypage-aside">
            <button
              class="nav-link"
              style="border: none; margin-left: 140px; color: white"
              @click="infoView">
              회원정보 보기
            </button>
          </v-list-item-title>
        </v-list-item>
        <v-list-item style="color: white">
          <v-list-item-title class="mypage-aside">
            <button
              class="nav-link"
              style="border: none; margin-left: 140px; color: white"
              @click="modify">
              회원정보 수정
            </button>
          </v-list-item-title>
        </v-list-item>
        <v-list-item style="color: white">
          <v-list-item-title class="mypage-aside">
            <button
              class="nav-link"
              style="border: none; margin-left: 140px; color: white"
              @click="pwdChange">
              비밀번호 수정
            </button>
          </v-list-item-title>
        </v-list-item>
        <v-list-item style="margin-left: 150px; color: white">
          <v-list-item-title
            @click="modals.mini = true"
            v-text="`회원탈퇴`"
            class="mypage-aside nav-link"
            style="font-style: italic; text-decoration: underline">
          </v-list-item-title>
        </v-list-item>
      </v-list-group>
      <v-list-group :value="true" no-action sub-group>
        <template v-slot:activator>
          <v-list-item-title style="color: white">
            글 댓글
            <i class="now-ui-icons arrows-1_minimal-down"></i>
          </v-list-item-title>
        </template>
        <v-list-item style="color: white">
          <v-list-item-title class="mypage-aside">
            <button
              @click="myBoard"
              class="nav-link"
              style="border: none; margin-left: 168px; color: white">
              내가 쓴 글
            </button>
          </v-list-item-title>
        </v-list-item>
        <v-list-item style="color: white">
          <v-list-item-title class="mypage-aside">
            <button
              @click="myComment"
              class="nav-link"
              style="border: none; margin-left: 150px; color: white">
              내가 쓴 댓글
            </button>
          </v-list-item-title>
        </v-list-item>
      </v-list-group>
      <v-list-group :value="true" no-action sub-group>
        <template v-slot:activator>
          <v-list-item-title style="color: white" @click="likeapt">
            나의 관심 지역
          </v-list-item-title>
        </template>
      </v-list-group>
    </v-list>
    <modal
      :show.sync="modals.mini"
      class=""
      :show-close="false"
      headerClasses="justify-content-center"
      type="mini">
      <div slot="header">
        <img src="img/userdelete.jpg" style="width: 100px" />
      </div>
      <h3 style="text-align: center; color: black">
        회원탈퇴를 <br />하시겠습니까?
      </h3>

      <h5 style="text-align: center; color: black">
        더이상 해당 계정으로 활동을 하실 수 없습니다.
      </h5>
      <template slot="footer">
        <n-button type="defalut" link @click.native="modals.mini = false"
          >아니오</n-button
        >
        <n-button type="danger" link @click="remove"
          >예, 탈퇴하겠습니다</n-button
        >
      </template>
    </modal>
  </div>
</template>
<script>
import { Modal, Button } from "@/components";
import { mapState, mapActions } from "vuex";

const userStore = "userStore";
export default {
  name: "UserFuncList",
  data() {
    return {
      modals: {
        mini: false,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  components: {
    Modal,
    [Button.name]: Button,
  },
  methods: {
    ...mapActions(userStore, ["userRemove"]),
    infoView() {
      this.$emit("func-select", "mypage");
    },
    modify() {
      this.$emit("func-select", "modify");
    },
    pwdChange() {
      this.$emit("func-select", "pwdchange");
    },
    likeapt() {
      this.$emit("func-select", "likeapt");
    },
    myBoard() {
      this.$emit("func-select", "myboard");
    },
    myComment() {
      this.$emit("func-select", "mycomment");
    },
    async remove() {
      await this.userRemove(this.userInfo.userId);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      this.$router.push({ name: "index" });
    },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
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
