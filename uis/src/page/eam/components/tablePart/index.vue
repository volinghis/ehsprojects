<template>
  <div>
    <el-table :data="tableData" resizable highlight-current-row border :max-height="tableHeight" :size="GlobalCss.buttonSize" :summary-method="getSummaries" show-summary style="width: 100%;">
      <el-table-column fixed="left" prop="id" label="备件编号" align="center"></el-table-column>
      <el-table-column fixed="left" prop="name" label="备件名称" align="center"></el-table-column>
      <el-table-column prop="type" label="备件型号" width="130" align="center"></el-table-column>
      <el-table-column prop="amount" label="数量" align="center"></el-table-column>
      <el-table-column prop="price" label="单价" align="center"></el-table-column>
      <el-table-column prop="unit" label="单位" align="center"></el-table-column>
      <el-table-column prop="totalPrice" label="总价" align="center"></el-table-column>
      <el-table-column fixed="right" label="操作" width="160" align="center">
        <template slot-scope="scope">
          <el-button @click="drawer = true" type="primary" :size="GlobalCss.buttonSize" style="margin-left: 16px;">编辑</el-button>
          <el-button @click="handleClickDel(scope.row)" :size="GlobalCss.buttonSize" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination class="pageHeight" background style="text-align:right;"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      :page-sizes="[10, 20, 50]"
      :page-size="10"
      layout="total, sizes, prev, pager, next"
      :total="100">
    </el-pagination>
    <el-drawer title="备件编辑" :visible.sync="drawer" :direction="direction" :before-close="handleClose">
      <editPart></editPart>
    </el-drawer>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss';
</style>
