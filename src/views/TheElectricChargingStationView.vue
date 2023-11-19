<script setup>
import { ref, onMounted } from "vue";
import { listStations } from "@/api/estation";
import { listSido, listGugun } from "@/api/map";

import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";

// const serviceKey = import.meta.env.VITE_OPEN_API_SERVICE_KEY;
const { VITE_OPEN_API_SERVICE_KEY } = import.meta.env;

const sidoList = ref([{text: "도시선택", value: "1"}]);
const gugunList = ref([{ text: "구군선택", value: "" }]);
const chargingStations = ref([]);
const selectStation = ref({});
const selectedOption = ref("12"); // 기본값으로 "관광지" 선택
const showTable = ref(true);
const selectedStation = ref({});

const param = ref({
  serviceKey: VITE_OPEN_API_SERVICE_KEY,
  pageNo: 1,
  numOfRows: 20,
  MobileOS :"ETC",
  MobileApp :"ETC",
  areaCode:1,
  sigunguCode: 1,
  _type:"json",
  contentTypeId:12,

});



onMounted(() => {
  // getChargingStations();
  getSidoList();
});



const getSidoList = () => {
  listSido(
    ({ data }) => {
      console.log(data)
      let options = [];
      options.push({ text: "도시선택", value: "" });
      data.forEach((sido) => {
        options.push({ text: sido.sidoName, value: sido.sidoCode});
      });
      sidoList.value = options; //시도리스트에는 시도가 다 들어가잇을껄
    },
    (err) => {
      console.log(err);
    }
  );
};


const onChangeSido = (val) => {  //변경되면   list구군을 
  param.value.areaCode = val;
  listGugun(
    { sido: val },
    ({ data }) => {
      //배열을 만들어서 구군이름으로 선택보여주게 
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
  param.value.sigunguCode = val;
  console.log(param)
  getChargingStations();
};


const onChangeAttraction = () => {
  // 선택된 값에 따라 param 설정
  param.value.contentTypeId = selectedOption.value;
  console.log(param)
  getChargingStations();
  
};

const getChargingStations = () => {
  listStations(
    param.value,
    ({ data }) => {
      if (data && data.response && data.response.body) {
        const items = Array.isArray(data.response.body.items.item)
          ? data.response.body.items.item
          : [data.response.body.items.item];

        console.log(items)
        chargingStations.value = items;
        console.log(chargingStations.value)
      } else {
        console.error('Invalid data structure:', data);
      }
    },
    (err) => {
      console.log(err);
    }
  );
};




const viewStation = (station) => {
  selectStation.value = station;
  showTable.value = false;
  selectedStation.value = station;
};





</script>



<template>
  <div class="container text-center mt-3">
    <div class="alert alert-success" role="alert">관광지 정보</div>

    <div class="row mb-2">
    <div class="col d-flex flex-row-reverse">
      <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido"/>
    </div>
    <div class="col"><VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" /></div>

    <div class="col">
      <select v-model="selectedOption" @change="onChangeAttraction" class="form-select">
        <option value="12">관광지</option>
        <option value="14">문화시설</option>
        <option value="15">축제공연행사</option>
        <option value="25">여행코스</option>
        <option value="28">레포츠</option>
        <option value="32">숙박</option>
        <option value="38">쇼핑</option>
        <option value="39">음식점</option>
      </select>
    
    
    
    </div>


  </div>



    <!-- 카카오맵 표시 -->
    

    <!-- 관광지 정보 테이블 -->
    <div v-if="!showTable">
      <img
        :src="selectedStation.firstimage"
        style="max-width: 100%; max-height: 100vh;"
      />
    </div>
    <div v-else>
      <table class="table table-hover">
        <thead>
          <tr class="text-center">
            <th scope="col">대표이미지</th>
            <th scope="col">관광지명</th>
            <th scope="col">주소</th>
          </tr>
        </thead>
        <tbody>
          <!-- 관광지 정보 반복 출력 -->
          <tr class="text-center" v-for="station in chargingStations" :key="station.title">
            <td>
              <img :src="station.firstimage" style="max-width: 100px; max-height: 100px;" />
            </td>
            <td>{{ station.title }}</td>
            <td>{{ station.addr1 }} {{ station.addr2 }}</td>
            <td>
              <button @click="viewStation(station)">여행지 담기</button>
            </td>
            <router-link :to="{ name: 'attractdetail', params: { station: JSON.stringify(station) } }" class="nav-link button-like">
              <button>자세히보기</button>
            </router-link>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <VKakaoMap :stations="chargingStations" :selectStation="selectStation" />
</template>

<style>
mark.purple {
  background: linear-gradient(to top, #c354ff 20%, transparent 30%);
}
</style>
