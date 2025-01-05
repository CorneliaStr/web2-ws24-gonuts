<template>
  <main>
    <h1>Login</h1>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="email">E-Mail</label>
        <input
            v-model="email"
            type="email"
            placeholder="E-Mail eingeben"
            required
        />
      </div>
      <div>
        <label for="password">Passwort</label>
        <input
            v-model="password"
            type="password"
            placeholder="Passwort eingeben"
            required
        />
      </div>
      <button type="submit">Einloggen</button>
    </form>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </main>
</template>

<script setup>
import {ref} from 'vue';
import { useRouter } from 'vue-router';
import {useAuthStore} from '@/stores/authStore';

const authStore = useAuthStore();
const router = useRouter();

const email = ref('');
const password = ref('');
const errorMessage = ref('');

const handleLogin = async () => {
  const success = await authStore.login(email.value, password.value);

  if (success) {
    errorMessage.value = '';
    router.push("/")
  } else {
    errorMessage.value =
        'Login fehlgeschlagen. Bitte überprüfen Sie Ihre Zugangsdaten.';
  }
};

</script>

<style scoped>
main {
  max-width: 400px;
  margin: auto auto 60px;
  padding: 20px;
}

h1 {
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  width: 100%;
  cursor: pointer;
}

.error {
  color: red;
  margin-top: 10px;
}
</style>
