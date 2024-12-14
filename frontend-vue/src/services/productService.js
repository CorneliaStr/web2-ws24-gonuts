import { ref } from "vue";


export default function productService() {
    const products = ref([]);
    const product = ref(null);

    const fetchProducts = async () => {
        let response = await fetch("http://localhost:8080/api/product");
        products.value = await response.json();
    }

    const fetchProductById = async (id) => {
        let response = await fetch(`http://localhost:8080/api/product/${id}`);
        product.value = await response.json();
    }

    return {
        product,
        products,
        fetchProducts,
        fetchProductById,
    }
}