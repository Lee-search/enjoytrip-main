<script setup>
import { ref, computed, onMounted } from "vue";
import { modifyUserInfo, modifyPassword, userConfirm } from "@/api/user";
import { useMemberStore } from "@/stores/member";
import { httpStatusCode } from "@/util/http-status";

// toRef: pinia 안의 userInfo 가져올 때, 반응형으로 감싸서 가져옴
const memberStore = useMemberStore();
const userInfo = computed(() => memberStore.userInfo);

const isUserModify = ref(false);
const isPwModify = ref(false);

// userInfo 최신화 로직
const fetchUserInfo = async () => {
  let token = sessionStorage.getItem("accessToken");
  await memberStore.getUserInfo(token);
};

// 회원정보 클릭
const onUserModify = () => {
  if(isPwModify.value) isPwModify.value = false;
  isUserModify.value = !isUserModify.value;
};

// 비밀번호 클릭
const onPwModify = () => {
  if(isUserModify.value) isUserModify.value = false;
  isPwModify.value = !isPwModify.value;
};

// 초기화 클릭
const resetForm = () => {
  formUserData.value.userName = userInfo.value.userName;
  formUserData.value.emailId = userInfo.value.emailId;
  formUserData.value.emailDomain = userInfo.value.emailDomain;
  formPwData.value.currentPwd = '';
  formPwData.value.newPwd = '';
  formPwData.value.newPwdcheck = '';
}

// 수정 버튼 관련 폼
const formUserData = ref({
  userId: userInfo.value.userId,
  userName: userInfo.value.userName,
  emailId: userInfo.value.emailId,
  emailDomain: userInfo.value.emailDomain,
});

// 회원정보 --------------------------------------------------
// 회원정보 수정하기 클릭
const onModifyUserInfo = async () => {
  // 비어있는 input값이 있는 경우
  if(
    formUserData.value.userName === '' ||
    formUserData.value.emailId === '' ||
    formUserData.value.emailDomain === ''
  ) {
    alert('회원 정보를 입력해주세요.');
    return;
  }
  
  await modifyUserInfo(
    formUserData.value,
    async (response) => {
      alert("회원 정보 수정 완료!");
      // userInfo 강제 업데이트
      // let token = sessionStorage.getItem("accessToken");
      // useMemberStore.getUserInfo(token);
      await fetchUserInfo();
      isUserModify.value = !isUserModify.value;
    },
    (error) => {
      console.error(error);
    }
  )
};

// 비밀번호 --------------------------------------------------------
const formPwData = ref({
  currentPwd: '',
  newPwd: '',
  newPwdcheck: '',
});

// 비밀번호 변경하기 클릭
const onModifyPassword = async () => {
  // 비어있는 input값이 있는 경우
  if(
    formPwData.value.currentPwd === '' ||
    formPwData.value.newPwd === '' ||
    formPwData.value.newPwdcheck === ''
  ) {
    alert('비밀번호를 입력해주세요.');
    return;
  }

  // 비밀번호 일치하지 않는 경우
  if (formPwData.value.newPwd !== formPwData.value.newPwdcheck) {
    alert('비밀번호를 다시 확인해주세요.');
    return;
  }
  
  await modifyPassword(
    {
      "userId": userInfo.userId,
      "currentPwd": formPwData.value.currentPwd,
      "newPwd": formPwData.value.newPwd,
    },
    (response) => {
      if (response.status = httpStatusCode.OK) {
        alert("비밀번호 수정 완료!");
        resetForm();
        isPwModify.value = !isPwModify.value;
      }
    },
    (error) => {
      alert("비밀번호를 확인해주세요.");
      console.error(error);
    }
  )
};

