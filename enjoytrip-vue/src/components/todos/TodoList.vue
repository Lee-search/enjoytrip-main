<template>
  <div class="container mt-3">
    <table class="table table-hover">
      <thead class="thead-light">
        <tr class="text-center">
          <th scope="col">여행지</th>
          <th scope="col">타이틀</th>
          <th scope="col">여행지 주소</th>
          <th scope="col">찜목록 제거</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="todo in todoStore.todos" :key="todo.contentid" class="align-middle">
          <td class="d-flex align-items-center">
            <img
              v-if="todo.firstimage"
              :src="todo.firstimage"
              alt="여행지 이미지"
              class="img-thumbnail mr-2"
              style="width: 100px; height: 100px; object-fit: cover;"
            />
            <div v-else class="no-image-placeholder mr-2">
              <img
                src="@/assets/noimg.png"
                alt="No Image Available"
                class="img-thumbnail"
                style="width: 100px; height: 100px; object-fit: cover;"
              />
            </div>
          </td>
          <td>{{ todo.title }}</td>
          <td>{{ todo.addr1 }}</td>
          <td>
            <button type="button" class="btn btn-outline-secondary" @click="todoStore.removeTodo(todo.contentid)">
              찜목록 제거
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- 버튼들을 오른쪽 하단으로 이동 -->
    <div class="d-flex justify-content-end mt-3">
      <!-- 저장하기 버튼 -->
    

      <!-- 공유하기 버튼 -->
      <form @submit.prevent="onSubmit">
    <div class="col-auto text-center">
      <button type="submit" class="btn btn-outline-primary mb-3">
        글작성
      </button>
    </div>
  </form>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useTodoStore } from "@/stores/todo";
import { useRouter } from "vue-router";
import { registArticle } from "@/api/board";
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();
const userInfo = computed(() => memberStore.userInfo);
const router = useRouter();

const todoStore = useTodoStore();

const article = ref({
  articleNo: 0,
  subject: "", // subject 초기값은 빈 문자열로 설정
  content: "", // content 초기값은 빈 문자열로 설정
  userId: "",
  userName: "",
  hit: 0,
  registerTime: "",
});

function onSubmit() {
  // content에 로컬 스토리지에 있는 모든 내용을 집어넣기
  article.value.content = processTodos(todoStore.todos);

  // subject에 "userName이 여행경로를 공유했습니다"와 같은 내용 참조
  article.value.subject = `${userInfo.value.userName}이(가) 여행경로를 공유했습니다.`;

  // 나머지 글 작성 로직은 그대로 유지
  writeArticle();
}

function processTodos(todos) {
  let result = "";

  todos.forEach((todo, index) => {
    result += `${index + 1}번: ${todo.addr1}\n`;
  });

  return result;
}


function writeArticle() {
  console.log("글등록하자!!", article.value);
  article.value.userId = userInfo.value.userId;
  article.value.userName = userInfo.value.userName;
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

function moveList() {
  router.push({ name: "article-list" });
}
</script>

<style scoped>
/* 스타일 코드 생략 */
</style> 