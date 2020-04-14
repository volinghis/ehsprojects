export default {
  props: {
    organName: String,
    organKey: String,
    userFlag: String,
    editUserForm: Object
  },
  mounted () {
    this.getPositions()
    if (this.userFlag === 'add') {
      this.form.orgKey = this.organKey
      this.form.orgName = this.organName
      this.form.gender = '男'
      this.inputShow = false
    } else {
      this.form = this.editUserForm
      if (this.userFlag === 'view') {
        this.inputShow = true
      }
    }
  },
  watch: {
    userFlag: function (val) {
      if (val === 'view') {
        this.inputShow = true
      } else {
        this.inputShow = false
      }
    },
    organName (newValue, oldValue) {
      this.form.orgName = newValue
    },
    organKey (newValue, oldValue) {
      this.form.orgKey = newValue
    },
    editUserForm: {
      handler (newValue, oldValue) {
        if (newValue) {
          this.form = newValue
        }
      },
      deep: true
    }
  },
  methods: {
    getPositions: function () {
      var that = this
      this.$axios.all([ this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=position') ])
        .then(this.$axios.spread(function (p) {
          that.positions = p.data
        }))
    },
    userCodeValidation: function (d) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgUser/userValidation', { params: { dataCode: d.dataCode, key: d.key } }).then(res => {
        if (res.data.resultType === 'error') {
          this.$message.error(res.data.message)
          this.form.dataCode = ''
        }
        if (res.data.resultType === 'ok') {
          this.$message({
            message: res.data.message,
            type: 'success'
          })
        }
      })
    }
  },
  data () {
    // 验证邮箱的规则
    var checkEmail = (rule, value, cb) => {
      const regEmail = /^([a-zA-Z]|[0-9])+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/
      if (regEmail.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的邮箱'))
    }
    // 验证手机号码的规则
    var checkMobile = (rule, value, cb) => {
      const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/
      if (regMobile.test(value)) {
        return cb()
      }
      cb(new Error('手机号码格式不正确'))
    }
    return {
      positions: [],
      inputShow: false,
      form: {
        name: '',
        gender: '',
        email: '',
        position: '',
        telephone: '',
        orgKey: '',
        orgName: '',
        dataCode: '',
        education: '',
        graduatedSchool: '',
        homeTown: '',
        profession: '',
        graduatedDate: '',
        remark: ''
      },
      rules: {
        dataCode: [
          { required: true, message: '请输入工号', trigger: 'blur' },
          { min: 4, max: 18, message: '长度在 4 到 18 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        position: [
          { required: true, message: '请输入职务', trigger: 'blur' }
        ],
        telephone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { validator: checkMobile, message: '请输入正确的手机号码', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { validator: checkEmail, message: '请输入正确的邮箱地址', trigger: 'blur' }
        ]
      }
    }
  }
}
