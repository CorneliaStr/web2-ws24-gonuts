import {defineStore} from "pinia";
import {ref} from "vue"

export const useCustomerStore = defineStore("customer", () => {
    const customer = ref({
        name: "",
        surname: "",
        adress: "",
        account: {
            email: "",
        },
        birthday: "",
    });

    function getCustomer(token) {
        fetch(`http://localhost:8080/api/customer`, {
            method: 'GET',
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(data => {
                customer.value = data;
            });
    }

    function updateCustomer(customer) {
        fetch(`http://localhost:8080/api/customer`, {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(customer),
        }).then(res => res.json())
            .then(data => {
                customer.value = data;
            });

    }

    function clearCustomer() {
        customer.value = null;
    }

    return {
        customer,
        getCustomer,
        updateCustomer,
        clearCustomer,
    }
})