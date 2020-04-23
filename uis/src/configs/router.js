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
const flowMainRoutes = {
  path: '/flow', component: _import('/flow/index'), name: 'flow', meta: { title: '流程' }
}
const adminRoutes = {
  path: '/admin', component: _import('/layout/container/index'), name: 'index', meta: { title: '管理后台' }
}
const loginRoutes = {
  path: '/login', component: _import('/user/login/index'), name: 'login', meta: { title: '登录', business: true }
}
const portalRoutes = [
  { path: '/', component: _import('/portal/home/index'), name: 'portalhome', meta: { title: '首页' } },
  { path: '/portal/news/list/index', component: _import('/portal/news/list/index'), name: 'newsList', meta: { title: '新闻列表', business: true } },
  { path: '/portal/news/view/index', component: _import('/portal/news/view/index'), name: 'newsView', meta: { title: '新闻详情', business: true } }
]
const portalMainRoutes = {
  path: '/', component: _import('/portal/main/index'), name: 'portalmain', meta: { title: '门户' }, children: portalRoutes
}

const enterRoutes = [{
  path: '/',
  component: _import('/main/index'),
  name: 'enterPage',
  meta: { title: '入口' },
  children: [
    flowMainRoutes,
    adminRoutes,
    loginRoutes,
    portalMainRoutes
  ]
}]
// 全局路由(无需嵌套)
const globalRoutes = [
  loginRoutes
]

// 主入口路由(需嵌套整体布局页面)

const vueRouter = new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  scrollBehavior: () => ({ x: 0, y: 0 }),
  isAdd: false
})
vueRouter.addRoutes(enterRoutes)

// 判断当前是否全局路由
function isGlobalRoutes (to) {
  for (var i in globalRoutes) {
    if (globalRoutes[i].name === to.name) {
      return true
    }
  }
  for (var j in portalRoutes) {
    if (portalRoutes[j].name === to.name) {
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
        router.path = type + md[i].key + (type === 'flow' ? '/:processInfo' : '')
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
  if (isGlobalRoutes(to)) {
    next({ replace: true })
  } else if (vueRouter.options.isAdd) {
    if (to.name && to.name !== 'index') {
      next({ replace: true })
    } else {
      if (to.path.startsWith('/flow')) {
        next({ path: to.path, replace: true })
      } else {
        next({ name: (adminRoutes.children.length > 0 ? adminRoutes.children[0].name : adminRoutes.name), replace: true })
      }
    }
  } else {
    Store.dispatch(GlobalVars.setResourceMenuKeyMethod, from.name)
    Axios.get(GlobalVars.globalServiceServlet + '/auth/menu/menuDatas').then(function (res) {
      Store.state.menuDatas = res.data
      var routes = []
      addDynamicMenu(routes, Store.state.menuDatas, '')
      adminRoutes.children = routes
      var flows = []
      addDynamicMenu(flows, Store.state.menuDatas, 'flow')
      flowMainRoutes.children = flows

      for (var i in enterRoutes) {
        if (enterRoutes[i].name === 'index') {
          enterRoutes[i] = adminRoutes
        }
        if (enterRoutes[i].name === 'flow') {
          enterRoutes[i] = flowMainRoutes
        }
      }
      vueRouter.addRoutes(enterRoutes)
      vueRouter.options.isAdd = true
      if (to.name && to.name !== 'index') {
        next({ replace: true })
      } else {
        if (to.path.startsWith('/flow')) {
          next({ path: to.path, replace: true })
        } else {
          next({ name: (adminRoutes.children.length > 0 ? adminRoutes.children[0].name : adminRoutes.name), replace: true })
        }
      }
    })
  }
}

vueRouter.afterEach(function (to, from) {
  if (!to.meta.business) {
    Store.dispatch(GlobalVars.addTabsMethodName, to)
    Store.dispatch(GlobalVars.setResourceMenuKeyMethod, to.name)
  }
  NProgress.done()
})
vueRouter.beforeEach((to, from, next) => { // 添加动态(菜单)路由
  console.log(to.name)
  NProgress.start()
  const token = sessionStorage.getItem(GlobalVars.userToken)
  if (!token) {
    if (isGlobalRoutes(to)) {
      next({ replace: true })
    } else {
      next({ name: 'login', replace: true })
    }
  } else {
    toDoPage(from, to, next)
  }
})
export default vueRouter
