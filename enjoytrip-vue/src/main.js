// import "./assets/main.css";

import { createApp } from "vue";
import { createPinia } from "pinia";
import persistedState from 'pinia-plugin-persistedstate';

import App from "./App.vue";
import router from "./router";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

const app = createApp(App);
const pinia = createPinia();
pinia.use(persistedState);

app.use(pinia);
// app.use(createPinia().use(piniaPluginPersistedstate));
app.use(router);

app.mount("#app");
