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
import Login from "@/components/Login.vue";
import {useAuthStore} from '@/stores/authStore';
import OrderHistory from "@/components/OrderHistory.vue";

const routes = [
    { path: "/", component: Home },
    { path: "/login", component: Login },
    { path: "/shop", component: Shop },
    { path: "/product/:id", component: ProductDetail },
    { path: "/favorites", component: Favorites },
    { path: "/cart", component: Cart },
    { path: "/account", component: Account },
    { path: "/shipping", component: Shipping },
    { path: "/payment", component: Payment },
    { path: "/orderHistory", component: OrderHistory },
    { path: "/admin", component: AdminView, meta: { requiresAdmin: true }},
    { path: "/productTable", component: ProductTable, meta: { requiresAdmin: true } },
    { path: "/addProduct", component: ProductForm, meta: { requiresAdmin: true } },
    { path: "/editProduct/:id", name: "editProduct", component: ProductForm, meta: { requiresAdmin: true } },
    { path: "/tagTable", component: TagTable, meta: { requiresAdmin: true } },
    { path: "/addTag", component: TagForm, meta: { requiresAdmin: true } },
    { path: '/editTag/:id', name: "editTag", component: TagForm, meta: { requiresAdmin: true } },

];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    let authStore = useAuthStore();

    if (to.matched.some(record => record.meta.requiresAdmin) && !authStore.isAdmin()) {
        next("/");
    } else {
        next();
    }
});

export default router;