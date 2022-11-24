<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/city.jpg')"></div>
    <div class="container">
      <div class="col-md-5" style="height: 60px"></div>
      <div class="col-md-5 ml-auto mr-auto">
        <card type="login" plain>
          <p>회원가입</p>

          <form v-on:submit.prevent>
            <div class="input-group-prepend">
              <div class="input-group no-border input-lg">
                <input
                  type="text"
                  id="userpwd"
                  required="required"
                  class="form-control"
                  style="
                    border-top-right-radius: 30px;
                    border-bottom-right-radius: 30px;
                  "
                  ref="userId"
                  placeholder="아이디"
                  v-model="user.userId" />
                <button
                  class="btn-round btn-primary"
                  type="button"
                  @click="idValidCheck()">
                  중복검사
                </button>
              </div>
            </div>

            <div>
              <div class="input-group no-border input-lg">
                <input
                  type="text"
                  id="userpwd"
                  required="required"
                  class="form-control"
                  placeholder="비밀번호"
                  ref="userPwd"
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
                  type="text"
                  id="userpwd"
                  class="form-control"
                  required="required"
                  ref="userName"
                  placeholder="이름"
                  v-model="user.userName" />
              </div>
            </div>
            <div>
              <div class="input-group no-border input-lg">
                <input
                  type="text"
                  id="userpwd"
                  class="form-control"
                  placeholder="전화번호"
                  required="required"
                  ref="tel"
                  @focus="telCheck"
                  v-model="user.userTel" />
              </div>
              <p v-show="checkTelFlag" class="input-error">
                전화번호를 정확히 입력해주세요(010-0000-0000)
              </p>
            </div>
            <div>
              <div class="input-group no-border input-lg">
                <input
                  type="text"
                  id="userpwd"
                  class="form-control"
                  placeholder="예) ssafy@ssafy.co.kr"
                  required="required"
                  ref="email"
                  @focus="emailCheck"
                  v-model="user.userEmail" />
              </div>
              <p v-show="checkEmailFlag" class="input-error">
                이메일 주소를 정확히 입력해주세요
              </p>
            </div>
            <div>
              <div class="input-group no-border input-lg">
                <input
                  type="text"
                  id="userpwd"
                  class="form-control"
                  placeholder="주소"
                  ref="userAddress"
                  required="required"
                  v-model="user.userAddress" />
              </div>
            </div>
            <button
              class="btn btn-primary btn-round btn-lg btn-block"
              @click="register">
              회원가입
            </button>
          </form>
        </card>
      </div>
    </div>
    <modal
      :show.sync="modals.mini"
      :show-close="false"
      headerClasses="justify-content-center"
      type="mini">
      <div slot="header">
        <img src="img/userdelete.jpg" style="width: 100px" />
      </div>
      <h3 style="text-align: center; color: black">
        정상적으로 회원가입이 되었습니다. <br />로그인 페이지로 이동합니다.
      </h3>
      <template slot="footer">
        <n-button type="defalut" link @click="goLogin">확인</n-button>
      </template>
    </modal>
  </div>
</template>
<script>
import { Card, Button, Modal } from "@/components";
import { mapActions } from "vuex";
import { idCheck } from "@/api/user.js";
const userStore = "userStore";

export default {
  name: "UserRegister",
  bodyClass: "login-page",
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
      idValid: false,
      checkPwd: "",
      checkPwdFlag: true,
      checkTelFlag: false,
      checkEmailFlag: false,
      modals: {
        mini: false,
      },
    };
  },
  components: {
    Card,
    Modal,
    [Button.name]: Button,
  },
  methods: {
    ...mapActions(userStore, ["userJoin", "idCheck"]),
    async register() {
      if (!this.idValid) {
        alert("중복확인이 필요합니다");
        this.$router.push({ name: "regist" });
      } else {
        await this.userJoin(this.user);
        this.modals.mini = true;
      }
    },
    checkPwdValid() {
      if (this.user.userPwd === this.checkPwd) {
        this.checkPwdFlag = true;
      } else {
        this.checkPwdFlag = false;
      }
    },
    idValidCheck() {
      idCheck(
        this.user.userId,
        ({ data }) => {
          if (data === "success") {
            this.idValid = true;
          } else {
            this.idValid = false;
          }
          if (this.idValid) {
            alert("사용 가능한 아이디입니다");
          } else {
            alert("사용 불가능한 아이디입니다");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    telCheck() {
      this.user.userTel = this.$refs.tel.value;

      const validateTel = /^\d{3}-\d{3,4}-\d{4}$/;
      if (!this.user.userTel) {
        this.checkTelFlag = false;
      } else if (!validateTel.test(this.user.userTel)) {
        this.checkTelFlag = true;
      } else this.checkTelFlag = false;
    },
    emailCheck() {
      this.user.userEmail = this.$refs.email.value;

      const validateEmail =
        /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
      if (!this.user.userEmail) {
        this.checkEmailFlag = false;
      } else if (!validateEmail.test(this.user.userEmail)) {
        this.checkEmailFlag = true;
      } else this.checkEmailFlag = false;
    },
    goLogin() {
      this.modals.mini = false;
      this.$router.push({ name: "login" });
    },
  },
};
</script>
<style></style>
