import Vue from "vue";
import Router from "vue-router";
import Index from "./pages/Index.vue";
import Landing from "./pages/Landing.vue";
import Login from "./pages/user/UserLogin.vue";
import Profile from "./pages/Profile.vue";
import AppUser from "./views/AppUser.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import UserPage from "./pages/user/UserPage";
import UserRegist from "./pages/user/UserRegist";
import BoardList from "./pages/board/BoardList";
import BoardView from "./pages/board/BoardView";
import BoardWrite from "./pages/board/BoardWrite";
import BoardModify from "./pages/board/BoardModify";
import BoardDelete from "./pages/board/BoardDelete";

Vue.use(Router);

export default new Router({
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
      path: "/user",
      name: "user",
      components: { default: AppUser, header: MainNavbar, mypage: UserPage, regist: UserRegist },
      children: [
        {
          path: "/mypage",
          name: "mypage",
          components: { default: UserPage },
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
      components: { default: AppUser, header: MainNavbar, boardlist: BoardList, boardView: BoardView, boardwrite: BoardWrite, boardmodify:BoardModify, boarddelete: BoardDelete},
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
