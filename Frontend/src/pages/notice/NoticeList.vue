<template>
  <div
    class="page-header clear-filter"
    filter-color="orange"
    style="width 300px">
    <div class="container">
      <div class="col-md-10 ml-auto mr-auto">
        <card type="list" plain>
          <b-container>
            <b-row>
              <b-col>
                <h3>공지사항</h3>
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
                        name: 'noticeDetail',
                        params: { articleNo: data.item.articleNo },
                      }"
                      >{{ data.item.subject }}</router-link
                    >
                  </template>
                </b-table>
                <b-row>
                  <b-col class="d-flex justify-content-end">
                    <b-form class="form-inline">
                      <b-form-select
                        class="form-control"
                        v-model="searchNotice">
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
</template>

<script>
import moment from "moment";

import { Card } from "@/components";
import { mapState } from "vuex";
import { listArticle, getArticle, getArticleBySubject } from "@/api/notice";
const userStore = "userStore";
export default {
  name: "NoticeList",
  components: {
    Card,
  },
  data() {
    return {
      articles: [],
      keyword: "",
      currentPage: 1,
      perPage: 10,
      searchNotice: "",
      fields: [
        { key: "articleNo", label: "글번호", thClass: "w10" },
        {
          key: "subject",
          label: "제목",
          thClass: "w60",
        },
        { key: "hit", label: "조회수", thClass: "w10" },
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
    });
  },
  methods: {
    moveWrite() {
      if (!this.userInfo?.userId) {
        alert("로그인이 필요한 서비스입니다.");
        this.$router.push({ name: "login" });
      } else if (this.userInfo?.userId != "admin") {
        alert("관리자만 작성할 수 있습니다.");
      } else {
        this.$router.push({ name: "noticeWrite" });
      }
    },
    searchBoard(event) {
      event.preventDefault();
      if (!this.searchNotice) {
        alert("카테고리를 선택해주세요");
        return;
      }
      this.articles = [];
      if (this.searchNotice == "articleNo") {
        if (isNaN(this.keyword)) {
          alert("숫자를 입력해주세요.");
          return;
        }
        //글번호 검색
        getArticle(this.keyword, (res) => {
          this.articles.push(res.data);
        });
      } else if (this.searchNotice == "subject") {
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
