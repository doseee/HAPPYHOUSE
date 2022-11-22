import { apiInstance } from "@/api";

const api = apiInstance();

function listArticle(success) {
  api
    .get(`/notice/list`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}
function writeArticle(article, success) {
  api
    .post(`/notice/write`, JSON.stringify(article))
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}
function getArticle(articleNo, success) {
  api
    .get(`/notice/view?articleNo=${articleNo}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function modifyArticle(article, success) {
  api
    .put(`/notice/modify`, JSON.stringify(article))
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function deleteArticle(articleNo, success) {
  api
    .delete(`/notice/delete?articleNo=${articleNo}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}
function getArticleById(userId, success) {
  api
    .get(`/notice/searchId?userId=${userId}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function getArticleBySubject(subject, success) {
  api
    .get(`/notice/searchTitle?subject=${subject}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

export {
  listArticle,
  writeArticle,
  getArticle,
  modifyArticle,
  deleteArticle,
  getArticleById,
  getArticleBySubject,
};
