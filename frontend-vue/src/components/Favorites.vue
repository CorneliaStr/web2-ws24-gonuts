<template>
  <main>

    <h1>Favoriten</h1>

    <favorites-card v-if="favorites.products && favorites.products.length > 0" v-for="product in favorites.products"
                    :key="product.id" :product="product"></favorites-card>

    <p v-else>Die Favoritenliste ist leer.</p>
  </main>
</template>

<script setup>
import {computed, onBeforeMount} from "vue";
import {useRoute} from "vue-router";
import {useFavoritesStore} from "@/stores/favoritesStore.js";
import {useAuthStore} from "@/stores/authStore.js";
import FavoritesCard from './FavoritesCard.vue';

const favoritesStore = useFavoritesStore();
const authStore = useAuthStore();
const route = useRoute();

const favorites = computed(() => favoritesStore.favorites);
const token = computed(() => authStore.token);

onBeforeMount(() => {
  favoritesStore.getFavorites(token.value);
});
</script>

<style scoped>
main {
  width: 100%;
}
</style>