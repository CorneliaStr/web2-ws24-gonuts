<template>
  <div>
    <img src="@/assets/images/banner.png" class="banner-image" alt="Banner">
  </div>

  <hr class="seperator">

  <h2 class="heading">Unsere Produkte</h2>

  <Carousel :value="products" :numVisible="3" :numScroll="3">
    <template #item="slotProps">
      <section class="products-list">
        <product-card :product="slotProps.data"></product-card>
      </section>
    </template>
  </Carousel>

  <hr class="seperator">
</template>

<script setup>
import Carousel from 'primevue/carousel';
import productService from "@/services/productService.js";
import { onMounted } from "vue";
import ProductCard from "@/components/ProductCard.vue";

const {products, fetchProducts} = productService();

onMounted(() => {
  fetchProducts()
});
</script>

<style scoped>
.banner-image {
  width: 100%;
}
</style>