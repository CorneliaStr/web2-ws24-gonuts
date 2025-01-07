<script setup>
import { ref, onBeforeMount, computed } from 'vue';
import orderService from "@/services/orderService.js";
import Button from 'primevue/button';
import { useOrderStore } from '@/stores/orderStore.js';


const orderStore = useOrderStore();
const order = computed(() => orderStore.order);

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



</script>


<template>
    <div class="breadcrump">
        <router-link class="link" to="../cart"><span >1. Warenkorb</span></router-link>
        <router-link class="link" to="../shipping" > <span class="activeLink">2. Versand </span></router-link>
        <router-link class="link" to="../payment">3. Bezahlmethode</router-link>
    </div>
    <div class="main-content">
        <div class="versand">
            <h1>Versand</h1>
            <section class="shipping-section" aria-labelledby="shipping-heading">
                

                <!-- Versandformular -->
                <form class="shipping-form" novalidate>
                    <div>
                        <label for="name">Name:</label>
                        <input
                            type="text" 
                            id="name" 
                            v-model="order.customer.name" 
                            required 
                            minlength="2" 
                            maxlength="50" 
                            title="Bitte geben Sie einen gültigen Namen ein (nur Buchstaben, Bindestriche oder Leerzeichen, mindestens 2 Zeichen)." 
                        />
                    </div>
                    <div>
                        <label for="surname">Nachname:</label>
                        <input 
                            type="text" 
                            id="surname" 
                            v-model="order.customer.surname" 
                            required 
                            minlength="2" 
                            maxlength="50" 
                            title="Bitte geben Sie einen gültigen Nachnamen ein (nur Buchstaben, Bindestriche oder Leerzeichen, mindestens 2 Zeichen)." 
                        />
                    </div>
                    <div>
                        <label for="adress">Adresse:</label>
                        <input 
                            type="text" 
                            id="adress" 
                            v-model="order.customer.adress" 
                            required 
                            minlength="5" 
                            maxlength="100" 
                            title="Bitte geben Sie eine gültige Adresse ein (mindestens 5 Zeichen, Buchstaben, Zahlen und Sonderzeichen wie ,.- sind erlaubt)." 
                        />
                    </div>
                    <div>
                        <label for="email">E-Mail:</label>
                        <input 
                            type="email" 
                            id="email" 
                            v-model="order.customer.account.email" 
                            required 
                            maxlength="100" 
                            title="Bitte geben Sie eine gültige E-Mail-Adresse ein."
                        />
                    </div>
                    
                    <!-- Versandoptionen -->
                    <fieldset class="shipping-options">
                        <div class="shipping-option">
                            <input 
                                type="radio" 
                                name="shipping" 
                                id="free-shipping" 
                                value="free" 
                                v-model="order.shippingOption" 
                                required 
                                :checked="true"
                                @change="order.shippingOption"
                            />
                            <label for="free-shipping">
                                Kostenloser Versand<br>
                                <small>zwischen 2-3 Werktagen</small>
                            </label>
                        </div>
                        <div class="shipping-option">
                            <input 
                                type="radio" 
                                name="shipping" 
                                id="express-shipping" 
                                value="express" 
                                @change="order.shippingOption"
                                required 
                            />
                            <label for="express-shipping">
                                Lieferung am nächsten Tag<br>
                                <small>24 € vom Warenkorb</small>
                            </label>
                        </div>
                    </fieldset>
                </form>

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
        <Button as="router-link" label="Weiter" to="/payment" />
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
    .versand {
        flex: 7
    }
    .uebersicht {
        flex: 3;
    }
    .uebersicht h3 {
        display: flex;
        justify-content: space-between;
        margin: 10px 0;
    }

    .align-right {
        margin-left: auto; /* Abstand zwischen Text und rechtsbündigem Inhalt */
        text-align: right; /* Text innerhalb des span rechts ausrichten */
        display: inline-block;
    }

    .product {
        display: flex; /* Flex-Layout für Bild und Beschreibung */
        align-items: center; /* Vertikale Zentrierung */
        gap: 10px; /* Abstand zwischen Bild und Text */
    }
    .beschreibung {
        flex: 1; /* Text nimmt den restlichen Platz (75%) ein */
        padding: 0 10px; /* Innerer Abstand für den Text */
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

    .orderList{
        margin-top: 3%;
    }

    .productList{
        margin-top:2%;
    }
    
    .buttons{
        padding: 2%;
    }

        /* Versand Formular Styling */
    .shipping-section {
        padding: 20px;
        border-radius: 5px;
        flex: 1;
    }

    .shipping-form {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 10px;
    }

    .shipping-form input, 
    .shipping-form select {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border-radius: 5px;
        font-size: 14px;
    }

    /* Versandoptionen Styling */
    .shipping-options {
        border: none;
        display: flex;
        gap: 10px;
        margin-top: 10px;
    }

    .shipping-option {
        flex: 1;
        padding: 15px;
        border: 1px solid #ddd;
        border-radius: 5px;
        text-align: center;
    }

    .shipping-option input[type="radio"] {
        margin-right: 5px;
    }



</style>