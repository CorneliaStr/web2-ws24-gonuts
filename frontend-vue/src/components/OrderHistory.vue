<script setup>
import { onBeforeMount, computed } from 'vue';
import Button from 'primevue/button';
import { useOrderStore } from '@/stores/orderStore.js';
import { useAuthStore} from "@/stores/authStore.js";


const orderStore = useOrderStore();
const order = computed(() => orderStore.order);
const orders = computed(() => orderStore.orders);

order.value = {
    orderPosition: [] 
};

const authStore = useAuthStore();
const token = computed(() => authStore.token);

function formatDate(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(date).toLocaleDateString('de-DE', options);
    }

onBeforeMount(async () => {
 orderStore.getOrders(authStore.token);
});

function orderTotalPrice(order) {
  let totalPrice = 0;
  for(let orderPosition of order.orderPosition){
    totalPrice += orderPosition.product.price * orderPosition.quantity;
  }
  return totalPrice.toFixed(2);
};

</script>


<template>
    <main>
        <h1>Bestellhistorie</h1>
        <section v-if="orders && orders.length > 0">
            <article class="orderList" v-for="order in orders">
                <h2>Bestellnummer {{ order.id }}</h2>
                <p>Datum: {{ formatDate(order.date) }}</p>
                    <div class="productList" v-for="orderPosition in order.orderPosition" :key="orderPosition.id">
                        <div class="product">
                            <img 
                                :src="'/src/assets' + (orderPosition.product?.image || 'default.jpg')" 
                                class="image-product" 
                                alt="...">
                            <div class="beschreibung">
                                <span>{{ orderPosition.product?.name || 'Unbekanntes Produkt' }}</span>
                                <p>{{ orderPosition.product?.description || 'Keine Beschreibung verfügbar' }}</p>
                                <p>Anzahl: {{ orderPosition.quantity || 0 }}</p>
                                <p>{{ orderPosition.product?.price || 0 }} €</p>
                            </div>
                        </div>
                    </div>
                <h3>Gesamtpreis: {{ orderTotalPrice(order) }} €</h3>
            </article>

        </section>

    </main>

</template>


<style scoped>

    .main-content {
        width: 100%;
        display: flex;
    }

    .product{
        display: flex;
        flex: 8;
        padding: 10px;
    }

    .beschreibung{
        padding-left: 5%;
        padding-top:1%;
    }


    .beschreibung span {
        font-weight: bold;
    }

    .image-product{
        width: 10%;
        object-fit: cover; 
    }

    .productList{
        margin-top:2%;
        display: flex;
        width: 100%;
    }



    .orderList h2{
       margin-left: 0%;
    }

    .orderList {
      flex: 1 1 calc(20% - 20px);
      box-sizing: border-box;
      background-color: #ffffff;
      border: 1px solid #ddd;
      border-radius: 8px;
      padding: 20px;
      text-align: left;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      text-decoration: none;
      color: inherit;

      margin: 5px;
    }

</style>