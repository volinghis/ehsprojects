import Router from '@/configs/router'

function openFlowWin (routerName, obj) {
  let processInfo = JSON.stringify(obj)
  const router = Router.resolve({
    name: 'flow' + routerName,
    params: { processInfo: processInfo }
  })
  window.open(router.href, '_blank')
}

export default {
  openFlowWin

}
