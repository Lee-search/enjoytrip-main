import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";
import TheElectricChargingStationView from "@/views/TheElectricChargingStationView.vue";
// import TheBoardView from "../views/TheBoardView.vue";

// import { storeToRefs } from "pinia";
// import { useMemberStore } from "@/stores/member";

// const memberStore = useMemberStore();
// const { userInfo, isValidToken } = storeToRefs(memberStore);
// const { getUserInfo } = useMemberStore;

// const onlyAuthUser = async (to, from, next) => {
//   let token = sessionStorage.getItem("accessToken");
//   console.log("로그인 처리 전", userInfo, token);

//   if (userInfo != null && token) {
//     console.log("토큰 유효성 체크하러 가자!!!!");
//     await getUserInfo(token);
//   }
//   if (!isValidToken || userInfo === null) {
//     alert("로그인이 필요한 페이지입니다..");
//     // next({ name: "login" });
//     router.push({ name: "user-login" });
//   } else {
//     console.log("로그인 했다!!!!!!!!!!!!!.");
//     next();
//   }
// };

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
          component: () => import("@/components/users/UserMyPage.vue"),
        },
        // {
        //   path: "modify/:userid",
        //   name: "user-modify",
        //   component: () => import("@/components/users/UserModify.vue"),
        // },
      ],
    },
    {
      path: "/board",
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
          component: () => import("@/components/boards/BoardDetail.vue"),
        },
        {
          path: "write",
          name: "article-write",
          component: () => import("@/components/boards/BoardWrite.vue"),
        },
        {
          path: "modify/:articleno",
          name: "article-modify",
          component: () => import("@/components/boards/BoardModify.vue"),
        },
      ],
    },
    {
      path: "/estations",
      name: "estations",
      // beforeEnter: onlyAuthUser,
      component: TheElectricChargingStationView,
    },
    {
      path: "/todos",
      name: "todos",
      component: () => import("@/views/TheTodoView.vue"),
    },
  ],
});

export default router;
