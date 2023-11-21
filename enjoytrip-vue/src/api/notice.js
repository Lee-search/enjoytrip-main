import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listNotice(param, success, fail) {
  local.get(`/notice`, { params: param }).then(success).catch(fail);
}

async function detailNotice(noticeno, success, fail) {
  await local.get(`/notice/${noticeno}`).then(success).catch(fail);
}

function registNotice(notice, success, fail) {
  // console.log("boardjs article", article);
  local.post(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

function getModifyNotice(noticeno, success, fail) {
  local.get(`/notice/modify/${noticeno}`).then(success).catch(fail);
}

function modifyNotice(notice, success, fail) {
  local.put(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

function deleteNotice(noticeno, success, fail) {
  local.delete(`/notice/${noticeno}`).then(success).catch(fail);
}

async function detailComments(noticeno, success, fail) {
  await local.get(`/notice/comments/${noticeno}`).then(success).catch(fail);
}

async function registComment(comment, success, fail) {
  await local.post(`/notice/comments`, JSON.stringify(comment), success, fail);
}

async function deleteComment(commentId, success, fail) {
  await local.delete(`/board/comments/${commentId}`, success, fail);
}

export {
  listNotice,
  detailNotice,
  registNotice,
  getModifyNotice,
  modifyNotice,
  deleteNotice,
  detailComments,
  registComment,
  deleteComment,
};
