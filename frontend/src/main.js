import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue';
import store from './store';
import PointsPage from "./views/PointsPage.vue";
import LoginRegister from "./views/LoginRegister.vue";
import NotFound from "./views/NotFound.vue";

const routes = [
    {
        path: '/',
        name: 'LoginRegister',
        component: LoginRegister
    },
    {
        path: '/points',
        name: 'PointsPage',
        component: PointsPage
    },
    {
        path: '/:catchAll(.*)',
        name: 'NotFound',
        component: NotFound
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

createApp(App).use(router).use(store).mount('#app');
