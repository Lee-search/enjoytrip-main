<template>
  <footer>
    <div class="copyright">
      <p>COPYRIGHT (C) 2023 <span>SSAFY INC.    </span> Designed by <a href="https://github.com/Lee-search/enjoytrip-vue">Bok&Lee</a> </p>
    </div>
    
    <div class="address">
      <p>주소: 서울특별시 강남구 역삼동 테헤란로 212, 멀티캠퍼스</p>
    </div>
    
  </footer>
</template>

<script>
export default {
  name: "FooterView",
  components: {},
  data() {
    return {};
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
footer {
  width: 100%;
  height: 70px;
  margin-top: 2%;
  background-color: antiquewhite;
}
.copyright {
  float: left;
  width: 50%;
}
.copyright p {
  font-size: 14px;
  color: #999;
  padding: 32px 0 0 40px;
  line-height: 10px;
}
.address {
  float: right;
  width: 50%;
}
.address p {
  font-size: 14px;
  color: #999;
  padding: 32px 40px 0 0;
  text-align: right;
  line-height: 10px;
}
</style>
