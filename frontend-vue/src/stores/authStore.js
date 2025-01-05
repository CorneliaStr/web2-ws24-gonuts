import { defineStore } from "pinia";
import { ref } from "vue";

export const useAuthStore = defineStore("favorites", () => {
    const token = ref(null);

    async function login(email, password) {
        try {
            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    email: email,
                    password: password,
                }),
            });

            if (!response.ok) {
                console.log(`Fehler: ${response.status}`);
                return false;
            }

            token.value = await response.text();

            return true;
        } catch (error) {
            console.error('Fehler beim Login:', error);
            return false;
        }
    }

    function deleteToken() {
       token.value = null;
    }

    return {
        token,
        login,
        deleteToken,
    }
})