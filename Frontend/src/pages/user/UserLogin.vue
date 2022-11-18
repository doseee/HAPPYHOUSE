<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="page-header-image" style="background-image: url('img/login.jpg')"></div>
    <div class="container">
      <div class="col-md-5 ml-auto mr-auto">
        <card type="login" plain>
          <p style="margin-bottom: 30px">HAPPY HOUSE</p>
          <fg-input-custom
            class="no-border input-lg"
            v-model="user.userid"
            addon-left-icon="now-ui-icons users_circle-08"
            placeholder="아이디"
          >
          </fg-input-custom>
          <label for="">(아이디 입력해주세요)</label>
          <fg-input-custom
            class="no-border input-lg"
            v-model="user.userpwd"
            addon-left-icon="now-ui-icons text_caps-small"
            placeholder="비밀번호"
          >
          </fg-input-custom>
          <label for="">(아이디 입력해주세요)</label>
          <div class="card-footer text-center">
            <a href="#pablo" class="btn btn-primary btn-round btn-lg btn-block" @click="confirm"
              >로그인</a
            >
          </div>
          <div class="card-footer text-center">
            <a href="#pablo" class="btn btn-primary btn-round btn-lg btn-block">카카오 로그인</a>
          </div>
          <div class="card-footer text-center">
            <a href="#pablo" class="btn btn-primary btn-round btn-lg btn-block">네이버 로그인</a>
          </div>
          <template slot="raw-content">
            <div class="pull-left">
              <h6>
                <router-link to="/regist" class="link footer-link">회원가입</router-link>
              </h6>
            </div>
            <div class="pull-right">
              <h6>
                <a href="#pablo" class="link footer-link">비밀번호 찾기</a>
              </h6>
            </div>
          </template>
        </card>
      </div>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import { Card, Button } from "@/components";
import FormGroupInputCustom from "@/components/Inputs/FormGroupInputCustom";

const userStore = "userStore";
export default {
  name: "login-page",
  bodyClass: "login-page",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
  components: {
    Card,
    [Button.name]: Button,
    [FormGroupInputCustom.name]: FormGroupInputCustom,
  },
};
</script>
<style></style>
