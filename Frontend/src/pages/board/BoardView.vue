<template>
  <div class="page-header clear-filter" filter-color="orange">
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <h3>글보기</h3>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-left">
          <button class="btn btn-round btn-warning" @click="moveList">
            목록
          </button>
        </b-col>
        <b-col class="text-right">
          <button
            type="button"
            class="btn btn-round btn-warning float-right"
            @click="moveModifyArticle">
            글수정
          </button>
          <button
            type="button"
            class="btn btn-round btn-danger float-right"
            @click="deleteArticle">
            글삭제
          </button>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col>
          <div
            v-html="
              `<h3>${article.subject}</h3><div class='text-right'><h6>${article.hit}</h6></div><div class='text-right'><h6>${article.userid}</div><div class='text-right'>${article.regtime}</h6></div>`
            " />
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-col>
      </b-row>
    </b-container>
    <main-footer></main-footer>
  </div>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    http.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
