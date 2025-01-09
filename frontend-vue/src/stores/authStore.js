import {defineStore} from "pinia";
import {ref} from "vue";

export const useAuthStore = defineStore("auth", () => {
    const token = ref(null);
    const admin = ref(false);
    const postLoginAction = ref(null);

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

    function executePostLoginAction() {
        if(postLoginAction.value && typeof postLoginAction.value === "function") {
            postLoginAction.value(token.value);
            postLoginAction.value = null;
        }
    }

    function setPostLoginAction(action) {
        postLoginAction.value = action;
    }

    function getPostLoginAction() {
        return postLoginAction.value;
    }

    function logout() {
        token.value = null;
        admin.value = false;
    }

    function isLoggedIn() {
        if(token.value) {
            return true;
        }
        return false;
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
                admin.value = result;
            }
        } catch (error) {
            console.error("Fehler bei der Anfrage:", error);
        }
    }

    function isAdmin() {
        return admin.value;
    }

    return {
        token,
        login,
        logout,
        isLoggedIn,
        isAdmin,
        setPostLoginAction,
        getPostLoginAction,
        executePostLoginAction,
    }
})