<template>
  <section class="admin-section">
    <h2>Tags</h2>
    <router-link :to="'/addTag'">
      <button>Neuer Tag</button>
    </router-link>
  </section>

  <table class="tag-table">
    <thead>
    <tr>
      <th>Id</th>
      <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(tag, index) in tags" :key="index">
      <td>{{ tag.id }}</td>
      <td>{{ tag.name }}</td>
    </tr>
    </tbody>

  </table>
</template>

<script setup>
import { useProductsStore } from "@/stores/productsStore.js";
import { computed, onMounted } from "vue";

const productStore = useProductsStore();
const tags = computed(() => productStore.tags);

onMounted(() => productStore.getTags())
</script>



<style scoped>
.admin-section{
  display: flex;
  justify-content: space-between;
}
.tag-table {
  margin: 20px;
  border-collapse: collapse;
  width: 100%;
}

.tag-table table {
  width: 100%;
  border: 1px solid #ddd;
}

.tag-table th, .tag-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}
</style>