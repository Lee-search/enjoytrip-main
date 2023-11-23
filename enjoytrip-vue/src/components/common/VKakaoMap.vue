<script setup>
import { ref, watch, onMounted,provide } from "vue";

var map;
const markers = ref([]);
const infowindow = ref(null);
const line = ref(null);
let accumulatedDistance = 0;
const distanceOverlay = ref(null);

const props = defineProps({ stations: Array, selectStation: Object });




watch(
  () => props.selectStation.value,
  () => {
    const moveLatLon = new kakao.maps.LatLng(
      props.selectStation.mapy,
      props.selectStation.mapx
    );
    map.panTo(moveLatLon);
    addMarker(moveLatLon, props.selectStation);
    showInfoWindow(props.selectStation);
    drawLine();
  },
  { deep: true }
);

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

const initMap = () => {

  


  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
    scrollwheel: false, // 휠 이벤트 비활성화
  };
  map = new kakao.maps.Map(container, options);

  var zoomControl = new kakao.maps.ZoomControl();

		// 지도의 우측에 확대 축소 컨트롤을 추가한다
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);  

  infowindow.value = new kakao.maps.InfoWindow({
    zIndex: 1,
  });

  line.value = new kakao.maps.Polyline({
    map: map,
    strokeWeight: 3,
    strokeColor: "#FF0000",
    strokeOpacity: 0.7,
  });


  
  distanceOverlay.value = new kakao.maps.CustomOverlay({
    map: map,
    content: '',
    position: new kakao.maps.LatLng(0, 0), // 초기화면에는 지도상에 표시되지 않도록 임의의 위치로 지정
    yAnchor: 1,
    zIndex: 2,
  });

  // kakao.maps.event.addListener(map, "click", (mouseEvent) => {
  //   infowindow.value.close();
  //   const newPosition = mouseEvent.latLng;
  //   updateSelectedStation(newPosition);
  // });

  kakao.maps.event.addListener(map, "rightclick", () => {
  // 마커를 하나씩 제거하고 경로를 다시 그림
    removeMarker();
  });

  

  container.addEventListener("mousewheel", (e) => {
    e.preventDefault();
  });



};

//마커를 추가하기
const addMarker = (position, station) => {
  const marker = new kakao.maps.Marker({
    map: map,
    position: position,
    clickable: true,
    title: station.statNm,
  });

  markers.value.push(marker);

  kakao.maps.event.addListener(marker, "click", () => {
    showInfoWindow(station);
  });

  // 마커가 2개 이상일 때 빨간 선 그리기
  if (markers.value.length >= 2) {
    drawLine();
    adjustMapBounds(); // 새로운 마커가 추가될 때마다 맵의 경계 조정


  }
};

const adjustMapBounds = () => {
  const bounds = new kakao.maps.LatLngBounds();
  markers.value.forEach((marker) => bounds.extend(marker.getPosition()));
  map.setBounds(bounds);
};



const showInfoWindow = (station) => {
  const content =
    `<div style="padding:10px;">` +
    `<img src="${station.firstimage}" style="max-width: 100px; max-height: 50px;" alt="역 이미지">` +
    `<div><strong>${station.title}</strong></div>` +
     
    `</div>`;

  infowindow.value.setContent(content);

  infowindow.value.open(map, markers.value[markers.value.length - 1]);
};



const drawLine = () => {
  const path = markers.value.map((marker) => marker.getPosition());
  line.value.setPath(path); 

  // getLength() 메서드를 사용하여 현재까지 그려진 선의 총 길이 계산
  const currentDistance = line.value.getLength();

  // 현재 거리를 누적 거리에 추가
  accumulatedDistance += currentDistance / 1000; // 미터를 킬로미터로 변환

  // 거리 정보를 커스텀 오버레이에 표시
  const content = `<div class="dotOverlay distanceInfo">총거리 <span class="number">${accumulatedDistance.toFixed(3)}</span>km</div>`;
  distanceOverlay.value.setContent(content);
  distanceOverlay.value.setPosition(path[path.length - 1]);

  console.log("누적 거리: ", accumulatedDistance);

  const bounds = new kakao.maps.LatLngBounds();
  path.forEach((position) => bounds.extend(position));
  map.setBounds(bounds);
  
};




const removeMarker = () => {
  if (markers.value.length > 0) {
    markers.value[markers.value.length - 1].setMap(null); // 마지막 마커 제거
    markers.value.pop(); // 마커 배열에서도 제거
    drawLine(); // 경로 다시 그리기
    adjustMapBounds(); // 맵의 경계 조정
  }

  // 마커가 없을 때는 누적 거리를 0으로 초기화
  if (markers.value.length === 0) {
    accumulatedDistance = 0;
    
    // 거리 정보를 커스텀 오버레이에 표시
    const path = markers.value.map((marker) => marker.getPosition());
    const content = `<div class="dotOverlay distanceInfo">총거리 <span class="number">${accumulatedDistance.toFixed(3)}</span>km</div>`;
    distanceOverlay.value.setContent(content);
    distanceOverlay.value.setPosition(path[path.length - 1]);
  }
};


provide('accumulatedDistance', accumulatedDistance);



</script>

<template>
  <div id="map"></div>
</template>

<style>
#map {
  width: 100%;
  height: 500px;
  margin: 0 20px;
}
</style>
