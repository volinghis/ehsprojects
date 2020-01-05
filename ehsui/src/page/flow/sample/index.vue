<template>
  <div>
    开启流程页面 param1:flow+routername,param2:流程对象
    this.GlobalMethods.openWin('flowroleedit', { processDefineKey: 'TestFlow' })
   <el-input
        v-model="flowSample.content"
        :rows="4"
        placeholder="内容"
        />
  </div>
</template>
<script>
import datas from './datas.js'
export default datas
</script>
