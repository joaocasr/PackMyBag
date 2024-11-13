import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import CatalogueView from '@/views/CatalogueView.vue'
import DetailedItemView from '@/views/DetailedItemView.vue'
import AboutView from '@/views/AboutView.vue'
import SignUpView from '@/views/SignUpView.vue'
import LoginView from '@/views/LoginView.vue'
import StylistsView from '@/views/StylistsView.vue'
import FormsView from '@/views/FormsView.vue'
import FavouritesView from '@/views/FavouritesView.vue'
import CartView from '@/views/CartView.vue'
import ShopHomeView from '@/views/ShopHomeView.vue'
import OrderView from '@/views/OrderView.vue'
import OrderDetailsView from '@/views/OrderDetailsView.vue'
import PaymentsView from '@/views/PaymentsView.vue'
import AddFootWear from '@/views/AddFootWear.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta:{
        "notShop":true
      }
    },
    {
      path: '/home/shop',
      name: 'shophome',
      component: ShopHomeView,
    },
    {
      path: '/catalogue',
      name: 'catalogue',
      component: CatalogueView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    {
      path: '/items/:id',
      name: 'detaileditem',
      component: DetailedItemView
    },
    {
      path: '/favourites',
      name: 'favourites',
      component: FavouritesView,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/payments',
      name: 'payments',
      component: PaymentsView,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/cart',
      name: 'cart',
      component: CartView,
      meta: {
        requiresAuth: true,
      }
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignUpView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/stylists',
      name: 'stylists',
      component: StylistsView
    },
    {
      path: '/stylists/forms',
      name: 'forms',
      component: FormsView
    },
    {
      path: '/orders',
      name: 'orders',
      component: OrderView,
      // meta: {
      //   requiresAuth: true,
      // }
    },
    {
      path: '/ordersdetails/:orderCode',
      name: 'ordersdetails',
      component: OrderDetailsView,
      props: true 
      // meta: {
      //   requiresAuth: true,
      // }
    },
    {
      path: '/addpart',
      name: 'addpart',
      component: () => import('@/views/AddPartView.vue'),
    },
    {
      path: '/addset',
      name: 'addset',
      component: () => import('@/views/AddSetView.vue'),
    },
    {
      path: '/addfootwear',
      name: 'addfootwear',
      component: AddFootWear,
      // component: () => import('@/views/AddFootWearView.vue'),
    }
  ]
})

import authService from '@/services/auth-service'
router.beforeEach((to, from, next) => {
  if (to.meta.notShop==true) {
    if(authService.getToken()!=null && authService.getToken().role=="Tecnico"){
      next("/home/shop");
    }
  }
  if (to.meta.requiresAuth==true) {
    if(authService.getToken()==null){
      next("login");
    }
    if(authService.getToken()!=null){
      next();
    }
  }
  else{
    next();
  }
});

export default router
