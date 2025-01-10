<script setup>

import { useProductsStore } from "@/stores/productsStore.js";
import { computed } from "vue";

const productStore = useProductsStore();
const products = computed(() => productStore.products);

const deleteProductById = (productId) => {
  productStore.deleteProductById(productId);
}
</script>

<template>

  <section class="admin-section">
    <h2>Produkte</h2>
    <router-link :to="'/addProduct'">
      <button>Neues Produkt</button>
    </router-link>
  </section>

  <table class="product-table">
    <thead>
    <tr>
      <th>Id</th>
      <th>Name</th>
      <th>Beschreibung</th>
      <th>Preis</th>
      <th>Image</th>
      <th>Tags</th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(product, index) in products" :key="index">
      <td>{{ product.id }}</td>
      <td>{{ product.name }}</td>
      <td>{{ product.description }}</td>
      <td>{{ product.price }}</td>
      <td>
        <img :src="'/src/assets' + product.image" :alt="product.name" class="product-image"/>
      </td>
      <td>
            <span v-for="(tag, tagIndex) in product.tags" :key="tagIndex" class="tag">
              {{ tag.name }}
            </span>
      </td>
      <td>
        <router-link :to="`/editProduct/${product.id}`">
          <button><i class="pi pi-pencil"></i></button>
        </router-link>
<!--        <button id="deleteButton" class="delete-button" v-on:click="deleteProductById(product.id)">-->
<!--          <i class="pi pi-trash"></i>-->
<!--        </button>-->
      </td>
    </tr>
    </tbody>

  </table>
</template>

<style scoped>
.admin-section {
  display: flex;
  justify-content: space-between;
}

.product-table {
  margin: 20px;
  border-collapse: collapse;
  width: 100%;
}

.product-table table {
  width: 100%;
  border: 1px solid #ddd;
}

.product-table th, .product-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.product-image {
  width: 50px;
  height: auto;
}

.tag {
  background-color: #f3f3f3;
  border: 1px solid #ddd;
  padding: 2px 6px;
  margin: 2px;
  border-radius: 4px;
  display: inline-block;
}

.delete-button {
  background: darkred;
  margin: 1px;
}
</style>