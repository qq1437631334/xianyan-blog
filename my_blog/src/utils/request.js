import axios from 'axios'
import { getToken } from '@/utils/auth'
import { message } from 'ant-design-vue'
import store from '@/store'
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: 20000 // 请求超时时间
})

// request interceptor
service.interceptors.request.use(
  config => {
    const token = getToken()
    if (token) {
      config.headers['Authorization'] = getToken()
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    const res = response.data

    if (res.code && res.code !== 20000) {
      if (res.code === 40003) {
        // 未登录
        message.error(res.msg)
        store.commit('SET_USER_INFO', {})
        this.$router.push('/login')
      }
      message.error(res.msg)
      return Promise.reject(res.msg)
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    return Promise.reject(error)
  }
)

export default service
