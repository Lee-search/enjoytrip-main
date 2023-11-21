<script setup>
import { ref } from 'vue';
import { useRouter } from "vue-router";

import { findUserId } from "@/api/user";

const router = useRouter();
const goToLoginPage = () => router.push("/user/login");

// 아이디 찾기 로직 ---------------------------------------
const isIdFound = ref(null);
const formData = ref({
  userName: "",
  emailId: "",
  emailDomain: "",
});

const submitFindUserId = async () => {

  // console.log(formData);
  // 비어있는 input값이 있는 경우
  if(
    formData.value.userName === '' ||
    formData.value.emailId === '' ||
    formData.value.emailDomain === ''
  ) {
    alert('회원 정보를 입력해주세요.');
    return;
  }

  // console.log(formData.value);
  await findUserId(
    formData.value,
    (response) => {
      if(response.data.userId) {
        console.log("아이디 찾기 완료!");
        foundId.value = response.data.userId;
        isIdFound.value = true;
      } else {
        // 해당 개인정보와 일치하는 아이디가 없는 경우
        isIdFound.value = false;
      }
    },
    (error) => {
      console.error(error);
    }
  )
};

// 찾은 아이디 저장
const foundId = ref("");

</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <h2 class="my-4 py-3 shadow bg-info text-white text-center rounded">
          아이디 찾기
        </h2>
      </div>
      <!-- 아이디를 찾은 후 페이지 -->
      <div v-if="isIdFound" class="col-lg-8">
        <div class="card p-4 shadow text-center">
          <p class="mb-4">가입하신 아이디는 다음과 같습니다.</p>
          <h2 class="mb-4 text-success">{{ foundId }}</h2>
          <div class="d-flex justify-content-center mb-4">
            <button class="btn btn-primary mx-2" @click="goToLoginPage">로그인 페이지로 이동</button>
          </div>
        </div>
      </div>
      <!-- 아이디 찾는 페이지 -->
      <div v-else class="col-lg-8">
        <div class="card p-4 shadow text-center">
          <p class="mb-4">아이디는 가입시 입력하신 개인정보를 통해 찾을 수 있습니다.</p>
          <p v-if="isIdFound === false" class="mb-2 text-danger">입력하신 정보를 찾을 수 없습니다.</p>
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
            <button class="btn btn-primary mx-2" @click.prevent="submitFindUserId">아이디 찾기</button>
            <button class="btn btn-secondary mx-2" @click="goToLoginPage">뒤로</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>