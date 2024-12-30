import { defineStore } from "pinia";
import { ref, watch } from "vue";

export const useProductsStore = defineStore("products", () => {
    const products = ref([]);
    const searchQuery = ref("");
    const tags = ref([]);
    let filteredProducts = ref([]);
    let selectedTag = ref(null);

    // Search Query
    watch([searchQuery, selectedTag], ([query, newTag]) => {
        filteredProducts.value = products.value.filter((product) => {
            const matchesQuery = query
                ? product.name.toLowerCase().includes(query.toLowerCase())
                : true;
            const matchesTag = newTag ? product.tags.some((tag) => tag.id === newTag.id) : true;
            return matchesQuery && matchesTag;
        });
    });

    function getProducts() {
        fetch("http://localhost:8080/api/product")
            .then(res => res.json())
            .then(data => {
                this.products = data;
                this.filteredProducts = data;
            });
    }

    function getTags() {
        fetch("http://localhost:8080/api/tag")
            .then(res => res.json())
            .then(data => this.tags = data);
    }

    return {
        products,
        searchQuery,
        filteredProducts,
        selectedTag,
        tags,
        getProducts,
        getTags,
    };

})