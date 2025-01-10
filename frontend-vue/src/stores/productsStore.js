import {defineStore} from "pinia";
import {ref, watch} from "vue";

export const useProductsStore = defineStore("products", () => {
    const products = ref([]);
    const searchQuery = ref("");
    const tags = ref([]);
    let filteredProducts = ref([]);
    let selectedTag = ref(null);
    let errorMessage = ref("");

    // Search Query
    watch([searchQuery, selectedTag], ([query, newTag]) => {
        if (query === null && newTag === null) {
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
            .then(data => tags.value = data);
    }

    function createProduct(product) {
        console.log(product)
        fetch(`http://localhost:8080/api/product`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(product),
        }).then(() => getProducts());
    }

    function createTag(tag) {
        errorMessage.value ="";
        console.log(tag)
        fetch(`http://localhost:8080/api/tag`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(tag),
        }).then(res => console.log(res.json()));
        getTags();
    }

    function getProductById(productId) {
        return products.value.find((product) => product.id == productId);
    }

    function updateTag(tag) {
        errorMessage.value ="";
        console.log(tag)
        fetch(`http://localhost:8080/api/tag`, {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(tag),
        }).then(() => getTags());

    }

    function deleteTag(tagID) {
        console.log(tagID)
        fetch(`http://localhost:8080/api/tag/${tagID}`, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
            },
        }).then(res => res.json())
            .then(data => data ? errorMessage.value = "" : errorMessage.value = "Das Löschen des Elements ist nicht möglich, da es möglicherweise noch verwendet wird.")
            .then(() => getTags());

    }

    function getTagById(tagId) {
        return tags.value.find((tag) => tag.id == tagId);
    }

    function updateProduct(product) {
        errorMessage.value = "";
        console.log(product)
        fetch(`http://localhost:8080/api/product`, {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(product),
        }).then(() => getProducts());

    }

    function deleteProductById(productId) {
        errorMessage.value = "";
        console.log(productId)
        fetch(`http://localhost:8080/api/product/${productId}`, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json',
            },
        }).then(res => res.json())
            .then(data => data ? errorMessage.value = "" : errorMessage.value = "Das Löschen des Elements ist nicht möglich, da es möglicherweise noch verwendet wird.")
            .then(() => getProducts());
    }

    return {
        products,
        searchQuery,
        filteredProducts,
        selectedTag,
        tags,
        errorMessage,
        getProducts,
        getTags,
        createProduct,
        createTag,
        getProductById,
        deleteTag,
        updateTag,
        getTagById,
        deleteProductById,
        updateProduct,
    };

})