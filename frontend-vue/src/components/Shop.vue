<template>

    <section id="tag-list" class="filter-button-container">
      <filter-tag v-for="tag in tags" :key="tag.id" :tag="tag"></filter-tag>
    </section>

    <div class="heading">
      <h2>Ergebnisse</h2>
    </div>

    <section id="products-list" class="products-list">
      <product-card v-for="product in products" :key="product.id" :product="product">

      </product-card>

    </section>


</template>

<script setup>
import FilterTag from './FilterTag.vue'
import { onMounted } from "vue";
import tagService from "@/components/services/TagService.js";
import productService from "@/components/services/productService.js";
import ProductCard from "@/components/ProductCard.vue";

const { tags, fetchTags } = tagService();
const { products, fetchProducts } = productService();

onMounted(fetchTags(), fetchProducts());
</script>

<style scoped>



.filter-button-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  padding: 20px;
}




</style>