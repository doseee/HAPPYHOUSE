import Vue from "vue";
import Router from "vue-router";
import Index from "@/pages/Index";
import Landing from "@/pages/Landing";
import Login from "@/pages/user/UserLogin";
import UserModify from "@/pages/user/UserModify";
import UserLikeApt from "@/pages/user/UserLikeApt";
import Profile from "@/pages/Profile";
import AppUser from "@/views/AppUser";
import AppBoard from "@/views/AppBoard";
import AptPage from "@/pages/apt/AptPage";
import MainNavbar from "@/layout/MainNavbar";
import MainFooter from "@/layout/MainFooter";
import UserPage from "@/pages/user/UserPage";
import UserRegist from "@/pages/user/UserRegist";
import BoardList from "@/pages/board/BoardList";
import BoardDetail from "@/pages/board/BoardDetail";
import BoardWrite from "@/pages/board/BoardWrite";
import BoardModify from "@/pages/board/BoardModify";
Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  linkExactActiveClass: "active",
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/landing",
      name: "landing",
      components: { default: Landing, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/login",
      name: "login",
      components: { default: Login, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/apt",
      name: "apt",
      components: { default: AptPage, header: MainNavbar },

      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/user",
      name: "user",
      components: { default: AppUser, header: MainNavbar, mypage: UserPage },
      children: [
        {
          path: "/mypage",
          name: "mypage",
          components: { default: UserPage },
        },
        {
          path: "/modify",
          name: "modify",
          components: { default: UserModify, header: MainNavbar },
        },
        {
          path: "/likeapt",
          name: "likeapt",
          components: { default: UserLikeApt, header: MainNavbar },
        },
        {
          path: "/regist",
          name: "regist",
          components: { default: UserRegist },
        },
      ],
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/board",
      name: "board",
      components: {
        default: AppBoard,
        header: MainNavbar,
        boardlist: BoardList,
        BoardDetail: BoardDetail,
        boardWrite: BoardWrite,
        boardModify: BoardModify,
      },
      redirect: "/board/list",
      children: [
        {
          path: "/list",
          name: "boardList",
          components: { default: BoardList },
        },
        {
          path: "view",
          name: "boardDetail",
          components: { default: BoardDetail },
        },
        {
          path: "write",
          name: "boardWrite",
          components: { default: BoardWrite },
        },
        {
          path: "modify",
          name: "boardModify",
          components: { default: BoardModify },
        },
      ],
    },
    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
