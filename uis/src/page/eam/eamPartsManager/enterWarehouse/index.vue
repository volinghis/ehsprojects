<template>
  <div class="cardHeight">
    <div class="fromHeight" style="margin: 0px 0px;">
      <div style="width:20%;float:left;">
        <!-- <el-autocomplete class="inline-input"
                         :size="GlobalCss.controlSize"
                         style="width:100%;"
                         v-model="form.query"
                         :fetch-suggestions="querySearch"
                         placeholder="可搜索编号，名称，类型"
                         @select="handleSelect">
          <el-button slot="append" icon="el-icon-search" @click="() => (queryParam = {})"></el-button>
        </el-autocomplete> -->
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
      <el-table :data="tableData" resizable  highlight-current-row border  @row-dblclick="handleClick" :span-method="objectSpanMethod" :size="GlobalCss.buttonSize" style="width: 100%;">
        <el-table-column prop="wareHouseName" label="所在仓库" align="center"></el-table-column>
        <el-table-column prop="wareHouseCode" label="入库编号" align="center"></el-table-column>
        <el-table-column prop="deviceCode" label="备件编号" align="center"></el-table-column>
        <el-table-column prop="deviceName" label="备件名称" align="center"></el-table-column>
        <el-table-column prop="norm" label="规格型号" width="100" align="center"></el-table-column>
        <el-table-column prop="leaveFactoryCode" label="出厂编号" align="center"></el-table-column>
        <el-table-column prop="leaveFactoryDate" label="出厂日期" align="center"></el-table-column>
        <el-table-column prop="supplier" label="供应商" align="center"></el-table-column>
        <el-table-column prop="amount" label="数量" align="center" width="90"></el-table-column>
        <el-table-column prop="price" label="单价" align="center" width="90"></el-table-column>
        <el-table-column prop="unit" label="单位" align="center" width="90"></el-table-column>
        <el-table-column prop="totalPrice" label="总价" align="center" width="90"></el-table-column>
        <el-table-column prop="status" label="任务状态" align="center" width="90">
          <template slot-scope="scope">
            <div slot="reference">
              <el-tag size="mini" v-if="(scope.row.status === '负责人审核')" type="primary">{{ scope.row.status}}</el-tag>
              <el-tag size="mini" v-else-if="(scope.row.status  === '已结束')" type="success">{{ scope.row.status}}</el-tag>
              <el-tag size="mini" v-else-if="(scope.row.status  === '填写单据')" type="danger">{{'已驳回'}}</el-tag>
            </div>
          </template>
        </el-table-column>
      <el-table-column prop="reviewer" label="审核人" align="center"></el-table-column>
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
