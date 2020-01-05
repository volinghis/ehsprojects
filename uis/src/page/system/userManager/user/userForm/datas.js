export default {
  props: {
    organName: String,
    organKey: String,
    editUserForm: Object
  },
  mounted () {
    if (this.editUserForm.orgName !== this.orgName) {
      this.form = this.editUserForm
    } else {
      this.form.orgKey = this.organKey
      this.form.orgName = this.organName
      this.form.gender = '男'
    }
  },
  watch: {
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
          { required: true, message: '请输入职务', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ],
        department: [
          { required: true, message: '请选择部门', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ]
      }
    }
  }
}
