<template>

  <section id="tag-list" class="filter-button-container">
    <filter-tag
        v-for="tag in tags"
        :key="tag.id"
        :tag="tag"
        @click="applyFilter(tag)"
        :class="{ active: activeFilter && activeFilter.id === tag.id }">

    </filter-tag>
  </section>

  <div class="heading">
    <h2>Ergebnisse</h2>
  </div>

  <section id="products-list" class="products-list">
    <product-card
        v-for="product in filteredProducts"
        :key="product.id"
        :product="product">
    </product-card>

  </section>


</template>

<script setup>
import FilterTag from './FilterTag.vue'
import { computed, onMounted, ref } from "vue";
import tagService from "@/services/TagService.js";
import productService from "@/services/productService.js";
import ProductCard from "@/components/ProductCard.vue";

const {tags, fetchTags} = tagService();
const {products, fetchProducts} = productService();

// Aktiver Filter
const activeFilter = ref(null);

// Filter anwenden
const applyFilter = (filter) => {
  activeFilter.value = filter;
};

const filteredProducts = computed(() => {
  if (!activeFilter.value) {
    return products.value; // Keine Filterung, alle Produkte anzeigen
  }
  return products.value.filter((product) =>
      product.tags.some((tag) => tag.id === activeFilter.value.id)
  );
});

onMounted(fetchTags(), fetchProducts());
</script>

<style scoped>
.filter-button-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  padding: 20px;
}

filter-buttons {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

button {
  padding: 10px 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f5f5f5;
  cursor: pointer;
}

button.active {
  background-color: #333;
  color: white;
  border-color: #333;
}

.tags span {
  display: inline-block;
  margin-right: 5px;
  padding: 2px 5px;
  background-color: #e0e0e0;
  border-radius: 3px;
  font-size: 12px;
}

</style>