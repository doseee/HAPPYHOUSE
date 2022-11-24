<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"></div>
    <div class="container">
      <div class="col-md-5 ml-auto mr-auto">
        <card type="login" plain>
          <p style="margin-bottom: 30px">비밀번호 찾기</p>

          <div class="input-group no-border input-lg">
            <input
              class="pwd-form-control"
              style="
                padding-left: 15px;
                font-size: 0.8571em;
                width: 60px;
                background-color: rgba(222, 222, 222, 0.3);
                border-radius: 30px 0px 0px 30px;
              "
              placeholder="아이디:"
              readonly />
            <input class="form-control" style="padding-left: 5px" ref="id" />
          </div>
          <div class="input-group no-border input-lg">
            <input
              class="pwd-form-control"
              style="
                padding-left: 15px;
                font-size: 0.8571em;
                width: 60px;
                background-color: rgba(222, 222, 222, 0.3);
                border-radius: 30px 0px 0px 30px;
              "
              placeholder="이름:"
              readonly />
            <input class="form-control" style="padding-left: 5px" ref="name" />
          </div>
          <div class="card-footer text-center">
            <button
              class="btn btn-primary btn-round btn-lg btn-block"
              @click="findPwd">
              확인
            </button>
          </div>
        </card>
      </div>
      <modal
        :show.sync="modals.mini"
        :show-close="false"
        headerClasses="justify-content-center"
        type="mini">
        <div slot="header">
          <img src="img/pwd.png" style="width: 100px" />
        </div>
        <h3 style="text-align: center; color: black">
          {{ userName }}님의 임시 비밀번호는 <br />
          {{ tmpPwd }} 입니다.
        </h3>
        <template slot="footer">
          <n-button
            type="defalut"
            link
            @click.native="modals.mini = false"></n-button>
          <n-button type="defalut" link @click="goLogin">확인</n-button>
          <n-button
            type="defalut"
            link
            @click.native="modals.mini = false"></n-button>
        </template>
      </modal>
    </div>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import { Card, Button, Modal } from "@/components";
import { userFindPwd } from "@/api/user.js";
const userStore = "userStore";

export default {
  name: "UserFindPwd",
  bodyClass: "login-page",
  data() {
    return {
      userId: "",
      userName: "",
      tmpPwd: "",
      modals: {
        mini: false,
      },
    };
  },
  methods: {
    async findPwd() {
      this.userName = this.$refs.name.value;
      let user = {
        userId: this.$refs.id.value,
        userName: this.$refs.name.value,
      };
      await userFindPwd(
        user,
        ({ data }) => {
          if (data.result === "success") {
            this.tmpPwd = data.tmpPwd;
            this.modals.mini = true;
          } else {
            alert("올바른 회원정보가 아닙니다");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    goLogin() {
      this.$router.push({ name: "login" });
    },
  },

  components: {
    Card,
    [Button.name]: Button,
    Modal,
  },
};
</script>
<style>
.pwd-form-control::-moz-placeholder {
  color: white;
}
.pwd-form-control:-moz-placeholder {
  color: white;
}
.pwd-form-control::-webkit-input-placeholder {
  color: white;
}
.pwd-form-control:-ms-input-placeholder {
  color: white;
}
</style>
