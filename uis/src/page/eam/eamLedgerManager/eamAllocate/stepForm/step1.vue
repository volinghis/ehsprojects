<template>
  <div>
    <el-divider></el-divider>
    <div style="width:500px; margin: 40px auto 0;">
      <el-form :model="allocateForm"
               ref="allocateForm"
               :rules="rules"
               label-width="100px"
               :size="GlobalCss.buttonSize">
        <el-form-item label="申请人："
                      prop="applicant">
          <el-input v-model="allocateForm.applicant"
                    :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="申请部门："
                      prop="allocateDept">
          <el-input v-model="allocateForm.allocateDept"
                    :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="调拨名称："
                      prop="applicationName">
          <el-input v-model="allocateForm.applicationName"
                    placeholder="请填写报废名称"></el-input>
        </el-form-item>
        <el-form-item label="申请日期："
                      prop="applicationTime">
          <el-date-picker v-model="allocateForm.applicationTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择日期"
                          style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="调拨原因：" prop="allocateReason">
          <el-input type="textarea"
                    :rows="2"
                    v-model="allocateForm.allocateReason"
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
                 style="margin:10px;">撤销</el-button>
      <el-button type="primary"
                 :size="GlobalCss.buttonSize"
                 @click="nextStep(allocateForm)"
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
      allocateForm: {
        applicant: '',
        allocateReason: '',
        applicationName: '',
        applicationTime: new Date(),
        allocateDept: ''
      },
      rules: {
        // applicant: [
        //   { required: true, message: '请选择申请人', trigger: 'blur' }
        // ],
        // applicationName: [
        //   { required: true, message: '请输入调拨名称', trigger: 'blur' }
        // ],
        // allocateReason: [
        //   { required: true, message: '请输入调拨原因', trigger: 'blur' }
        // ]
      }
    }
  },
  mounted () {
    const curUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.allocateForm.applicant = curUser.username
    this.allocateForm.allocateDept = curUser.orgName
  },
  methods: {
    nextStep: function (allocateForm) {
      this.$refs.allocateForm.validate((valid) => {
        if (valid) {
          this.$emit('nextStep', this.allocateForm)
        } else {
          return false
        }
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
