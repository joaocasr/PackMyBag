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

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
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
      },
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
    }
  ]
})
/* codigo para proteger rotas depois colocar
import authService from '@/services/auth-service'
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth==true) {
    if(authService.getToken()==null){
      next("login");
    }
    if(authService.getToken()!=null){
      next();
    }
  }else{
    next();
  }
});*/
export default router
