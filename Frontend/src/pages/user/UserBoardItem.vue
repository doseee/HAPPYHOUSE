<template>
  <b-tr>
    <b-td class="a-c">{{ idx }}</b-td>
    <b-td class="a-c">{{ subject }}</b-td>
    <b-td class="a-c">{{ registerTime }}</b-td>
    <b-td class="a-c">
      <b-button
        style="background: rgba(201, 138, 195, 0.6)"
        @click="goArticle"
        class="wh btn-sm">
        보기
      </b-button>
    </b-td>
    <b-td class="a-c">
      <b-button
        @click="deleteMyArticle"
        class="wh btn-sm btn-neutral"
        style="color: rgba(201, 138, 195, 0.6)">
        삭제
      </b-button>
    </b-td>
  </b-tr>
</template>

<script>
import { deleteArticle } from "@/api/board.js";
import { mapActions, mapState } from "vuex";
const userStore = "userStore";
export default {
  name: "UserBoardItem",
  props: {
    idx: Number,
    subject: String,
    registerTime: String,
    articleNo: Number,
  },
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["setBoardList"]),
    goArticle() {
      this.$router.push({
        name: "boardDetail",
        params: {
          articleNo: this.articleNo,
        },
      });
    },
    deleteMyArticle() {
      if (confirm("삭제하시겠습니까?")) {
        deleteArticle(this.articleNo, (res) => {
          let msg = "문제가 발생했습니다.";
          if (res.data.data == "success") {
            this.setBoardList(this.userInfo.userId);
          } else {
            alert("글 삭제 중 오류 발생");
          }
        });
      }
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
