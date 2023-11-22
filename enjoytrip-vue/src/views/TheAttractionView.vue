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







</script>



<template>
  <div class="container text-center mt-3">
    <div style="background-color: #ffffff; color: #2c3e50; padding: 20px;">
      <h1 style="font-size: 2em;">관광지 검색</h1>
      <p>지역별 관광지 정보를 검색하시오.</p>
    </div>

    <div class="row mb-2">
      <div class="col d-flex flex-row-reverse">
        <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" style="background-color: #333; color: #fff; height: 40px;" />
      </div>
      <div class="col">
        <VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" style="background-color: #333; color: #fff; height: 40px;" />
      </div>
    </div>

    <div class="col">
      <button @click="selectOption('12')" :class="{ 'btn': true, 'btn-primary': selectedOption === '12', 'btn-secondary': selectedOption !== '12' }" style="margin-right: 5px; margin-bottom: 5px;">관광지</button>
      <button @click="selectOption('14')" :class="{ 'btn': true, 'btn-primary': selectedOption === '14', 'btn-secondary': selectedOption !== '14' }" style="margin-right: 5px; margin-bottom: 5px;">문화시설</button>
      <button @click="selectOption('15')" :class="{ 'btn': true, 'btn-primary': selectedOption === '15', 'btn-secondary': selectedOption !== '15' }" style="margin-right: 5px; margin-bottom: 5px;">축제공연행사</button>
      <button @click="selectOption('25')" :class="{ 'btn': true, 'btn-primary': selectedOption === '25', 'btn-secondary': selectedOption !== '25' }" style="margin-right: 5px; margin-bottom: 5px;">여행코스</button>
      <button @click="selectOption('28')" :class="{ 'btn': true, 'btn-primary': selectedOption === '28', 'btn-secondary': selectedOption !== '28' }" style="margin-right: 5px; margin-bottom: 5px;">레포츠</button>
      <button @click="selectOption('32')" :class="{ 'btn': true, 'btn-primary': selectedOption === '32', 'btn-secondary': selectedOption !== '32' }" style="margin-right: 5px; margin-bottom: 5px;">숙박</button>
      <button @click="selectOption('38')" :class="{ 'btn': true, 'btn-primary': selectedOption === '38', 'btn-secondary': selectedOption !== '38' }" style="margin-right: 5px; margin-bottom: 5px;">쇼핑</button>
      <button @click="selectOption('39')" :class="{ 'btn': true, 'btn-primary': selectedOption === '39', 'btn-secondary': selectedOption !== '39' }" style="margin-right: 5px; margin-bottom: 5px;">음식점</button>
    </div>
  </div>
 



    <!-- 카카오맵 표시 -->
    
    <!-- 관광지 정보 테이블 -->
    <div v-if="!showTable" class="detail-container">
  <div class="image-container2">
    <img v-if="chargingStations.firstimage" :src="chargingStations.firstimage" class="card-img-top"  />
    <div v-else class="no-image-placeholder">
      <img src="@/assets/noimg.png" alt="No Image Available" />
    </div>
  </div>
  
  <div class="overview-container" style="max-width: 700px;">
    <p>{{ detatilattract }}</p>
    <button @click="viewStation2(temp.value)" class="btn btn-secondary">뒤로가기</button>
  </div>
  
</div>



<div v-else>
  <div class="container">
    <div class="row">
      <!-- 관광지 정보 반복 출력 -->
      <div v-for="station in chargingStations" :key="station.title" class="col-md-3 mb-4">
        <div class="card h-100">
          <!-- Check if the image is available -->
          <div class="image-container">
            <img v-if="station.firstimage" :src="station.firstimage" class="card-img-top" />
            <div v-else class="no-image-placeholder">
              <img src="@/assets/noimg.png" alt="No Image Available" />
            </div>
          </div>
          <div class="card-body d-flex flex-column">
            <h5 class="card-title">{{ station.title }}</h5>
            <p class="card-text">{{ station.addr1 }} {{ station.addr2 }}</p>
            <div class="mt-auto">
              <button @click="viewStation(station)" class="btn btn-primary">여행지 담기</button>
              <button @click="viewStation2(station)" class="btn btn-secondary">자세히보기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<VKakaoMap :stations="chargingStations" :selectStation="selectStation" :style="{ width: '1600px', height: '900px', margin: '0 auto' }" />

  
 
</template>

<style scoped>
mark.purple {
  background: linear-gradient(to top, #c354ff 20%, transparent 30%);
}

.container {
  max-width: 800px;
  margin: 0 auto;
}

.card {
  max-height: 400px;
  max-width: 300px;
}

.image-container img,
.no-image-placeholder img {
  width: 100%;
  height: auto;
}

.card-title {
  font-size: 1.2em; /* 텍스트 크기 조절 */
}

.card-text {
  font-size: 1em; /* 텍스트 크기 조절 */
}

.btn {
  font-size: 0.8em; /* 버튼 크기 조절 */
}

.no-image-placeholder {
  background-color: #f0f0f0; /* 이미지가 없을 때의 배경색 설정 */
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100px; /* 원하는 높이 설정 */
}



.detail-container {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap; /* Allow items to wrap to the next line */
  margin: 0 20px; /* Adjust the margin as needed for the space on both sides */
}


.image-container {
  max-height: 300px;
  overflow: hidden;
}

.image-container img, .no-image-placeholder img {
  width: 150%; /* 이미지를 오른쪽으로 늘리기 위해 너비를 더 크게 설정 */
  height: auto;
  object-fit: cover;
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
