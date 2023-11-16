<script setup>
import { ref } from "vue";
import { useTodoStore } from "@/stores/todo";

const todoStore = useTodoStore();

const title = ref("");

const addTodo = () => {
  if (title.value.length > 0) {
    todoStore.addTodo(title.value);
    title.value = "";
  }
};
</script>

<template>
  <div class="container text-center">
    <div class="row">
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          v-model="title"
          @keypress.enter="addTodo"
          placeholder="할일 추가 ..."
        />
        <button class="btn btn-outline-secondary" type="button" @click="addTodo">추가</button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
