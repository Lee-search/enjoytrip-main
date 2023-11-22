<script setup></script>

<!--로그인, 회원가입, 마이페이지, 아이디/비밀번호 찾기, 웰컴 페이지 -->
<template>
  <div class="full-screen-container bg-image">
    <router-view></router-view>
  </div>
</template>

<style scoped>
.bg-image {
  background-image: url('@/assets/img/background.png');
  background-size: cover;
  background-position: center;
  position: relative;
  /* 페이드 아웃 효과를 위한 그림자 설정 */
  box-shadow: inset 0 0 50px 50px rgba(255, 255, 255, 1); 
}

.full-screen-container {
  height: 100vh;
  width: 100%;
  margin: 0;
  padding: 0;
  text-align: center;
}
</style>