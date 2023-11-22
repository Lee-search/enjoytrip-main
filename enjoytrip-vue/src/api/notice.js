import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listNotice(param, success, fail) {
  local.get(`/notice`, { params: param }).then(success).catch(fail);
}

async function detailNotice(articleno, success, fail) {
  await local.get(`/notice/${articleno}`).then(success).catch(fail);
}

function registNotice(article, success, fail) {
  // console.log("boardjs article", article);
  local.post(`/notice`, JSON.stringify(article)).then(success).catch(fail);
}

function getModifyNotice(articleno, success, fail) {
  local.get(`/notice/modify/${articleno}`).then(success).catch(fail);
}

function modifyNotice(article, success, fail) {
  local.put(`/notice`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteNotice(articleno, success, fail) {
  local.delete(`/notice/${articleno}`).then(success).catch(fail);
}

export {
  listNotice,
  detailNotice,
  registNotice,
  getModifyNotice,
  modifyNotice,
  deleteNotice,
};
