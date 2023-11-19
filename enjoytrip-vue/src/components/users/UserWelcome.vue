<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

const goToLoginPage = () => router.push("/user/login");
const goToMainPage = () => router.push("/");
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <h2 class="my-4 py-3 shadow bg-primary text-white text-center rounded">
          회원가입 완료
        </h2>
      </div>
      <div class="col-lg-8">
        <div class="card p-4 shadow text-center">
          <h2 class="mb-4">환영합니다!</h2>
          <p class="mb-4">회원가입이 성공적으로 완료되었습니다.</p>
          <div class="d-flex justify-content-center mb-4">
            <button class="btn btn-primary mx-2" @click="goToLoginPage">로그인</button>
            <button class="btn btn-success mx-2" @click="goToMainPage">메인 페이지로 이동</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>