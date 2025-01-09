<template>
  <section class="product-shop">
    <img v-if="product" :src="'/src/assets' + product.image" class="product-image" alt="product.name">
    <div class="product-infos-container">
      <h2>
        {{ product.name }}
      </h2>
      <hr class="seperator">
      <div v-if="product" class="preis">
        <strong>{{ product.price }}€</strong>
        <input type="number" placeholder="Menge" v-model="quantity">
      </div>

      <hr class="seperator">

      <p>
        {{ product.description }}
      </p>
      <div>
        <router-link to="/favorites">
          <button>Zur Merkliste hinzufügen</button>
        </router-link>
        <button @click="addToCart(product,quantity, token)">Zum Warenkorb hinzufügen</button>

      </div>
    </div>
  </section>

  <hr class="seperator">

  <h2 class="heading">Ähnliche Produkte</h2>
  <section class="products-list">
    <product-card v-for="product in similarProducts.slice(0,4)" :product="product"></product-card>
  </section>

</template>

<script setup>
import {useProductsStore} from "@/stores/productsStore.js";
import {useAuthStore} from "@/stores/authStore.js";
import {computed, onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import ProductCard from "@/components/ProductCard.vue";
import {useOrderStore} from '@/stores/orderStore.js';

const route = useRoute();
const router = useRouter();

const productStore = useProductsStore();
const orderStore = useOrderStore();
const products = computed(() => productStore.products);
const product = ref({});

const quantity = ref(1);

const authStore = useAuthStore();
const token = computed(() => authStore.token);

const similarProducts = computed(() => {
  if (!product.value || !product.value.tags) return [];
  return products.value.filter(p =>
      p.tags.some((tag) => tag.id === product.value.tags[0].id)
  );
});


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

onMounted(() => {
  product.value = productStore.getProductById(route.params.id);
});
</script>

<style scoped>

.product-image {
  width: 550px;
  height: 464px;
  background: #00000000;
  border-color: #AAAAAA;
  border-width: 2px;
  border-style: solid;
  border-radius: 4px 4px 4px 4px;
}

button {
  margin: 10px;
}

.product-shop {
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  gap: 30px;
}

.product-infos-container {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  flex-wrap: nowrap;
}

.preis {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}

</style>