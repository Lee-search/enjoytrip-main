<script setup>
import { ref, onMounted, computed } from 'vue';
import { getArticleCount, detailArticle  } from '@/api/board';

import { useMemberStore } from "@/stores/member";

import seoulImg from "@/assets/attract/seoul1.png"
import busanImg from "@/assets/attract/busan2.png"
import jejuImg from "@/assets/attract/jeju3.png"
import incheonImg from "@/assets/attract/incheon4.png"
import yeosuImg from "@/assets/attract/yeosu5.png"
import suwonImg from "@/assets/attract/suwon6.png"
import daejeonImg from "@/assets/attract/daejeon7.png"
import gwangjuImg from "@/assets/attract/gwangju8.png"

const memberStore = useMemberStore();
const userInfo = computed(() => memberStore.userInfo);

const popularDestinations = ref([
  {
    name: "서울특별시",
    imageUrl: seoulImg,
    description: "서울은 대한민국의 수도로, 역사적인 궁궐, 현대적인 건축물, 활기찬 시장 및 쇼핑 지구로 유명합니다. 전통과 현대가 조화를 이루는 도시로, 주요 관광지로는 경복궁, 남산서울타워, 인사동, 동대문디자인플라자 등이 있습니다."
  },
  {
    name: "부산광역시",
    imageUrl: busanImg,
    description: "부산은 한국의 최대 항구도시이며, 아름다운 해변, 산, 사찰 등 다양한 관광지를 제공합니다. 특히 해운대 해수욕장, 감천문화마을, 부산국제영화제, 자갈치시장 등이 유명합니다."
  },
  {
    name: "제주특별자치도",
    imageUrl: jejuImg,
    description: "제주도는 화산섬으로 유네스코 세계자연유산에 등재된 곳입니다. 아름다운 해변, 청정 자연, 독특한 문화가 특징인 곳으로 한라산, 성산일출봉, 제주올레길, 만장굴 등이 대표적입니다."
  },
  {
    name: "인천광역시",
    imageUrl: incheonImg,
    description: "인천은 한국의 서해안에 위치한 항구도시로, 현대적인 건물과 중국 문화가 어우러진 차이나타운, 송도국제도시, 월미도 등 다양한 관광지가 있습니다."
  },
  {
    name: "여수시",
    imageUrl: yeosuImg,
    description: "여수는 남해안에 위치한 항구도시로, 아름다운 섬과 해안 경관이 특징입니다. 여수밤바다, 오동도, 해상케이블카, 여수엑스포 등이 주요 관광지로 꼽힙니다."
  },
  {
    name: "수원시",
    imageUrl: suwonImg,
    description: "수원은 경기도에 위치하며, 조선시대의 성곽인 수원화성이 유명합니다. 또한, 전통시장, 현대적인 쇼핑몰, 국립수목원 등 다양한 볼거리를 제공합니다."
  },
  {
    name: "대전광역시",
    imageUrl: daejeonImg,
    description: "대전은 중부권의 중심도시로, 과학기술, 문화, 예술이 어우러진 도시입니다. 국립중앙과학관, 엑스포과학공원, 대청호 등이 대표적인 관광지입니다."
  },
  {
    name: "광주광역시",
    imageUrl: gwangjuImg,
    description: "광주는 예술과 문화의 도시로 알려져 있으며, 광주비엔날레, 무등산 국립공원, 1913송정역시장 등 역사적이고 문화적인 명소가 많습니다."
  },
]);

// 임의 게시글 8개 작성
// 게시판 전체 글 갯수 얻어온 후 랜덤 아티클 작성

// onMounted(async () => {
//   await loadArticleCount();
//   console.log(articleCount.value);
// });

// const articleCount = ref(0);
// onMounted(() => {
//   async () => {
//     getArticleCount(
//       ({ response }) => {
//         articleCount.value = response.data.count;
//       },
//       (error) => {
//         console.log(error);
//       }
//     )
//   }
// });

const randomArticles = ref([]);
onMounted(() => {
  for (let i = 1; i <= 8; i++) {
    getArticle(i);
  }
});

