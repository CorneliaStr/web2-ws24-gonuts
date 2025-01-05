import { defineStore } from "pinia";
import { ref } from "vue";

export const useFavoritesStore = defineStore("favorites", () => {
    const favorites = ref([]);

    async function getFavorites(id) {
        try {
            const response = await fetch(`http://localhost:8080/api/favorites/${id}`);
            favorites.value = await response.json();
        } catch (error) {
            console.error("Fehler beim Abrufen der Favoriten:", error);
        }
    }


    return {
        favorites,
        getFavorites,
    }
})