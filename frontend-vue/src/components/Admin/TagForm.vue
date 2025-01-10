<template>
  <form @submit.prevent="handleSubmit" class="tag-form">
    <h2>{{ isEditMode ? "Tag bearbeiten" : "Neuen Tag erstellen" }}</h2>
    <div>
      <label for="name">Name:</label>
      <input id="name" v-model="tag.name" type="text" placeholder="Tag-Name eingeben" required
      />
    </div>
    <button type="submit">
      {{ isEditMode ? "Speichern" : "Erstellen" }}
    </button>
  </form>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useProductsStore } from "@/stores/productsStore.js";

const router = useRouter()
const productStore = useProductsStore();

const isEditMode = router.currentRoute.value.name === "editTag";

const tag = isEditMode ? productStore.getTagById(router.currentRoute.value.params.id) : { name: "" };

const handleSubmit = () => {
  if (isEditMode) {
    console.log(tag.value);
    productStore.updateTag(tag);
    console.log("Tag aktualisiert:", tag.value);
  } else {
    productStore.createTag(tag);
    console.log("Neuer Tag erstellt:", tag.value);
  }
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