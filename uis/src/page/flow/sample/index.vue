<template>
  <div>
    开启流程页面
    this.GlobalMethods.openWin('flowroleedit', { processDefineKey: 'TestFlow' })
  </div>
</template>>
<script>
export default {
  methods: {
    handerSubmit: function (process) {
      console.log(process)
    }
  }
}
</script>
