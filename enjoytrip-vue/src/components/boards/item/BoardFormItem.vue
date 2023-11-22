<script setup>
import { ref, watch, computed, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { registArticle, getModifyArticle, modifyArticle } from "@/api/board";
import { registNotice, getModifyNotice, modifyNotice } from "@/api/notice";

import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();
// const { userInfo } = memberStore;
const userInfo = computed(() => memberStore.userInfo);

const router = useRouter();
const route = useRoute();

const props = defineProps({
  type: String,
  routeType: String
});

onMounted(() => {
  console.log("BoardFormItem: ", props.routeType);
})

const article = ref({
  articleNo: 0,
  subject: "",
  content: "",
  userId: "",
  userName: "",
  hit: 0,
  registerTime: "",
});

// 게시글 수정하기  -------------------------------------------
if (props.type === "modify") {
  let { articleno } = route.params;
  // console.log(articleno + "번글 수정");
  if (props.routeType === 'notice') {

    // if(userInfo.value.userId !== 'admin') {
    //   alert("해당 게시판은 'admin' 유저만 접근 가능합니다.");
    //   return;
    // }

    getModifyNotice(
      articleno,
      ({ data }) => {
        article.value = data;
      },
      (error) => {
        console.log(error);
      }
    )
  } else {
    getModifyArticle(
      articleno,
      ({ data }) => {
        article.value = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}

// 입력받은 제목과 내용 검증 -----------------------------------
const subjectErrMsg = ref("");
const contentErrMsg = ref("");
watch(
  () => article.value.subject,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 30) {
      subjectErrMsg.value = "제목을 확인해 주세요!!!";
    } else subjectErrMsg.value = "";
  },
  { immediate: true }
);
watch(
  () => article.value.content,
  (value) => {
    let len = value.length;
    if (len == 0 || len > 500) {
      contentErrMsg.value = "내용을 확인해 주세요!!!";
    } else contentErrMsg.value = "";
  },
  { immediate: true }
);

// 글 작성 or 글 수정 클릭
function onSubmit() {
  // event.preventDefault();

  if (subjectErrMsg.value) {
    alert(subjectErrMsg.value);
  } else if (contentErrMsg.value) {
    alert(contentErrMsg.value);
  } else {
    props.type === "regist" ? writeArticle() : updateArticle();
  }
}

function writeArticle() {
  console.log("글등록하자!!", article.value);
  article.value.userId = userInfo.value.userId;
  article.value.userName = userInfo.value.userName;

  if (props.routeType === 'notice') {
    registNotice(
      article.value,
      (response) => {
        let msg = "글등록 처리시 문제 발생했습니다.";
        if (response.status == 201) msg = "글등록이 완료되었습니다.";
        alert(msg);
        moveList();
      },
      (error) => console.log(error)
    );
  } else {
    registArticle(
      article.value,
      (response) => {
        let msg = "글등록 처리시 문제 발생했습니다.";
        if (response.status == 201) msg = "글등록이 완료되었습니다.";
        alert(msg);
        moveList();
      },
      (error) => console.log(error)
    );
  }
}
function updateArticle() {
  console.log(article.value.articleNo + "번글 수정하자!!", article.value);
  if(props.routeType === 'notice') {
    modifyNotice(
      article.value,
      (response) => {
        let msg = "글수정 처리시 문제 발생했습니다.";
        if (response.status == 200) msg = "글정보 수정이 완료되었습니다.";
        alert(msg);
        moveList();
        // router.push({ name: "article-view" });
        // router.push(`/board/view/${article.value.articleNo}`);
      },
      (error) => console.log(error)
    );
  } else {
    modifyArticle(
      article.value,
      (response) => {
        let msg = "글수정 처리시 문제 발생했습니다.";
        if (response.status == 200) msg = "글정보 수정이 완료되었습니다.";
        alert(msg);
        moveList();
        // router.push({ name: "article-view" });
        // router.push(`/board/view/${article.value.articleNo}`);
      },
      (error) => console.log(error)
    );
  }
}

function moveList() {
  if(props.routeType === 'notice') {
    router.push({ name: "notice-list" });
  } else {
    router.push({ name: "article-list" });
  }
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <div class="mb-3">
      <label for="subject" class="form-label">제목 : </label>
      <input type="text" class="form-control" v-model="article.subject" placeholder="제목..." />
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">내용 : </label>
      <textarea class="form-control" v-model="article.content" rows="10"></textarea>
    </div>
    <div class="col-auto text-center">
      <button type="submit" class="btn btn-outline-primary mb-3" v-if="type === 'regist'">
        글작성
      </button>
      <button type="submit" class="btn btn-outline-success mb-3" v-else>글수정</button>
      <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="moveList">
        목록으로이동...
      </button>
    </div>
  </form>
</template>

<style scoped></style>
