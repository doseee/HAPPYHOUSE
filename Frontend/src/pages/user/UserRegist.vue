<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="page-header-image" style="background-image: url('img/login.jpg')"></div>
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="regist" plain>
            <p>회원가입</p>

            <fg-input id="userId" class="no-border input-lg" placeholder="아이디">
            </fg-input>

            <button type="button" class="btn btn-round btn-warning">
              <!---->중복검사<!---->
            </button>

            <fg-input
              id="userPwd"
              type="password"
              class="no-border input-lg"
              placeholder="비밀번호"
            >
            </fg-input>

            <fg-input
              id="pwck"
              type="password"
              class="no-border input-lg"
              placeholder="비밀번호 확인"
            >
            </fg-input>
            <fg-input id="userName" class="no-border input-lg" placeholder="이름">
            </fg-input>
            <fg-input id="userTel" class="no-border input-lg" placeholder="전화번호">
            </fg-input>
            <fg-input
              type="email"
              id="userEmail"
              class="no-border input-lg"
              placeholder="이메일"
            >
            </fg-input>
            <fg-input id="userAddress" class="no-border input-lg" placeholder="주소">
            </fg-input>

            <div class="card-footer text-center">
              <a href="#pablo" class="btn btn-warning btn-round btn-lg btn-block"
                >회원가입</a
              >
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
    <main-footer></main-footer>
  </div>
</template>
<script>
import { Card, Button, FormGroupInput } from "@/components";
import MainFooter from "@/layout/MainFooter";
export default {
  name: "UserRegister",
  bodyClass: "UserRegister",
  components: {
    Card,
    MainFooter,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
};
</script>
<style></style>
