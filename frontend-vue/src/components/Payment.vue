<script setup>
import {onBeforeMount, computed} from 'vue';
import Button from 'primevue/button';
import {useOrderStore} from '@/stores/orderStore.js';
import {useAuthStore} from '@/stores/authStore.js';

const orderStore = useOrderStore();
const authStore = useAuthStore();
const order = computed(() => orderStore.order);
const token = computed(() => authStore.token);

order.value = {
  orderPosition: [] // Leeres Array, damit keine Fehler beim Zugriff auftreten
};

onBeforeMount(async () => {
  orderStore.getOrder();
});

// Berechne die Gesamtsumme
const totalPrice = computed(() => {
  return order.value?.orderPosition?.reduce((sum, position) => {
    return sum + (position.product?.price * position.quantity || 0);
  }, 0) || 0;
});


function placeOrder() {
  order.value.date = new Date()
  orderStore.updateOrder(order.value);
}

</script>


<template>
  <div class="breadcrump">
    <router-link class="link" to="../cart"><span>1. Warenkorb</span></router-link>
    <router-link class="link" to="../shipping"> 2. Versand</router-link>
    <router-link class="link" to="../payment"><span class="activeLink">3. Bezahlmethode </span></router-link>
  </div>
  <div class="main-content">
    <div class="payment">
      <h1>Bezahlmethode</h1>
      <section class="payment-section" aria-labelledby="payment-heading">
        <h3 id="payment-heading">Bezahlmethode</h3>

        <div class="payment-option">
          <input type="radio" :checked="true" :disabled="true" name="payment" id="express-payment">
          <label for="express-payment">Auf Rechnung<br></label>
        </div>
      </section>
    </div>
    <div class="uebersicht">
      <h1>Übersicht</h1>
      <section id="orderList" class="orderList">
        <div v-if="order.orderPosition && order.orderPosition.length > 0">
          <div class="productList" v-for="orderPosition in order.orderPosition" :key="orderPosition.id">
            <div class="product">
              <img
                  :src="'/src/assets' + (orderPosition.product?.image || 'default.jpg')"
                  class="image-product"
                  alt="Produktbild">
              <div class="beschreibung">
                <span>{{ orderPosition.product?.name || 'Unbekanntes Produkt' }}</span>
                <p>{{ orderPosition.product?.description || 'Keine Beschreibung verfügbar' }}</p>
                <p>{{ orderPosition.product?.price || 0 }} €</p>
              </div>
            </div>
          </div>

        </div>
      </section>
      <br>
      <hr>
      <h3>
        Summe Artikel:
        <span class="align-right">{{ totalPrice.toFixed(2) }}</span>
      </h3>
      <h3>
        Versand:
        <span class="align-right">Kostenlos</span>
      </h3>
      <hr>
      <h3>
        Gesamt:
        <span class="align-right">{{ totalPrice.toFixed(2) }}</span>
      </h3>
    </div>
  </div>
  <section class="buttons">

    <Button as="router-link" to="/" v-bind:disabled="!token" v-on:click="placeOrder()" severity="success">Bezahlen
    </Button>
    <Button as="router-link" to="/" severity="secondary">Abbrechen</Button>
  </section>
</template>


<style scoped>

.breadcrump {
  width: 100%;
  display: flex;
  justify-content: space-evenly;
  padding: 1rem;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  text-decoration: none !important;
  background-color: lightgrey;
  margin-bottom: 3%
}

.link {
  color: #000;
  text-decoration: none;
}

.activeLink {
  font-weight: bold;
}

.main-content {
  display: flex;
}

.payment {
  flex: 7;
}

.uebersicht {
  flex: 3;
  margin-left: 2%;
}

.uebersicht h3 {
  display: flex;
  justify-content: space-between;
  margin: 10px 0;
}

.align-right {
  margin-left: auto;
  text-align: right;
}

.product {
  display: flex;
  align-items: center;
  display: inline-block;
  gap: 10px;
}

.beschreibung {
  flex: 1;
  padding: 0 10px;
}

.beschreibung span {
  font-weight: bold;
}

.image-product {
  flex: 0 0 25%;
  max-width: 100px;
  max-height: 100px;
  height: auto;
  object-fit: cover;
  border-radius: 5px;
}

.orderList {
  margin-top: 3%;
}

.productList {
  margin-top: 2%;
}

.buttons {
  padding: 2%;
}

/* Versandoptionen Styling */
.payment-options {
  border: none;
  display: flex;
  gap: 10px;
  margin-top: 10px;
}

.payment-option {
  flex: 1;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 5px;
  text-align: center;
}

.payment-option input[type="radio"] {
  margin-right: 5px;
}


</style>