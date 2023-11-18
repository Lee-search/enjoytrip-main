<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginUser = ref({
  userId: "",
  userPwd: "",
});

// 로그인 검증 후 메인이동
const login = async () => {
  await userLogin(loginUser.value);
  let token = sessionStorage.getItem("accessToken");
  console.log("1. ", token, "isLogin: ", isLogin);

  if (isLogin) {
    console.log("로그인 성공");
    getUserInfo(token);
    changeMenuState();
  }
  router.push("/");
};

// 회원가입 이동
const join = () => router.push("/user/join")
</script>

<template>
  <div class="container ">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <h2 class="my-4 py-3 shadow bg-primary text-white text-center rounded">
          로그인
        </h2>
      </div>
      <div class="col-lg-8">
        <form class="card p-4 shadow">
          <div class="mb-3 text-start">
            <label for="userid" class="form-label">아이디:</label>
            <input type="text" class="form-control" v-model="loginUser.userId" placeholder="아이디..." />
          </div>
          <div class="d-flex justify-content-end mb-3">
            <div class="form-check">
              <input class="form-check-input" type="checkbox" id="saveid" />
              <label class="form-check-label" for="saveid">아이디 저장</label>
            </div>
          </div>
          <div class="mb-3 text-start">
            <label for="userpwd" class="form-label">비밀번호:</label>
            <input type="password" class="form-control" v-model="loginUser.userPwd" @keyup.enter="login" placeholder="비밀번호..." />
          </div>
          <div class="text-center">
            <button type="button" class="btn btn-primary mb-3" @click="login">로그인</button>
            <button type="button" class="btn btn-success ms-2 mb-3" @click="join">회원가입</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
