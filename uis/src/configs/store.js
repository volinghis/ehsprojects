import Vue from 'vue'
import Vuex from 'vuex'
import Router from '@/configs/router'
import GlobalCss from '@/styles/variables.scss'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    contentHeight: 0,
    menuDatas: [],
    selectedTabs: [],
    resourceMenuKey: ''
  },
  mutations: {

    setResourceMenuKey (state, menukey) {
      state.resourceMenuKey = menukey
    },
    addTabs (state, route) {
      let isAdd = true
      for (var i = state.selectedTabs.length - 1; i >= 0; i--) {
        if (state.selectedTabs[i].name === route.name) {
          isAdd = false
        }
      }
      if (isAdd) {
        state.selectedTabs.push(route)
      }
    },
    removeTabs (state, name) {
      let c = 1
      for (let i = state.selectedTabs.length - 1; i >= 0; i--) {
        if (state.selectedTabs[i].name === name) {
          c = i
          state.selectedTabs.splice(i, 1)
        }
      }
      Router.push({ name: state.selectedTabs[c - 1].name })
    },
    resize (state) {
      let ccHeight = document.body.offsetHeight >= parseInt(GlobalCss.bodyMinHight) ? document.body.offsetHeight : parseInt(GlobalCss.bodyMinHight)
      state.contentHeight = (ccHeight - parseInt(GlobalCss.headerHeight) - parseInt(GlobalCss.tabHeight))
    }
  },
  actions: {
    resizeWindowMethod (context) {
      context.commit('resize')
    },

    addTabsMethod (context, route) {
      context.commit('addTabs', route)
    },
    removeTabsMethod (context, name) {
      context.commit('removeTabs', name)
    },
    setResourceMenuKey (context, menukey) {
      context.commit('setResourceMenuKey', menukey)
    }

  }
})
