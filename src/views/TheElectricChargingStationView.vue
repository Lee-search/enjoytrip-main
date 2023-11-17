<script setup>
import { ref, onMounted } from "vue";
import { listStations } from "@/api/estation";
import { listSido, listGugun } from "@/api/map";

import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";

// const serviceKey = import.meta.env.VITE_OPEN_API_SERVICE_KEY;
const { VITE_OPEN_API_SERVICE_KEY } = import.meta.env;

const sidoList = ref([]);
const gugunList = ref([{ text: "구군선택", value: "" }]);
const chargingStations = ref([]);
const selectStation = ref({});

const param = ref({
  serviceKey: VITE_OPEN_API_SERVICE_KEY,
  pageNo: 1,
  numOfRows: 20,
  zscode: 0,
});

onMounted(() => {
  // getChargingStations();
  getSidoList();
});

const getSidoList = () => {
  listSido(
    ({ data }) => {
      let options = [];
      options.push({ text: "시도선택", value: "" });
      data.forEach((sido) => {
        options.push({ text: sido.sidoName, value: sido.sidoCode });
      });
      sidoList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};

const onChangeSido = (val) => {
  listGugun(
    { sido: val },
    ({ data }) => {
      let options = [];
      options.push({ text: "구군선택", value: "" });
      data.forEach((gugun) => {
        options.push({ text: gugun.gugunName, value: gugun.gugunCode });
      });
      gugunList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};

const onChangeGugun = (val) => {
  param.value.zscode = val;
  getChargingStations();
};

const getChargingStations = () => {
  listStations(
    param.value,
    ({ data }) => {
      chargingStations.value = data.items[0].item;
    },
    (err) => {
      console.log(err);
    }
  );
};



const viewStation = (station) => {
  selectStation.value = station;
};
</script>




<template>
  <div class="container text-center mt-3">
    <div class="alert alert-success" role="alert">전기차 충전소</div>
    <div class="row mb-2">
      <div class="col d-flex flex-row-reverse">
        <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />
      </div>
      <div class="col"><VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" /></div>
    </div>
    <VKakaoMap :stations="chargingStations" :selectStation="selectStation" />




    <!-- <table class="table table-hover">
      <thead>
        <tr class="text-center">
          <th scope="col">시도</th>
          <th scope="col">구군</th>
          <th scope="col">관광지명</th>
          <th scope="col">주소</th>
          <th scope="col">위도</th>
          <th scope="col">경도</th>
        </tr>
      </thead>

      <tbody>
        <tr
          class="text-center"
          v-for="station in chargingStations"
          :key="station.statId + station.chgerId"
          @click="viewStation(station)"
        >
          <th>{{ station.statNm }}</th>
          <td>{{ station.statId }}</td>
          <td>{{ station.stat }}</td>
          <td>{{ station.addr }}</td>
          <td>{{ station.lat }}</td>
          <td>{{ station.lng }}</td>
        </tr>
      </tbody>
    </table> -->

    <table class="table table-striped">
      <thead>
        <tr>
          <th>대표이미지</th>
          <th>관광지명</th>
          <th>주소</th>
          <th>위도</th>
          <th>경도</th>
        </tr>
      </thead>
      <tbody id="trip-list">
        <tr
        class="text-center"
          v-for="markerItem in chargingStations"
          :key="markerItem.statId + markerItem.chgerId"
          @click="viewStation(markerItem)"
        >
          <td><img :src="markerItem.firstimage" /></td>
          <td>{{ markerItem.title }}</td>
          <td>{{ markerItem.addr1 }} {{ markerItem.addr2 }}</td>
          <td>{{ markerItem.mapy }}</td>
          <td>{{ markerItem.mapx }}</td>
        </tr>
      </tbody>
    </table>



  </div>
</template>

<style>
mark.purple {
  background: linear-gradient(to top, #c354ff 20%, transparent 30%);
}
</style>
