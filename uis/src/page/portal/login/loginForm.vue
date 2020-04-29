<template>
  <div>
    <!-- <p><span>管理后台登陆</span></p> -->
    <el-form ref="loginForm"
             size="mini"
             :rules="rules"
             :model="loginForm">
      <el-form-item prop="account">
        <el-input v-model="loginForm.account"
                  placeholder="请输入账号"><template slot="prepend"><i class="el-icon-user-solid"></i></template></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password"
                  placeholder="请输入密码"
                  type="password"><template slot="prepend"><i class="fa fa-key"></i></template></el-input>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="remeberAccount">记住账号</el-checkbox>
        <el-button type="danger"
                   @click="login"
                   size="mini"
                   :loading="loading"
                   long>登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data () {
    return {
      remeberAccount: true,
      loginForm: {
        account: localStorage.getItem(this.GlobalVars.userLocal) ? localStorage.getItem(this.GlobalVars.userLocal) : '',
        password: ''
      },
      loading: false,
      rules: {
        account: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 4, max: 18, message: '长度在 4 到 18 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    login () {
      var current = this
      current.$refs['loginForm'].validate(valid => {
        if (valid) {
          current.loading = true

          current.$axios.post(current.GlobalVars.globalServiceServlet + '/auth/login/doLogin', current.loginForm)
            .then(res => {
              // 成功了, 更新数据(成功)
              if (res.data.resultType === 'ok') {
                // 数据存储
                if (current.remeberAccount) {
                  localStorage.setItem(
                    current.GlobalVars.userLocal,
                    current.loginForm.account
                  )
                } else {
                  localStorage.removeItem(current.GlobalVars.userLocal)
                }
                current.$store.dispatch(current.GlobalVars.setResourceMenuKeyMethod, 'login')
                current.$axios.get(current.GlobalVars.globalServiceServlet + '/base/user/getCurrentUser')
                  .then(res => {
                    sessionStorage.setItem(
                      current.GlobalVars.userToken,
                      JSON.stringify(res.data)
                    )
                    current.$router.push({ name: 'index', replace: true })
                  })
              } else {
                this.loading = false
                this.$notify.error(res.data.message)
              }
            }).catch(function () {
              current.loading = false
            })
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
$bg-color:#ce0000;
/deep/.el-input-group__prepend{
  padding: 0 10px;
}
.el-button{
   float: right;
  background-color:$bg-color;
  border-color:$bg-color;
}
.el-button:hover{
  background-color:$bg-color;
  border-color:$bg-color;
}
/deep/.el-checkbox__input.is-checked .el-checkbox__inner{
  background-color:$bg-color;
  border-color:$bg-color;
}
/deep/.el-checkbox__input.is-checked + .el-checkbox__label{
 color:$bg-color;
}
.el-checkbox__inner:hover{
border-color:$bg-color;
}
p{
  color:$bg-color;
}
</style>
