<template>
  <div class="page-header clear-filter" filter-color="orange">
    <b-container class="bv-example-row md-3">
      <b-row class="mb-1">
        <b-col class="text-left">
          <b-button
            style="background: rgba(201, 138, 195, 0.6)"
            class="btn btn-round"
            @click="listArticle">
            목록
          </b-button>
        </b-col>
        <b-col v-if="userInfo && userInfo.userId == `admin`" class="text-right">
          <b-button
            type="button"
            style="background: rgba(201, 138, 195, 0.6)"
            class="btn btn-round float-right"
            @click="moveModifyArticle">
            글수정
          </b-button>
          <b-button
            type="button"
            style="color: rgba(201, 138, 195, 0.6)"
            class="btn btn-round btn-neutral"
            @click="deleteArticleCheck">
            글삭제
          </b-button>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col>
          <div
            v-html="
              `<h3>${article.subject}</h3><div class='text-right'><h6>조회수&nbsp;&nbsp;:&nbsp;&nbsp;${article.hit}</h6></div><div class='text-right'>${article.registerTime}</h6></div>`
            " />
          <hr style="background-color: white" />
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { getArticle, deleteArticle, getNavNotice } from "@/api/notice.js";
import { mapState, mapActions } from "vuex";
const userStore = "userStore";
const noticeStore = "noticeStore";
export default {
  name: "NoticeDetail",
  data() {
    return {
      article: {},
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
  },
  methods: {
    ...mapActions(noticeStore, ["setNavNotices"]),
    listArticle() {
      this.$router.push({ name: "noticeList" });
    },
    moveModifyArticle() {
      if (this.userInfo?.userId === undefined || this.userInfo.userId == null) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({ name: "login" });
      } else {
        if (this.userInfo?.userId != "admin") {
          alert("다른 사용자의 글은 수정 할 수 없습니다.");
        } else {
          this.$router.replace({
            name: "noticeModify",
            params: {
              articleno: this.article.articleNo,
            },
          });
        }
      }
    },
    deleteArticleCheck() {
      if (this.userInfo.userId == "admin") {
        if (confirm("삭제하시겠습니까?")) {
          deleteArticle(this.$route.params.articleNo, (res) => {
            let msg = "문제가 발생했습니다.";
            if (res.data.data == "success") {
              msg = "글이 삭제되었습니다.";
              getNavNotice((res) => {
                if (res.data.result == "success") {
                  this.setNavNotices(res.data.data);
                } else {
                  alert("게시글 삭제에 실패하였습니다.");
                }
              });
            }
            alert(msg);
            this.$router.push({ name: "noticeList" });
          });
        }
      } else {
        alert("관리자만 접근 가능한 서비스입니다.");
      }
    },
  },
};
</script>

<style></style>
