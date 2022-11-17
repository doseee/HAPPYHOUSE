<template>
  <navbar
    position="fixed"
    type="white"
    :transparent="transparent"
    :color-on-scroll="colorOnScroll"
    menu-classes="ml-auto"
  >
    <template>
      <router-link class="navbar-brand" to="/"> HAPPY HOUSE </router-link>
    </template>
    <template slot="navbar-notice" class="navbar-notice">
      <textra :data="words" :timer="1.1" />
    </template>
    <template>
      <template slot="navbar-menu">
        <li class="nav-item">
          <a
            class="nav-link"
            href="https://www.creative-tim.com/product/vue-now-ui-kit"
            target="_blank"
          >
            <i class="now-ui-icons ui-1_calendar-60"></i>
            <p>공지사항</p>
          </a>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/list">
            <!--i class="now-ui-icons files_paper"></-i-->
            <!--img class="now-ui-icons" src="img/qna.png" width="16px" /-->
            <i class="now-ui-icons ui-2_chat-round"></i>
            <p>Q&amp;A</p>
          </router-link>
        </li>
        <li class="nav-item">
          <a
            class="nav-link"
            href="https://www.creative-tim.com/product/vue-now-ui-kit"
            target="_blank"
          >
            <i class="now-ui-icons travel_istanbul"></i>
            <p>거래조회</p>
          </a>
        </li>
        <li class="nav-item">
          <a
            class="nav-link"
            href="https://www.creative-tim.com/product/vue-now-ui-kit"
            target="_blank"
          >
            <i class="now-ui-icons files_paper"></i>
            <p>뉴스</p>
          </a>
        </li>
        <li class="nav-item">
          <drop-down tag="li" title="로그인" icon="now-ui-icons users_single-02">
            <nav-link to="/login"> <i class="now-ui-icons users_circle-08"></i> Login </nav-link>
            <nav-link to="/mypage"> <i class="now-ui-icons users_single-02"></i> mypage </nav-link>
          </drop-down>
        </li>
      </template>
    </template>
  </navbar>
</template>

<script>
import { DropDown, Navbar, NavLink } from "@/components";
import { Popover } from "element-ui";
export default {
  name: "main-navbar",
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
  },
  data() {
    return {
      words: ["My text to show", "palnet", "fail", "sample text"],
    };
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
