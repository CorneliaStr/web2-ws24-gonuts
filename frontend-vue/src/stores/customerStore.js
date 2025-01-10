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

    return {
        customer,
        getCustomer,
    }
})