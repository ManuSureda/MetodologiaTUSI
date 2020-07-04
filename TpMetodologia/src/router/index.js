import Vue from 'vue'
import VueRouter from 'vue-router'
//import Home from '../views/Home.vue'
import bills from '@/components/bills.vue';
import calls from '@/components/calls.vue';
import destinations from '@/components/destinations.vue';

Vue.use(VueRouter)

  const routes = [
/*  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import( webpackChunkName: "about"  '../views/About.vue')
  },*/
  {
    path: '/bills',
    name: 'bills',
    component : bills
  },
  {
    path: '/calls',
    name: 'calls',
    component : calls
  },
  {
    path: '/destinations',
    name: 'destinations',
    component : destinations
  }
]

const router = new VueRouter({
  routes
})

export default router
