<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle, detailComments, registComment, deleteComment } from "@/api/board";

import { useMemberStore } from "@/stores/member";
const memberStore = useMemberStore();
const { userInfo } = memberStore;

const route = useRoute();
const router = useRouter();

const { articleno } = route.params;

const article = ref({});
// const article = ref({
//   articleNo: 0,
//   subject: "",
//   content: "",
//   userId: "",
//   userName: "",
//   hit: 0,
//   registerTime: "",
// });

const comments = ref([]);
// comments[0] = {
//   commentId: 1,
//   articleNo: 251,
//   userId: 'ssafy',
//   userName: '김싸피',
//   commentText: '댓글 테스트',
//   commentTime: "2023-11-19 21:56:53"
// }

onMounted(async () => {
  await getArticle();
  await getComments();
  // console.log("Article: ", article.value.userId);
  // console.log("Storage: ", userInfo.userId);
  // console.log(comments);
});

const getArticle = async () => {
  console.log(articleno + "번 글 조회");
  await detailArticle(
    articleno,
    ({ data }) => {
      article.value = data;
      // console.log(article.value)
    },
    (error) => {
      console.log(error);
    }
  );
};

const getComments = async () => {
  console.log(articleno + "번의 댓글 조회");
  await detailComments (
    articleno,
    ({ data }) => {
      comments.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};

function moveList() {
  router.push({ name: "article-list" });
}

// 글 수정 ---------------------------------------
function moveModify() {
  router.push({ name: "article-modify", params: { articleno } });
}

// 글 삭제 ---------------------------------------
function onDeleteArticle() {
  // const { articleno } = route.params;
  console.log(articleno + "번글 삭제하러 가자!!!");
  deleteArticle(
    articleno,
    (response) => {
      if (response.status == 200) moveList();
    },
    (error) => {
      console.log(error);
    }
  );
}

// 댓글 쓰기 ------------------------------------
const newComment = ref({
  articleNo: articleno,
  userId: userInfo.userId,
  userName: userInfo.userName,
  commentText: "",
});

const onSubmitComment = async () => {
  // console.log(newComment);
  await registComment(
    newComment.value,
    (response) => {
      // let msg = "댓글 처리시 문제 발생했습니다.";
      // if (response.status == 201) msg = "댓글 등록이 완료되었습니다.";
    },
    (error) => console.log(error)
  );
  await getComments();
  newComment.value.commentText = "";
};

const onDeleteComment = async (commentId, userId) => {
  // console.log("댓글을 작성한 사람: ", userId);
  // console.log('댓글 지우려고 하는 사람: ', userInfo.userId);
  if (userInfo.userId !== 'admin' && userId !== userInfo.userId) { 
    alert("댓글 작성자를 확인해주세요!");
    return;
  }
  
  await deleteComment(
    commentId,
    (response) => {
      // let msg = "댓글 처리시 문제 발생했습니다.";
      // if (response.status == 201) msg = "댓글 삭제가 완료되었습니다.";
    },
    (error) => console.log(error)
  );
  await getComments();
};
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-4 py-3 shadow bg-primary text-white text-center rounded">
          - {{ article.articleNo }}번 글 -
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="row my-2">
          <h2 class="px-5">{{ article.subject }}</h2>
        </div>
        <div class="row text-start ">
          <div class="col-md-8">
            <div class="clearfix align-content-center">
              <img
                class="avatar me-2 float-md-start bg-light p-2"
                src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
              />
              <p>
                <span class="fw-bold">작성자: {{ article.userName }}</span> <br />
                <span class="text-secondary fw-light">{{ article.registerTime }}</span>
              </p>
            </div>
          </div>
          <div class="col-md-4 align-self-center text-end">
            <p>
              <span class="text-secondary">조회: {{ article.hit }}</span> <br />
              <span class="text-secondary fw-light">댓글: {{ comments.length }}</span>
            </p>
          </div>

          <div class="divider mb-3 border"></div>
          <div class="text-secondary">
            {{ article.content }}
          </div>
      
          <div class="divider mb-3 mt-3"></div>
          <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-outline-primary mb-3" @click="moveList">
              글목록
            </button>
            <!-- 글작성자와 로그인 유저가 일치 or admin 일때만 변경 가능 -->
            <button 
              type="button" 
              class="btn btn-outline-success mb-3 ms-1" 
              @click="moveModify"
              v-show="article.userId === userInfo.userId || userInfo.userId === 'admin'">
              글수정
            </button>
            <button 
              type="button"
              class="btn btn-outline-danger mb-3 ms-1"
              @click="onDeleteArticle"
              v-show="article.userId === userInfo.userId || userInfo.userId === 'admin'">
              글삭제
            </button>
          </div>
          <div class="divider mb-3"></div>
          <div class="card mb-4">
            <div class="card-body">
              <h5 class="card-title">댓글 : {{ comments.length }}</h5>
              <ul class="list-group list-group-flush">
                <li class="list-group-item" v-for="comment in comments" :key="comment.id">
                  <p class="mb-1"><strong>작성자:</strong> {{ comment.userName }}</p>
                  <p class="mb-1">{{ comment.commentText }}</p>
                  <div class="d-flex justify-content-between align-items-center">
                    <small class="text-muted">{{ comment.commentTime }}</small>
                    <button class="btn btn-sm btn-outline-secondary" @click="onDeleteComment(comment.commentId, comment.userId)">삭제</button>
                  </div>
                </li>
              </ul>
              <!-- 댓글 작성 -->
              <div class="mt-2 mb-3">
                <label for="commentText" class="form-label">댓글 작성</label>
                <textarea class="form-control" id="commentText" rows="3" v-model="newComment.commentText"></textarea>
              </div>
              <div class="text-end">
                <button type="button" class="btn btn-primary" @click="onSubmitComment">쓰기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
