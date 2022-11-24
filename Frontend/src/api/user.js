import { apiInstance } from "@/api";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function idCheck(userId, success) {
  api
    .post(`user/check-id/${userId}`)
    .then(success)
    .catch((err) => {
      console.log(err);
    });
}

async function findById(userId, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userId}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userId, success, fail) {
  await api.get(`/user/logout/${userId}`).then(success).catch(fail);
}

async function update(user, success, fail) {
  await api.put(`/user/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function remove(userId, success, fail) {
  await api.delete(`/user/user/${userId}`).then(success).catch(fail);
}

async function pwdCheckModify(user, success, fail) {
  await api
    .put(`/user/user/modify-pwd`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function userFindPwd(user, success, fail) {
  await api
    .put(`/user/user/find-pwd`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function selectComment(params, success, fail) {
  await api
    .get(`/user/user-comment?userId=${params.userId}`)
    .then(success)
    .catch(fail);
}
async function selectBoard(params, success, fail) {
  await api
    .get(`/user/user-board?userId=${params.userId}`)
    .then(success)
    .catch(fail);
}
async function join(user, success, fail) {
  await api.post(`/user/user`, JSON.stringify(user)).then(success).catch(fail);
}

/////////////////관심지역 등록 //////////////////////

//관심지역 등록
async function registLikeDong(params, success) {
  await api
    .post(
      // eslint-disable-next-line prettier/prettier
      `/user/add-like?dongCode=${params.dongCode}&userId=${params.userId}`
    )
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}
//관심지역 제거
async function deleteLikeDong(params, success) {
  await api
    .delete(
      // eslint-disable-next-line prettier/prettier
      `/user/delete-like?dongCode=${params.dongCode}&userId=${params.userId}`
    )
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

//관심지역 리스트 조회
async function selectLikeDong(params, success) {
  await api
    .get(
      // eslint-disable-next-line prettier/prettier
      `/user/mylike?userId=${params.userId}`
    )
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

export {
  login,
  findById,
  tokenRegeneration,
  logout,
  update,
  remove,
  join,
  registLikeDong,
  deleteLikeDong,
  selectLikeDong,
  idCheck,
  pwdCheckModify,
  userFindPwd,
  selectComment,
  selectBoard,
};
