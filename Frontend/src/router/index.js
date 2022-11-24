import Vue from "vue";
import Router from "vue-router";
import Index from "@/pages/Index";
import Login from "@/pages/user/UserLogin";
import UserModify from "@/pages/user/UserModify";
import UserLikeApt from "@/pages/user/UserLikeApt";
import UserFindPwd from "@/pages/user/UserFindPwd";
import AppUser from "@/views/AppUser";
import AppBoard from "@/views/AppBoard";
import AptPage from "@/pages/apt/AptPage";
import AppNotice from "@/views/AppNotice";
import AppNews from "@/views/AppNews";
import MainNavbar from "@/layout/MainNavbar";
import UserPage from "@/pages/user/UserPage";
import UserRegist from "@/pages/user/UserRegist";
import BoardList from "@/pages/board/BoardList";
import BoardDetail from "@/pages/board/BoardDetail";
import BoardWrite from "@/pages/board/BoardWrite";
import BoardModify from "@/pages/board/BoardModify";
import NoticeList from "@/pages/notice/NoticeList";
import NoticeDetail from "@/pages/notice/NoticeDetail";
import NoticeWrite from "@/pages/notice/NoticeWrite";
import NoticeModify from "@/pages/notice/NoticeModify";
import NewsList from "@/pages/news/NewsList";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  linkExactActiveClass: "active",
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index, header: MainNavbar },
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
        {
          path: "/findpwd",
          name: "findpwd",
          components: { default: UserFindPwd },
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
          path: "/board/list",
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
      path: "/notice",
      name: "notice",
      components: {
        default: AppNotice,
        header: MainNavbar,
        noticeList: NoticeList,
        noticeDetail: NoticeDetail,
        noticeWrite: NoticeWrite,
        noticeModify: NoticeModify,
      },
      redirect: "/notice/list",
      children: [
        {
          path: "/notice/list",
          name: "noticeList",
          components: { default: NoticeList },
        },
        {
          path: "view",
          name: "noticeDetail",
          components: { default: NoticeDetail },
        },
        {
          path: "write",
          name: "noticeWrite",
          components: { default: NoticeWrite },
        },
        {
          path: "modify",
          name: "noticeModify",
          components: { default: NoticeModify },
        },
      ],
    },
    {
      path: "/news",
      name: "news",
      components: {
        default: AppNews,
        newsList: NewsList,
        header: MainNavbar,
      },
      props: {
        header: { colorOnScroll: 100 },
        footer: { backgroundColor: "black" },
      },
      redirect: "/news/list",
      children: [
        {
          path: "/news/list",
          name: "newsList",
          components: { default: NewsList },
        },
      ],
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
