<template>
  <div class="container clear-filter">
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8">
        <card type="login" plain>
          <h3 style="color: black">
            {{ userInfo.userName }}님의 회원정보 페이지
          </h3>

          <div class="input-group no-border input-lg">
            <div class="input-group-prepend">
              <i class="input-group-text now-ui-icons users_circle-08"></i>
            </div>
            <input
              class="form-control"
              :placeholder="'아이디: \t' + userInfo.userId"
              readonly />
          </div>
          <div class="input-group no-border input-lg">
            <div class="input-group-prepend">
              <i class="input-group-text now-ui-icons ui-1_email-85"></i>
            </div>
            <input
              class="form-control"
              :placeholder="'이메일: \t' + userInfo.userEmail"
              readonly />
          </div>
          <div class="input-group no-border input-lg">
            <div class="input-group-prepend">
              <i class="input-group-text now-ui-icons tech_mobile"></i>
            </div>
            <input
              class="form-control"
              :placeholder="'전화번호: \t' + userInfo.userTel"
              readonly />
          </div>
          <div class="input-group no-border input-lg">
            <div class="input-group-prepend">
              <i class="input-group-text now-ui-icons shopping_shop"></i>
            </div>
            <input
              class="form-control"
              :placeholder="'주소: \t' + userInfo.userAddress"
              readonly />
          </div>
        </card>
      </div>
    </div>
  </div>
</template>
<script>
import { FormGroupInput, Card } from "@/components";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "UserInfoView",
  bodyClass: "user-info-view",
  data() {
    return {
      userid: null,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },

  components: {
    [FormGroupInput.name]: FormGroupInput,
    Card,
  },
};
</script>
<style>
.user-page-title {
  padding: 10px;
  background-color: gray;
}
</style>
