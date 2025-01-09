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

    function addToCart(productDTO, quantity){
        fetch(`http://localhost:8080/api/order/addProduct/${quantity}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(productDTO)
        }).then(res => res.json())
        .then(data =>{
            order.value = data;
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

    function deleteOrderPosition(orderPositionDTO, orderID){
        fetch(`http://localhost:8080/api/order/deleteOP/${orderID}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(orderPositionDTO)
        }).then(res => res.json())
        .then(data =>{
            order.value = data;
        })
    }

    const getOrder = () => order.value;

    return {
        order,
        getCart,
        addToCart,
        getOrderById,
        updateOrder,
        getOrder,
        deleteOrderPosition
    }
})