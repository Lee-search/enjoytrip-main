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
      <button type="button" class="btn btn-primary" @click="saveData">
        저장하기
      </button>

      <!-- 공유하기 버튼 -->
      <button type="button" class="btn btn-success ml-2" @click="writeNew">
        공유하기
      </button>
    </div>
  </div>
</template>
















<script setup>
import { useTodoStore } from "@/stores/todo";

const todoStore = useTodoStore();

// 저장하기 버튼 클릭 시 실행되는 함수
const saveData = () => {
  // 저장 로직을 여기에 추가
  console.log("저장하기 버튼이 클릭되었습니다.");
};

// 공유하기 버튼 클릭 시 실행되는 함수
const writeNew = () => {
  // 공유하기 로직을 여기에 추가
  console.log("공유하기 버튼이 클릭되었습니다.");
};
</script>
<style scoped>
/* Set a fixed size for images */
img.img-thumbnail {
  width: 100px;
  height: 100px;
  object-fit: cover;
}

.no-image-placeholder img {
  width: 100px;
  height: 100px;
  object-fit: cover;
}

/* Set a fixed height for table rows */


</style>