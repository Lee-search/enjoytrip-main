<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const selectStation = ref(null);

onMounted(() => {
  console.log(route.query.stationData1); // 첫 번째 데이터
console.log(route.query.stationData2); // 두 번째 데이터
console.log(route.query.stationData3); // 세 번째 데이터
  // route.params를 사용하여 데이터에 접근
  // if (route.params.stationData) {
  //   selectStation.value = route.params.stationData;
  // }
});
</script>

<template>
  <div>
    <h1>관광지 상세 정보</h1>
    <p>{{ selectStation }}</p>
  </div>
</template>