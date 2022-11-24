<template>
  <div style="background-color: #f6f6f6; opacity: 0.9; height: 300px">
    <div class="col-md-10 ml-auto mr-auto">
      <card type="list" plain>
        <b-container>
          <b-row class="mb-5">
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
            </b-col>
          </b-row>
        </b-container>
      </card>
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
import { getBestArticle } from "@/api/board.js";

const userStore = "userStore";
export default {
  name: "BoardListMain",
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
  beforeCreate() {
    getBestArticle((res) => {
      if (res.data.result == "success") {
        this.articles = res.data.data;
      } else {
        alert("게시글 가져오기에 실패하였습니다.");
      }
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
  },
};
</script>

<style scope></style>
