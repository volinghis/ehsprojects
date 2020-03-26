<template>
  <div>
    <el-steps class="steps"
              :active="active"
              simple
              align-center
              finish-status="success">
      <el-step title="申请信息" />
      <el-step title="选择设备" />
      <el-step title="结束" />
    </el-steps>
    <div class="content">
      <keep-alive>
        <step1 v-if="active === 1"
               @nextStep="nextStep"
               @handleCancel="handleCancel"
               :businessKey=businessKey />
        <step2 v-if="active === 2"
               @nextStep="nextStep"
               @prevStep="prevStep"
               :businessKey=businessKey />
      </keep-alive>
      <step3 v-if="active === 3"
             @prevStep="prevStep" />

    </div>
  </div>
</template>
<script>
import Step1 from './step1'
import Step2 from './step2'
import Step3 from './step3'
export default {
  name: 'stepForm',
  components: {
    Step1,
    Step2,
    Step3
  },
  data () {
    return {
      active: 1,
      appData: {},
      businessKey: '',
      reqBean: {
        allocateForm: {},
        eamLedgerDatas: [],
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
      if (this.active < 3) {
        if (this.active === 1) {
          this.reqBean.allocateForm = val
          this.active += 1
        } else if (this.active === 2) {
          this.reqBean.eamLedgerDatas = val.tableData
          Object.assign(this.reqBean.allocateForm, val.allocateForm)
          this.active += 1
        }
      }
    },
    prevStep: function () {
      if (this.active > 0) {
        this.active -= 1
      }
    },
    handerSubmit (process) {
      this.reqBean.flowProcessInfo = process
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamAllocate/addEamAllocate', this.reqBean).then(res => {
        if (res.data.resultType === 'ok') {
          this.$message({
            message: '提交成功',
            type: 'success'
          })
          window.close()
        } else {
          this.$message({
            message: '提交失败',
            type: 'warning'
          })
        }
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handleCancel () { // 撤销
      this.$router.push({ name: 'eamAllocate' })
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
