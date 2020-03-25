<template>
<div>
        <el-table :data="notices"
                        :size="GlobalCss.buttonSize"
                           :height="(($store.state.contentHeight-10)/3-30-30)"
                        >
  <el-table-column prop="content">
                </el-table-column>
        </el-table>

  <el-pagination
      :current-page="pages.page"
      @current-change="changePage"
      :page-size="pages.size"

      layout="total, prev, pager, next"
      :total="pages.total">
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
