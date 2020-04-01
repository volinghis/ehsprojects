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
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ],
        telephone: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { min: 11, max: 11, message: '号码为11位数字', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        position: [
          { required: true, message: '请输入职务', trigger: 'blur' }
        ],
        department: [
          { required: true, message: '请选择部门', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ]
      }
    }
  }
}
