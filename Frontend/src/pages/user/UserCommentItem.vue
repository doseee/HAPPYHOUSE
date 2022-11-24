<template>
  <b-tr>
    <b-td class="a-c">{{ idx }}</b-td>
    <b-td class="a-c">{{ content }}</b-td>
    <b-td class="a-c">{{ registerTime }}</b-td>
    <b-td class="a-c">
      <b-button
        style="background: rgba(201, 138, 195, 0.6)"
        @click="goComment"
        class="wh btn-sm">
        보기
      </b-button>
    </b-td>
    <b-td class="a-c">
      <b-button
        @click="deleteMyComment"
        class="wh btn-sm btn-neutral"
        style="color: rgba(201, 138, 195, 0.6)">
        삭제
      </b-button>
    </b-td>
  </b-tr>
</template>

<script>
import { deleteComment } from "@/api/board.js";
import { mapActions, mapState } from "vuex";
const userStore = "userStore";
export default {
  name: "UserCommentItem",
  props: {
    idx: Number,
    content: String,
    registerTime: String,
    commentNo: Number,
    articleNo: Number,
  },
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["setCommentList"]),
    goComment() {
      this.$router.push({
        name: "boardDetail",
        params: {
          articleNo: this.articleNo,
        },
      });
    },
    deleteMyComment() {
      if (confirm("정말로 삭제 하시겠습니까?")) {
        deleteComment(this.commentNo, (res) => {
          if (res.data.data == "success") {
            this.setCommentList(this.userInfo.userId);
          } else {
            alert("댓글 삭제에 실패하였습니다.");
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
