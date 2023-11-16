<script setup>
import { useTodoStore } from "@/stores/todo";

const todoStore = useTodoStore();
const { changeTodoComplete, removeTodo } = todoStore;

defineProps({ todo: Object });
</script>

<template>
  <div class="row mb-1">
    <div class="col-lg-11">
      <span :class="{ completed: todo.completed }" @click="changeTodoComplete(todo.id)">
        {{ todo.title }}
      </span>
    </div>
    <div class="col-lg-1">
      <button type="button" class="btn btn-outline-danger btn-sm" @click="removeTodo(todo.id)">
        X
      </button>
    </div>
  </div>
</template>

<style scoped>
.completed {
  text-decoration: line-through;
  font-style: italic;
  font-weight: bold;
}
</style>
