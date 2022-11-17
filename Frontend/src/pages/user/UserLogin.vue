<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="page-header-image" style="background-image: url('img/login.jpg')"></div>
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="login" plain>
            <p>HAPPY HOUSE</p>
            <fg-input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons users_circle-08"
              placeholder="아이디"
            >
            </fg-input>

            <fg-input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons text_caps-small"
              placeholder="비밀번호"
            >
            </fg-input>

            <div class="card-footer text-center">
              <a href="#pablo" class="btn btn-primary btn-round btn-lg btn-block">로그인</a>
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
    <main-footer></main-footer>
  </div>
</template>
<script>
import { Card, Button, FormGroupInput } from "@/components";
import MainFooter from "@/layout/MainFooter";
export default {
  name: "login-page",
  bodyClass: "login-page",
  components: {
    Card,
    MainFooter,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
};
</script>
<style></style>
