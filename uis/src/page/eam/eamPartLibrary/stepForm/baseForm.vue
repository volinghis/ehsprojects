<template>
  <el-card :bordered="true">
    <el-steps class="steps" :active="active" align-center finish-status="success">
      <el-step title="填写备件基本信息" />
      <el-step title="填写备件厂家信息" />
      <el-step title="完成" />
    </el-steps>
    <div class="content">
      <step1 v-if="active === 1" @nextStep="nextStep"/>
      <step2 v-if="active === 2" @nextStep="nextStep" @prevStep="prevStep"/>
      <step3 v-if="active === 3" @prevStep="prevStep" @finish="finish"/>
    </div>
  </el-card>
</template>
<script>
import Step1 from './step1'
import Step2 from './step2'
import Step3 from './step3'

export default {
  name: 'StepForm',
  components: {
    Step1,
    Step2,
    Step3
  },
  data () {
    return {
      active: 1,
      form: null
    }
  },
  methods: {
    nextStep () {
      if (this.active < 3) {
        this.active += 1
      }
    },
    prevStep () {
      if (this.active > 0) {
        this.active -= 1
      }
    },
    finish () {
      this.active = 1
    }
  }
}
</script>

<style lang="scss" scoped>
  .steps {
    max-width: 950px;
    margin: 16px auto;
  }
</style>
