<template>
  <div>
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
  </div>
</template>
<script>
export default {
  name: "UserInfoView",
  bodyClass: "user-info-view",
};
</script>
<style>
.user-page-title {
  padding: 10px;
  background-color: gray;
}
</style>
