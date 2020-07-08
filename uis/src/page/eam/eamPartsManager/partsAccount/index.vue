<template>
  <div>
    <el-card shadow="hover" style="margin-bottom:20px;">
    <div class="queryBodys">
      <el-form ref="ruleForm" style="width:700px;" label-suffix="：" label-position="right" size="mini" label-width="110px" :inline-message="true" :status-icon="true" class="demo-ruleForm">
        <el-form-item label="查询" >
          <el-input size="small" v-model="queryBean.query" placeholder="请输入商品名称、编号、规格、物资编码、物资类型" style="width:61%;">
            <el-button slot="append" @click="getTableData" icon="el-icon-search"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item label="仓库">
          <el-radio-group v-model="queryBean.warehouseNames" @change="getTableData()">
            <el-radio border label="ALL">全部</el-radio>
            <el-radio border :key="item.key" :label="item.text" :value="item.key" v-for="item in wareHouses">{{item.text}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="库存是否充足" >
          <el-radio-group v-model="queryBean.reserve" @change="getTableData()">
            <el-radio border label="ALL">全部</el-radio>
            <el-radio border label="ENOUGH">充足</el-radio>
            <el-radio border label="NOENOUGH">预警</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </div>
    <div class="ehs_form_item_message">
      1)该列表显示展示所有商品信息。<br />
      2)在该页面可以根据各种条件进行查询。<br />
    </div>
    </el-card>
    <!-- <div class="operate">
       <el-button type="primary" icon="el-icon-plus" :size="GlobalCss.controlSize" @click="handleAdd()">新增</el-button>
       <el-button type="success" icon="el-icon-download" class="buttonHeight" :size="GlobalCss.controlSize" @click="exportExcel()">导出</el-button>
    </div> -->
    <el-card shadow="hover">
    <template>
      <el-table :data="tableData" ref="multipleTable" resizable border highlight-current-row :size="GlobalCss.buttonSize">
        <el-table-column fixed="left" type="index" width="50" align="center"></el-table-column>
        <!-- <el-table-column prop="partsImg" label="图片" width="70" sortable show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-image style="width: 30px; height: 30px" :src="scope.row.partsImg" :preview-src-list="[scope.row.partsImg]"></el-image>
          </template>
        </el-table-column> -->
        <el-table-column prop="deviceCode" label="商品编号" min-width="100" sortable show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-link type="primary" @click="handleView(scope.row)">{{scope.row.deviceCode}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="deviceName" label="商品名称" min-width="100" sortable show-overflow-tooltip align="center"></el-table-column>
        <!-- <el-table-column prop="completePoint" label="资料完整度" sortable show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-progress :percentage="scope.row.completePoint" :color="customColors" sortable show-overflow-tooltip align="center"></el-progress>
          </template>
        </!--> -->
        <el-table-column prop="wareHouseCode" label="仓库编码" min-width="100" sortable show-overflow-tooltip align="center"></el-table-column>
        <el-table-column prop="wareHouseName" label="所在仓库" min-width="100" sortable show-overflow-tooltip align="center"></el-table-column>
        <el-table-column prop="norm" label="规格型号" min-width="100" sortable show-overflow-tooltip align="center"></el-table-column>
        <el-table-column prop="materialCode" label="物资编码"  min-width="100" sortable show-overflow-tooltip align="center"></el-table-column>
        <el-table-column prop="materialType" label="物资类别"  min-width="100" sortable show-overflow-tooltip align="center"></el-table-column>
        <!-- <el-table-column prop="manufacturer"  label="生产厂家" sortable show-overflow-tooltip align="center"></el-table-column> -->
        <el-table-column prop="amount" label="数量" min-width="80" sortable show-overflow-tooltip align="center"> </el-table-column>
        <el-table-column prop="price" label="单价" min-width="80" sortable show-overflow-tooltip align="center"> </el-table-column>
        <el-table-column prop="warningValue" label="预警值" min-width="90" sortable show-overflow-tooltip align="center"></el-table-column>
      </el-table>
      <div>
        <div style="text-align:right;">
          <el-pagination class="pageHeight" :current-page.sync="queryBean.page" :page-size="queryBean.size"
                         layout="total, prev, pager, next" :total="queryBean.totalCount">
          </el-pagination>
        </div>
      </div>
    </template>
    </el-card>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
