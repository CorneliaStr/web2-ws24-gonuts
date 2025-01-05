
<template>
  <form @submit.prevent="addProduct" class="product-form">
    <h2>Produkt erstellen</h2>
    <div>
      <label for="name">Name:</label>
      <input type="text" id="name" v-model="newProduct.name" required/>
    </div>
    <div>
      <label for="description">Beschreibung:</label>
      <input type="text" id="description" v-model="newProduct.description" required/>
    </div>
    <div>
      <label for="price">Preis:</label>
      <input type="number" id="price" v-model="newProduct.price" required/>
    </div>
    <div>
      <label for="imageUrl">Image URL:</label>
      <input type="text" id="imageUrl" v-model="newProduct.imageUrl" required/>
    </div>
    <div>
      <label for="tags">Tags:</label>
      <select id="tags" v-model="newProduct.tags" multiple>
        <option v-for="tag in tags" :key="tag.id" :value="tag">{{ tag.name }}</option>
      </select>
    </div>
    <button type="submit">Add Product</button>
  </form>
</template>


<script setup>

import { onMounted, ref } from "vue";
import { useProductsStore } from "@/stores/productsStore.js";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";

const productStore = useProductsStore();

const {tags} = storeToRefs(productStore);

const router = useRouter()

const newProduct = ref({
  name: '',
  description: '',
  price: null,
  imageUrl: '',
  tags: [],
});

const addProduct = () => {
  productStore.createProduct(newProduct);

  // Reset the form
  newProduct.value = {
    name: '',
    description: '',
    price: null,
    imageUrl: '',
    tags: [],
  };

  router.push("/admin");
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