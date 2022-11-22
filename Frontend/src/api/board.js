import { apiInstance } from "@/api";

const api = apiInstance();

function listArticle(success) {
  api
    .get(`/board/list`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}
function listComment(articleNo, success) {
  api
    .get(`/comment/list?articleNo=${articleNo}`)
    .then(success)
    .catch((err) => {
      console.log(err);
    });
}
function writeArticle(article, success) {
  api
    .post(`/board/write`, JSON.stringify(article))
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function writeComment(comment, success) {
  api
    .post(`/comment/write`, JSON.stringify(comment))
    .then(success)
    .catch((err) => {
      console.log(err);
    });
}

function getArticle(articleNo, success) {
  api
    .get(`/board/view?articleNo=${articleNo}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function modifyArticle(article, success) {
  api
    .put(`/board/modify`, JSON.stringify(article))
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function deleteArticle(articleNo, success) {
  api
    .delete(`/board/delete?articleNo=${articleNo}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}
function deleteComment(commentNo, success) {
  api
    .delete(`/comment/delete?commentNo=${commentNo}`)
    .then(success)
    .catch((err) => {
      console.log(err);
    });
}
function getArticleById(userid, success) {
  api
    .get(`/board/searchid/${userid}`)
    .then(success)
    .catch((err) => {
      alert("문제가 발생했습니다.");
      console.log(err);
    });
}

function getArticleBySubject(subject, success) {
  api
    .get(`/board/searchtitle/${subject}`)
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
  writeComment,
  listComment,
  deleteComment,
};
