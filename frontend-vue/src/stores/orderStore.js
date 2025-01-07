import { defineStore } from "pinia";
import { ref } from "vue";

export const useOrderStore = defineStore("order", () => {
    const order = ref([]);

     function getCart() {
            fetch(`http://localhost:8080/api/order/cart`)
                .then(res => res.json())
                .then(data => {
                    order.value = data;
                });
    }

    function getOrderById(id){
        fetch(`http://localhost:8080/api/order/${id}`)
           .then(res => res.json())
           .then(data => {
                order.value = data;
            });
    }

    function addToCart(productDTO){
        fetch(`http://localhost:8080/api/order/addProduct`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(productDTO)
        })
    }

    function updateOrder(orderDTO){
        fetch(`http://localhost:8080/api/order/update`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(orderDTO)
        })
    }

    const getOrder = () => order.value;

    return {
        order,
        getCart,
        addToCart,
        getOrderById,
        updateOrder,
        getOrder
    }
})