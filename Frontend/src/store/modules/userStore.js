import jwtDecode from "jwt-decode";
import router from "@/router";
import {
  login,
  findById,
  tokenRegeneration,
  logout,
  update,
  remove,
  join,
  selectLikeDong,
  selectComment,
  selectBoard,
} from "@/api/user";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isValidToken: false,
    likeList: [],
    commentList: [],
    boardList: [],
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_LIKE_LIST: (state, likeList) => {
      state.likeList = likeList;
    },
    SET_COMMENT_LIST: (state, commentList) => {
      state.commentList = commentList;
    },
    SET_BOARD_LIST: (state, boardList) => {
      state.boardList = boardList;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            // console.log("login success token created!!!! >> ", accessToken, refreshToken);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findById(
        decodeToken.userId,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", data.userInfo);
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("?????? ?????? ??????!!!!");
          }
        },
        async (error) => {
          console.log(
            "getUserInfo() error code [?????? ???????????? ?????? ?????????.] ::: ",
            error.response.status,
          );
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        },
      );
    },
    async tokenRegeneration({ commit, state }) {
      console.log(
        "?????? ????????? >> ?????? ?????? ?????? : {}",
        sessionStorage.getItem("access-token"),
      );
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("????????? ?????? >> ????????? ?????? : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken ?????? ?????? >> ?????? ?????????!!!!
          if (error.response.status === 401) {
            console.log("?????? ??????");
            // ?????? ????????? ??? DB??? ????????? RefreshToken ??????.
            await logout(
              state.userInfo.userid,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("???????????? ?????? ?????? ??????");
                } else {
                  console.log("???????????? ?????? ?????? ??????");
                }
                alert("RefreshToken ?????? ??????!!! ?????? ???????????? ?????????.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              },
            );
          }
        },
      );
    },
    async userJoin({ commit }, user) {
      console.log(user);
      console.log(user.userAddress);
      await join(
        user,
        ({ data }) => {},
        (error) => {
          console.log(error);
        },
      );
    },
    async userUpdate({ commit }, user) {
      await update(
        user,
        ({ data }) => {},
        (error) => {
          console.log(error);
        },
      );
    },

    async userRemove({ commit }, userId) {
      await remove(
        userId,
        ({ data }) => {
          commit("SET_IS_LOGIN", false);
          commit("SET_USER_INFO", null);
          commit("SET_IS_VALID_TOKEN", false);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    setLikeList: ({ commit }, userId) => {
      selectLikeDong(
        {
          userId: userId,
        },
        ({ data }) => {
          commit("SET_LIKE_LIST", data);
        },
      );
    },
    setCommentList: ({ commit }, userId) => {
      selectComment(
        {
          userId: userId,
        },
        ({ data }) => {
          commit("SET_COMMENT_LIST", data);
        },
      );
    },
    setBoardList: ({ commit }, userId) => {
      selectBoard(
        {
          userId: userId,
        },
        ({ data }) => {
          console.log(data);
          commit("SET_BOARD_LIST", data);
        },
      );
    },
    async userLogout({ commit }, userId) {
      await logout(
        userId,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("?????? ?????? ??????!!!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default userStore;
