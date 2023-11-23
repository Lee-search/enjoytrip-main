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
        // 중복 체크
        const isDuplicate = todos.value.some((todo) => todo.title === title.title);
        console.log(title)
        console.log(isDuplicate)

        if (isDuplicate) {
          alert('이미 같은 제목의 항목이 있습니다.');
          return; // 중복된 경우 함수 종료
        }
      
        // 중복이 아닌 경우 데이터 추가
        alert('여행지가 성공적으로 담겼습니다!');
        todos.value.push({
          addr1: title.addr1,
          contentid: title.contentid,
          firstimage: title.firstimage,
          title: title.title,
        });
        
      };
      // const changeTodoComplete = (id) => {
      //   todos.value = todos.value.map((todo) =>
      //     todo.id === id ? { ...todo, completed: !todo.completed } : todo
      //   );
      // };

      const removeall = () => {
        todos.value =[]
      };


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
        removeall
      };
    },
    { persist: { storage: localStorage } } //로컬스토리지에저장
    // { persist: { storage: sessionStorage } }
  );
