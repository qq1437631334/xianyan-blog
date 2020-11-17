import Vue from 'vue'
import Vuex from 'vuex'
import persistedState from 'vuex-persistedstate'
import * as Cookies from 'js-cookie'
Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    userInfo: {}
  },
  mutations: {
    SET_USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo
    }
  },
  getters: {
    getUserInfo() {
      return store.state.userInfo
    }
  },
  actions: {
  },
  modules: {
  },
  plugins: [
    persistedState({
      storage: {
        getItem: key => Cookies.get(key),
        setItem: (key, value) => Cookies.set(key, value, { expires: 7 }),
        removeItem: key => Cookies.remove(key)
      }
    })
  ]
})

export default store
