import {defineStore} from "pinia";
import {ref} from "vue";

export const useAuthStore = defineStore("auth", () => {
    const token = ref(null);
    const isAdmin = ref(false);

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
                console.error(`Fehler: ${response.status}`);
                return false;
            }

            token.value = await response.text();
            checkIsAdmin();

            return true;
        } catch (error) {
            console.error('Fehler beim Login:', error);
            return false;
        }
    }

    function logout() {
        token.value = null;
        isAdmin.value = false;
    }

    async function checkIsAdmin() {
        if (!token) {
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/api/auth/isAdmin', {
                method: 'GET',
                headers: {
                    'Authorization': token.value,
                    'Content-Type': 'application/json'
                }
            });

            const result = await response.json();
            if (typeof result === 'boolean') {
                isAdmin.value = result;
            }
        } catch (error) {
            console.error("Fehler bei der Anfrage:", error);
        }
    }

    return {
        token,
        isAdmin,
        login,
        logout,
    }
})