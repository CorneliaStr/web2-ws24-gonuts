import { ref } from "vue";


export default function favoritesService() {
    const favorites = ref([]);

    const fetchFavoritesById = async (id) => {
        let response = await fetch(`http://localhost:8080/api/favorites/${id}`);
        favorites.value = await response.json();
    }

    return {
        favorites,
        fetchFavoritesById
    }
}