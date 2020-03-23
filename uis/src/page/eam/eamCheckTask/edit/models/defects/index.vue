<template>
  <div>
    <el-form :model="defect" :rules="defectRules" label-suffix="：" ref="defect" style="width:700px;" label-width="100px"
      :inline-message="true" :status-icon="true" class="demo-ruleForm">
      <el-form-item label="设备位置" prop="deviceAddress">
        <el-select v-model="defect.deviceAddress" @change="addressNameGet" style="width:100%" placeholder="请选择设备位置">
          <el-option v-for="item in deviceAddresses" :key="item.key" :label="item.text" :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item  label="缺陷对象" required >
        <el-radio-group v-model="defect.objectType" @change="objectTypeChange">
          <el-radio label="BY_SYSTEM">按系统</el-radio>
          <el-radio label="BY_PROFESSIONA">按专业</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item  prop="objectKey">
        <el-select v-model="defect.objectKey" @change="objectNameGet" style="width:100%" placeholder="请选择">
          <el-option v-for="item in objects" :key="item.key" :label="item.text" :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="缺陷描述" prop="question">
        <el-input type="textarea" v-model="defect.question"></el-input>
      </el-form-item>
          <el-form-item label="问题设备" prop="devices">
        <el-input type="textarea" v-model="defect.devices"></el-input>
      </el-form-item>
         <el-form-item label="缺陷等级" prop="level">
        <el-radio-group v-model="defect.level" >
          <el-radio label="NORMAL">一般缺陷</el-radio>
          <el-radio label="MAJOR">重大缺陷</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="附件" >
           <file-upload v-model="defect.files"    ></file-upload>
      </el-form-item>
        <el-form-item label="缺陷状态" prop="status">
        <el-radio-group v-model="defect.status" >
          <el-radio label="ERROR">未处理</el-radio>
          <el-radio label="OK">已处理</el-radio>
        </el-radio-group>
      </el-form-item>
       <el-form-item>
    <el-button type="primary" @click="submitForm()" size="mini">保存</el-button>
    <el-button  size="mini" @click="cancelForm()">取消</el-button>
  </el-form-item>
    </el-form>
  </div>
</template>
<script>
import datas from './datas'

import fileUpload from '@components/upload/index'
export default datas
datas.components = {
  'file-upload': fileUpload
}
</script>
<style lang="scss" scoped>
  @import "./styles.scss";
</style>
