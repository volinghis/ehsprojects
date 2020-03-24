<template>
  <div>
    <el-form style="width:700px;" label-suffix="：" label-width="100px" class="demo-ruleForm">
      <el-form-item label="设备位置">
        <span>{{addressNameGet(ruleForm)}}</span>
      </el-form-item>
      <el-form-item label="缺陷对象">
        <span>{{(objectNameGet(ruleForm))}}</span>
      </el-form-item>
      <el-form-item label="缺陷描述">
        <span>{{ruleForm.question}}</span>
      </el-form-item>
      <el-form-item label="问题设备">
        <span>{{ruleForm.devices}}</span>
      </el-form-item>
      <el-form-item label="缺陷等级">
        <span>{{transDefectLevel(ruleForm)}}</span>
      </el-form-item>
            <el-form-item label="缺陷状态">
        <span>{{transDefectStatus(ruleForm)}}</span>
      </el-form-item>
      <el-form-item label="附件">
        <file-upload v-model="repair.files" :disabled="true"></file-upload>
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
