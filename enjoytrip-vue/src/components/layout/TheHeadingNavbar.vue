<script setup>
import { useMenuStore } from "@/stores/menu";
import { storeToRefs } from "pinia";

const menuStore = useMenuStore();

// 반응형을 유지하면서 스토어에서 속성을 추출하려면, storeToRefs()를 사용
// https://pinia.vuejs.kr/core-concepts/
const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;

import { useMemberStore } from "@/stores/member";
const memberStore = useMemberStore();

const logout = () => {
  console.log("로그아웃!!!!");
  memberStore.userLogout();
  changeMenuState();
};
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light shadow sticky-top">
    <div class="container my-2">
      <router-link :to="{ name: 'main' }" class="navbar-brand">
        <img src="@/assets/logo.png" class="rounded mx-auto d-block" alt="..." width="80" />
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarScroll"
        aria-controls="navbarScroll"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarScroll">
        <ul class="navbar-nav my-2 my-lg-0" > 
          <li class="nav-item">
            <router-link :to="{ name : 'attraction' }" class="nav-link">지역별여행지</router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name : 'plan' }" class="nav-link">나의여행계획</router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name : 'notice' }" class="nav-link">공지사항</router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'artice' }" class="nav-link">공유게시판</router-link>
          </li>
        </ul>
        <!-- <form class="d-flex" role="search">
          <input
            class="form-control me-2"
            type="search"
            placeholder="검색..."
            aria-label="Search"
          />
          <button class="btn btn-outline-success" type="button">search</button>
        </form> -->
        <ul class="navbar-nav ms-auto my-2 my-lg-0" >
          <template v-for="menu in menuList" :key="menu.routeName">
            <template v-if="menu.show">
              <template v-if="menu.routeName === 'user-logout'">
                <li class="nav-item">
                  <router-link to="/" @click.prevent="logout" class="nav-link">{{
                    menu.name
                  }}</router-link>
                </li>
              </template>
              <template v-else>
                <li class="nav-item">
                  <router-link :to="{ name: menu.routeName }" class="nav-link">{{
                    menu.name
                  }}</router-link>
                </li>
              </template>
            </template>
          </template>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
