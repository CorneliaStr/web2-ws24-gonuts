<template>
  <h2>Favoriten</h2>

  <favorites-card v-for="product in favorites.products" :key="product.id" :product="product"></favorites-card>

</template>

<script setup>
import {onBeforeMount} from "vue";
import {useRoute} from "vue-router";
import favoritesService from "@/services/favoritesService.js";
import FavoritesCard from './FavoritesCard.vue';

const route = useRoute();
const {favorites, fetchFavoritesById} = favoritesService();

onBeforeMount(() => {
  fetchFavoritesById(route.params.id);
});
</script>

<style scoped>

</style>