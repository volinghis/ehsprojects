<template>
  <div class="cardHeight">
    <div class="fromHeight" style="margin: 0px 0px;">
      <div style="width:20%;float:left;">
         <div class="queryBodys">
          <el-form ref="ruleForm" style="width:700px;" label-suffix="：" label-position="left" size="mini" label-width="80px" :inline-message="true" :status-icon="true"
            class="demo-ruleForm">
            <el-form-item label="仓库">
              <el-radio-group v-model="queryBean.warehouse" @change="flushData()">
                <el-radio border label="ALL">全部</el-radio>
                <el-radio border label="DAY">一号仓库</el-radio>
                <el-radio border label="WEEK">周</el-radio>
                <el-radio border label="MONTH">月</el-radio>
                <el-radio border label="YEAR">年</el-radio>
              </el-radio-group>
            </el-form-item>

              <el-form-item label="出库类型">
              <el-radio-group v-model="queryBean.outBoundType" @change="flushData()">
                <el-radio border label="ALL">全部</el-radio>
                <el-radio border label="OWNER">我创建的</el-radio>
                <el-radio border label="NEEDEXECUTE">需要我执行的</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="审批状态">
              <el-radio-group v-model="queryBean.status" @change="flushData()">
                <el-radio border label="ALL">全部</el-radio>
                <el-radio border label="ENABLE">负责人审核</el-radio>
                <el-radio border label="ENABLE">已驳回</el-radio>
                <el-radio border label="DISABLE">已结束</el-radio>
                <el-radio border label="DISABLE">已超时</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="线路类型" prop="isGive">
              <el-radio-group v-model="queryBean.isGive">
                <el-radio border label="ALL">全部</el-radio>
                <el-radio :label="item.id" :key="item.id" v-for="item in isGive" >{{item.name}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-input v-model="queryBean.query" placeholder="请输入备件名称或者备件编号">
              <el-button slot="append" @click="getTableData" icon="el-icon-search"></el-button>
            </el-input>
          </el-form>
        </div>
        <div class="ehs_form_item_message">
          1)该列表显示展示所有设备信息。<br />
          2)在该页面可以进行查询和设备更新操作。<br />
          3)黄色代表已经驳回的任务，<span style="color:red;">红色</span>代表超过7天未处理的任务
        </div>
      </div>
      <div class="operatorHeight" style="float:right;">
        <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.controlSize" @click="handleAdd()">新增</el-button>
        <el-button type="success" icon="el-icon-download" class="buttonHeight" :size="GlobalCss.controlSize" @click="exportExcel()">导出</el-button>
      </div>
    </div>
    <template>
      <el-table :data="tableData" resizable :height="tableHeight" highlight-current-row border :row-class-name="tableRowClassName" :size="GlobalCss.buttonSize" style="width: 100%;">
        <el-table-column prop="wareHouseName" label="所在仓库" sortable align="center"></el-table-column>
        <el-table-column prop="wareHouseCode" label="出库编号" sortable align="center">
          <template slot-scope="scope">
            <el-link type="primary" @click="handleClick(scope.row)">{{scope.row.wareHouseCode}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="deviceCode" label="备件编号" sortable align="center"></el-table-column>
        <el-table-column prop="deviceName" label="备件名称" sortable align="center"></el-table-column>
        <el-table-column prop="norm" label="规格型号" sortable align="center"></el-table-column>
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
        <el-pagination class="pageHeight" background :current-page.sync="queryBean.page"
                       :page-size="queryBean.size" layout="total, prev, pager, next" :total="totalCount">
        </el-pagination>
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
