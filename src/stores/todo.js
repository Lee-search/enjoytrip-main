import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useTodoStore = defineStore(
  "todo",
  () => {
    const id = ref(0);
    const todos = ref([]);

    const allTodosCount = computed(() => todos.value.length);
    const completedTodosCount = computed(() => todos.value.filter((todo) => todo.completed).length);
    const activeTodosCount = computed(() => todos.value.filter((todo) => !todo.completed).length);

    const addTodo = (title) => {
      todos.value.push({ id: id.value++, title, completed: false });
    };

    const changeTodoComplete = (id) => {
      todos.value = todos.value.map((todo) =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      );
    };

    const removeTodo = (id) => {
      todos.value = todos.value.filter((todo) => todo.id !== id);
    };

    return {
      id,
      todos,
      allTodosCount,
      completedTodosCount,
      activeTodosCount,
      addTodo,
      changeTodoComplete,
      removeTodo,
    };
  },
  { persist: { storage: localStorage } }
  // { persist: { storage: sessionStorage } }
);
