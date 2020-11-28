/* eslint-disable */
// import Vue from 'vue'

import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import ELEMENT from 'element-ui'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets
// 引入markdown组件
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import '@/styles/index.scss' // global css

import '@/icons' // icon
import '@/permission' // permission control


Vue.use(ELEMENT)
Vue.use(mavonEditor)

// set ElementUI lang to EN
// Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
// Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
