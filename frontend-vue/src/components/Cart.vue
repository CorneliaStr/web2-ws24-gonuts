<script setup>
import { ref, onBeforeMount, computed } from 'vue';
import orderService from "@/services/orderService.js";
import Button from 'primevue/button';

const { order, fetchOrderById } = orderService();

// Initialisiere mit einem Standardwert
order.value = {
    orderPosition: [] // Leeres Array, damit keine Fehler beim Zugriff auftreten
};

onBeforeMount(async () => {
    try {
        await fetchOrderById(1); // Läd die Order-Daten
    } catch (error) {
        console.error('Failed to fetch order:', error);
    }
});

// Berechne die Gesamtsumme
const totalPrice = computed(() => {
  return order.value?.orderPosition?.reduce((sum, position) => {
    return sum + (position.product?.price || 0);
  }, 0) || 0;
});


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
            <h3>Summe Artikel: {{  totalPrice.toFixed(2)  }}</h3>
            <h3>Versand: Kostenlos</h3>
            <hr>
            <h3>Gesamt: {{  totalPrice.toFixed(2)  }}</h3>
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
        flex-grow: 2;
    }
    .uebersicht {
        flex-grow: 1;
    }

    .product{
        display: flex;
    }

    .beschreibung{
        padding-left: 5%;
        padding-top:1%;
    }

    .beschreibung p{
        margin-top: 25%;
    }

    .beschreibung span {
        font-weight: bold;
    }

    .image-product{
        width: 20%;
        height: 20%;
        object-fit: cover;
        margin-right: 10px;
 
    }

    .orderList{
        margin-top: 3%;
    }

    .productList{
        margin-top:2%;
    }
    
    .buttons{
        padding: 2%;
    }


</style>