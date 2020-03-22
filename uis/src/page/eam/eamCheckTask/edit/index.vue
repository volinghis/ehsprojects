<template>
  <div>
    <el-divider content-position="left">添加工作日志</el-divider>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" style="width:700px;" label-width="100px"
      :inline-message="true" :status-icon="true" class="demo-ruleForm">
      <el-form-item label="任务名称" prop="name">
        <el-input v-model="ruleForm.name" placeholder="请选择计划名称"></el-input>
        <div class="ehs_form_item_message">任务名称必填，且要求3到20个字符</div>
      </el-form-item>
      <el-form-item label="执行结果">
        <el-radio-group :size="GlobalCss.buttonSize" v-model="ruleForm.result">
          <el-radio border label="NORMAL">正常执行</el-radio>
          <el-radio border label="NOTEXECUTE_NOWORK">不执行-缺工</el-radio>
          <el-radio border label="NOTEXECUTE_PERSON_CHANGE">不执行-人员调离</el-radio>
          <el-radio border label="NOTEXECUTE_OTHER">不执行-其他</el-radio>
        </el-radio-group>
        <div class="ehs_form_item_message">请根据实际情况填写，正常执行的情况下，可以填写检修记录，缺陷，备件使用等信息</div>
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" v-model="ruleForm.description"></el-input>
      </el-form-item>
    </el-form>
    <el-collapse v-model="activeNames" v-if="normalExecute()">
      <el-collapse-item title="检修记录" name="rep">
        <el-table :data="this.ruleForm.eamCheckRepair" border :size="GlobalCss.buttonSize">
          <el-table-column type="index" align="center" width="50" fixed="left" label="序号"></el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="address" width="140" label="设备位置"></el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="objectKey" width="140" label="检修对象"></el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="question"  label="问题描述"></el-table-column>
           <el-table-column align="center" show-overflow-tooltip prop="user"  label="检修执行人"></el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="result" width="140" label="检修结论"></el-table-column>
          <el-table-column align="center" show-overflow-tooltip   width="100"  label="操作"></el-table-column>
        </el-table>
      </el-collapse-item>
      <el-collapse-item title="缺陷记录" name="def">
        <div>控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；</div>
        <div>页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。</div>
      </el-collapse-item>
      <el-collapse-item title="备件使用情况" name="rev">
        <div>简化流程：设计简洁直观的操作流程；</div>
        <div>清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；</div>
        <div>帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。</div>
      </el-collapse-item>

    </el-collapse>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
  @import "./styles.scss";
</style>
