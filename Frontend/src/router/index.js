import Vue from "vue";
import Router from "vue-router";
import Index from "@/pages/Index";
import Landing from "@/pages/Landing";
import Login from "@/pages/user/UserLogin";
import UserModify from "@/pages/user/UserModify";
import Profile from "@/pages/Profile";
import AppUser from "@/views/AppUser";
import AptPage from "@/pages/apt/AptPage";
import MainNavbar from "@/layout/MainNavbar";
import MainFooter from "@/layout/MainFooter";
import UserPage from "@/pages/user/UserPage";
import UserRegist from "@/pages/user/UserRegist";
import BoardList from "@/pages/board/BoardList";
import BoardView from "@/pages/board/BoardView";
import BoardWrite from "@/pages/board/BoardWrite";
import BoardModify from "@/pages/board/BoardModify";
import BoardDelete from "@/pages/board/BoardDelete";
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
        default: AppUser,
        header: MainNavbar,
        boardlist: BoardList,
        boardView: BoardView,
        boardwrite: BoardWrite,
        boardmodify: BoardModify,
        boarddelete: BoardDelete,
      },
      redirect: "/board/list",
      children: [
        {
          path: "/list",
          name: "boardlist",
          components: { default: BoardList },
        },
        {
          path: "view/:articleno",
          name: "boardview",
          components: { default: BoardView },
        },
        {
          path: "write",
          name: "boardwrite",
          components: { default: BoardWrite },
        },
        {
          path: "modify",
          name: "boardmodify",
          components: { default: BoardModify },
        },
        {
          path: "delete/:articleno",
          name: "boarddelete",
          ccomponents: { default: BoardDelete },
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