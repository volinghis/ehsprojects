import Vue from 'vue'
import Router from 'vue-router'
import Axios from './axios'

import Store from '@/configs/store'
import GlobalVars from '@components/global/globalVars.js'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
Vue.use(Router)

const routerPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return routerPush.call(this, location).catch(error => error)
}

function _import (file) {
  try {
    return require('@/page' + file + '.vue').default
  } catch (ex) {
    return _import('/404')
  }
}
// 全局路由(无需嵌套)
const globalRoutes = [
  { path: '/404', component: _import('/404'), name: '404', meta: { title: '404' } },
  { path: '/user/login', component: _import('/user/login/index'), name: 'login', meta: { title: '登录' } }
]
// 主入口路由(需嵌套整体布局页面)
const mainRoutes = {
  path: '/', component: _import('/layout/container/index'), name: 'index', meta: { title: '门户' }, children: []
}
const flowMainRoutes = {
  path: '/flow', component: _import('/flow/index'), name: 'flow', meta: { title: '流程' }, children: []
}
const vueRouter = new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  scrollBehavior: () => ({ x: 0, y: 0 }),
  isAdd: false
})
// 判断当前是否全局路由
function isGlobalRoutes (to) {
  for (var i in globalRoutes) {
    if (globalRoutes[i].path === to.path) {
      return true
    }
  }
  return false
}
// 添加动态(菜单)路， 参数menu：菜单列表
function addDynamicMenu (routes, md, type) {
  if (md && md.length > 0) {
    for (var i = 0; i < md.length; i++) {
      if (md[i].component && md[i].leaf && !md[i].children) {
        var router = {}

        if (type === 'flow') {
          router.path = md[i].path + '/:processInfo'
        } else {
          router.path = md[i].path
        }
        router.name = type + md[i].key
        router.meta = { title: md[i].label, business: md[i].business }
        router.component = _import(md[i].component)
        routes.push(router)
      } else {
        addDynamicMenu(routes, md[i].children, type)
      }
    }
  }
}
function toDoPage (from, to, next) {
  console.log(123)
  console.log(vueRouter.query)
  if (isGlobalRoutes(to) || vueRouter.options.isAdd) {
    next()
  } else {
    Store.dispatch(GlobalVars.setResourceMenuKeyMethod, from.name)
    Axios.get(GlobalVars.globalServiceServlet + '/auth/menu/menuDatas').then(function (res) {
      Store.state.menuDatas = res.data
      var routes = []
      addDynamicMenu(routes, Store.state.menuDatas, '')
      mainRoutes.children = routes
      var flows = []
      addDynamicMenu(flows, Store.state.menuDatas, 'flow')
      flowMainRoutes.children = flows
      flowMainRoutes.children.push({ name: 'flowroleedit', meta: { business: true }, path: '/flowSample/:processInfo', component: _import('/flow/sample/index') })
      // mainRoutes.children.push(flowMainRoutes)

      vueRouter.addRoutes([// vue-routers2.2版本以上才支持。
        mainRoutes,
        flowMainRoutes,
        { path: '*', redirect: { name: '404' } }
      ])
      vueRouter.options.isAdd = true
      if (to.name === 'index') {
        next({ name: (mainRoutes.children.length > 0 ? mainRoutes.children[0].name : mainRoutes.name), replace: true })
      } else {
        next({ path: to.path, replace: true })
      }
    })
  }
}
vueRouter.addRoutes(globalRoutes)
vueRouter.afterEach(function (to, from) {
  if (!to.meta.business) {
    Store.dispatch(GlobalVars.addTabsMethodName, to)
    Store.dispatch(GlobalVars.setResourceMenuKeyMethod, to.name)
  }
  NProgress.done()
})
vueRouter.beforeEach((to, from, next) => { // 添加动态(菜单)路由
  NProgress.start()
  const token = sessionStorage.getItem(GlobalVars.userToken)
  if (!token) {
    if (isGlobalRoutes(to)) {
      next()
    } else {
      Store.dispatch(GlobalVars.setResourceMenuKeyMethod, from.name)
      Axios.get(GlobalVars.globalServiceServlet + '/base/user/getCurrentUser')
        .then(res => {
          sessionStorage.setItem(
            GlobalVars.userToken,
            JSON.stringify(res.data)
          )
          toDoPage(from, to, next)
        })
    }
  } else {
    toDoPage(from, to, next)
  }
})
export default vueRouter
