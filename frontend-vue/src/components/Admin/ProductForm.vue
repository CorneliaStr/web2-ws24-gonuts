<template>
  <form @submit.prevent="handleSubmit" class="product-form">
    <h2>{{ isEditMode ? "Produkt bearbeiten" : "Produkt erstellen" }}</h2>
    <div>
      <label for="name">Name:</label>
      <input type="text" id="name" v-model="product.name" required/>
    </div>
    <div>
      <label for="description">Beschreibung:</label>
      <input type="text" id="description" v-model="product.description" required/>
    </div>
    <div>
      <label for="price">Preis:</label>
      <input type="number" id="price" v-model="product.price" required/>
    </div>
    <div>
      <label for="imageUrl">Image URL:</label>
      <input type="text" id="imageUrl" v-model="product.image" required />
    </div>
    <div>
      <label for="tags">Tags:</label>
      <select id="tags" v-model="product.tags" multiple>
        <option v-for="tag in tags" :key="tag.id" :value="tag">{{ tag.name }}</option>
      </select>
    </div>
    <button type="submit">{{ isEditMode ? "Speichern" : "Erstellen" }}</button>
  </form>
</template>


<script setup>

import { onMounted } from "vue";
import { useProductsStore } from "@/stores/productsStore.js";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";

const productStore = useProductsStore();

const {tags} = storeToRefs(productStore);

const router = useRouter()

const isEditMode = router.currentRoute.value.name === "editProduct";
const product = isEditMode
    ? productStore.getProductById(router.currentRoute.value.params.id)
    : {
      name: '',
      description: '',
      price: null,
      imageUrl: '',
      tags: [],
    };

const handleSubmit = () => {
  if (isEditMode) {
    productStore.updateProduct(product);
    console.log("Tag aktualisiert:", product.value);
  } else {
    productStore.createProduct(product);
    console.log("Neuer Tag erstellt:", product.value);
  }
  router.push("/productTable");
};

onMounted(() => productStore.getTags())
</script>

<style scoped>
.product-form {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.product-form div {
  display: flex;
  flex-direction: column;
}

.product-form label {
  font-weight: bold;
}

.product-form input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.product-form button {
  padding: 10px 15px;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.product-form input, .product-form select {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

</style>