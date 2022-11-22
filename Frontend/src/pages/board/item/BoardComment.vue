<template>
  <b-row class="my-3">
    <b-col sm="2">
      <label for="textarea-no-auto-shrink"
        ><strong>{{ userId }}</strong
        ><br />
        {{ registerTime | dateFormat }}
      </label>
    </b-col>
    <b-col sm="10" class="d-flex bor">
      <span id="textarea-no-auto-shrink" rows="3" max-rows="8">{{
        content
      }}</span>
      <b-col class="text-right">
        <button
          v-if="userInfo && userInfo.userId == userId"
          @click="checkDelete">
          삭제
        </button>
      </b-col>
    </b-col>
  </b-row>
</template>

<script>
import moment from "moment";
import { deleteComment } from "@/api/board.js";
import { mapState } from "vuex";
const userStore = "userStore";
export default {
  name: "BoardComment",
  props: {
    userId: String,
    registerTime: String,
    content: String,
    articleNo: Number,
    commentNo: Number,
  },
  filters: {
    dateFormat(registerTime) {
      return moment(new Date(registerTime)).format("YY.MM.DD");
    },
  },
  methods: {
    checkDelete() {
      if (confirm("정말로 삭제 하시겠습니까?")) {
        deleteComment(this.commentNo, (res) => {
          if (res.data.data == "success") {
            this.$emit("reListComment");
          } else {
            alert("문제가 발생했습니다.");
          }
        });
      }
    },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>

<style>
.mt-2 {
  padding: auto;
  margin: 30px;
}
</style>