</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <h2 class="my-4 py-3 shadow bg-primary text-white text-center rounded">
          마이페이지
        </h2>
      </div>
      <div class="col-lg-8">
        <div class="card m-auto px-2 py-2">
          <div class="row g-2">
            <!-- 이미지 부분 -->
            <div class="col-md-4 d-flex align-items-center justify-content-center">
              <img
                src="@/assets/img/profile-icon.png"
                class="img-fluid rounded"
                alt="..."
              />
            </div>
            <!-- 정보 리스트 부분 -->
            <div class="col-md-8 d-flex align-items-center">
              <div class="w-100">
                <ul class="list-group list-group-flush text-center">
                  <li class="list-group-item">아이디: {{ userInfo.userId }}</li>
                  <li class="list-group-item">이름: {{ userInfo.userName }}</li>
                  <li class="list-group-item">이메일: {{ userInfo.emailId }}@{{ userInfo.emailDomain }}</li>
                </ul>
                <div class="mt-auto p-2 d-flex justify-content-end">
                  <button class="btn btn-outline-primary btn-sm mx-2" @click="onUserModify">회원정보 수정</button>
                  <button class="btn btn-outline-danger btn-sm mx-2" @click="onPwModify">비밀번호 변경</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 회원 정보 수정 -->
      <div class="col-lg-8 mt-4 mb-2" v-show="isUserModify">
        <div class="card m-auto px-2 py-2">
          <form class="row g-2 p-4">
            <!-- 정보 리스트 부분 -->
            <div class="mb-3 text-start">
              <label for="username" class="form-label">이름:</label>
              <input type="text" class="form-control" id="username" v-model="formUserData.userName" placeholder="이름..." />
            </div>
            <div class="mb-3 text-start">
              <label for="emailid" class="form-label">이메일:</label>
              <div class="input-group">
                <input type="text" class="form-control" id="emailid" v-model="formUserData.emailId" placeholder="이메일 아이디" />
                <span class="input-group-text">@</span>
                <select class="form-select" v-model="formUserData.emailDomain" aria-label="이메일 도메인 선택">
                  <option selected >선택</option>
                    <option value="ssafy.com">싸피</option>
                    <option value="google.com">구글</option>
                    <option value="naver.com">네이버</option>
                    <option value="kakao.com">카카오</option>
                </select>
              </div>
            </div>
            <div class="text-center">
              <button type="button" class="btn btn-warning mb-3" @click="onModifyUserInfo">수정하기</button>
              <button type="button" class="btn btn-secondary ms-2 mb-3" @click="resetForm">초기화</button>
            </div>
          </form>
        </div>
      </div>

      <!-- 비밀번호 변경 -->
      <div class="col-lg-8 mt-4 mb-2" v-show="isPwModify">
        <div class="card m-auto px-2 py-2">
          <form class="row g-2 p-4">
            <!-- 정보 리스트 부분 -->
            <div class="mb-3 text-start">
              <label for="currentpass" class="form-label">기존 비밀번호:</label>
              <input type="password" class="form-control" id="currentpass" v-model="formPwData.currentPwd" placeholder="비밀번호..." />
            </div>
            <div class="mb-3 text-start">
              <label for="newpass" class="form-label">새 비밀번호:</label>
              <input type="password" class="form-control" id="newpass" v-model="formPwData.newPwd" placeholder="새 비밀번호..." />
            </div>
            <div class="text-start">
              <label for="newpass-check" class="form-label">새 비밀번호 확인:</label>
              <input type="password" class="form-control" id="newpass-check" v-model="formPwData.newPwdcheck" placeholder="새 비밀번호 확인..." />
            </div>
            <div v-if="formPwData.newPwd === '' || formPwData.newPwdcheck === ''"></div>
            <div v-else-if="formPwData.newPwd === formPwData.newPwdcheck" class="text-success mb-3">비밀번호가 일치합니다.</div>
            <div v-else class="text-danger mb-3">비밀번호가 일치하지 않습니다.</div>
            <div class="text-center">
              <button type="button" class="btn btn-warning mb-3" @click="onModifyPassword">변경하기</button>
              <button type="button" class="btn btn-secondary ms-2 mb-3" @click="resetForm">초기화</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
</style>