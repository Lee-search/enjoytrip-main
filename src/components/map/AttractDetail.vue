<template>
  <div>
    <h1>Attract Detail</h1>
    <p v-if="station">{{ station }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const station = ref(null);

onMounted(() => {
  if (route.params.station) {
    try {
      station.value = JSON.parse(route.params.station);
      console.log(station)
    } catch (error) {
      console.error('Error parsing station JSON:', error);
    }
  }
});
</script>