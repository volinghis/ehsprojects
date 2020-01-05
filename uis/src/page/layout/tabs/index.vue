<template>
  <div class="business-tabs">
  <el-tabs  type="card" :value="activeTab" @tab-click="tabClick" @tab-remove="tabRemove">
    <el-tab-pane v-for="(item,index) in $store.state.selectedTabs" :key="item.name" :label="item.meta.title" :name="item.name" :closable="index>0"/>

  </el-tabs>
  </div>
</template>
<script>
import datas from './datas.js'
export default datas
</script>
<style lang="scss" >
@import "./styles.scss";
</style>
