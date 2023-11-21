<script setup>
import { ref, computed, watch } from 'vue';
import { useRouter } from "vue-router";

import { idcheck, registration } from "@/api/user";

const router = useRouter();

const formData = ref({
  username: '',
  userid: '',
  userpwd: '',
  pwdcheck: '',
  emailid: '',
  emailDomain: '',
});

// 초기화 클릭 시 실행
const resetForm = () => {
  formData.value = {
    username: '',
    userid: '',
    userpwd: '',
    pwdcheck: '',
    emailid: '',
    emailDomain: '',
  };
};

// 아이디 중복 검사 ----------------------------------------------
// 사용자가 아이디 입력 필드의 값을 변경할 때마다 중복 검사 결과가 초기화
const isUserIdAvailable  = ref(null);
watch(() => formData.value.userid, () => {
  isUserIdAvailable.value = null;
});

// 아이디 이미 존재하는지 확인
const checkedMsgValue = computed(() => {
  if (isUserIdAvailable.value === null) {
    return '아이디 중복확인을 진행해주세요.';
  } else if (isUserIdAvailable.value === false) {
    return `${formData.value.userid}는 이미 사용 중인 아이디입니다.`;
  } else if (isUserIdAvailable.value === true) {
    return `${formData.value.userid}는 사용 가능한 아이디입니다.`;
  }
  return '';
});

const checkedMsgStatus = computed(() => {
  if (isUserIdAvailable.value === null) {
    return 'text-secondary'; // Bootstrap의 회색 텍스트 클래스
  } else if (isUserIdAvailable.value === false) {
    return 'text-danger'; // Bootstrap의 빨간색 텍스트 클래스
  } else if (isUserIdAvailable.value === true) {
    return 'text-success'; // Bootstrap의 초록색 텍스트 클래스
  }
  return '';
});

const checkUserId = async () => {
  if(formData.value.userid === '') {
    console.log("아이디 업력되지 않음..");
    return;
  }
  await idcheck(
    formData.value.userid, 
    (response) => {
      // 아이디 사용 가능
      if (response.data.count === 0) {
        isUserIdAvailable.value = true;
      } else {  // 아이디 사용 불가능
        isUserIdAvailable.value = false;
      }
    },
    (error) => {
      console.error(error);
    }
  )
};

// 회원가입 버튼 ----------------------------------------------------
const submitRegistration = async () => {
  // 비어있는 input값이 있는 경우
  if(
    formData.value.username === '' ||
    formData.value.userid === '' ||
    formData.value.userpwd === '' ||
    formData.value.pwdcheck === '' ||
    formData.value.emailid === '' ||
    formData.value.emailDomain === ''
  ) {
    alert('회원 정보를 입력해주세요.');
    return;
  }

  // 아이디 중복검사 하지 않은 경우
  if (isUserIdAvailable.value === false || isUserIdAvailable.value === null) {
    alert('아이디 중복확인이 완료되지 않았습니다.');
    return;
  }

  // 비밀번호 일치하지 않는 경우
  if (formData.value.userpwd !== formData.value.pwdcheck) {
    alert('비밀번호를 다시 확인해주세요.');
    return;
  }

  // console.log(formData.value);
  // formData 하고 memberDto가 일치하지 않음, 추기
  const tempData = {
    userName: formData.value.username,
    userId: formData.value.userid,
    userPwd: formData.value.userpwd,
    emailId: formData.value.emailid,
    emailDomain: formData.value.emailDomain,
  }

  await registration(
    tempData,
    (response) => { // 회원가입 성공
      // alert("회원 가입 완료!!!");
      router.push({ name: "user-welcome" });
    },
    (error) => {
      console.error(error);
    }
  )
};
</script>

<template>
  <div class="container vh-100">
  <div class="row justify-content-center">
    <div class="col-lg-8">
      <h2 class="my-4 py-3 shadow bg-info text-white text-center rounded">
        회원가입
      </h2>
    </div>
    <div class="col-lg-8">
      <form class="card p-4 shadow overflow-auto">
        <div class="mb-3 text-start">
          <label for="username" class="form-label">이름:</label>
          <input type="text" class="form-control" id="username" v-model="formData.username" placeholder="이름..." />
        </div>
        <div class="mb-3 text-start">
          <label for="userid" class="form-label">아이디:</label>
          <div class="row">
            <div class="col-9 col-lg-10">
              <input type="text" class="form-control" id="userid" v-model="formData.userid" placeholder="아이디..." />
            </div>
            <div class="col-3 col-lg-2">
              <button class="btn btn-primary" @click.prevent="checkUserId">중복확인</button>
            </div>
          </div>
        </div>
        <div :class="checkedMsgStatus">{{ checkedMsgValue }}</div>

        <div class="mb-3 text-start">
          <label for="userpwd" class="form-label">비밀번호:</label>
          <input type="password" class="form-control" id="userpwd" v-model="formData.userpwd" placeholder="비밀번호..." />
        </div>
        <div class="mb-3 text-start">
          <label for="pwdcheck" class="form-label">비밀번호 확인:</label>
          <input type="password" class="form-control" id="pwdcheck" v-model="formData.pwdcheck" placeholder="비밀번호 확인..." />
        </div>
        <div v-if="formData.userpwd === '' || formData.pwdcheck === ''"></div>
        <div v-else-if="formData.userpwd === formData.pwdcheck" class="text-success">비밀번호가 일치합니다.</div>
        <div v-else class="text-danger">비밀번호가 일치하지 않습니다.</div>

        <div class="mb-3 text-start">
          <label for="emailid" class="form-label">이메일:</label>
          <div class="input-group">
            <input type="text" class="form-control" id="emailid" v-model="formData.emailid" placeholder="이메일 아이디" />
            <span class="input-group-text">@</span>
            <select class="form-select" v-model="formData.emailDomain" aria-label="이메일 도메인 선택">
              <option selected >선택</option>
                <option value="ssafy.com">싸피</option>
                <option value="google.com">구글</option>
                <option value="naver.com">네이버</option>
                <option value="kakao.com">카카오</option>
            </select>
          </div>
        </div>
        <div class="text-center">
          <button type="button" class="btn btn-success mb-3" @click="submitRegistration">회원가입</button>
          <button type="button" class="btn btn-secondary ms-2 mb-3" @click="resetForm">초기화</button>
        </div>
      </form>
    </div>
  </div>
  </div>
</template>

<style scoped></style>
