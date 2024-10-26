import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import CatalogueView from '@/views/CatalogueView.vue'
import DetailedItemView from '@/views/DetailedItemView.vue'

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
      path: '/items/:id',
      name: 'detaileditem',
      component: DetailedItemView
    }
  ]
})

export default router
