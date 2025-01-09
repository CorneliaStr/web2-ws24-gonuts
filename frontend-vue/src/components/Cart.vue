<script setup>
import { onBeforeMount, computed } from 'vue';
import Button from 'primevue/button';
import { useOrderStore } from '@/stores/orderStore.js';
import { useAuthStore} from "@/stores/authStore.js";


const orderStore = useOrderStore();
const order = computed(() => orderStore.order);

order.value = {
    orderPosition: [] 
};

const authStore = useAuthStore();
const token = computed(() => authStore.token);

onBeforeMount(async () => {
    orderStore.getCart(token.value);

});

// Berechne die Gesamtsumme
const totalPrice = computed(() => {
  return order.value?.orderPosition?.reduce((sum, position) => {
    return sum + (position.product?.price * position.quantity || 0);
  }, 0) || 0;
});

function updateQuantity(orderId, newQuantity) {
      console.log(`OrderPosition mit ID ${orderId} wurde auf ${newQuantity} aktualisiert.`);
      orderStore.updateOrder(order.value);
    }

function deleteProduct( orderPosition, orderID ){
    console.log(orderID)
    orderStore.deleteOrderPosition(orderPosition, orderID);
}

</script>


<template>
    <div class="breadcrump">
        <router-link class="link" to="../cart"><span class="activeLink">1. Warenkorb</span></router-link>
        <router-link class="link" to="../shipping">2. Versand</router-link>
        <router-link class="link" to="../payment">3. Bezahlmethode</router-link>
    </div>
    <div class="main-content">
        <div class="warenkorb">
            <h1>Warenkorb</h1>
            <section id="orderList" class="orderList">
                <div v-if="order.orderPosition && order.orderPosition.length > 0">
                    <div class="productList" v-for="orderPosition in order.orderPosition" :key="orderPosition.id">
                        <div class="product">
                            <img 
                                :src="'/src/assets' + (orderPosition.product?.image || 'default.jpg')" 
                                class="image-product" 
                                alt="...">
                            <div class="beschreibung">
                                <span>{{ orderPosition.product?.name || 'Unbekanntes Produkt' }}</span>
                                <p>{{ orderPosition.product?.description || 'Keine Beschreibung verfügbar' }}</p>
                                <p>{{ orderPosition.product?.price || 0 }} €</p>
                            </div>
                        </div>
                        <select v-model="orderPosition.quantity" @change="updateQuantity(orderPosition.id, orderPosition.quantity)">
                            <!-- Optionen von 1 bis 100 -->
                            <option v-for="i in 100" :key="i" :value="i">{{ i }}</option>
                        </select>
                        <Button id="deleteBTN" size="small" label="Entfernen" v-on:click="deleteProduct(orderPosition, order.id)"  severity="danger" />
                    </div>
                </div>
                <div v-else>
                    <p>Loading...</p>
                </div>
            </section>
            <br>
            <hr>
        </div>
        <div class="uebersicht">
            <h1>Übersicht</h1>
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
        <Button as="router-link" label="Weiter" to="/shipping" />
        <Button as="router-link" label="Abbrechen" to="/" severity="secondary" />
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

    .link{
        color: #000;
        text-decoration: none;
    }

    .activeLink{
        font-weight: bold;
    }

    .main-content {
        width: 100%;
        display: flex;
    }
    .warenkorb {
        flex: 7;
    }

    .uebersicht{
        flex: 3;
        margin-left: 5%;
    }

    .uebersicht h3 {
        display: flex;
        justify-content: space-between;
        margin: 10px 0;
    }

    .align-right {
        margin-left: auto; 
        text-align: right;
        display: inline-block;
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

    .orderList{
        margin-top: 3%;
    }

    .productList{
        margin-top:2%;
        display: flex;
        width: 100%;
    }
    
    .buttons{
        padding: 2%;
    }

    select{
        flex: 1;
        margin-right: 10px;
        height: 50px;
        width: 100px;
    }

    #deleteBTN{
        flex: 1;
        margin-right: 10px;
        height: 50px;
        width: 200px;
    }




</style>