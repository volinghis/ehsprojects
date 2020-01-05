import Router from '@/configs/router'
function openWin (routerName, obj) {
  let processInfo = JSON.stringify(obj)
  const router = Router.resolve({
    name: routerName,
    params: { processInfo: processInfo }
  })
  // Router.push({ name: 'flowroleedit', params: { processDefineKey: 'TestFlow' } })
  // window.open(router.href + '?' + 'params=' + JSON.stringify(obj), '_blank')
  window.open(router.href, '_blank')
}

export default {
  openWin
}
