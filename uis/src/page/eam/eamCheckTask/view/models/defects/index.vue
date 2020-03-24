<template>
  <div>
    <el-form style="width:700px;" label-suffix="：" label-width="100px" class="demo-ruleForm">
      <el-form-item label="设备位置">
        <span>{{addressNameGet(defect)}}</span>
      </el-form-item>
      <el-form-item label="缺陷对象">
        <span>{{(objectNameGet(defect))}}</span>
      </el-form-item>
      <el-form-item label="缺陷描述">
        <span>{{defect.question}}</span>
      </el-form-item>
      <el-form-item label="问题设备">
        <span>{{defect.devices}}</span>
      </el-form-item>
      <el-form-item label="缺陷等级">
        <span>{{transDefectLevel(defect)}}</span>
      </el-form-item>
            <el-form-item label="缺陷状态">
        <span>{{transDefectStatus(defect)}}</span>
      </el-form-item>
      <el-form-item label="附件">
        <file-upload v-model="defect.files" :disabled="true"></file-upload>
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
