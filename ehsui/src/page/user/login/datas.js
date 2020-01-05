export default {
  data () {
    return {
      remeberAccount: true,
      loading: false,
      result: { message: '' },
      loginForm: {
        account: localStorage.getItem(this.GlobalVars.userLocal) ? localStorage.getItem(this.GlobalVars.userLocal) : '',
        password: '',
        captchCode: ''
      },
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
    initLayout () {
      document.querySelector('.loginBody').style.left =
        (document.querySelector('.logingPanel').offsetWidth - 840) / 2 + 'px'
      document.querySelector('.loginBody').style.top =
        (document.querySelector('.logingPanel').offsetHeight - 450) / 2 + 'px'
      document.querySelector('.footerBody').style.right =
        (document.querySelector('.logingPanel').offsetWidth - 840) / 2 + 'px'
    },
    login () {
      var current = this
      current.result.message = ''
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
                current.$router.push({ name: 'index' })
              } else {
                current.result.message = res.data.message
              }
            }).catch(function () {

            }).then(function () {
              current.loading = false
            })
        }
      })
    }
  },
  mounted () {
    this.initLayout()
    window.onresize = () => {
      return (() => {
        this.initLayout()
      })()
    }
  }
}
