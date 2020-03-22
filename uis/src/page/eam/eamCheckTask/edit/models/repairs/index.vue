<template>
  <div>
    <el-divider content-position="left">添加工作日志</el-divider>
    <el-form :model="repair" :rules="repaiRules" ref="repair" style="width:700px;" label-width="100px"
      :inline-message="true" :status-icon="true" class="demo-ruleForm">
      <el-form-item label="设备位置" prop="deviceAddress">
        <el-select v-model="ruleForm.deviceAddress" style="width:100%" placeholder="请选择设备位置">
          <el-option v-for="item in deviceAddresses" :key="item.key" :label="item.text" :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="objectType">
        <el-radio-group v-model="repair.objectType" @change="objectTypeChange">
          <el-radio label="BY_SYSTEM">按系统</el-radio>
          <el-radio label="BY_PROFESSIONA">按专业</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="检修对象" prop="objectKey">
        <el-select v-model="repair.objectKey" style="width:100%" placeholder="请选择">
          <el-option v-for="item in objects" :key="item.key" :label="item.text" :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="问题描述" prop="question">
        <el-input type="textarea" v-model="repair.question"></el-input>
      </el-form-item>
      <el-form-item label="检修记事" >
           <file-upload v-model="repair.note"    ></file-upload>
      </el-form-item>
         <el-form-item label="检修执行人" prop="userType">
        <el-radio-group v-model="repair.userType" >
          <el-radio label="OWNER">本人</el-radio>
          <el-radio label="OUTER">外部人员</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item  prop="orgName" label="公司" v-if="outerInfoShow()">
          <el-input v-model="repair.orgName" placeholder="公司" ></el-input>
      </el-form-item>
        <el-form-item prop="userName" label="姓名" v-if="outerInfoShow()">
          <el-input v-model="repair.userName" placeholder="姓名" ></el-input>
      </el-form-item>
        <el-form-item prop="result">
        <el-radio-group v-model="repair.result" >
          <el-radio label="ERROR">未解决</el-radio>
          <el-radio label="OK">已解决</el-radio>
        </el-radio-group>
      </el-form-item>
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
