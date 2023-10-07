import Vue from 'vue'
import VueRouter from 'vue-router'

//导入组件
import Manager from '../views/Manager'
import Login from '../views/Login'
import Register from '../views/Register'

import UserView from '../components/UserView'
import Home from '../components/Home'
import Test from '../components/Test'

Vue.use(VueRouter)


const routes = [
  {
    path: '/',
    name: 'Manager',
    component: Manager,
    redirect: to => {
      // 方法接收目标路由作为参数
      // return 重定向的字符串路径/路径对象
        return { path: '/login'}
    },
    children:[
      {
        path: '/user',
        name: 'UserView',
        component: UserView
      },
      {
        path: '/home',
        name: 'Home',
        component: Home
      },
      {
        path: '/test',
        name: 'Test',
        component: Test
      },
    ]
  },
  {
    name:'Login',
    path:'/login',
    component:Login
  },
  {
    name:'Register',
    path:'/Register',
    component:Register
  }
  
  

]

const router = new VueRouter({
  routes
})

export default router
