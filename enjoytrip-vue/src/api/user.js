import { localAxios } from "@/util/http-commons";

const local = localAxios();

/* User Login/Logout Logic */
async function userConfirm(param, success, fail) {
  console.log("param", param);
  await local.post(`/user/login`, param).then(success).catch(fail);
  console.log("userConfirm ok");
}

async function findById(userid, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

/* User Registration Logic */
async function idcheck(userid, success, fail) {
  await local.get(`/user/idcheck/${userid}`).then(success).catch(fail);
}

async function registration(user, success, fail) {
  await local.post(`/user/registration`, user).then(success).catch(fail);
}

async function findUserId(user, success, fail) {
  await local.post(`/user/find/id`, user).then(success).catch(fail);
}

async function findUserPassword(user, success, fail) {
  await local.post(`/user/find/pwd`, user).then(success).catch(fail);
}

/* User Modify Logic */
async function modifyUserInfo(user, success, fail) {
  console.log(user);
  await local.put(`/user/modify/info`, user).then(success).catch(fail);
}

async function modifyPassword(user, success, fail) {
  console.log(user);
  await local.post(`/user/modify/pwd`, user).then(success).catch(fail);
}

export { 
  userConfirm, 
  findById, 
  tokenRegeneration, 
  logout, 
  idcheck, 
  registration, 
  findUserId, 
  findUserPassword, 
  modifyUserInfo,
  modifyPassword,
};
