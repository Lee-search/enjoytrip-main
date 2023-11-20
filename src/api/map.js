import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listSido(success, fail) {
  local.get(`/map/sido`).then(success).catch(fail);
}

function listGugun(param, success, fail) {
  local.get(`/map/gugun`, { params: param }).then(success).catch(fail);
}


function getAttractionList(params, success, fail) {
  local
    .post(`/map/list`, JSON.stringify(params))
    .then(success)
    .catch(fail);
}



function getAttractionDetail(param, success, fail) {
  local.get(`/map/detail`, { params: param }).then(success).catch(fail);
}

export { listSido, listGugun,getAttractionList,getAttractionDetail };
//