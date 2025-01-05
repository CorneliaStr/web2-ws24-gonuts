<template>
  <h2>Favoriten</h2>

  <favorites-card v-for="product in favorites.products" :key="product.id" :product="product"></favorites-card>

</template>

<script setup>
import {computed, onBeforeMount} from "vue";
import {useRoute} from "vue-router";
import{useFavoritesStore} from "@/stores/favoritesStore.js";
import FavoritesCard from './FavoritesCard.vue';

const favoritesStore = useFavoritesStore();
const route = useRoute();
const favorites = computed(() => favoritesStore.favorites);

onBeforeMount(() => {
  favoritesStore.getFavorites(route.params.id);
});
</script>

<style scoped>

</style>