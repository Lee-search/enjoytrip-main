<script setup>
import { ref, onMounted } from "vue";
import { listStations } from "@/api/estation";
import { listSido, listGugun,getAttractionDetail } from "@/api/map";
import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";
import { useTodoStore } from "@/stores/todo";
const todoStore = useTodoStore();





// const serviceKey = import.meta.env.VITE_OPEN_API_SERVICE_KEY;
const { VITE_OPEN_API_SERVICE_KEY } = import.meta.env;

const sidoList = ref([{text: "도시선택", value: "1"}]);
const gugunList = ref([{ text: "구군선택", value: "1" }]);
const chargingStations = ref([]);
const selectStation = ref({});
const selectedOption = ref("12"); // 기본값으로 "관광지" 선택
const showTable = ref(true);
const detatilattract=ref();

const temp=ref();

// const wishList = reactive([]);  //장바구니 리스트


const selectOption = (option) => {
  selectedOption.value = option;
  // 선택된 옵션에 따라 원하는 동작 수행
  
  onChangeAttraction();
};


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
  getChargingStations();
  getSidoList();
});


// const productsList = () => {
//   const wishListNames = wishList.value.map((item) => item.name);
//   products.value.forEach((item) => {
//     store.setAmount(item.price);
//     item.price = store.formattedAmount;
//     item.isFavorite = !item.isFavorite && wishListNames.includes(item.name) ?  true : false;
//   });
// };



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




//리스트 목록에 추가




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

        console.log(items)  //item cotents id가 존재함
        chargingStations.value = items;
        console.log(chargingStations.value) //여기에도 cotentid가존재함
        // chargingStations.value.forEach(station => {
        // station.contentid = items.contentid;
        // });
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



const getdetail = (contentid) => {
  // 관광지 상세 정보를 가져올 때 필요한 파라미터 설정
  console.log(contentid)
  const detailParam = {
    contentId: contentid,
    // 기타 필요한 파라미터도 추가할 수 있습니다
  };

  // getAttractionDetail 함수 호출
  getAttractionDetail(
    detailParam,
    ({ data }) => {
      // 성공한 경우
      if (data) {
        // 가져온 데이터를 활용하여 원하는 작업 수행
        detatilattract.value = data.attraction.overview;
      } else {
        console.error('Invalid data structure:', data);
      }
    },
    (err) => {
      // 실패한 경우
      console.error('Error fetching attraction detail:', err);
    }
  );
};




const viewStation = (station) => {
  selectStation.value = station;
  console.log(selectStation.value)
  todoStore.addTodo(selectStation.value);

  


  
};

const viewStation2 = (station) => {
  selectStation.value = station;
  showTable.value = !showTable.value;
  temp.value=chargingStations.value
  chargingStations.value=selectStation.value //카카오맵중
  console.log(chargingStations)
  console.log(temp.value.length)
  console.log(typeof(temp))
  getdetail(station.contentid)
  // chargingStations.value =temp.value

};



// 지도 열고 닫기 추가 ---------------------------
// 지도 표시 상태를 제어하는 반응형 속성
const isMapOpen = ref(true);

// 지도 표시 상태를 토글하는 함수
const toggleMap = () => {
  isMapOpen.value = !isMapOpen.value;
};

</script>



