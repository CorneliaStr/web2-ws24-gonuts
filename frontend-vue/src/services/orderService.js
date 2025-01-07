import { ref } from "vue";


export default function orderService() {
    const order = ref(null);


    const fetchOrderById = async (id) => {
        let response = await fetch(`http://localhost:8080/api/order/${id}`);
        order.value = await response.json();
    }

    const fetchCart = async () =>{
        let response = await fetch('http://localhost:8080/api/order/cart');
        order.value = await response.json();
    }

    const fetchAddToCart = async (productDTO) =>{
        let response = await fetch('http://localhost:8080/api/order/addProduct', {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json', 
            },
            body: JSON.stringify(productDTO),
        });
        if (response.ok) {
            console.log("Product added successfully");
            return;
        }
    }

    const fetchUpdateOrder = async (orderDTO) =>{
        console.log(JSON.stringify(orderDTO))
        let response = await fetch('http://localhost:8080/api/order/update', {
            method: 'POST', // HTTP-Methode
            headers: {
                'Content-Type': 'application/json', 
            },
            body: JSON.stringify(orderDTO), 
        });
        if (response.ok) {
            console.log("Product added successfully");
            return;
        }
    }

    return {
        order,
        fetchOrderById,
        fetchCart,
        fetchAddToCart,
        fetchUpdateOrder,
    }
}