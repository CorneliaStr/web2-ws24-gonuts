import Home from "@/components/Home.vue";
import Shop from "@/components/Shop.vue";
import ProductDetail from "@/components/ProductDetail.vue";
import { createRouter, createWebHashHistory } from "vue-router";
import Favorites from "@/components/Favorites.vue";
import Cart from "@/components/Cart.vue";
import Account from "@/components/Account.vue";
import Shipping from "@/components/Shipping.vue";
import Payment from "@/components/Payment.vue";
import ProductForm from "@/components/Admin/ProductForm.vue";
import TagForm from "@/components/Admin/TagForm.vue";
import TagTable from "@/components/Admin/TagTable.vue";
import ProductTable from "@/components/Admin/ProductTable.vue";
import AdminView from "@/components/Admin/AdminView.vue";


const routes = [
    { path: "/", component: Home },
    { path: "/shop", component: Shop },
    { path: "/product/:id", component: ProductDetail },
    { path: "/favorites/:id", component: Favorites },
    { path: "/cart", component: Cart },
    { path: "/account", component: Account },
    { path: "/shipping", component: Shipping },
    { path: "/payment", component: Payment },
    { path: "/admin", component: AdminView },
    { path: "/productTable", component: ProductTable },
    { path: "/addProduct", component: ProductForm },
    { path: "/addTag", component: TagForm },
    { path: "/tagTable", component: TagTable },

];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

export default router;