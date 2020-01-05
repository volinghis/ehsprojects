
<template>
  <div>
    <el-row type="flex"
            class="row-bg"
            justify="center"
            style="margin-top:50px;">
      <el-col :span="8">
        <el-form :model="ruleForm"
                 status-icon
                 :rules="rules"
                 :size="GlobalCss.buttonSize"
                 ref="ruleForm"
                 label-width="100px"
                 class="demo-ruleForm">
          <el-form-item label="输入原密码："
                        prop="pass">
            <el-input type="password"
                      v-model="ruleForm.pass"
                      autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="输入新密码："
                        prop="newPass">
            <el-input v-model="ruleForm.newPass"
                      type="password"
                      autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认新密码："
                        prop="checkPass">
            <el-input type="password"
                      v-model="ruleForm.checkPass"
                      autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary"
                       @click="submitForm">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data () {
    var checknewPass = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('新密码不能为空'))
      } else {
        callback()
      }
    }
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        // if (this.ruleForm.checkPass !== '') {
        //   this.$refs.ruleForm.validateField('checkPass')
        // }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm.newPass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        pass: '',
        checkPass: '',
        newPass: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        newPass: [
          { validator: checknewPass, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm () {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          console.log(this.ruleForm)
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/userManager/changPassword', this.ruleForm).then(res => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
            } else {
              this.$message({
                message: res.data.message,
                type: 'info'
              })
            }
          }).catch((error) => {
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
