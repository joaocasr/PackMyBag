import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import CatalogueView from '@/views/CatalogueView.vue'
import AboutView from '@/views/AboutView.vue'
import SignUpView from '@/views/SignUpView.vue'
import SignUpView2 from '@/views/SignUpView2.vue'

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
      path: '/signup',
      name: 'signup',
      component: SignUpView
    },
    {
      path: '/signup/signup2',
      name: 'signup2',
      component: SignUpView2
    },
    {
      path: '/stylists',
      name: 'stylists',
      component: AboutView
    }
  ]
})

export default router
