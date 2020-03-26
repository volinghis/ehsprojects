<template>
  <div>
    <el-form :model="partsData" :rules="partsDataRules" label-suffix="：" ref="partsData" style="width:700px;" label-width="100px"
      :inline-message="true" :status-icon="true" class="demo-ruleForm">
      <el-form-item label="所在仓库" prop="wareHouse">
        <el-select v-model="partsData.wareHouse" style="width:100%" placeholder="请选择仓库位置" @change="selectWareHouse">
          <el-option v-for="item in wareHouseNames" :key="item.key" :label="item.text" :value="item.key" ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="备件名称" prop="deviceName">
        <el-input  v-model="partsData.deviceName" @focus="selectParts" placeholder="请选择备件"></el-input>
      </el-form-item>
      <el-form-item label="数量" prop="amount" >
        <el-input  v-model="partsData.amount"></el-input>
        <div class="ehs_form_item_message">剩余库存：{{overAmount}}</div>
      </el-form-item>
      <el-form-item label="领用人" prop="receivePerson">
         <UserSelector size="medium" v-model="partsData.receivePerson" :propOrgValue="partsData.receivePerson"  @change="userSelectChange"
                              ref="userSelect" style="width:100%;"></UserSelector>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()" size="mini">保存</el-button>
        <el-button  size="mini" @click="cancelForm()">取消</el-button>
      </el-form-item>
    </el-form>
    <el-dialog title="备件选择" :visible.sync="dialogTableVisible" width="60%">
    <div>
      <el-form :model="queryBean">
        <el-input :size="GlobalCss.controlSize" v-model="queryBean.query" placeholder="请输入名称、编号、类型搜索" style="margin:5px 0px; width:30%;float:left;">
          <el-button slot="append" @click="getAllParts" icon="el-icon-search"></el-button>
        </el-input>
      </el-form>
      <template>
        <el-table :data="partsTable" border :size="GlobalCss.controlSize">
          <el-table-column width="45" align="center">
            <template slot-scope="scope">
              <el-radio v-model="tableRadio" @change.native="getCurrentRow(scope.row)"></el-radio>
            </template>
          </el-table-column>
          <el-table-column prop="wareHouseCode" label="入库编号" align="center"></el-table-column>
          <el-table-column prop="wareHouseName" label="所在仓库" align="center"></el-table-column>
          <el-table-column prop="deviceCode" label="备件编号" align="center"></el-table-column>
          <el-table-column prop="deviceName" label="备件名称" align="center"></el-table-column>
          <el-table-column prop="norm" label="规格型号" align="center"></el-table-column>
          <el-table-column prop="materialType" label="物资类别" align="center"></el-table-column>
          <el-table-column prop="dummyAmount" label="数量" align="center"></el-table-column>
          <el-table-column prop="warningValue" label="预警值" align="center">
            <template slot-scope="scope">
              <div slot="reference" class="name-wrapper">
                <el-tag type="danger" size="small">{{ scope.row.warningValue }}</el-tag>
              </div>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination style="text-align:right;" background :current-page.sync="queryBean.page" :page-size="queryBean.size"
          @current-change="changePage" layout="total, prev, pager, next" :total="queryBean.totalCount">
        </el-pagination>
        <div style="margin: 10px;text-align: center;">
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="overSelect" :size="GlobalCss.buttonSize">确 定</el-button>
            <el-button @click="resetSelect" :size="GlobalCss.buttonSize">取 消</el-button>
          </span>
        </div>
      </template>
    </div>
    </el-dialog>
  </div>
</template>
<script>
import datas from './datas'

import fileUpload from '@components/upload/index'
import UserSelector from '@/components/org/user-selector/index.vue'
export default datas
datas.components = {
  'file-upload': fileUpload,
  UserSelector
}
</script>
<style lang="scss" scoped>
  @import "./styles.scss";
</style>
