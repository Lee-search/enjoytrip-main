import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";
// import TheBoardView from "../views/TheBoardView.vue";
import TheAttractionView from "@/views/TheAttractionView.vue";
import { useMemberStore } from "@/stores/member";

const onlyAuthUser = async (to, from, next) => {
  // console.log("to: ", to, "from: ", from);

  const memberStore = useMemberStore();
  const token = sessionStorage.getItem("accessToken");

  if (token && !memberStore.userInfo) {
    try {
      await memberStore.getUserInfo(token);
    } catch (error) {
      console.error('User info fetch failed', error);
      next({ name: "user-login" });
      return;
    }
  }

  if (!memberStore.isValidToken || !memberStore.userInfo) {
    next({ name: "user-login" });
    return;
  }

  // if (from.matched[0].name === 'notice' && memberStore.userInfo.userId !== 'admin') {
  //   alert("관리자 페이지 입니다!");
  //   next({ name: "notice-list" });
  //   return;
  // }

  next();
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
      path: "/attraction",  // 지역별여행지
      name: "attraction",
      component: TheAttractionView,
    },
    // {
    //   path: "/attractdetail/:stationData1/:stationData2/:stationData3",
    //   name: "attractdetail",
    //   component: AttractDetailVue,
    //   props: true,
    // },
    {
      path: "/plan",   // 나의여행계획
      name: "plan",
      component: () => import("@/views/TheTodoView.vue"),
    },
    {
      path: "/notice",  // 공지사항
      name: "notice",
      component: () => import("../views/TheNoticeView.vue"),
      redirect: { name: "notice-list" },
      children: [
        {
          path: "list",
          name: "notice-list",
          component: () => import("@/components/boards/BoardList.vue"),
          meta: { type: "notice" },
        },
        {
          path: "view/:noticeno",
          name: "notice-view",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardDetail.vue"),
          meta: { type: "notice" },
        },
        {
          path: "write",
          name: "notice-write",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardWrite.vue"),
          meta: { type: "notice" },
        },
        {
          path: "modify/:noticeno",
          name: "notice-modify",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardModify.vue"),
          meta: { type: "notice" },
        },
      ],
    },
    {
      path: "/artice",   // 여행후기공유
      name: "artice",
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
          meta: { type: "article" },
        },
        {
          path: "view/:articleno",
          name: "article-view",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardDetail.vue"),
          meta: { type: "article" },
        },
        {
          path: "write",
          name: "article-write",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardWrite.vue"),
          meta: { type: "article" },
        },
        {
          path: "modify/:articleno",
          name: "article-modify",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/boards/BoardModify.vue"),
          meta: { type: "article" },
        },
      ],
    },
  ],
});

export default router;
