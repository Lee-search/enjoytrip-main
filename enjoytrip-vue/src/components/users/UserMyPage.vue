<script setup>
import { ref } from "vue";

// 내정보 데이터 임시
// session 토큰 보내서 출력하도록 해야함
const userInfo = ref({
  userid: "SSAFY",
  username: "김싸피",
  email: "ssafy@ssafy.com",
});
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
        <div class="card mt-3 m-auto" style="max-width: 700px">
          <div class="row g-0">
            <div class="col-md-4">
              <img
                src="https://source.unsplash.com/random/250x250/?food"
                class="img-fluid rounded mt-4"
                alt="..."
              />
            </div>
            <div class="col-md-8">
              <div class="card-body text-start">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">아이디: {{ userInfo.userid }}</li>
                  <li class="list-group-item">이름: {{ userInfo.username }}</li>
                  <li class="list-group-item">이메일: {{ userInfo.email }}</li>
                </ul>
              </div>
              <div class="d-flex justify-content-center mb-4">
                <button class="btn btn-primary mx-2" @click="goToLoginPage">수정</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>