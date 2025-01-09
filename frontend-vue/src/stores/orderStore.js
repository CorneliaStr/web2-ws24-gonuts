import {defineStore} from "pinia";
import {ref} from "vue";

export const useOrderStore = defineStore("order", () => {
    const order = ref([]);

    function getCart(token) {
        fetch(`http://localhost:8080/api/order/cart`, {
            method: 'GET',
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(data => {
                order.value = data;
            });
    }

    function getOrderById(id) {
        fetch(`http://localhost:8080/api/order/${id}`)
            .then(res => res.json())
            .then(data => {
                order.value = data;
            });
    }

    function addToCart(productDTO, quantity, token) {
        fetch(`http://localhost:8080/api/order/addProduct`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                product: productDTO,
                quantity: quantity,
                token: token,
            })
        }).then(res => res.json())
            .then(data => {
                order.value = data;
            })

    }

    function updateOrder(orderDTO) {
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