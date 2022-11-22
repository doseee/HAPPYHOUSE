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
              <b-row class="mt-3 mb-5">
                <b-col>
                  <b-table
                    class="mb-6 text-center"
                    id="my-table"
                    :fields="fields"
                    :items="articles"
                    :per-page="perPage"
                    :current-page="currentPage">
                    <template #cell(subject)="data">
                      <router-link
                        :to="{
                          name: 'boardDetail',
                          params: { articleNo: data.item.articleNo },
                        }"
                        >{{ data.item.subject }}</router-link
                      >
                    </template>
                  </b-table>
                  <b-row>
                    <b-col class="d-flex justify-content-end">
                      <b-form class="form-inline">
                        <b-form-select class="form-control" v-model="searchQnA">
                          <option value="userId" selected>아이디</option>
                          <option value="articleNo">글번호</option>
                          <option value="subject">제목</option>
                        </b-form-select>
                        <b-form-input
                          type="text"
                          class="ml-1"
                          v-model="keyword"
                          @keyup.enter="searchBoard" />
                        <b-button
                          type="button"
                          class="ml-1 btn md-warning md-sm"
                          @click="searchBoard"
                          variant="warning"
                          >search</b-button
                        >
                      </b-form>
                    </b-col>
                  </b-row>
                  <div class="d-flex justify-content-center mt-5">
                    <b-pagination
                      v-model="currentPage"
                      :total-rows="rows"
                      :per-page="perPage"
                      aria-controls="my-table"></b-pagination>
                  </div>
                </b-col>
              </b-row>
            </b-container>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";

import { Card } from "@/components";
import { mapState } from "vuex";
import {
  listArticle,
  getArticleById,
  getArticle,
  getArticleBySubject,
} from "@/api/board";
const userStore = "userStore";
export default {
  name: "BoardList",
  components: {
    Card,
  },
  data() {
    return {
      articles: [],
      keyword: "",
      currentPage: 1,
      perPage: 10,
      searchQnA: "",
      fields: [
        { key: "articleNo", label: "글번호", thClass: "w10" },
        {
          key: "subject",
          label: "제목",
          thClass: "w60",
        },
        { key: "hit", label: "조회수", thClass: "w10" },
        { key: "userId", label: "작성자", thClass: "w10" },
        {
          key: "registerTime",
          label: "작성일",
          thClass: "w10",
          formatter: (value, key, item) => {
            return moment(new Date(value)).format("YY.MM.DD");
          },
        },
      ],
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    rows() {
      return this.articles.length;
    },
  },
  created() {
    listArticle((res) => {
      this.articles = res.data;
      // this.rows = this.articles.length;
      // this.totalPage = this.articles.length / this.pageSize + 1;
    });
  },
  methods: {
    moveWrite() {
      if (!this.userInfo?.userid) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({ name: "login" });
      } else {
        this.$router.push({ name: "boardRegister" });
      }
    },
    searchBoard(event) {
      event.preventDefault();
      if (!this.searchQnA) {
        alert("카테고리를 선택해주세요");
        return;
      }
      this.articles = [];
      //검색기능
      if (this.searchQnA == "userId") {
        //아이디 검색
        getArticleById(this.keyword, (res) => {
          this.articles = res.data;
        });
      } else if (this.searchQnA == "articleNo") {
        if (isNaN(this.keyword)) {
          alert("숫자를 입력해주세요.");
          return;
        }
        //글번호 검색
        getArticle(this.keyword, (res) => {
          this.articles.push(res.data);
        });
      } else if (this.searchQnA == "subject") {
        //제목 검색
        getArticleBySubject(this.keyword, (res) => {
          this.articles = res.data;
        });
      } else {
        listArticle((res) => {
          this.articles = res.data;
        });
      }
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
