<template>
  <div class="search-bar">
    <input type="text"
           placeholder="Suche..."
           v-model="searchQuery"
           @keyup.enter="onSearch">
  </div>
</template>

<script setup>

import { useProductsStore } from "@/stores/productsStore.js";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";

const productStore = useProductsStore();

const {searchQuery} = storeToRefs(productStore);
const router = useRouter();

const onSearch = () => {
  router.push({
    path: "/shop", // Zielroute
  });
}

</script>

<style scoped>

</style>