
<template>
  <div class="hello">
    <chart ref="chart1" style="height:100%:width:100%" :theme="GlobalVars.ehsecharts" :options="orgOptions" :auto-resize="true"></chart>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss'
</style>
