<template>
  <navbar
    position="fixed"
    type="white"
    :transparent="transparent"
    :color-on-scroll="colorOnScroll"
    menu-classes="ml-auto">
    <template>
      <router-link class="navbar-brand" to="/"> HAPPY HOUSE </router-link>
    </template>

    <template slot="navbar-notice">
      <textra
        style="width: 510px; padding-top: 3px; padding-left: 25px"
        :data="navNotices"
        :infinite="true"
        :timer="2"
        filter="bottom-top" />
    </template>
    <template>
      <template slot="navbar-menu">
        <li class="nav-item">
          <router-link class="nav-link" to="/notice/list">
            <i class="now-ui-icons ui-1_calendar-60"></i>
            <p>공지사항</p>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/board/list">
            <!--i class="now-ui-icons files_paper"></-i-->
            <!--img class="now-ui-icons" src="img/qna.png" width="16px" /-->
            <i class="now-ui-icons ui-2_chat-round"></i>
            <p>COMMUNITY</p>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/apt">
            <i class="now-ui-icons travel_istanbul"></i>
            <p>거래조회</p>
          </router-link>
        </li>
        <li class="nav-item">
          <a
            class="nav-link"
            href="https://www.creative-tim.com/product/vue-now-ui-kit"
            target="_blank">
            <i class="now-ui-icons files_paper"></i>
            <p>뉴스</p>
          </a>
        </li>
        <!--로그인 O -->
        <li class="nav-item" v-if="userInfo">
          <drop-down
            tag="li"
            title="내 정보"
            icon="now-ui-icons users_circle-08">
            <nav-link to="/mypage">
              <i class="now-ui-icons users_single-02"></i> 마이페이지
            </nav-link>
            <nav-link class="link">
              <button @click.prevent="onClickLogout">
                <i class="now-ui-icons media-1_button-power"></i>
                로그아웃
              </button>
            </nav-link>
          </drop-down>
        </li>
        <!--로그인 X -->
        <li class="nav-item" v-else>
          <router-link class="nav-link" to="/login">
            <i class="now-ui-icons users_single-02"></i>
            <p>로그인</p>
          </router-link>
        </li>
      </template>
    </template>
  </navbar>
</template>

<script>
import { DropDown, Navbar, NavLink } from "@/components";
import { Popover } from "element-ui";
import { mapState, mapGetters, mapActions } from "vuex";
import { getNavNotice } from "@/api/notice.js";
const userStore = "userStore";
const noticeStore = "noticeStore";
export default {
  name: "main-navbar",
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
  },
  beforeCreate() {
    getNavNotice((res) => {
      if (res.data.result == "success") {
        this.setNavNotices(res.data.data);
      } else {
        alert("게시글 가져오기에 실패하였습니다.");
      }
    });
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapState(noticeStore, ["navNotices"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),
    ...mapActions(noticeStore, ["setNavNotices"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "index" });
    },
  },
  data() {
    return {};
  },
  components: {
    DropDown,
    Navbar,
    NavLink,
    [Popover.name]: Popover,
  },
};
</script>

<style scoped></style>
