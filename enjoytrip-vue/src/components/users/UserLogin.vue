<script setup>
import { ref, onMounted } from "vue";
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

  if (isLogin) {

    if (saveId.value) { // 체크박스 활성화 시, 아이디 저장
      setCookie('userId', loginUser.value.userId, 7); // 7일 동안 저장
    } else {  // 쿠키에서 아이디 제거
      setCookie('userId', '', -1);
    }

    console.log("로그인 성공");
    getUserInfo(token);
    changeMenuState();

    router.push("/");
  } else {
    alert("아이디와 비밀번호를 확인해주세요.");
  }
  
};

// 쿠키를 통해 아이디 저장, 저장되어있으면 확인 후 form을 채워넣도록 함
const saveId = ref(false);
const setCookie = (name, value, days) => {
  let expires = "";
  if (days) {
    const date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    expires = "; expires=" + date.toUTCString();
  }
  // "name=value; expires=date; path=/";
  document.cookie = `${name}=${value || ""}${expires}; path=/`;
};

const getCookie = (name) => {
  let value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
  return value ? decodeURIComponent(value[2]) : null;
};

onMounted(() => {
  const savedUserId = getCookie('userId');
  if (savedUserId) {
    loginUser.value.userId = savedUserId;
    saveId.value = true;
  }
});


// 회원가입 이동
const join = () => router.push("/user/join");
// 찾기 이동
const find = (path) => router.push(`/user/find/${path}`);

</script>

<template>
  <div class="container">
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
              <input class="form-check-input" type="checkbox" id="saveid" v-model="saveId"/>
              <label class="form-check-label" for="saveId">아이디 저장</label>
            </div>
          </div>
          <div class="mb-4 text-start">
            <label for="userpwd" class="form-label">비밀번호:</label>
            <input type="password" class="form-control" v-model="loginUser.userPwd" @keyup.enter="login" placeholder="비밀번호..." />
          </div>
          <div class="mb-3 text-center">
            <button type="button" class="btn btn-primary" @click="login">로그인</button>
            <button type="button" class="btn btn-success ms-2" @click="join">회원가입</button>
          </div>
          <div class="d-flex justify-content-end">
            <div class="text-center">
              <button type="button" class="btn btn-link" @click="find('id')">아이디 찾기</button>
              <button type="button" class="btn btn-link" @click="find('pwd')">비밀번호 찾기</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
