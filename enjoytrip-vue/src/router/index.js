import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";
import TheElectricChargingStationView from "@/views/TheElectricChargingStationView.vue";
// import TheBoardView from "../views/TheBoardView.vue";
import TheAttractionView from "@/views/TheAttractionView.vue";

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = sessionStorage.getItem("accessToken");

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
    next({ name: "user-login" });
  } else {
    next();
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: TheMainView,
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/TheUserView.vue"),
      children: [
        {
          path: "login",
          name: "user-login",
          component: () => import("@/components/users/UserLogin.vue"),
        },
        {
          path: "join",
          name: "user-join",
          component: () => import("@/components/users/UserRegister.vue"),
        },
        {
          path: "mypage",
          name: "user-mypage",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/users/UserMyPage.vue"),
        },
        // {
        //   path: "modify/:userid",
        //   name: "user-modify",
        //   component: () => import("@/components/users/UserModify.vue"),
        // },
        {
          path: "welcome",
          name: "user-welcome",
          component: () => import("@/components/users/UserWelcome.vue"),
        },
        {
          path: "find/id",
          name: "user-find-id",
          component: () => import("@/components/users/UserFindId.vue"),
        },
        {
          path: "find/pwd",
          name: "user-find-pwd",
          component: () => import("@/components/users/UserFindPwd.vue"),
        },
      ],
    },
    {
      path: "/announce",  // 공지사항
      name: "announce",
      component: TheElectricChargingStationView,
    },
    {
      path: "/attraction",  // 지역별여행지
      name: "attraction",
      component: TheAttractionView,
    },
    {
      path: "/plan",   // 나의여행계획
      name: "plan",
      component: TheElectricChargingStationView,
    },
    {
      path: "/board",   // 여행후기공유
      name: "board",
      // component: TheBoardView,
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/TheBoardView.vue"),
      redirect: { name: "article-list" },
      children: [
        {
          path: "list",
          name: "article-list",
          component: () => import("@/components/boards/BoardList.vue"),
        },
        {
          path: "view/:articleno",
          name: "article-view",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardDetail.vue"),
        },
        {
          path: "write",
          name: "article-write",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardWrite.vue"),
        },
        {
          path: "modify/:articleno",
          name: "article-modify",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardModify.vue"),
        },
      ],
    },
    {
      path: "/estations",   // (예제)전기차
      name: "estations",
      component: TheElectricChargingStationView,
    },
  ],
});

export default router;
