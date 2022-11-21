const aptStore = {
  namespaced: true,
  state: {
    houses: [], //전체 주택 목록
    housesfilter: [], //필터링 후 주택 목록
    house: null,
    sidoCode: null,
    gugunCode: null,
    dongCode: null,
  },
  getters: {},
  mutations: {
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
    },
    CLEAR_HOUSE_FILTER_LIST: (state) => {
      state.housesfilter = [];
    },
    CLEAR_HOUSE: (state) => {
      state.house = null;
    },
    CLEAR_SIDO_CODE: (state) => {
      state.sidoCode = null;
    },
    CLEAR_GUGUN_CODE: (state) => {
      state.gugunCode = null;
    },
    CLEAR_DONG_CODE: (state) => {
      state.dongCode = null;
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    SET_HOUSE_FILTER_LIST: (state, houses) => {
      //   console.log(houses);
      state.housesfilter = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_SIDO_CODE: (state, sidoCode) => {
      state.sidoCode = sidoCode;
    },
    SET_GUGUN_CODE: (state, gugunCode) => {
      state.gugunCode = gugunCode;
    },
    SET_DONG_CODE: (state, dongCode) => {
      state.dongCode = dongCode;
    },
  },
  actions: {
    clearHouses: ({ commit }) => {
      commit("CLEAR_HOUSE_LIST");
    },
    clearHousesFilter: ({ commit }) => {
      commit("CLEAR_HOUSE_FILTER_LIST");
    },
    clearHouse: ({ commit }) => {
      commit("CLEAR_HOUSE");
    },
    setHouses: ({ commit }, houses) => {
      commit("SET_HOUSE_LIST", houses);
    },
    setHousesFilter: ({ commit }, houses) => {
      commit("SET_HOUSE_FILTER_LIST", houses);
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    setSidoCode: ({ commit }, sidoCode) => {
      commit("SET_SIDO_CODE", sidoCode);
    },
    setGugunCode: ({ commit }, gugunCode) => {
      commit("SET_GUGUN_CODE", gugunCode);
    },
    setDongCode: ({ commit }, dongCode) => {
      commit("SET_DONG_CODE", dongCode);
    },
    clearSidoCode: ({ commit }) => {
      commit("CLEAR_SIDO_CODE");
    },
    clearGugunCode: ({ commit }) => {
      commit("CLEAR_GUGUN_CODE");
    },
    clearDongCode: ({ commit }) => {
      commit("CLEAR_DONG_CODE");
    },
  },
};

export default aptStore;
