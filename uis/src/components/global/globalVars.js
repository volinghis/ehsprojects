const computeWindowSizeMethodName = 'resizeWindowMethod'
const addTabsMethodName = 'addTabsMethod'
const removeTabsMethodName = 'removeTabsMethod'
const userToken = 'USER_TOKEN'
const userLocal = 'USER_ACCOUNT'
const servletContextPath = ''
const setResourceMenuKeyMethod = 'setResourceMenuKey'
const ehsecharts = 'ehsecharts'
const setBreadcrumbItemsMethod = 'setBreadcrumbItems'
const globalServiceServlet = process.env.NODE_ENV === 'production' ? '/platformehs' : '/ehs'
export default {
  computeWindowSizeMethodName,
  servletContextPath,
  userToken,
  addTabsMethodName,
  removeTabsMethodName,
  setResourceMenuKeyMethod,
  userLocal,
  ehsecharts,
  setBreadcrumbItemsMethod,
  globalServiceServlet
}
