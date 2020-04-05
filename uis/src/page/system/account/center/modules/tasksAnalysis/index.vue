
<template>
  <div class="hello">

    <chart ref="chart3"  :theme="GlobalVars.ehsecharts" :style="chartSize()" :initOptions="chartSize()" :options="orgOptions" ></chart>

  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss'
</style>
