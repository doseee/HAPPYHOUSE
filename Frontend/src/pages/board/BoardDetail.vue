<template>
  <div class="page-header clear-filter" filter-color="orange">
    <b-container class="bv-example-row md-3">
      <b-row class="mb-1">
        <b-col class="text-left">
          <button class="btn btn-round btn-warning" @click="listArticle">
            목록
          </button>
        </b-col>
        <b-col
          v-if="userInfo && userInfo.userId == article.userId"
          class="text-right">
          <button
            type="button"
            class="btn btn-round btn-warning float-right"
            variant="success"
            @click="moveModifyArticle">
            글수정
          </button>
          <button
            type="button"
            class="btn btn-round btn-danger float-right"
            variant="danger"
            @click="deleteArticleCheck">
            글삭제
          </button>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col>
          <div
            v-html="
              // eslint-disable-next-line prettier/prettier
              `<h3>${article.subject}</h3><div class='text-right'><h6>조회수&nbsp;&nbsp;:&nbsp;&nbsp;${article.hit}</h6></div><div class='text-right'><h6>${article.userId} &nbsp;&nbsp;| &nbsp;&nbsp;${article.registerTime} </div>`
            " />
          <hr style="background-color: white" />
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-col>
      </b-row>
      <hr style="background-color: white" />
      <div v-if="userInfo">
        <b-form-textarea
          id="textarea"
          v-model="content"
          placeholder="댓글 작성"
          rows="3"
          max-rows="6"></b-form-textarea>
        <b-row class="mb-5">
          <b-col class="text-right">
            <button
              type="button"
              class="btn btn-round btn-warning float-right"
              variant="success"
              @click="commentCheck">
              댓글 작성
            </button>
          </b-col>
        </b-row>
      </div>

      <div class="mb-3">
        <board-comment
          v-for="comment in comments"
          :key="comment.commentno"
          v-bind="comment"
          @reListComment="reListComment" />
      </div>
    </b-container>
  </div>
</template>

<script>
import {
  getArticle,
  writeComment,
  listComment,
  deleteArticle,
} from "@/api/board.js";
import { mapState } from "vuex";
import BoardComment from "@/pages/board/item/BoardComment";

const userStore = "userStore";
export default {
  name: "BoardDetail",
  components: {
    BoardComment,
  },
  data() {
    return {
      article: {},
      comments: [],
      content: null,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getArticle(this.$route.params.articleNo, (res) => {
      this.article = res.data;
    });
    listComment(this.$route.params.articleNo, (res) => {
      this.comments = res.data;
    });
  },
  methods: {
    reListComment() {
      listComment(this.article.articleNo, (res) => {
        this.comments = res.data;
      });
    },
    commentCheck() {
      if (this.content != null && this.content != "") {
        this.commentRegist();
        this.content = "";
      } else {
        alert("답변을 작성해주세요.");
      }
    },
    commentRegist() {
      let comment = {
        userId: this.userInfo?.userId,
        articleNo: this.article.articleNo,
        content: this.content,
      };
      writeComment(comment, (res) => {
        let msg = "답변 등록시 문제가 발생했습니다.";
        if (res.data.data === "success") {
          msg = "답변 등록이 완료되었습니다.";
        }
        alert(msg);
        this.reListComment();
      });
    },
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      if (this.userInfo?.userId === undefined || this.userInfo.userId == null) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({ name: "login" });
      } else {
        if (this.userInfo?.userId != this.article.userId) {
          alert("다른 사용자의 글은 수정 할 수 없습니다.");
        } else {
          this.$router.replace({
            name: "boardModify",
            params: {
              articleno: this.article.articleNo,
            },
          });
        }
      }
    },
    deleteArticleCheck() {
      if (this.userInfo?.userId === undefined || this.userInfo.userId == null) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({ name: "login" });
      } else {
        if (confirm("삭제하시겠습니까?")) {
          deleteArticle(this.$route.params.articleNo, (res) => {
            let msg = "문제가 발생했습니다.";
            if (res.data.data == "success") {
              msg = "글이 삭제되었습니다.";
            }
            alert(msg);
            this.$router.push({ name: "boardList" });
          });
        }
      }
    },
  },
};
</script>

<style></style>
