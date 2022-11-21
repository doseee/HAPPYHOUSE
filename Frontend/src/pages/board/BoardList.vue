<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="content">
      <div class="container">
        <div class="col-md-10 ml-auto mr-auto">
          <card type="list" plain>
            <p>Q&A</p>
            <b-container>
              <b-row>
                <b-col>
                  <h3>글목록</h3>
                </b-col>
              </b-row>
              <b-row class="mb-1">
                <b-col class="text-right">
                  <button
                    type="button"
                    class="btn btn-round btn-warning float-right"
                    @click="moveWrite()">
                    <!---->글쓰기<!---->
                  </button>
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  <b-table
                    width="300px"
                    striped
                    hover
                    :items="articles"
                    :fields="fields"
                    @row-clicked="viewArticle">
                  </b-table>
                </b-col>
              </b-row>
              <!--나중에 페이지네이션 구현-->
            </b-container>
          </card>
        </div>
      </div>
    </div>
    <main-footer></main-footer>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    http.get(`/board`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { articleno: article.articleno },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
