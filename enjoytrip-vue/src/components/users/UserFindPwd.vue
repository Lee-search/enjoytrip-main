<script setup>
import { ref } from 'vue';
import { useRouter } from "vue-router";

import { findUserPassword } from "@/api/user";

const router = useRouter();
const goToLoginPage = () => router.push("/user/login");

// 비밀번호 찾기 로직 ---------------------------------------
const isPwdFound = ref(null);
const formData = ref({
  userId: "",
  userName: "",
  emailId: "",
  emailDomain: "",
});

const submitFindUserPwd = async () => {

  // console.log(formData);
  // 비어있는 input값이 있는 경우
  if(
    formData.value.userId === '' ||
    formData.value.userName === '' ||
    formData.value.emailId === '' ||
    formData.value.emailDomain === ''
  ) {
    alert('회원 정보를 입력해주세요.');
    return;
  }

  // console.log(formData.value);
  await findUserPassword(
    formData.value,
    (response) => {
      if(response.data.userPwd) {
        console.log("비밀번호 찾기 완료!");
        foundPwd.value = response.data.userPwd;
        isPwdFound.value = true;
      } else {
        // 해당 개인정보와 일치하는 아이디가 없는 경우
        isPwdFound.value = false;
      }
    },
    (error) => {
      console.error(error);
    }
  )
};

// 찾은 비밀번호 저장
const foundPwd = ref("");

</script>

<template>
  
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <h2 class="my-4 py-3 shadow bg-info text-white text-center rounded">
          비밀번호 찾기
        </h2>
      </div>
      <!-- 비밀번호를 찾은 후 페이지 -->
      <div v-if="isPwdFound" class="col-lg-8">
        <div class="card p-4 shadow text-center">
          <p class="mb-2">가입하신 아이디의 비밀번호는 다음과 같습니다.</p>
          <p class="mb-4 text-secondary">보안을 위해 비밀번호를 변경하실 것을 권장합니다.</p>
          <h2 class="mb-4 text-success">{{ foundPwd }}</h2>
          <div class="d-flex justify-content-center mb-4">
            <button class="btn btn-primary mx-2" @click="goToLoginPage">로그인 페이지로 이동</button>
          </div>
        </div>
      </div>
      <!-- 비밀번호 찾는 페이지 -->
      <div v-else class="col-lg-8">
        <div class="card p-4 shadow text-center">
          <p class="mb-4">비밀번호는 가입시 입력하신 개인정보를 통해 찾을 수 있습니다.</p>
          <p v-if="isPwdFound === false" class="mb-2 text-danger">입력하신 정보를 찾을 수 없습니다.</p>
          <div class="mb-3 text-start">
            <label for="userid" class="form-label">아이디:</label>
            <input type="text" class="form-control" id="userid" v-model="formData.userId" placeholder="아이디..." />
          </div>
          <div class="mb-3 text-start">
            <label for="username" class="form-label">이름:</label>
            <input type="text" class="form-control" id="username" v-model="formData.userName" placeholder="이름..." />
          </div>
          <div class="mb-3 text-start">
            <label for="emailid" class="form-label">이메일:</label>
            <div class="input-group mb-3">
              <input type="text" class="form-control" id="emailid" v-model="formData.emailId" placeholder="이메일 아이디" />
              <span class="input-group-text">@</span>
              <input type="text" class="form-control" id="emailDomain" v-model="formData.emailDomain" placeholder="이메일 도메인" />
            </div>
          </div>
          <div class="d-flex justify-content-center mb-3">
            <button class="btn btn-primary mx-2" @click.prevent="submitFindUserPwd">비밀번호 찾기</button>
            <button class="btn btn-secondary mx-2" @click="goToLoginPage">뒤로</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>