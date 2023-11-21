<script setup>
import { ref, onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { listArticle } from "@/api/board.js";
import { listNotice } from "@/api/notice";

import VSelect from "@/components/common/VSelect.vue";
import BoardListItem from "@/components/boards/item/BoardListItem.vue";
import VPageNavigation from "@/components/common/VPageNavigation.vue";


// route.meta를 사용하여 현재 라우트의 메타 데이터에 접근
const router = useRouter();
const route = useRoute();
const routeType = computed(() => route.meta.type);

const selectOption = ref([
  { text: "검색조건", value: "" },
  { text: "글번호", value: "article_no" },
  { text: "제목", value: "subject" },
  { text: "작성자아이디", value: "user_id" },
]);

const postingList = ref([]);  // 서로 다른 게시판에서 통일되게 사용할 이름 (articles, notices)
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;

// 검색 파라미터 설정
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: "",
  word: "",
});

onMounted(() => {
  console.log("현재 라우트 타입:", routeType.value);
  if (routeType.value === 'notice') {
    getNoticeList();
  } else {
    getArticleList();
  }
});

const changeKey = (val) => {
  // console.log("BoarList에서 선택한 조건 : " + val);
  param.value.key = val;
};

const onPageChange = (val) => {
  // console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = val;
  param.value.pgno = val;
  if (routeType === 'notice') {
    getNoticeList();
  } else {
    getArticleList();
  }
};

const moveWrite = () => {
  if (routeType === 'notice') {
    router.push({ name: "notice-write" });
  } else {
    router.push({ name: "article-write" });
  }
};

// 공지사항 게시판 ---------------------------------------------------
const getNoticeList = () => {
  // console.log("서버에서 글목록 얻어옴", param.value);
  listNotice(
    param.value,
    ({ data }) => {
      postingList.value = data.articles;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    }
  );
};

// 여행 공유 게시판 ---------------------------------------------------
const getArticleList = () => {
  // console.log("서버에서 글목록 얻어옴", param.value);
  listArticle(
    param.value,
    ({ data }) => {
      postingList.value = data.articles;
      currentPage.value = data.currentPage;
      totalPage.value = data.totalPageCount;
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <div class="container">
    <div v-if="routeType === 'article'" class="row justify-content-center">
      <div class="col-lg-6">
        <h2 class="my-4 py-3 shadow bg-info text-white text-center rounded">
          여행 공유 게시판
        </h2>
      </div>
      <div class="col-lg-12">
        <div class="row align-self-center mb-2">
          <div class="col-4 col-md-2 text-start">
            <button type="button" class="btn btn-outline-primary btn-sm" @click="moveWrite">
              글쓰기
            </button>
          </div>
          <div class="col-8 col-md offset-6">
            <form class="d-flex">
              <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
              <div class="input-group input-group-sm ms-1">
                <input
                  type="text"
                  class="form-control"
                  v-model="param.word"
                  placeholder="검색어..."
                />
                <button class="btn btn-dark" type="button" @click="getArticleList">검색</button>
              </div>
            </form>
          </div>
        </div>
        <table class="table table-hover">
          <thead>
            <tr class="text-center">
              <th scope="col">글번호</th>
              <th scope="col">제목</th>
              <th scope="col">작성자</th>
              <th scope="col">조회수</th>
              <th scope="col">작성일</th>
            </tr>
          </thead>
          <tbody>
            <BoardListItem
              v-for="article in postingList"
              :key="article.articleNo"
              :article="article"
            ></BoardListItem>
          </tbody>
        </table>
      </div>
      <VPageNavigation
        :current-page="currentPage"
        :total-page="totalPage"
        @pageChange="onPageChange"
      ></VPageNavigation>
    </div>

    <div v-if="routeType === 'notice'" class="row justify-content-center">
      <div class="col-lg-6">
        <h2 class="my-4 py-3 shadow bg-danger text-white text-center rounded">
          공지 게시판
        </h2>
      </div>
      <div class="col-lg-12">
        <div class="row align-self-center mb-2">
          <div class="col-4 col-md-2 text-start">
            <button type="button" class="btn btn-outline-primary btn-sm" @click="moveWrite">
              글쓰기
            </button>
          </div>
          <div class="col-8 col-md offset-6">
            <form class="d-flex">
              <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
              <div class="input-group input-group-sm ms-1">
                <input
                  type="text"
                  class="form-control"
                  v-model="param.word"
                  placeholder="검색어..."
                />
                <button class="btn btn-dark" type="button" @click="getNoticeList">검색</button>
              </div>
            </form>
          </div>
        </div>
        <table class="table table-hover">
          <thead>
            <tr class="text-center">
              <th scope="col">글번호</th>
              <th scope="col">제목</th>
              <th scope="col">작성자</th>
              <th scope="col">조회수</th>
              <th scope="col">작성일</th>
            </tr>
          </thead>
          <tbody>
            <BoardListItem
              v-for="notice in postingList"
              :key="notice.articleNo"
              :article="notice"
            ></BoardListItem>
          </tbody>
        </table>
      </div>
      <VPageNavigation
        :current-page="currentPage"
        :total-page="totalPage"
        @pageChange="onPageChange"
      ></VPageNavigation>
    </div>
  </div>
</template>

<style scoped></style>
