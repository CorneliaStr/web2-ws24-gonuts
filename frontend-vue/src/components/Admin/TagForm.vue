<template>
  <form @submit.prevent="addTags" class="tag-form">
    <h2>Tag erstellen</h2>
    <div>
      <label for="name">Name:</label>
      <input type="text" id="name" v-model="newTag.name" required/>
    </div>
    <button type="submit">Add Tag</button>
  </form>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useProductsStore } from "@/stores/productsStore.js";

const router = useRouter()
const productStore = useProductsStore();

const newTag = ref({
  name: '',
});

const addTags = () => {
  productStore.createTag(newTag);

  // Reset the form
  newTag.value = {
    name: '',
  };

  router.push("/tagTable");
};

</script>

<style scoped>
.tag-form {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.tag-form div {
  display: flex;
  flex-direction: column;
}

.tag-form label {
  font-weight: bold;
}

.tag-form input {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.tag-form button {
  padding: 10px 15px;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

</style>