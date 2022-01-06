import { createWebHistory, createRouter } from "vue-router";

const routes =  [
    {
        path: "/",
        name: "index",
        redirect: '/coin-calculator',
        component: () => import("./components/ConvertCoin")
    },
    {
        path: "/coin-calculator",
        name: "coin-calculator",
        component: () => import("./components/ConvertCoin")
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
