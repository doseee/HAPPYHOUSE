<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="content">
      <v-list id="sidebar" class="clear-filter nav-link">
        <v-list-group :value="true" no-action sub-group prepend-icon="mdi-account-circle">
          <template v-slot:activator>
            <v-list-item-title style="color: white">
              회원관리
              <i class="now-ui-icons arrows-1_minimal-down"></i>
            </v-list-item-title>
          </template>

          <v-list-item style="margin-left: 120px; color: white">
            <v-list-item-title v-text="`회원정보 보기`" class="mypage-aside"></v-list-item-title>
          </v-list-item>
          <v-list-item style="margin-left: 120px; color: white">
            <v-list-item-title v-text="`회원정보 수정`" class="mypage-aside"></v-list-item-title>
          </v-list-item>
          <v-list-item style="margin-left: 150px; color: white">
            <v-list-item-title
              @click="modals.mini = true"
              v-text="`회원탈퇴`"
              class="mypage-aside"
              style="font-style: italic; text-decoration: underline"
            ></v-list-item-title>
          </v-list-item>
        </v-list-group>
        <v-list-group :value="true" no-action sub-group>
          <template v-slot:activator>
            <v-list-item-title style="color: white">
              글 댓글
              <i class="now-ui-icons arrows-1_minimal-down"></i>
            </v-list-item-title>
          </template>

          <v-list-item style="margin-left: 140px; color: white">
            <v-list-item-title v-text="`내가 쓴 글`" class="mypage-aside"></v-list-item-title>
          </v-list-item>
          <v-list-item style="margin-left: 120px; color: white">
            <v-list-item-title v-text="`내가 쓴 댓글`" class="mypage-aside"></v-list-item-title>
          </v-list-item>
        </v-list-group>
        <v-list-group :value="true" no-action sub-group>
          <template v-slot:activator>
            <v-list-item-title style="color: white"> 나의 관심 지역 </v-list-item-title>
          </template>
        </v-list-group>
      </v-list>
      <template>
        <info-view></info-view>
      </template>
      <modal
        :show.sync="modals.mini"
        class=""
        :show-close="false"
        headerClasses="justify-content-center"
        type="mini"
      >
        <div slot="header">
          <img src="img/userdelete.jpg" style="width: 100px" />
        </div>
        <h3 style="text-align: center; color: black">회원탈퇴를 <br />하시겠습니까?</h3>

        <h5 style="text-align: center; color: black">
          더이상 해당 계정으로 활동을 하실 수 없습니다.
        </h5>
        <template slot="footer">
          <n-button type="defalut" link @click.native="modals.mini = false">아니오</n-button>
          <n-button type="danger" link>예, 탈퇴하겠습니다</n-button>
        </template>
      </modal>
    </div>
  </div>
</template>
<script>
import InfoView from "@/pages/user/UserInfoView";
import { Modal, Button, FormGroupInput } from "@/components";
export default {
  name: "UserPage",
  data: () => ({
    user: [
      ["회원정보 보기", "/infoview"],
      ["회원정보 수정", "/infomodify"],
      ["회원탈퇴", "/infodelete"],
    ],
    modals: {
      mini: false,
    },
  }),
  components: {
    InfoView,
    Modal,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
};
</script>
<style>
#sidebar {
  width: 350px;
  height: 100%;
  padding: 5px 15px;
  float: left;
  background-color: rgba(201, 138, 195, 0.00001);
}
</style>
