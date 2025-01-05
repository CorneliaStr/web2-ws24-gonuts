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
        <input type="number" placeholder="Menge">
      </div>

      <hr class="seperator">

      <p>
        {{ product.description }}
      </p>
      <div>
        <router-link to="/favorites">
          <button class="button-secondary" >Zur Merkliste hinzufügen</button>
        </router-link>
        <router-link to="/cart">
          <button class="button-primary" @click="addToCart(product)">Zum Warenkorb hinzufügen</button>
        </router-link>

      </div>
    </div>
  </section>

  <hr class="seperator">

  <h2 class="heading">Ähnliche Produkte</h2>
  <section class="products-list">
    <product-card v-for="product in similarProducts" :product="product"></product-card>
  </section>

</template>

<script setup>
import { computed, onBeforeMount } from "vue";
import { useRoute } from "vue-router";
import productService from "@/services/productService.js";
import orderService from "@/services/orderService";
import ProductCard from "@/components/ProductCard.vue";

const route = useRoute();
const {product, products, fetchProductById, fetchProducts} = productService();
const {productDTO, fetchAddToCart} = orderService();

const similarProducts = computed(() => {
  if (!product.value || !product.value.tags) return [];
  return products.value.filter(p =>
      p.tags.some((tag) => tag.id === product.value.tags[0].id)
  );
});

onBeforeMount(() => {
  fetchProductById(route.params.id);
  fetchProducts();
});

const addToCart = async (product) =>{
  console.log(product)
  fetchAddToCart(product);
}


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

.button-secondary {
  width: 194px;
  height: 55px;
  padding: 0px 10px 0px 10px;
  background: #FFFFFF;
  color: #000000;
  border-color: #444444;
  border-width: 1px;
  border-style: solid;
  border-radius: 3px 3px 3px 3px;
  font-family: "Source Sans 3";
  font-weight: 500;
  font-size: 18px;
  text-align: center;
}

.button-primary {
  width: 194px;
  height: 55px;
  padding: 0px 10px 0px 10px;
  background: #444444;
  color: #FFFFFF;
  border-color: #444444;
  border-width: 1px;
  border-style: solid;
  border-radius: 3px 3px 3px 3px;
  font-family: "Source Sans 3";
  font-weight: 500;
  font-size: 18px;
  text-align: center;
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