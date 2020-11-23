import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import antd from 'ant-design-vue'
import APlayer from '@moefe/vue-aplayer'
import 'ant-design-vue/dist/antd.css'
import '@/permission'
Vue.config.productionTip = false
Vue.use(antd)
Vue.use(APlayer, {
  defaultCover: 'https://github.com/u3u.png',
  productionTip: true
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
