<template>
  <div class="cardHeight">
    <div class="fromHeight" style="margin: 0px 0px;">
      <div style="width:20%;float:left;">
        <el-input :size="GlobalCss.controlSize" v-model="form.query" placeholder="请输入备件名称或者备件编号">
          <el-button slot="append" @click="getTableData" icon="el-icon-search"></el-button>
        </el-input>
      </div>
      <div class="operatorHeight" style="float:right;">
        <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.controlSize" @click="handleAdd()">新增</el-button>
        <el-button type="success" icon="el-icon-download" class="buttonHeight" :size="GlobalCss.controlSize" @click="exportExcel()">导出</el-button>
      </div>
    </div>
    <template>
      <el-table :data="tableData" resizable :height="tableHeight" highlight-current-row border :row-class-name="tableRowClassName" :size="GlobalCss.buttonSize" style="width: 100%;">
        <el-table-column prop="wareHouseName" label="所在仓库" sortable align="center"></el-table-column>
        <el-table-column prop="wareHouseCode" label="入库编号" sortable align="center">
          <template slot-scope="scope">
            <el-link type="primary" @click="handleClick(scope.row)">{{scope.row.wareHouseCode}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="deviceCode" label="备件编号" sortable align="center"></el-table-column>
        <el-table-column prop="deviceName" label="备件名称" sortable align="center"></el-table-column>
        <el-table-column prop="norm" label="规格型号" width="100" sortable align="center"></el-table-column>
        <el-table-column prop="leaveFactoryCode" label="出厂编号" sortable align="center"></el-table-column>
        <el-table-column prop="leaveFactoryDate" label="出厂日期" sortable align="center"></el-table-column>
        <el-table-column prop="supplier" label="供应商" sortable align="center"></el-table-column>
        <el-table-column prop="amount" label="数量" sortable align="center" width="90"></el-table-column>
        <el-table-column prop="price" label="单价" sortable align="center" width="90"></el-table-column>
        <el-table-column prop="unit" label="单位" sortable align="center" width="90"></el-table-column>
        <el-table-column prop="totalPrice" label="总价" sortable align="center" width="90"></el-table-column>
        <el-table-column prop="status" label="任务状态" sortable align="center" width="110">
          <template slot-scope="scope">
            <div slot="reference">
              <el-tag size="mini" v-if="(scope.row.status === '负责人审核')" type="primary">{{ scope.row.status}}</el-tag>
              <el-tag size="mini" v-else-if="(scope.row.status  === '已结束')" type="success">{{ scope.row.status}}</el-tag>
              <el-tag size="mini" v-else-if="(scope.row.status  === '填写单据')" type="warning">{{'已驳回'}}</el-tag>
            </div>
          </template>
        </el-table-column>
      <el-table-column prop="reviewer" label="审核人" sortable align="center"></el-table-column>
    </el-table>
      <div style="text-align:right;">
        <el-pagination class="pageHeight" background :current-page.sync="form.page" :page-size="form.size" layout="total, prev, pager, next" :total="totalCount"></el-pagination>
      </div>
    </template>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
