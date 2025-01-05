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
            method: 'POST', // HTTP-Methode
            headers: {
                'Content-Type': 'application/json', // Damit der Server weiß, dass JSON gesendet wird
            },
            body: JSON.stringify(productDTO), // Konvertieren des Objekts in JSON
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
        fetchAddToCart
    }
}