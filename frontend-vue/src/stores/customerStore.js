import {defineStore} from "pinia";
import {ref} from "vue"

export const useCustomerStore = defineStore("customer", () => {
    const customer = ref(null);

    async function getCustomer(token) {
        try {
            const response = await fetch('http://localhost:8080/api/customer', {
                method: 'GET',
                headers: {
                    'Authorization': token,
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                customer.value = await response.json();
                return await response.json();

            } else if (response.status === 401) {
                console.error("Unbefugter Zugriff - Token ungültig");
            } else {
                console.error("Fehler beim Abrufen der Kundendaten", response.status);
            }
        } catch (error) {
            console.error("Fehler bei der Anfrage:", error);
        }
    }

    return {
        customer,
        getCustomer,
    }
})