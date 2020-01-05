<template>
   <el-container >
     <el-header> <el-steps  :active="0">
    <el-step v-for="item in processDefineInfo.steps" :key="item.stepKey" :title="item.stepName"></el-step>
  </el-steps></el-header>
      <el-container>
    <el-container>
      <el-main> <router-view ref="flowContent"></router-view></el-main>
    </el-container>
    <div style="width:200px" class="ehs-flows-right"><el-input
        type="textarea"
        :rows="4"
        placeholder="审批意见"
        />
       <div>
      <el-row>
        <el-col :span="8">审批人:</el-col>
        <el-col :span="16"><user-selector @change="userSelectorChange"></user-selector></el-col>
      </el-row>
      <el-button type="primary"  :size="GlobalCss.buttonSize" @click="startFlow">发起审批</el-button>
    </div>
      <el-timeline>
    <el-timeline-item
      v-for="(activity, index) in activities"
      :key="index"
      :icon="activity.icon"
      :type="activity.type"
      :color="activity.color"
      :size="activity.size"
      :timestamp="activity.timestamp">
      {{activity.content}} <br>意见:{{activity.comments}}
    </el-timeline-item>
  </el-timeline></div>
  </el-container>
   </el-container>
<!-- <div class="ehs-flows" >

  <div class="ehs-flows-left"> <el-steps direction="vertical" :active="0">
    <el-step title="填写单据"></el-step>
    <el-step title="部门经理审核"></el-step>
    <el-step title="总经理审核"></el-step>
  </el-steps></div>
    <div class="ehs-flows-middle">
      <router-view></router-view>
    </div>
    <div class="ehs-flows-right">
<el-input
  type="textarea"
  :rows="4"
  placeholder="审批意见"
  >
</el-input>

     <el-button type="danger"  :size="GlobalCss.buttonSize">驳回</el-button>
    <el-button type="info"  :size="GlobalCss.buttonSize">部门经理审批</el-button>
    <el-button type="success"  :size="GlobalCss.buttonSize">结束</el-button>
    <el-button type="warning"  :size="GlobalCss.buttonSize">撤回</el-button>
    <el-timeline>
    <el-timeline-item
      v-for="(activity, index) in activities"
      :key="index"
      :icon="activity.icon"
      :type="activity.type"
      :color="activity.color"
      :size="activity.size"
      :timestamp="activity.timestamp">
      {{activity.content}} <br>意见:{{activity.comments}}
    </el-timeline-item>
  </el-timeline>
    </div>

</div> -->
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
