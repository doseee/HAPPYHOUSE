import { apiInstance } from "@/api";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
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

async function join(user, success, fail) {
  await api.post(`/user/user`, JSON.stringify(user)).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, update, remove, join };
