import Home from "@/components/Home.vue";
import Shop from "@/components/Shop.vue";
import ProductDetail from "@/components/ProductDetail.vue";
import { createRouter, createWebHashHistory } from "vue-router";
import Favorites from "@/components/Favorites.vue";
import Cart from "@/components/Cart.vue";
import Account from "@/components/Account.vue";
import Shipping from "@/components/Shipping.vue";
import Payment from "@/components/Payment.vue";


const routes = [
    { path: "/", component: Home },
    { path: "/shop", component: Shop },
    { path: "/product/:id", component: ProductDetail },
    { path: "/favorites/:id", component: Favorites },
    { path: "/cart", component: Cart },
    { path: "/account", component: Account },
    { path: "/shipping", component: Shipping },
    { path: "/payment", component: Payment },

];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

export default router;