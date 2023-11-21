import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useTodoStore = defineStore(
  "todo",
  () => {
    // const title = ref();
    const todos = ref([]);
    // const add1 = ref();
    // const attraction = ref([])
    const allTodosCount = computed(() => todos.value.length);  // 총목록

    // const completedTodosCount = computed(() => todos.value.filter((todo) => todo.completed).length);  해야할일 끝낸거
    // const activeTodosCount = computed(() => todos.value.filter((todo) => !todo.completed).length);   해야할목록

    const addTodo = (title) => {
      todos.value.push({
        
        addr1: title.addr1,
        contentid: title.contentid,
        firstimage: title.firstimage,
        title: title.title,
      }); //여기다가 정보담기 
    };

    // const changeTodoComplete = (id) => {
    //   todos.value = todos.value.map((todo) =>
    //     todo.id === id ? { ...todo, completed: !todo.completed } : todo
    //   );
    // };

    const removeTodo = (contentid) => {
      todos.value = todos.value.filter((todo) => todo.contentid !== contentid);
    };

    return {
      
      todos,
      allTodosCount,
      // addr1,
      // contentid,
      // firstimage,
      // title,
      // completedTodosCount,
      // activeTodosCount,
      addTodo,
      // changeTodoComplete,
      removeTodo,
    };
  },
  { persist: { storage: localStorage } } //로컬스토리지에저장
  // { persist: { storage: sessionStorage } }
);
