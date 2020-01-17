import Router from '@/configs/router'
function openFlowWin (routerName, obj, closeCallBack) {
  let processInfo = JSON.stringify(obj)
  const router = Router.resolve({
    name: 'flow' + routerName,
    params: { processInfo: processInfo }
  })
  var w = document.body.offsetWidth - 20
  var h = document.body.offsetHeight
  var openerWin = window.open(router.href, 'flowwin' + (obj.processDefineKey ? obj.processDefineKey : '') + (obj.processInstanceId ? obj.processInstanceId : ''), 'status=0,toolbar=0,titlebar=0,location=0,menubar=0,resizable=0,scrollbars=0,width=' + w + ',height=' + h)
  var openerTimer = setInterval(function () {
    if (openerWin.closed) {
      if (closeCallBack) {
        try { closeCallBack() } catch (e) {}
      }
      clearInterval(openerTimer)
    }
  }, 500)
}

export default {
  openFlowWin
}