<template>

  <!-- 검색 컨테이너 -->
  <div class="container text-center mt-3">
    
    <!-- 제목 및 설명 -->
    <div class="p-3">
      <h2 class="text-primary">관광지 검색</h2>
      <p class="text-secondary">지역별 관광지 정보를 검색하시오.</p>
      <p class="text-secondary">공공API 문제로 관광지가 나타나지 않을 수 있습니다.</p>
    </div>
    <!-- 조건 selector -->
    <div class="row mb-3">
      <div class="col d-flex justify-content-end">
        <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" class="bg-dark text-white" />
      </div>
      <div class="col">
        <VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" class="bg-dark text-white" />
      </div>
    </div>
    <!-- 검색 버튼 -->
    <div class="col mb-3">
      <button @click="selectOption('12')" :class="['btn', selectedOption === '12' ? 'btn-primary' : 'btn-secondary']" class="me-1 mb-1">관광지</button>
      <button @click="selectOption('14')" :class="['btn', selectedOption === '14' ? 'btn-primary' : 'btn-secondary']" class="me-1 mb-1">문화시설</button>
      <button @click="selectOption('15')" :class="['btn', selectedOption === '15' ? 'btn-primary' : 'btn-secondary']" class="me-1 mb-1">축제공연행사</button>
      <button @click="selectOption('25')" :class="['btn', selectedOption === '25' ? 'btn-primary' : 'btn-secondary']" class="me-1 mb-1">여행코스</button>
      <button @click="selectOption('28')" :class="['btn', selectedOption === '28' ? 'btn-primary' : 'btn-secondary']" class="me-1 mb-1">레포츠</button>
      <button @click="selectOption('32')" :class="['btn', selectedOption === '32' ? 'btn-primary' : 'btn-secondary']" class="me-1 mb-1">숙박</button>
      <button @click="selectOption('38')" :class="['btn', selectedOption === '38' ? 'btn-primary' : 'btn-secondary']" class="me-1 mb-1">쇼핑</button>
      <button @click="selectOption('39')" :class="['btn', selectedOption === '39' ? 'btn-primary' : 'btn-secondary']" class="me-1 mb-1">음식점</button>
    </div>
  </div>

  <div class="divider mb-3"></div>

  <!-- 카카오맵 표시 -->
  <div class="container text-center">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <!-- 지도 열기/닫기 버튼 -->
        <button @click="toggleMap" class="btn btn-outline-secondary w-100">
          <span v-if="isMapOpen">지도 닫기</span>
          <span v-else>지도 열기</span>
        </button>
        <VKakaoMap
          v-if="isMapOpen"
          :stations="chargingStations" 
          :selectStation="selectStation"
          :style="{ width: '100%', height: '500px' }" />
      </div>
    </div>
  </div>

  <div class="divider mb-3"></div>

  <!-- 상세 정보 보기 -->
  <div v-if="!showTable" class="detail-container text-center">
    <div class="image-container bg-white text-primary p-3">
      <img v-if="chargingStations.firstimage" :src="chargingStations.firstimage" class="card-img-top" />
      <div v-else class="no-image-placeholder">
        <img src="@/assets/noimg.png" alt="No Image Available" />
      </div>
    </div>
    <div class="overview-container mx-auto" style="max-width: 700px;">
      <p>{{ detatilattract }}</p>
      <!-- 에러 나서 일단 비활성화 해둠 -->
      <!-- <button @click="viewStation2(temp.value)" class="btn btn-secondary">뒤로가기</button> -->
      <button @click="viewStation2(temp.value)" class="btn btn-secondary">뒤로가기</button>
    </div>
  </div>

  <!-- 관광지 목록 보기 -->
  <div v-else class="container">
    <div class="row">
      <div v-for="station in chargingStations" :key="station.title" class="col-md-3 mb-4">
        <div class="card h-100">
          <div class="image-container p-2">
            <img v-if="station.firstimage" :src="station.firstimage" class="card-img-top img-fluid" />
            <div v-else class="no-image-placeholder">
              <img src="@/assets/noimg.png" alt="No Image Available" class="img-fluid"/>
            </div>
          </div>
          <div class="card-body d-flex flex-column">
            <h5 class="card-title">{{ station.title }}</h5>
            <p class="card-text">{{ station.addr1 }} {{ station.addr2 }}</p>
            <div class="mt-auto align-self-end">
              <button @click="viewStation(station)" class="btn btn-primary me-2">여행지 담기</button>
              <button @click="viewStation2(station)" class="btn btn-secondary">자세히보기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
 
</template>

<style scoped>

.detail-container {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap; /* Allow items to wrap to the next line */
  margin: 0 20px; /* Adjust the margin as needed for the space on both sides */
}

.image-container, .no-image-placeholder {
  width: 300px;  /* 원하는 너비 */
  height: 300px; /* 원하는 높이 */
  overflow: hidden;
}

.image-container img, .no-image-placeholder img {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 이미지 비율을 유지하면서 컨테이너를 채움 */
}


.no-image-placeholder {
  background-color: #f0f0f0;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 300px;
}

.overview-container {
  margin-left: 20px;
  flex-grow: 1; /* 남은 공간을 모두 차지하도록 설정 */
}

</style>
