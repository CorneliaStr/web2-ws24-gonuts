import { ref } from "vue";


export default function orderService() {
    const order = ref(null);


    const fetchOrderById = async (id) => {
        let response = await fetch(`http://localhost:8080/api/order/${id}`);
        order.value = await response.json();
    }

    return {
        order,
        fetchOrderById,
    }
}