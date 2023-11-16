<script setup>
import TodoHeader from "@/components/todos/TodoHeader.vue";
import TodoInput from "@/components/todos/TodoInput.vue";
import TodoList from "@/components/todos/TodoList.vue";
</script>

<template>
  <div class="container text-center mt-3">
    <div class="alert alert-warning" role="alert">오늘의 할일</div>
    <div class="row">
      <div class="col">
        <todo-header></todo-header>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <todo-input></todo-input>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <todo-list></todo-list>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
