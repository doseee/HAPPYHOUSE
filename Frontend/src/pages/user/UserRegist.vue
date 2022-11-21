<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"></div>
    <div class="container">
      <div class="col-md-5 ml-auto mr-auto">
        <card type="regist" plain>
          <p>회원가입</p>

          <div>
            <div class="input-group no-border input-lg">
              <input
                id="userpwd"
                class="form-control"
                style="
                  border-top-right-radius: 30px;
                  border-bottom-right-radius: 30px;
                "
                placeholder="아이디"
                v-model="user.userId" />
              <button class="btn-round btn-warning">중복검사</button>
            </div>
          </div>

          <div>
            <div class="input-group no-border input-lg">
              <input
                id="userpwd"
                class="form-control"
                placeholder="비밀번호"
                v-model="user.userPwd" />
            </div>
          </div>
          <div
            v-if="!checkPwdFlag"
            class="input-group"
            style="margin-bottom: 0px">
            <label>비밀번호가 동일하지 않습니다.</label>
          </div>
          <div>
            <div class="input-group no-border input-lg">
              <input
                id="userpwd"
                class="form-control"
                placeholder="이름"
                v-model="user.userName" />
            </div>
          </div>
          <div>
            <div class="input-group no-border input-lg">
              <input
                id="userpwd"
                class="form-control"
                placeholder="전화번호"
                v-model="user.userTel" />
            </div>
          </div>
          <div>
            <div class="input-group no-border input-lg">
              <input
                id="userpwd"
                class="form-control"
                placeholder="예) ssafy@ssafy.co.kr"
                ref="email"
                @keyup="emailCheck"
                v-model="user.userEmail" />
            </div>
            <p v-show="checkEmailFlag" class="input-error">
              이메일 주소를 정확히 입력해주세요.
            </p>
          </div>
          <div>
            <div class="input-group no-border input-lg">
              <input
                id="userpwd"
                class="form-control"
                placeholder="주소"
                v-model="user.userAddress" />
            </div>
          </div>

          <div class="card-footer text-center">
            <button
              class="btn btn-warning btn-round btn-lg btn-block"
              @click="register">
              회원가입
            </button>
          </div>
          <div class="card-footer text-center">
            <a href="#pablo" class="btn btn-warning btn-round btn-lg btn-block"
              >카카오로 회원가입</a
            >
          </div>
          <div class="card-footer text-center">
            <a href="#pablo" class="btn btn-warning btn-round btn-lg btn-block"
              >네이버로 회원가입</a
            >
          </div>
        </card>
      </div>
    </div>
  </div>
</template>
<script>
import { Card, Button } from "@/components";
import { mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserRegister",
  bodyClass: "UserRegister",
  data() {
    return {
      user: {
        userId: "",
        userPwd: "",
        userName: "",
        userEmail: "",
        userTel: "",
        userAddress: "",
      },
      checkPwd: "",
      checkPwdFlag: true,
      checkEmailFlag: false,
    };
  },
  computed: {
    hasIcon() {
      return this.addonLeftIcon !== undefined;
    },
  },
  components: {
    Card,
    [Button.name]: Button,
  },
  methods: {
    ...mapActions(userStore, ["userJoin"]),
    async register() {
      await this.userJoin(this.user);
      this.$router.push({ name: "login" });
    },
    checkPwdValid() {
      if (this.user.userPwd === this.checkPwd) {
        this.checkPwdFlag = true;
      } else {
        this.checkPwdFlag = false;
      }
    },
    emailCheck() {
      this.user.userEmail = this.$refs.email.value;

      const validateEmail =
        /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;

      if (!validateEmail.test(this.user.userEmail) || !this.user.userEmail) {
        this.checkEmailFlag = true;
      } else this.checkEmailFlag = false;
    },
  },
};
</script>
<style></style>
