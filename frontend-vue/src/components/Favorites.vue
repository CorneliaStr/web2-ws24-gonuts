<template>
  <h2>Merkliste</h2>

  <section class="favorite" v-for="product in favorites.products">
    <a class="element-group">
      <img v-if="product" :src="'/src/assets' + product.image" class="product-image" alt="product.name">
      <div class="element-group-v">
        <h3 class="product-name">{{product.name}}</h3>
        <strong>{{product.price}} €</strong>
      </div>
    </a>
    <div class="element-group">
      <input class="number-step" value="1" max="50" min="1" name="points" step="1" type="number">
      <button class="cart-button">Zum Warenkorb hinzufügen</button>
    </div>
  </section>

</template>

<script setup>
import { onBeforeMount } from "vue";
import { useRoute } from "vue-router";
import favoritesService from "@/services/favoritesService.js";

const route = useRoute();
const {favorites, fetchFavoritesById} = favoritesService();

onBeforeMount(() => {
  fetchFavoritesById(route.params.id);
});
</script>

<style scoped>
.favorite {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.favorite:hover {
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.favorite p {
  font-size: larger;
}

.favorite h3 {
  margin-bottom: 2px;
  margin-top: 5px;
}

.element-group {
  display: flex;
  gap: 15px;
}

.product-name {
  font-weight: bold;
}

.product-image {
  width: 120px;
  height: 140px;
}
</style>