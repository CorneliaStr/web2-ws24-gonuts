import Home from "@/components/Home.vue";
import Shop from "@/components/Shop.vue";
import ProductDetail from "@/components/ProductDetail.vue";
import { createRouter, createWebHashHistory } from "vue-router";
import Favorites from "@/components/Favorites.vue";
import Cart from "@/components/Cart.vue";
import Account from "@/components/Account.vue";


const routes = [
    { path: "/", component: Home },
    { path: "/shop", component: Shop },
    { path: "/product/:id", component: ProductDetail },
    { path: "/favorites", component: Favorites },
    { path: "/cart", component: Cart },
    { path: "/account", component: Account },

];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

export default router;