<template>
  <el-card :bordered="true">
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
               @handleCancel="handleCancel" />
        <step2 v-if="active === 2"
               @nextStep="nextStep"
               @prevStep="prevStep" />
      </keep-alive>
      <step3 v-if="active === 3"
             @nextStep="nextStep"
             @prevStep="prevStep" />

      <step4 v-if="active === 4"
             @prevStep="prevStep"
             @finish="finish"
             @keepAdd="keepAdd" />

    </div>
  </el-card>
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
      reqBean: {
        allocateForm: {},
        eamLedgerDatas: [],
        flowProcessInfo: {}
      }
    }
  },
  methods: {
    nextStep: function (val) {
      if (this.active < 4) {
        if (this.active === 1) {
          this.reqBean.allocateForm = val
          console.log(this.reqBean.allocateForm)
          this.active += 1
        } else if (this.active === 2) {
          this.reqBean.eamLedgerDatas = val.tableData
          Object.assign(this.reqBean.allocateForm, val.allocateForm)
          this.active += 1
        } else if (this.active === 3) {
          this.handerSubmit()
        }
      }
    },
    prevStep: function () {
      if (this.active > 0) {
        this.active -= 1
      }
    },
    finish: function (val) {
      this.$router.push({ name: 'eamAllocate' })
      window.close()
    },
    handerSubmit (process) {
      this.reqBean.flowProcessInfo = process
      console.log(this.reqBean)
      // this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamAllocate/addEamAllocate', this.reqBean).then(res => {
      //   if (res.data.resultType === 'ok') {
      //     this.active += 1
      //   } else {
      //     this.$message({
      //       message: '提交失败',
      //       type: 'warning'
      //     })
      //   }
      // }).catch(error => {
      //   this.$message.message({ message: error })
      // })
    },
    keepAdd () { // 继续添加
      this.active = 1
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
