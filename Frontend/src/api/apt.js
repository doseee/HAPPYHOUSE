import { apiInstance } from "@/api";

const api = apiInstance();

function sidoList(success) {
  api
    .get(`/apt/sido`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

function gugunList(params, success) {
  api
    .get(`/apt/gugun?sido=${params.sidoCode}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}
function dongList(params, success) {
  api
    .get(`/apt/dong?gugun=${params.sidoCode}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

function houseListByDong(params, success) {
  api
    .get(`/apt/apt?dong=${params.dongCode}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}

function dealsByCode(params, success) {
  api
    .get(`/apt/apt-deals?aptCode=${params.aptCode}`)
    .then(success)
    .catch((error) => {
      alert("문제가 발생했습니다.");
      console.log(error);
    });
}
export { sidoList, gugunList, dongList, houseListByDong, dealsByCode };
