<template>
<div>
        <el-table :data="datas"
                        :size="GlobalCss.buttonSize"
                           :height="(($store.state.contentHeight-10)/3-30-30)"
                        >
      <el-table-column prop="processName"
                        align="center"
                        label="申请名称">
                        <template slot-scope="scope">
     <span class="processNames" @click="processNameClick(scope.row)">{{scope.row.processName}}</span>
      </template>
      </el-table-column>
      <el-table-column prop="currentStep"
                        align="center"
                        label="当前环节">
      </el-table-column>
      <el-table-column prop="processCreateUser"
                        align="center"
                        label="申请人">
      </el-table-column>
      <el-table-column prop="processCreateTime"
                        align="center"
                        label="申请时间">
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
