<script setup></script>

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
          <a href="./attraction.html" class="btn btn-secondary my-2">관광지 조회</a>
        </p>
      </div>
    </div>
  </section>
</template>


<style scoped></style>
