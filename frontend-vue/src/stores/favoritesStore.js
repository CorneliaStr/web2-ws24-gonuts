import {defineStore} from "pinia";
import {ref} from "vue";

export const useFavoritesStore = defineStore("favorites", () => {
    const favorites = ref([]);

    function getFavorites(token) {
        fetch(`http://localhost:8080/api/favorites/`, {
            method: 'GET',
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(data => {
                favorites.value = data;
            });
    }

    function addToFavorites(productDTO, token) {
        fetch(`http://localhost:8080/api/favorites/addProduct`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                product: productDTO,
                token: token,
            })
        }).then(res => res.json())
            .then(data => {
                favorites.value = data;
            })

    }

    return {
        favorites,
        getFavorites,
        addToFavorites,
    }
})