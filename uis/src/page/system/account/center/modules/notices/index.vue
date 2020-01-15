<template>
<div>
        <el-table :data="notices"
                        :size="GlobalCss.buttonSize"
                           :height="(($store.state.contentHeight-10)/3-30-30)"
                        >
  <el-table-column prop="notice">
                </el-table-column>
        </el-table>
     <el-pagination
      :current-page="1"
      :page-size="20"
      :pager-count="5"
      layout="total, prev, pager, next"
      :total="1000">
    </el-pagination>
</div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss'
</style>
