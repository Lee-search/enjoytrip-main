<script setup>


</script>

<template>
  <div style="height: 40px"></div>
  <section style="position: relative">
    <img
      class="img-fluid card-img"
      src="@/assets/img/main-test.jpg"
      alt="바다이미지"
      style="width: 100%"
    />
    <div
      class="py-lg-5"
      style="position: absolute; top: 50%; left: 70%; transform: translate(-50%, -50%)"
    >
      <div class="col-md-8 mx-auto" style="width: auto">
        <h1 class="fw-light"><b>기억에 남는 여행.</b></h1>
        <p class="lead text-muted">EnjoyTrip과 함께</p>
        <p>
          <a
            class="btn btn-primary my-2"
            data-bs-toggle="modal"
            data-bs-target="#registerModal"
            aria-current="page"
            href="#"
            >회원가입</a
          >
          <router-link to="/estations" class="btn btn-secondary my-2">여행 떠나기</router-link>
        </p>
      </div>
    </div>
  </section>

  <div class="container">
    <a
      class="weatherwidget-io mt-3 mb-3"
      href="https://forecast7.com/en/35d91127d77/south-korea/"
      data-label_1="오늘의 날씨"
      data-icons="Climacons Animated"
      data-theme="pure"
      >오늘의 날씨</a
    >
  </div>




</template>


<style scoped></style>
