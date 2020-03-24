<template>
  <div>
    <el-form style="width:700px;" label-suffix="：" label-width="100px" class="demo-ruleForm">
      <el-form-item label="设备位置">
        <span>{{addressNameGet(ruleForm)}}</span>
      </el-form-item>
      <el-form-item label="检修对象">
        <span>{{(objectNameGet(ruleForm))}}</span>
      </el-form-item>
      <el-form-item label="问题描述">
        <span>{{ruleForm.question}}</span>
      </el-form-item>
      <el-form-item label="检修记事">
         <file-upload v-model="repair.note"  :disabled="true"  ></file-upload>
      </el-form-item>
      <el-form-item label="检修执行人">
        <span>{{transExecutor(ruleForm)}}</span>
      </el-form-item>
      <el-form-item label="检修结论">
        <span>{{transResult(ruleForm)}}</span>
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
