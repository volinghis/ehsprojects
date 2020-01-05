<template>
  <el-card :bordered="true" class="cardStyle">
    <el-steps class="steps" :active="active" align-center finish-status="success">
      <el-step title="申请信息" />
      <el-step title="选择设备" />
      <el-step title="选择审批人" />
      <el-step title="结束" />
    </el-steps>
    <div class="content">
      <step1 v-if="active === 1" @nextStep="nextStep"/>
      <step2 v-if="active === 2" @nextStep="nextStep" @prevStep="prevStep"/>
      <step3 v-if="active === 3" @nextStep="nextStep" @prevStep="prevStep"/>
      <step4 v-if="active === 4" @prevStep="prevStep" @finish="finish"/>
    </div>
  </el-card>
</template>
<script>
import Step1 from '../stepOne/index'
import Step2 from '../stepTwo/index'
import Step3 from '../stepThree/index'
import Step4 from '../stepFour/index'

export default {
  name: 'stepForm',
  components: {
    Step1,
    Step2,
    Step3,
    Step4
  },
  data () {
    return {
      active: 1,
      form: null
    }
  },
  methods: {
    nextStep: function () {
      if (this.active < 4) {
        this.active += 1
      }
    },
    prevStep: function () {
      if (this.active > 0) {
        this.active -= 1
      }
    },
    finish: function () {
      this.active = 1
    }
  }
}
</script>

<style lang="scss" scoped>
  .steps {
    max-width: 80%;
    margin: 16px auto;
  }
  .cardStyle{
    position: relative;
    width: 80%;
    left: 10%;
    top:10%;
  }
</style>
