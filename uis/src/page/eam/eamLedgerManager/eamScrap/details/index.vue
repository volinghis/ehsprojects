<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'0px 20px',background:'#fff'}" class="divHeight">
    <el-form ref="form"  :model="scrapForm" label-position="right" label-width="100px" :size="GlobalCss.buttonSize" class="formWidth" :disabled="true">
      <div class="searchHeight">
        <el-row :gutter="24">
          <el-col :span="8">
            <el-form-item label="申请人："  prop="applicant">
              <el-input v-model="scrapForm.applicant"></el-input>
            </el-form-item>
            <el-form-item label="申请部门：" prop="scrapDept">
              <el-input v-model="scrapForm.scrapDept"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报废名称：" prop="applicationName">
              <el-input v-model="scrapForm.applicationName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="申请日期：" prop="applicationTime">
              <el-date-picker v-model="scrapForm.applicationTime" type="date" placeholder="请选择日期" style="width:100%"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="报废原因："  prop="scrapReason">
            <el-input type="textarea"  :autosize="{ minRows: 2, maxRows: 6}" placeholder="请输入内容" v-model="scrapForm.scrapReason"
                      maxlength="300" show-word-limit ></el-input>
          </el-form-item>
        </el-row>
      </div>
      <!--设备表格-->
      <div class="tableHeight" :style="{height:tableHeight}">
      <el-table :data="tableData"
                border
                ref="multipleTable"
                class="tableHeight"
                :size="GlobalCss.controlSize">
        <el-table-column type="index"> </el-table-column>
        <el-table-column prop="deviceNum"
                         label="设备编号"> </el-table-column>
        <el-table-column prop="deviceName"
                         label="设备名称"> </el-table-column>
        <el-table-column prop="deviceModel"
                         label="规格型号"> </el-table-column>
        <el-table-column prop="installLocationName"
                         label="安装位置"> </el-table-column>
        <el-table-column prop="personName"
                         label="负责人"> </el-table-column>
      </el-table>
      </div>
    </el-form>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
