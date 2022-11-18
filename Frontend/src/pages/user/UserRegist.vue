<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="page-header-image" style="background-image: url('img/login.jpg')"></div>
    <div class="container">
      <div class="col-md-5 ml-auto mr-auto">
        <card type="regist" plain>
          <p>회원가입</p>

          <div>
            <fg-input-custom-dupli
              id="userId"
              class="no-border input-lg"
              placeholder="아이디"
              addon-left-icon=""
              style="border-top-right-radius: 10px; border-bottom-right-radius: 10px"
            >
            </fg-input-custom-dupli>
          </div>

          <fg-input-custom
            id="userPwd"
            type="password"
            class="no-border input-lg"
            placeholder="비밀번호"
            addon-left-icon=""
          >
          </fg-input-custom>

          <fg-input-custom
            id="pwck"
            type="password"
            class="no-border input-lg"
            placeholder="비밀번호 확인"
            addon-left-icon=""
          >
          </fg-input-custom>
          <fg-input-costom
            id="userName"
            class="no-border input-lg"
            placeholder="이름"
            addon-left-icon=""
          >
          </fg-input-costom>
          <fg-input-custom
            id="userTel"
            class="no-border input-lg"
            placeholder="전화번호"
            addon-left-icon=""
          >
          </fg-input-custom>
          <fg-input-custom
            type="email"
            id="userEmail"
            class="no-border input-lg"
            placeholder="이메일"
            addon-left-icon=""
          >
          </fg-input-custom>
          <fg-input-custom
            id="userAddress"
            class="no-border input-lg"
            placeholder="주소"
            addon-left-icon=""
          >
          </fg-input-custom>

          <div class="card-footer text-center">
            <a href="#pablo" class="btn btn-warning btn-round btn-lg btn-block">회원가입</a>
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
import FormGroupInputCustom from "@/components/Inputs/FormGroupInputCustom";
import FormGroupInputDupli from "@/components/Inputs/FormGroupInputDupli";
export default {
  name: "UserRegister",
  bodyClass: "UserRegister",
  components: {
    Card,
    [Button.name]: Button,
    [FormGroupInputCustom.name]: FormGroupInputCustom,
    [FormGroupInputDupli.name]: FormGroupInputDupli,
  },
};
</script>
<style></style>