// 게시글 수 얻어오기
// const loadArticleCount = async () => {
//   getArticleCount(
//     ({ response }) => {
//       articleCount.value = response.data.count;
//     },
//     (error) => {
//       console.log(error);
//     }
//   )
// };

// 임의의 게시판 글 불러오기
const getArticle = async (articleno) => {
  console.log(articleno + "번 글 조회");
  await detailArticle(
    articleno,
    ({ data }) => {
      let article = {
        no: data.articleNo,
        name: data.userName,
        subject: data.subject,
        description: data.content,
      }
      randomArticles.value.push(article);
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<template>
  <!-- 메인 이미지 배너 섹션 -->
  <div id="heroCarousel" class="carousel slide mb-4" data-bs-ride="carousel" data-bs-interval="5000">
    <!-- 인디케이터 -->
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#heroCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#heroCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#heroCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>

    <!-- 슬라이드 이미지 -->
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="@/assets/img/main-view-1.jpg" class="d-block w-100 carousel-image" alt="Slide 1">
      </div>
      <div class="carousel-item">
        <img src="@/assets/img/main-view-2.jpg" class="d-block w-100 carousel-image" alt="Slide 2">
      </div>
      <div class="carousel-item">
        <img src="@/assets/img/main-view-3.jpg" class="d-block w-100 carousel-image" alt="Slide 3">
      </div>
    </div>

    <!-- 이미지 상단 컴포넌트 -->
    <div class="carousel-caption d-none d-md-block">
      <h3>여행의 모든 것, EnjoyTrip</h3>
      <p>새로운 경험을 찾아 떠나세요!</p>
      <!-- 로그인된 상태일 때 '글쓰기' 버튼 표시 -->
      <router-link v-if="userInfo" :to="{ name: 'article' }" class="btn btn-success">글쓰러 가기</router-link>
      <!-- 로그인되지 않은 상태일 때 '로그인' 및 '회원가입' 버튼 표시 -->
      <div v-else>
        <router-link :to="{ name: 'user-login' }" class="btn btn-outline-light me-2">로그인</router-link>
        <router-link :to="{ name: 'user-join' }" class="btn btn-primary">회원가입</router-link>
      </div>
    </div>

    <!-- 좌/우 컨트롤 버튼 -->
    <button class="carousel-control-prev" type="button" data-bs-target="#heroCarousel" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#heroCarousel" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>

  <div class="divider mb-3"></div>

  <!-- 여행지 정보 카드뷰 배너 -->
  <div class="container">
    <h2 class="font-weight mb-3">추천 여행지</h2>
    <p class="fw-light mb-3">EnjoyTrip과 함께 아래 관광지로의 여행을 준비해보세요.</p>
    <div class="row">
      <div class="col-md-3 mb-4" v-for="(destination, index) in popularDestinations" :key="index">
        <div class="card h-100">
          <img :src="destination.imageUrl" class="card-img-top" :alt="destination.name">
          <div class="card-body d-flex flex-column">
            <h5 class="card-title">{{ destination.name }}</h5>
            <p class="card-text">{{ destination.description }}</p>
            <div class="mt-auto text-end">
              <router-link :to="'/attraction'" class="btn btn-outline-primary">둘러보기</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="divider mb-3"></div>

  <!-- 사용자 후기 배너 -->
  <div class="container">
    <h2 class="font-weight mb-3">여행 후기</h2>
    <p class="fw-light mb-3">EnjoyTrip과 함께한 후기를 확인해보세요.</p>
    <div class="row">
      <div class="col-md-3 mb-4" v-for="(article, index) in randomArticles" :key="index">
        <div class="card h-100">
          <div class="card-body d-flex flex-column">
            <h5 class="card-title">{{ article.subject }}</h5>
            <p class="card-text">게시자: {{ article.name }}</p>
            <p class="card-text">{{ article.description }}</p>
            <div class="mt-auto text-end">
              <router-link :to="`/article/view/${article.no}`" class="btn btn-outline-primary">자세히보기</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.carousel-image {
  height: 500px; 
  object-fit: cover; 
}

.carousel-caption {
  background: rgba(0, 0, 0, 0.5);
  padding: 1em;
  margin: 2em;
}
</style>