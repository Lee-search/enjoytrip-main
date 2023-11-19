<script setup>
import { ref, watch, onMounted } from "vue";

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
    console.log(props.selectStation);
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
  };
  map = new kakao.maps.Map(container, options);

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
    removeMarkers();
    drawLine();
  });

  kakao.maps.event.addListener(map, "dragend", () => {
    
  });
};

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
  }
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
};

const removeMarkers = () => {
  markers.value.forEach((marker) => marker.setMap(null));
  markers.value = [];
  accumulatedDistance = 0;
  distanceOverlay.value.setContent('');
};
</script>

<template>
  <div id="map"></div>
</template>

<style>
#map {
  width: 100%;
  height: 700px;
}
</style>
