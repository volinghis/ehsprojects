<template>
  <div>
    <el-divider></el-divider>
    <div style="width:500px; margin: 40px auto 0;">
      <el-form :model="scrapStepForm"
               ref="scrapStepForm"
               :disabled="disable"
               :rules="rules"
               label-width="100px"
               :size="GlobalCss.buttonSize">
        <el-form-item label="申请人："
                      prop="applicant">
          <el-input v-model="scrapStepForm.applicant"
                    :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="申请部门："
                      prop="scrapDept">
          <el-input v-model="scrapStepForm.scrapDept"
                    :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="报废名称："
                      prop="applicationName">
          <el-input v-model="scrapStepForm.applicationName"
                    placeholder="请填写报废名称"></el-input>
        </el-form-item>
        <el-form-item label="申请日期："
                      prop="applicationTime">
          <el-date-picker v-model="scrapStepForm.applicationTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择日期"
                          style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="报废原因："
                      prop="scrapReason">
          <el-input type="textarea"
                    :rows="2"
                    v-model="scrapStepForm.scrapReason"
                    maxlength="100"
                    show-word-limit></el-input>
        </el-form-item>
      </el-form>
    </div>
    <el-divider></el-divider>
    <div style="text-align:center;">
      <el-button @click="cancel"
                 type="warning"
                 :size="GlobalCss.buttonSize"
                 style="margin:10px;">取消</el-button>
      <el-button type="primary"
                 :size="GlobalCss.buttonSize"
                 @click="nextStep(scrapStepForm)"
                 style="margin:10px;">下一步</el-button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'stepOne',
  data () {
    return {
      value: '1',
      disable: false,
      sessionUser: {},
      scrapStepForm: {
        applicant: '',
        scrapReason: '',
        applicationName: '',
        applicationTime: new Date(),
        scrapDept: ''
      },
      rules: {
        applicationName: [
          { required: true, message: '请输入报废名称', trigger: 'blur' }
        ],
        scrapReason: [
          { required: true, message: '请输入报废原因', trigger: 'blur' }
        ]
      },
      date: new Date()
    }
  },
  props: {
    businessKey: {
      type: String
    }
  },
  mounted () {
    if (this.businessKey) {
      this.disable = true // 表单数据只读
      this.getScrapByKey(this.businessKey)
    } else {
      this.sessionUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
      const curUser = this.sessionUser
      this.scrapStepForm.applicant = curUser.username
      this.scrapStepForm.scrapDept = curUser.orgName
    }
  },
  methods: {
    nextStep: function (scrapStepForm) {
      this.$refs.scrapStepForm.validate((valid) => {
        if (valid) {
          this.$emit('nextStep', this.scrapStepForm)
        } else {
          return false
        }
      })
    },
    getScrapByKey (key) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamScrap/getScrapByKey', { params: { key: key } }).then(res => {
        var resData = res.data
        this.scrapStepForm = resData
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    cancel: function () {
      this.$emit('handleCancel')
    }
  }
}
</script>

<style lang="scss" scoped>
.el-textarea {
  padding: 0px;
}
</style>
