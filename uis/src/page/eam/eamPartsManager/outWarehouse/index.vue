<template>
<!-- <el-card> -->
  <div class="cardHeight">
     <div class="queryBodys">
      <el-form ref="ruleForm" style="width:700px;" label-suffix="：" label-position="right" size="mini" label-width="80px" :inline-message="true" :status-icon="true" class="demo-ruleForm">
        <el-form-item label="查询" >
          <el-input size="small" v-model="queryBean.query" placeholder="请输入备件名称、编号、规格、物资编码、物资类型" style="width:61%;">
            <el-button slot="append" @click="getTableData" icon="el-icon-search"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item label="仓库">
          <el-radio-group v-model="queryBean.warehouseNames" @change="getTableData()">
            <el-radio border label="ALL">全部</el-radio>
            <el-radio border :key="item.key" :label="item.text" :value="item.key" v-for="item in wareHouses">{{item.text}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出库类型">
          <el-radio-group v-model="queryBean.outBoundTypes" @change="getTableData()">
            <el-radio border label="ALL">全部</el-radio>
            <el-radio border :key="item.key" :label="item.text" :value="item.key" v-for="item in outTypes">{{item.text}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审批状态" >
          <el-radio-group v-model="queryBean.flowstatus" @change="getTableData()">
            <el-radio border label="ALL">全部</el-radio>
            <el-radio border label="APPROVL">审批中</el-radio>
            <el-radio border label="END">已结束</el-radio>
            <el-radio border label="REJECT">已驳回</el-radio>
            <el-radio border label="OVERDUE">已逾期</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </div>
    <div class="ehs_form_item_message">
      1)该列表显示展示所有备件信息。<br />
      2)在该页面可以根据各种条件进行查询。<br />
      3)黄色代表已经驳回的任务，<span style="color:red;">红色</span>代表超过7天未处理的任务
    </div>
    <div class="operate">
       <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.controlSize" @click="handleAdd()">新增</el-button>
       <!-- <el-button type="success" icon="el-icon-download" class="buttonHeight" :size="GlobalCss.controlSize" @click="exportExcel()">导出</el-button> -->
    </div>
    <template>
      <el-table :data="tableData" resizable :max-height="tableHeight" border :row-class-name="tableRowClassName"
        :size="GlobalCss.buttonSize" style="width: 100%;">
        <el-table-column prop="wareHouseName" label="所在仓库" sortable align="center"></el-table-column>
        <el-table-column prop="wareHouseCode" label="出库编号" sortable align="center">
          <template slot-scope="scope">
            <el-link type="primary" @click="handleClick(scope.row)">{{scope.row.wareHouseCode}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="deviceCode" label="备件编号" sortable align="center"></el-table-column>
        <el-table-column prop="deviceName" label="备件名称" sortable align="center"></el-table-column>
        <el-table-column prop="norm" label="规格型号" sortable align="center"></el-table-column>
        <!-- <el-table-column prop="leaveFactoryCode" label="出厂编号" sortable align="center"></el-table-column>
        <el-table-column prop="leaveFactoryDate" label="出厂日期" sortable align="center"></el-table-column> -->
        <!-- <el-table-column prop="supplier" label="供应商" sortable align="center"></el-table-column> -->
        <el-table-column prop="amount" label="数量" sortable align="center" width="80"></el-table-column>
        <el-table-column prop="price" label="单价" sortable align="center" width="70"></el-table-column>
        <el-table-column prop="unit" label="单位" sortable align="center" width="80"></el-table-column>
        <el-table-column prop="totalPrice" label="总价" sortable align="center" width="90"></el-table-column>
        <el-table-column prop="status" label="任务状态" sortable align="center" width="95">
          <template slot-scope="scope">
            <div slot="reference">
              <el-tag size="mini" v-if="(scope.row.status === 'usertask2')" type="primary">{{ scope.row.statusName}}</el-tag>
              <el-tag size="mini" v-else-if="(scope.row.status  === 'END')" type="success">{{ scope.row.statusName}}
              </el-tag>
              <el-tag size="mini" v-else-if="(scope.row.status  === 'usertask1')" type="warning">{{'已驳回'}}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="reviewer" label="审核人" sortable align="center"></el-table-column>
      </el-table>
      <div style="text-align:right;">
        <el-pagination class="pageHeight" background :current-page.sync="queryBean.page" :page-size="queryBean.size"
          @current-change="changePage" layout="total, prev, pager, next" :total="queryBean.totalCount">
        </el-pagination>
      </div>
    </template>
  </div>
<!-- </el-card> -->
</template>
<script>
import datas from './datas'
export default datas

</script>
<style lang="scss" scoped>
  @import "./styles.scss";

</style>
