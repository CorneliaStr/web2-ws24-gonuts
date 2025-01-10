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
      <th></th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(tag, index) in tags" :key="index">
      <td>{{ tag.id }}</td>
      <td>{{ tag.name }}</td>
      <td class="text-right">
        <router-link :to="`/editTag/${tag.id}`">
          <button><i class="pi pi-pencil"></i></button>
        </router-link>
        <button id="deleteButton" class="delete-button" v-on:click="deleteTagById(tag.id)">
          <i class="pi pi-trash"></i>
        </button>
      </td>
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

const deleteTagById = (tag) => {
  productStore.deleteTag(tag);
}
</script>


<style scoped>
.admin-section {
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

.text-right {
  display: flex;
  justify-content: center;
}

.delete-button {
  background: darkred;
  margin-left: 5px;
}
</style>