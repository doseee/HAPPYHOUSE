<template>
  <div class="container clear-filter">
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8">
        <card type="login" plain>
          <h3 style="color: black">
            {{ userInfo.userName }}님의 회원수정 페이지
          </h3>
          <div class="input-group no-border input-lg">
            <div class="input-group-prepend">
              <i class="input-group-text now-ui-icons users_circle-08"></i>
            </div>
            <input
              style="
                padding-left: 15px;
                font-size: 0.8571em;
                width: 60px;
                background-color: rgba(222, 222, 222, 0.3);
              "
              placeholder="아이디:"
              readonly />
            <input
              class="form-control"
              style="padding-left: 0px"
              :placeholder="userInfo.userId"
              readonly />
          </div>
          <div class="input-group no-border input-lg">
            <div class="input-group-prepend">
              <i class="input-group-text now-ui-icons ui-1_email-85"></i>
            </div>
            <input
              style="
                padding-left: 15px;
                font-size: 0.8571em;
                width: 60px;
                background-color: rgba(222, 222, 222, 0.3);
              "
              placeholder="이메일: "
              readonly />
            <input
              type="text"
              class="form-control"
              style="padding-left: 0px"
              :value="user.userEmail"
              ref="inputemail"
              @keyup="updateEmail" />
          </div>
          <div class="input-group no-border input-lg">
            <div class="input-group-prepend">
              <i class="input-group-text now-ui-icons tech_mobile"></i>
            </div>
            <input
              style="
                padding-left: 15px;
                font-size: 0.8571em;
                width: 70px;
                background-color: rgba(222, 222, 222, 0.3);
              "
              placeholder="전화번호: "
              readonly />
            <input
              class="form-control"
              style="padding-left: 0px"
              :placeholder="userInfo.userTel"
              readonly />
          </div>
          <div class="input-group no-border input-lg">
            <div class="input-group-prepend">
              <i class="input-group-text now-ui-icons shopping_shop"></i>
            </div>
            <input
              style="
                padding-left: 15px;
                font-size: 0.8571em;
                width: 50px;
                background-color: rgba(222, 222, 222, 0.3);
              "
              placeholder="주소: "
              readonly />
            <input
              class="form-control"
              style="padding-left: 0px"
              :value="user.userAddress"
              ref="inputaddress"
              @keyup="updateAddress" />
          </div>
          <div align="right">
            <button
              @click="modify"
              class="btn btn-outline-dark btn-round btn-sm">
              수정
            </button>
          </div>
          <modal
            :show.sync="modals.mini"
            :show-close="false"
            headerClasses="justify-content-center"
            type="mini">
            <h3 style="text-align: center; color: black">
              성공적으로 수정되었습니다
            </h3>
            <template slot="footer">
              <n-button
                type="danger"
                link
                style="margin-left: 80px"
                @click="remove"
                >확인</n-button
              >
            </template>
          </modal>
        </card>
      </div>
    </div>
  </div>
</template>
<script>
import { Button, Modal, Card } from "@/components";
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserModify",
  bodyClass: "user-info-view",
  data() {
    return {
      modals: {
        mini: false,
      },
      user: {
        userId: this.propsData.userId,
        userEmail: this.propsData.userEmail,
        userAddress: this.propsData.userAddress,
        userPwd: this.propsData.userPwd,
      },
    };
  },
  props: {
    propsData: Object,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userUpdate", "getUserInfo", "userConfirm"]),
    async modify() {
      await this.userUpdate(this.user);
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      await this.getUserInfo(token);
      //console.log("1. confirm() token >> " + token);
      this.modals.mini = true;
    },
    remove() {
      this.modals.mini = false;
    },
    updateEmail() {
      this.user.userEmail = this.$refs.inputemail.value;
    },
    updateAddress() {
      this.user.userAddress = this.$refs.inputaddress.value;
    },
  },

  components: {
    Modal,
    Card,
    [Button.name]: Button,
  },
};
</script>
<style>
.user-page-title {
  padding: 10px;
  background-color: gray;
}
</style>
