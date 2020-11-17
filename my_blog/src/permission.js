import router from './router'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import store from '@/store/index'
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration
// 设置黑名单 需要登录才能访问
const blackList = [
  '/manager/userInfo',
  '/manager/collection',
  '/manager/comment'
]
/**
 * 在路由跳转之前进行一些操作
 * to：跳转的目标路由
 * from：表示从哪个路由进行跳转
 * next：这是一个函数，用于控制路由跳转（this.$router.push()）
 */
router.beforeEach(async(to, from, next) => {
  NProgress.start()
  document.title = getPageTitle(to.meta.title)
  const username = store.state.userInfo.username
  if (username) {
    next()
  } else {
    // token不存在，判断当前跳转的路由是否是黑名单
    if (blackList.indexOf(to.path) >= 0) {
      // 是黑名单，跳转到登录页
      next('/login')
    } else {
      // 不在黑名单内，可以直接继续
      next()
    }
  }
  NProgress.done()
})

// 在路由跳转之后进行一系列的操作
router.afterEach(() => {
  NProgress.done()
})
