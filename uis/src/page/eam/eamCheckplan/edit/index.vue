<template>
<div >
  <el-card style="margin-left:10px;margin-right:10px;">
<el-divider content-position="left">创建计划</el-divider>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-suffix=":" style="width:600px;" label-width="100px" :inline-message="true" :status-icon="true" class="demo-ruleForm">

  <el-form-item label="计划名称" prop="name" >
    <el-input v-model="ruleForm.name" placeholder="请选择计划名称" ></el-input>
    <div class="ehs_form_item_message">计划名称必填，且要求3到20个字符，建议输入：xx年/月xx部门xx计划</div>
  </el-form-item>
   <el-form-item label="执行频率" >
    <el-radio-group v-model="ruleForm.rate">
      <el-radio label="DAY">天</el-radio>
      <el-radio label="WEEK">周</el-radio>
      <el-radio label="MONTH">月</el-radio>
      <el-radio label="YEAR">年</el-radio>
    </el-radio-group>
     <div class="ehs_form_item_message">选择天时，每日自动执行一次计划。选择周时，每周一执行一次计划。选择月时，每月1日执行一次计划。选择年时，每年1月1日执行一次计划</div>
  </el-form-item>
  <el-form-item label="计划年度" >
    <el-select v-model="ruleForm.year" style="width:100%" @change="yearChange" placeholder="请选择年度">
    <el-option
        v-for="item in years"
        :key="item.value"
        :label="item.label"
        :value="item.value">
    </el-option>
    </el-select>
       <div class="ehs_form_item_message">可选年度当前年和下年，如选择当前年，执行频率为年，此计划不会被执行。如当前时间为本年度最后一月，选择当前年，执行频率为月，则此计划不会被执行，以此类推</div>
  </el-form-item>
  <el-form-item label="计划周期" required>
    <el-col :span="11">
      <el-form-item prop="startTime">
        <el-date-picker type="date" placeholder="开始日期" :editable="false" :default-value="defaultStartTime" v-model="ruleForm.startTime" :picker-options="pickerOptionsStartTime" @change="startTimeChange" :clearable="false" style="width: 100%;"></el-date-picker>
      </el-form-item>
    </el-col>
    <el-col class="line" :span="2" style="text-align:center">-</el-col>
    <el-col :span="11">
      <el-form-item prop="endTime">
        <el-date-picker placeholder="结束日期" :editable="false" type="date" :default-value="defaultEndTime" v-model="ruleForm.endTime" :picker-options="pickerOptionsEndTime"  :clearable="false" style="width: 100%;"></el-date-picker>
      </el-form-item>
    </el-col>
     <div class="ehs_form_item_message">开始日期/结束日期为必选。且开始日期要大于等于当前日期，结束日期要大于开始日期。计划将在此周期内自动按执行频率执行,超时后计划失效。</div>
  </el-form-item>
   <el-form-item label="设备位置" prop="deviceAddress" >
    <el-select v-model="ruleForm.deviceAddress" style="width:100%" placeholder="请选择设备位置">
    <el-option
        v-for="item in deviceAddresses"
        :key="item.key"
        :label="item.text"
        :value="item.key">
    </el-option>
    </el-select>
  </el-form-item>
     <el-form-item  prop="checkScopeType">
    <el-radio-group v-model="ruleForm.checkScopeType" @change="checkScopeTypeChange">
      <el-radio label="BY_SYSTEM">按系统</el-radio>
      <el-radio label="BY_PROFESSIONA">按专业</el-radio>
    </el-radio-group>

  </el-form-item>
       <el-form-item label="巡检范围" prop="checkScope" >
 <el-checkbox-group v-model="ruleForm.checkScope">
    <el-checkbox v-for="item in checkScopes"
        :key="item.key"
        :label="item.key"
        border
        size="mini"
        >{{item.text}}</el-checkbox>
  </el-checkbox-group>
        <div class="ehs_form_item_message">检修范围提供多选功能，但在非大型巡检计划的情况下，不建议多选，为了确保业务工作的具体化，建议创建多个计划</div>
  </el-form-item>
   <el-form-item label="执行部门" prop="checkor" >
     <org-selector v-model="ruleForm.checkor" style="width:100%" size="medium" :props="{multiple:true}"></org-selector>
  </el-form-item>
  <el-form-item label="启用" >
    <el-switch v-model="ruleForm.enable"></el-switch>
     <div class="ehs_form_item_message">此开关关闭，计划在周期内将不进行执行。计划创建完成后，也可进行调整。如不希望计划在创建完成后开始，可以进行关闭。</div>
  </el-form-item>
   <el-form-item label="授权查看"  >
    <el-radio-group v-model="ruleForm.viewType" >
      <el-radio label="ORG">仅执行部门</el-radio>
      <el-radio label="ALL">所有人</el-radio>
    </el-radio-group>
     <div class="ehs_form_item_message">选择仅执行部门时，仅计划执行部门能查看此计划，选择所有人，所有人都可查看此计划</div>
   </el-form-item>

  <el-form-item label="备注" >
    <el-input type="textarea" v-model="ruleForm.notes"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
    <el-button @click="returnPage()" size="mini">返回</el-button>
      <div class="ehs_form_item_message">计划一经创建，仅能对计划进行延期，或启用停止，不能对其他信息进行修改。请仔细核对信息，如确认无误，请进行创建</div>
  </el-form-item>
</el-form>
  </el-card>
</div>
</template>
<script>
import datas from './datas'
import orgSelector from '@components/org/org-selector/index'
export default datas
datas.components = {
  'org-selector': orgSelector
}
</script>
<style lang="scss" scoped>
  @import "./styles.scss";

</style>
