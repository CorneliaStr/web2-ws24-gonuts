<template>

  <section id="tag-list" class="filter-button-container">
    <filter-tag
        v-for="tag in tags"
        :key="tag.id"
        :tag="tag"
        @click="applyFilter(tag)"
        :class="{ active: selectedTag && selectedTag.id === tag.id }">

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
import { computed, onMounted } from "vue";
import ProductCard from "@/components/ProductCard.vue";
import { useProductsStore } from "@/stores/productsStore.js";
import { storeToRefs } from "pinia";

const productStore = useProductsStore();
const filteredProducts = computed(() => productStore.filteredProducts)
const tags = computed(() => productStore.tags);
// Aktiver Filter
const {selectedTag} = storeToRefs(productStore);

// Filter anwenden
const applyFilter = (filter) => {
  if (selectedTag.value === filter) {
    selectedTag.value = null;
  } else {
    selectedTag.value = filter;
  }
};

onMounted(() => {
  productStore.getTags();
});
</script>

<style scoped>
.filter-button-container {
  display: flex;
  flex-wrap: wrap;
  padding: 20px;
}

.admin-section {
  margin: 5px 10px;
}

</style>