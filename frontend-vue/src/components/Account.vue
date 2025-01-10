<template>
  <form class="shipping-form" @submit.prevent="handleSubmit">
    <div class="form-group">
      <label for="name">Name:</label>
      <input
          type="text"
          id="name"
          v-model="customer.name"
          required
          minlength="2"
          maxlength="50"
      />
    </div>
    <div class="form-group">
      <label for="surname">Nachname:</label>
      <input
          type="text"
          id="surname"
          v-model="customer.surname"
          required
          minlength="2"
          maxlength="50"
      />
    </div>
    <div class="form-group">
      <label for="adress">Adresse:</label>
      <input
          type="text"
          id="adress"
          v-model="customer.adress"
          required
          minlength="5"
          maxlength="100"
      />
    </div>
    <div class="form-group">
      <label for="email">E-Mail:</label>
      <input
          type="email"
          id="email"
          v-model="customer.account.email"
          required
          maxlength="100"
      />
    </div>
    <div class="form-group">
      <label for="birthdate">Geburtsdatum:</label>
      <input
          type="date"
          id="birthdate"
          v-model="customer.birthday"
          required
      />
    </div>
    <div class="form-group"></div>

    <button class="form-group" type="submit">Speichern</button>
  </form>
</template>

<script setup>
import { onBeforeMount, computed } from 'vue';
import {useCustomerStore} from "@/stores/customerStore.js";
import {useAuthStore} from "@/stores/authStore.js";

const authStore = useAuthStore();
const token = computed(() => authStore.token);

const customerStore = useCustomerStore();
const customer = computed(() => customerStore.customer);

const handleSubmit = () => {
  customerStore.updateCustomer(customer.value);
};

onBeforeMount(() => {
  customerStore.getCustomer(token.value);
})

</script>

<style scoped>
.shipping-form {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

label {
  margin-bottom: 5px;
}

input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 14px;
}

input:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

#birthdate {
  cursor: pointer;
}
</style>