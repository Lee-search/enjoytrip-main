<script setup>
import { useTodoStore } from "@/stores/todo";

import IconPen from "@/components/icons/IconPen.vue";
import IconSpinner from "@/components/icons/IconSpinner.vue";
import IconCheck from "@/components/icons/IconCheck.vue";

const todoStore = useTodoStore();
</script>

<template>
  <div class="container text-center">
    <div class="row">
      <div class="col">
        <div class="alert alert-primary" role="alert">
          <IconPen /> 할일 : {{ todoStore.allTodosCount }}
        </div>
      </div>
      <div class="col">
        <div class="alert alert-info" role="alert">
          <IconSpinner /> 진행 : {{ todoStore.activeTodosCount }}
        </div>
      </div>
      <div class="col">
        <div class="alert alert-danger" role="alert">
          <IconCheck /> 완료 : {{ todoStore.completedTodosCount }}
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
