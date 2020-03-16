<template>
  <div style="text-align:center;">
    <el-form style="margin: 40px auto 0;" :size="GlobalCss.buttonSize">
      <div style="max-width: 100%;" >
        <i class="el-icon-circle-check" style="color:#67C23A;font-size:58px;"></i><br><br><br>
        <span style="font-size:24px;">设备报废申请相关信息已录入完成，请在右侧选择审批人并点击提交</span><br>
      </div>
    </el-form>
    <el-divider></el-divider>
    <div>
      <el-button type="primary" @click="prevStep" :size="GlobalCss.buttonSize">上一步</el-button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'stepFour',
  data () {
    return {
      loading: false
    }
  },
  mounted () {
    this.$store.state.showFlowEl = true
  },
  methods: {
    prevStep: function () {
      this.$emit('prevStep')
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
