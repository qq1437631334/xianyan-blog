import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/index',
    name: 'layout',
    component: () => import('@/views/layout/index'),
    children: [
      {
        path: '/index',
        name: 'Index',
        component: () => import('@/views/index/index')
      },
      {
        path: '/type',
        name: 'Type',
        component: () => import('@/views/type/index')
      },
      {
        path: '/blogList/:typeId',
        name: 'BlogList',
        component: () => import('@/views/blog/blog-list')
      },
      {
        path: '/search/:blogTitle',
        name: 'Search',
        component: () => import('@/views/blog/search')
      },
      {
        path: '/timeLine',
        name: 'TimeLine',
        component: () => import('@/views/time-line/index')
      },
      {
        path: '/about',
        name: 'About',
        component: () => import('@/views/about/index')
      },
      {
        path: '/manager',
        name: 'Manager',
        redirect: '/manager/userInfo',
        component: () => import('@/views/manager/index'),
        children: [
          {
            path: 'userInfo',
            name: 'UserInfo',
            component: () => import('@/views/manager/user-info')
          },
          {
            path: 'collection',
            name: 'Collection',
            component: () => import('@/views/manager/user-collection')
          },
          {
            path: 'comment',
            name: 'Comment',
            component: () => import('@/views/manager/user-comment')
          }
        ]
      },
      {
        path: '/info/:id',
        name: 'Info',
        component: () => import('@/views/blog/index')
      }
    ]
  },

  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/index')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: '/',
  routes
})

export default router
