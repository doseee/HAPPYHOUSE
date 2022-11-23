<template>
  <b-tr>
    <b-td class="a-c">{{ idx }}</b-td>
    <b-td class="a-c">{{ sidoName }} {{ gugunName }} {{ dongName }} </b-td>
    <b-td class="a-c">
      <b-button
        style="background: rgba(201, 138, 195, 0.6)"
        @click="goLikeDong"
        class="wh btn-sm">
        조회
      </b-button>
    </b-td>
    <b-td class="a-c">
      <b-button
        @click="deleteDong"
        class="wh btn-sm btn-neutral"
        style="color: rgba(201, 138, 195, 0.6)">
        삭제
      </b-button>
    </b-td>
  </b-tr>
</template>

<script>
import { deleteLikeDong } from "@/api/user.js";
import { mapActions, mapState } from "vuex";
const userStore = "userStore";
export default {
  name: "UserLikeAptItem",
  props: {
    idx: Number,
    dongCode: String,
    dongName: String,
    gugunCode: String,
    gugunName: String,
    sidoCode: String,
    sidoName: String,
  },
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["setLikeList"]),
    goLikeDong() {
      this.$router.push({
        name: "apt",
        params: {
          sidoCodeParam: this.sidoCode,
          gugunCodeParam: this.gugunCode,
          dongCodeParam: this.dongCode,
        },
      });
    },
    deleteDong() {
      deleteLikeDong(
        {
          dongCode: this.dongCode,
          userId: this.userInfo.userId,
        },
        ({ data }) => {
          if (data == "success") {
            //vuex 리스트 다시 조회
            this.setLikeList(this.userInfo.userId);
          } else {
            alert("관심지역 해제에 실패하였습니다.");
          }
        },
      );
    },
  },
};
</script>

<style scoped>
.wh {
  color: white;
}
.a-c {
  vertical-align: baseline;
  padding: 0;
}
</style>
