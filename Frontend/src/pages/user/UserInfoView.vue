<template>
  <div class="container clear-filter" style="padding: 0; z-index: -1">
    <div class="col-md-5 ml-auto mr-auto">
      <card type="login" plain>
        <h3 style="color: black">(홍길동)님의 회원정보 페이지</h3>

        <fg-input
          class="no-border input-lg"
          addon-left-icon="now-ui-icons users_circle-08"
          placeholder="아이디: ssafy"
          readonly
        ></fg-input>

        <fg-input
          class="no-border input-lg"
          addon-left-icon="now-ui-icons ui-1_email-85"
          placeholder="이메일 : ssafy@ssafy.com"
          readonly
        >
        </fg-input>

        <fg-input
          class="no-border input-lg"
          addon-left-icon="now-ui-icons tech_mobile"
          placeholder="전화번호 : 010-1234-1234"
          readonly
        >
        </fg-input>

        <fg-input
          class="no-border input-lg"
          addon-left-icon="now-ui-icons shopping_shop"
          placeholder="주소 : 서울특별시 광진구"
          readonly
        >
        </fg-input>
      </card>
    </div>
  </div>
</template>
<script>
import { FormGroupInput } from "@/components";
export default {
  name: "UserInfoView",
  bodyClass: "user-info-view",
  components: {
    [FormGroupInput.name]: FormGroupInput,
  },
};
</script>
<style>
.user-page-title {
  padding: 10px;
  background-color: gray;
}
</style>
