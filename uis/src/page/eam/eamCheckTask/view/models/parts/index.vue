<template>
  <div>
    <el-form style="width:700px;" label-suffix="：" label-width="100px" class="demo-ruleForm">
      <el-form-item label="所在仓库">
        <span>{{partsData.wareHouseName}}</span>
      </el-form-item>
      <el-form-item label="备件名称">
        <span>{{partsData.deviceName}}</span>
      </el-form-item>
      <el-form-item label="备件编号">
        <span>{{partsData.deviceCode}}</span>
      </el-form-item>
      <el-form-item label="数量">
         <span>{{partsData.amount}}</span>
      </el-form-item>
      <el-form-item label="领用人">
        <span>{{partsData.receivePersonName}}({{partsData.receiveOrg}})</span>
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
