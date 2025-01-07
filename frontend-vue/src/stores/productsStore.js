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
        if(query === null && newTag === null){
            filteredProducts.value = products.value;
        }
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
                products.value = data;
                filteredProducts.value = data;
            });
    }

    function getTags() {
        fetch("http://localhost:8080/api/tag")
            .then(res => res.json())
            .then(data => this.tags = data);
    }

    function createProduct(product) {
        fetch(`http://localhost:8080/api/product`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(product.value),
        });
        getProducts();
    }

    function createTag(tag) {
        fetch(`http://localhost:8080/api/tag`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(tag.value),
        }).then(res => console.log(res.json()));
        getTags();
    }

    function getProductById(productId) {
        return products.value.find((product) => product.id == productId);
    }

    return {
        products,
        searchQuery,
        filteredProducts,
        selectedTag,
        tags,
        getProducts,
        getTags,
        createProduct,
        createTag,
        getProductById,
    };

})