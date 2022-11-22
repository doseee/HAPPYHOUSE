<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userId-group" label="작성자:" label-for="userId">
          <fg-input
            class="no-border input-lg"
            id="userId"
            :disabled="isUserId"
            v-model="userInfo.userId"
            type="text"
            required
            placeholder="작성자 입력..."></fg-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요.">
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"></b-form-textarea>
        </b-form-group>

        <button
          type="submit"
          class="btn btn-round btn-warning"
          v-if="this.type === 'register'">
          등록
        </button>
        <button type="submit" class="btn btn-round btn-warning" v-else>
          수정
        </button>
        <button type="reset" class="btn btn-round btn-warning">초기화</button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { FormGroupInput } from "@/components";
import { writeArticle, modifyArticle, getArticle } from "@/api/board";
import { mapState } from "vuex";
const userStore = "userStore";
export default {
  name: "BoardInputItem",
  components: {
    [FormGroupInput.name]: FormGroupInput,
  },
  data() {
    return {
      article: {
        articleno: 0,
        content: "",
        hit: 0,
        subject: "",
        userId: "",
      },
      isUserId: false,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      getArticle(this.$route.params.articleno, (res) => {
        this.article = res.data;
      });
      this.isUserId = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.userInfo.userId &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticleM();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
    },
    registArticle() {
      let article = {
        userId: this.userInfo.userId,
        subject: this.article.subject,
        content: this.article.content,
      };
      writeArticle(article, (res) => {
        let msg = "등록 처리시 문제가 발생했습니다.";
        if (res.data.data === "success") {
          msg = "등록이 완료되었습니다.";
        }
        alert(msg);
        this.moveList();
      });
    },
    modifyArticleM() {
      let article = {
        articleNo: this.article.articleNo,
        userId: this.userInfo.userId,
        subject: this.article.subject,
        content: this.article.content,
      };
      modifyArticle(article, (res) => {
        let msg = "수정 처리시 문제가 발생했습니다.";
        if (res.data.data === "success") {
          msg = "수정이 완료되었습니다.";
        }
        alert(msg);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "boardList" });
      });
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>

<style></style>
