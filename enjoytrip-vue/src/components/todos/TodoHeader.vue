<template>
  <div class="container text-center">
    <div class="row">
      <div class="col-lg-8 mx-auto"> <!-- Set col-lg-8 and mx-auto for centering -->
        <div class="alert alert-primary" role="alert">
          <IconPen /> 여행지갯수: {{ todoStore.allTodosCount }}
        </div>
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