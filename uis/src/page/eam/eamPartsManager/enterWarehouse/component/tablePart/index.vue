<template>
  <div>
    <el-table :data="tableData"
              resizable
              highlight-current-row
              border
              :max-height="tableHeight"
              :size="GlobalCss.buttonSize"
              :summary-method="getSummaries"
              show-summary
              style="width: 100%;">
      <el-table-column fixed="left"
                       prop="deviceCode"
                       label="备件编号"
                       align="center"></el-table-column>
      <el-table-column fixed="left"
                       prop="deviceName"
                       label="备件名称"
                       align="center"></el-table-column>
      <el-table-column prop="norm"
                       label="规格型号"
                       width="130"
                       align="center"></el-table-column>
      <el-table-column prop="leaveFactoryCode"
                       label="出厂编号"
                       width="130"
                       align="center"></el-table-column>
      <el-table-column prop="leaveFactoryDate"
                       label="出厂日期"
                       width="130"
                       align="center"></el-table-column>
      <el-table-column prop="supplier"
                       label="供应商"
                       width="130"
                       align="center"></el-table-column>
      <!-- <el-table-column prop="warningValue"
                       label="预警值"
                       width="130"
                       align="center"></el-table-column> -->
      <el-table-column prop="amount"
                       label="数量"
                       align="center"></el-table-column>
      <el-table-column prop="price"
                       label="单价"
                       align="center"></el-table-column>
      <el-table-column prop="unit"
                       label="单位"
                       align="center"></el-table-column>
      <el-table-column prop="totalPrice"
                       label="总价"
                       align="center"></el-table-column>
      <el-table-column fixed="right"
                       label="操作"
                       width="160"
                       align="center">
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row)"
                     :size="GlobalCss.buttonSize"
                     type="primary">编辑</el-button>
          <el-button @click="handleDel(scope.$index,tableData)"
                     :size="GlobalCss.buttonSize"
                     type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination class="pageHeight"
                   background
                   style="text-align:right;"
                   :current-page.sync="form.page"
                   :page-size="form.size"
                   layout="total, prev, pager, next"
                   :total="totalCount">
    </el-pagination>
    <el-drawer title="备件编辑"
               :visible.sync="drawer"
               :direction="direction"
               destroy-on-close
               :before-close="handleClose">
      <el-divider></el-divider>
      <editPart ref="partData"
                :partsForm="partsFormEdit"></editPart>
      <div style="text-align:center;">
        <el-button type="primary"
                   :size="GlobalCss.buttonSize"
                   @click="saveForm">保 存</el-button>
        <el-button :size="GlobalCss.buttonSize"
                   @click="handleClose">取 消</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
