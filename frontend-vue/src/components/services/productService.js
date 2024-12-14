import { ref } from "vue";


export default function productService() {
    const products = ref([]);
    const fetchProducts = async () => {
        let response = await fetch("http://localhost:8080/api/product");
        products.value = await response.json();
    }

    return {
        products,
        fetchProducts
    }
}