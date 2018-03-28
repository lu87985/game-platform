import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Main from '@/components/Main'
import Home from '@/components/Home'
import '../assets/css/overall.css'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/main',
      name: 'Main',
      component: Main
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    }
  ]
})
