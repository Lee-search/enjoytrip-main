<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import BoardFormItem from "./item/BoardFormItem.vue";

const route = useRoute();
const routeType = computed(() => route.name === 'notice-write' ? 'notice' : 'article');
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-4 py-3 shadow bg-info text-white text-center rounded">
          글쓰기
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <!-- 바인딩 태그로 routeType 전달-->
        <BoardFormItem :routeType="routeType" type="regist" />
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
