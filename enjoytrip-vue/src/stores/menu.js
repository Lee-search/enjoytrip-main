import { ref } from "vue";
import { defineStore } from "pinia";

// import { useMemberStore } from "@/stores/member";
// const memberStore = useMemberStore();
// const { userInfo } = memberStore;

export const useMenuStore = defineStore("menuStore", () => {
  const menuList = ref([
    { name: "회원가입", show: true, routeName: "user-join" },
    { name: "로그인", show: true, routeName: "user-login" },
    // { name: "오늘할일", show: false, routeName: "todos" },

    // { name: `${userInfo.userName}님, 안녕하세요!`, show: false, routeName: "name" },
    { name: "내정보", show: false, routeName: "user-mypage" },
    { name: "로그아웃", show: false, routeName: "user-logout" },
    // { name: "찜목록", show: false, routeName: "plan" },
  ]);

  const changeMenuState = () => {
    menuList.value = menuList.value.map((item) => ({ ...item, show: !item.show }));
  };
  return {
    menuList,
    changeMenuState,
  };
}, {
  persist: true
});
