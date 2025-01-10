<template>
  <section class="favorite">
    <router-link :to="'/product/' + product.id" class="element-group">
      <img v-if="product" :src="'/src/assets' + product.image" class="product-image" alt="product.name">
      <div class="element-group-v">
        <h3 class="product-name">{{product.name}}</h3>
        <strong>{{product.price}} €</strong>
      </div>
    </router-link>

    <div class="element-group">
      <input class="number-step" value="1" max="50" min="1" name="points" step="1" type="number" v-model="quantity">
      <button class="cart-button" @click="addToCart(product,quantity, token)">Zum Warenkorb hinzufügen</button>
    </div>
  </section>
</template>

<script setup>
import {useOrderStore} from "@/stores/orderStore.js";
import {computed, ref} from "vue";
import {useAuthStore} from "@/stores/authStore.js";
import {useRouter} from "vue-router";

const orderStore = useOrderStore();
const quantity = ref(1);

const authStore = useAuthStore();
const token = computed(() => authStore.token);

const router = useRouter();

const props = defineProps(["product"]);

const addToCart = async (product, quantity, token) => {
  if (authStore.isLoggedIn()) {
    await orderStore.addToCart(product, quantity, token);
    router.push("/cart");
  }
  // Die postLoginAction wird nach dem erfolgreichen Login ausgeführt.
  else {
    authStore.setPostLoginAction(async (token) => {
      await orderStore.addToCart(product, quantity, token);
      router.push("/cart");
    });
    router.push("/login");
  }
}
</script>

<style scoped>
.favorite {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.favorite:hover {
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.2);
}

.element-group {
  display: flex;
  gap: 15px;
  text-decoration: none;
  color: inherit;
}

.product-name {
  margin-bottom: 2px;
  margin-top: 5px;
  font-weight: bold;
  text-decoration: none;
}

.product-image {
  width: 120px;
  height: 140px;
}
</style>