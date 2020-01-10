<template>
   <el-container >
     <el-header> <el-steps  :active="processDefineInfo.currentStepNum"   finish-status="success" >
    <el-step v-for="item in processDefineInfo.steps" :key="item.stepKey" :title="item.stepName"></el-step>
  </el-steps></el-header>
      <el-container>
    <el-container>
      <el-main> <router-view ref="flowContent"></router-view></el-main>
    </el-container>
    <div style="width:200px" class="ehs-flows-right">
      <el-input
        type="textarea"
        v-model="vars.taskComment"
        :rows="4"
        placeholder="备注"
        />
       <div>
      <el-row v-if="(processInstance.nextStep&&processInstance.nextStep[0].stepKey!='END')||processDefineInfo.start">
        <el-col :span="8">审批人:</el-col>
        <el-col :span="16"><user-selector @change="userSelectorChange"></user-selector></el-col>
      </el-row>
      <el-button type="primary"  :size="GlobalCss.buttonSize" @click="startFlow" v-if="processDefineInfo.start" >提交</el-button>
      <el-button type="success" v-for="step in processInstance.nextStep" :key="step.stepKey" :size="GlobalCss.buttonSize" @click="doneProcess(step.stepKey)">{{step.stepName}}</el-button>
      <el-button type="danger"  :size="GlobalCss.buttonSize" v-if="processInstance.nextStep&&!processInstance.candoCanel" @click="rejectProcess">驳回</el-button>
       <el-button type="warning"  :size="GlobalCss.buttonSize" v-if="processInstance.candoCanel" @click="cancelProcess">撤销</el-button>
    </div>
      <el-timeline>
    <el-timeline-item
      v-for="(comment, index) in comments"
      :key="index"
      :color="(index==0?'#0bbd87':'')"
      :timestamp="comment.creationTime">
      {{comment.creationName}}({{comment.operType}})<br>备注:{{comment.content}}
    </el-timeline-item>
  </el-timeline></div>
  </el-container>
   </el-container>

</template>
<script>
import datas from './datas.js'
import userSelector from '@components/org/user-selector/index'
export default datas
datas.components = {
  'user-selector': userSelector
}
</script>
<style lang="scss" scoped>
@import './styles.scss';
</style>
