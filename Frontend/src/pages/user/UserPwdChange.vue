<template>
  <div class="container clear-filter">
    <div class="row">
      <div class="col-1"></div>
      <div class="col-8">
        <card type="login" plain>
          <h3 style="color: black">비밀번호 수정</h3>

          <div class="input-group no-border input-lg">
            <input
              style="
                padding-left: 15px;
                font-size: 0.8571em;
                width: 120px;
                background-color: rgba(222, 222, 222, 0.3);
                border-radius: 30px 0px 0px 30px;
              "
              placeholder="현재 비밀번호:"
              readonly />
            <input class="form-control" style="padding-left: 0px" ref="pass1" />
          </div>
          <div class="input-group no-border input-lg">
            <input
              style="
                padding-left: 15px;
                font-size: 0.8571em;
                width: 120px;
                background-color: rgba(222, 222, 222, 0.3);
                border-radius: 30px 0px 0px 30px;
              "
              placeholder="새 비밀번호:"
              readonly />
            <input class="form-control" style="padding-left: 0px" ref="pass2" />
          </div>
          <div class="input-group no-border input-lg">
            <input
              style="
                padding-left: 15px;
                font-size: 0.8571em;
                width: 120px;
                background-color: rgba(222, 222, 222, 0.3);
                border-radius: 30px 0px 0px 30px;
              "
              placeholder="새 비밀번호 확인:"
              readonly />
            <input class="form-control" style="padding-left: 0px" ref="pass3" />
          </div>
          <div align="right">
            <button
              class="btn btn-outline-dark btn-round btn-sm"
              @click="modifyPwd">
              수정
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
          <img src="img/userdelete.jpg" style="width: 100px" />
        </div>
        <h3 style="text-align: center; color: black">
          비밀번호가 정상적으로 변경 되었습니다.
        </h3>
        <template slot="footer">
          <n-button type="defalut" link @click="goMypage">확인</n-button>
        </template>
      </modal>
    </div>
  </div>
</template>
<script>
import { Button, Card, Modal } from "@/components";
import { mapState } from "vuex";
import { pwdCheckModify } from "@/api/user.js";
const userStore = "userStore";

export default {
  name: "UserPwdChange",
  bodyClass: "user-info-view",
  data() {
    return {
      userPwd: "",
      newPwd: "",
      newPwdCheck: "",
      modals: {
        mini: false,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    async modifyPwd() {
      this.userPwd = this.$refs.pass1.value;
      this.newPwd = this.$refs.pass2.value;
      this.newPwdCheck = this.$refs.pass3.value;
      if (this.newPwd != this.newPwdCheck) {
        alert("비밀번호가 서로 일치하지 않습니다.");
      } else {
        let user = {
          userId: this.userInfo.userId,
          userPwd: this.userPwd,
          newPwd: this.newPwd,
        };
        await pwdCheckModify(
          user,
          ({ data }) => {
            if (data === "success") {
              this.modals.mini = true;
            } else {
              alert("올바르지 않은 비밀번호입니다.");
            }
          },
          (error) => {
            console.log(error);
          },
        );
      }
    },
    goMypage() {
      this.modals.mini = false;
      this.$emit("gotomypage", "mypage");
    },
  },
  components: {
    [Button.name]: Button,
    Card,
    Modal,
  },
};
</script>
<style>
.user-page-title {
  padding: 10px;
  background-color: gray;
}
</style>
