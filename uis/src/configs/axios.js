import Vue from 'vue'
import Axios from 'axios'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import GlobalVars from '@components/global/globalVars.js'
import Store from '@/configs/store'

// 添加请求拦截器
Axios.interceptors.request.use(function (config) {
  NProgress.start()
  if (config.url.indexOf('?') > 0) {
    config.url = config.url + '&tt=' + Math.random()
  } else {
    config.url = config.url + '?tt=' + Math.random()
  }
  config.url = config.url + '&resoureMenuKey=' + Store.state.resourceMenuKey

  // 在发送请求之前做些什么
  return config
}, function (error) {
  NProgress.done()
  // 对请求错误做些什么
  return Promise.reject(error)
})
// 添加响应拦截器
Axios.interceptors.response.use(function (response) {
  NProgress.done()
  // 对响应数据做点什么
  return response
}, function (error) {
  NProgress.done()
  if (error.response.status === 901) {
    sessionStorage.removeItem(GlobalVars.userToken)
    location.reload()
  } else {
    if (process.env.NODE_ENV === 'production') {
      Vue.prototype.$notify.error({
        title: '请求异常：' + error.response.status
      })
    } else {
      Vue.prototype.$notify.error({
        title: '请求异常：' + error.response.status,
        message: error.request.responseURL
      })
    }
  }
  // 对响应错误做点什么
  return Promise.reject(error)
})
export default Axios
