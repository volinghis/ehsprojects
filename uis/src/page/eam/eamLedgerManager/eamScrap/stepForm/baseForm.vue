<template>
  <div>
    <el-steps class="steps"
              :active="active"
              align-center
              simple
              finish-status="success">
      <el-step title="申请信息" />
      <el-step title="选择设备" />
      <el-step title="进入审批流程" />
    </el-steps>
    <div class="content">
      <keep-alive>
        <step1 v-if="active === 1"
               @nextStep="nextStep"
               @handleCancel="handleCancel" :businessKey=businessKey />
      </keep-alive>
        <step2 v-if="active === 2"
               @nextStep="nextStep"
               @prevStep="prevStep" :businessKey=businessKey />
      <step3 v-if="active === 3"
             @nextStep="nextStep"
             @prevStep="prevStep" />

      <step4 v-if="active === 4"
             @prevStep="prevStep"
             @finish="finish"
             @keepAdd="keepAdd" />

    </div>
  </div>
</template>
<script>
import Step1 from './step1'
import Step2 from './step2'
import Step3 from './step3'
import Step4 from './step4'
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
      businessKey: '',
      reqBean: {
        eamScrap: {},
        scrapDatas: [],
        flowProcessInfo: {}
      }
    }
  },
  created: function () {
    var processObj = JSON.parse(this.$route.params.processInfo)
    if (JSON.stringify(processObj) !== '{}') {
      if (JSON.stringify(processObj.data) !== undefined) {
        this.businessKey = processObj.data.key
      } else {
        this.businessKey = processObj.businessKey
      }
    }
  },
  methods: {
    nextStep: function (val) {
      if (this.active < 4) {
        if (this.active === 1) {
          this.reqBean.eamScrap = val
          this.active += 1
        } else if (this.active === 2) {
          this.reqBean.scrapDatas = val
          this.active += 1
        } else if (this.active === 3) {
          this.handerSubmit(this.active)
        }
      }
    },
    prevStep: function () {
      if (this.active > 0) {
        this.active -= 1
      }
    },
    finish: function (val) {
      window.close()
      this.$router.push({ name: 'eamScrap' })
    },
    handerSubmit (process) {
      // 提交数据 准备开始流程
      this.reqBean.flowProcessInfo = process
      var current = this
      current.$axios.post(current.GlobalVars.globalServiceServlet + '/eam/eamScrap/addEamScrap', this.reqBean).then(res => {
        if (res.data.resultType === 'ok') {
          this.active += 1
        } else {
          this.$message({
            message: '提交失败',
            type: 'warning'
          })
        }
      }).catch(error => {
        this.$message.error(error)
      })
    },
    keepAdd () { // 继续添加
      this.active = 1
    },
    handleCancel () { // 取消
      this.$router.push({ name: 'eamScrap' })
      window.close()
    }
  }
}
</script>

<style lang="scss" scoped>
.steps {
  max-width: 1000px;
  margin: 16px auto;
}
</style>
