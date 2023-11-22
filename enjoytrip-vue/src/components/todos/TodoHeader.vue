<template>
  <div class="container text-center">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <h2 class="my-4 py-3 shadow bg-info text-white text-center rounded">
          나의 여행 계획
        </h2>
        <p class="text-secondary">관광지 갯수: {{ todoStore.allTodosCount }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useTodoStore } from "@/stores/todo";

const todoStore = useTodoStore();
</script>

<style scoped>
/* Add additional styling if needed */
</style>