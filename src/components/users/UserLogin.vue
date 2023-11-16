<script setup>
import { useMenuStore } from "@/stores/menu";

// const menuStore = useMenuStore();

const { changeMenuState } = useMenuStore();

const login = () => {
  console.log("login successful !!!");
  changeMenuState();
};
</script>

<template>
  <div><button type="button" @click="login">로그인</button></div>
</template>

<style scoped></style>
