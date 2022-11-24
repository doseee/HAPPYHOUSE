const noticeStore = {
  namespaced: true,
  state: {
    navNotices: [],
  },
  getters: {},
  mutations: {
    SET_NAV_NOTICE: (state, navNotices) => {
      //   console.log(houses);
      state.navNotices = navNotices;
    },
  },
  actions: {
    setNavNotices: ({ commit }, navNotices) => {
      commit("SET_NAV_NOTICE", navNotices);
    },
  },
};
export default noticeStore;
