import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listArticle(param, success, fail) {
  local.get(`/board`, { params: param }).then(success).catch(fail);
}

async function detailArticle(articleno, success, fail) {
  await local.get(`/board/${articleno}`).then(success).catch(fail);
}

function registArticle(article, success, fail) {
  // console.log("boardjs article", article);
  local.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getModifyArticle(articleno, success, fail) {
  local.get(`/board/modify/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  local.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  local.delete(`/board/${articleno}`).then(success).catch(fail);
}

async function detailComments(articleno, success, fail) {
  await local.get(`/board/comments/${articleno}`).then(success).catch(fail);
}

async function registComment(comment, success, fail) {
  await local.post(`/board/comments`, JSON.stringify(comment), success, fail);
}

async function deleteComment(commentId, success, fail) {
  await local.delete(`/board/comments/${commentId}`, success, fail);
}

async function getArticleCount(success, fail) {
  await local.get(`/board/count`, success, fail);
}

export {
  listArticle,
  detailArticle,
  registArticle,
  getModifyArticle,
  modifyArticle,
  deleteArticle,
  detailComments,
  registComment,
  deleteComment,
  getArticleCount,
};
