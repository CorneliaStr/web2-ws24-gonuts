<script setup>
import SharedHeader from './components/shared/SharedHeader.vue'
import SharedFooter from './components/shared/SharedFooter.vue'
import { onMounted } from "vue";
import { useProductsStore } from "@/stores/productsStore.js";

const productStore = useProductsStore();

onMounted(() => productStore.getProducts());
</script>

<template>
  <shared-header></shared-header>

    <main class="app-container">
      <RouterView></RouterView>
    </main>

  <shared-footer></shared-footer>
</template>

<style scoped>
.app-container {
  justify-content: center;
  padding: 20px;
  width: 70%;

  background-color: #fff;
  padding: 20px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
</style>
