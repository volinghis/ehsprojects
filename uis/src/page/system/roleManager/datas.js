export default {
  data () {
    return {
      dialogFormVisible: false,
      loading: true,
      isShow: true,
      totalCount: 0,
      roleForm: {
        name: '',
        dataCode: '',
        remark: ''
      },
      form: {
        query: '',
        page: 1,
        size: 20
      },
      rules: {
        name: [
          { required: true, message: '请输入角色名称', trigger: 'blur' }
        ],
        dataCode: [
          { required: true, message: '请输入角色编码', trigger: 'blur' }
        ]
      },
      tableData: []
    }
  },
  mounted () {
    this.initTable()
  },
  methods: {
    initTable () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/role/getRoleInfoList', this.form)
        .then((res) => {
          this.loading = false
          this.tableData = res.data.dataList
          this.totalCount = res.data.totalCount
        })
    },
    handleEdit (row) {
      this.dialogFormVisible = true
      this.roleForm = row
    },
    handleCurrentChange (val) { // 页面跳转
      this.form.page = val
      this.initTable()
    },
    handleRoleAdd () { // 新增弹窗打开
      this.dialogFormVisible = true
      this.roleForm = {}
    },
    onSubmit () {
      this.$refs.roleForm.validate((valid) => {
        if (valid) {
          this.handleSubmit()
        } else {
          return false
        }
      })
    },
    handleSubmit () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/role/saveRoleInfo', this.roleForm).then(res => {
        if (res.data.resultType === 'ok') {
          this.$message({
            message: `角色${this.roleForm.name}保存成功`,
            type: 'success'
          })
          this.dialogFormVisible = false
          this.$refs.roleForm.resetFields()
          this.initTable()
        } else {
          this.$message({
            message: res.data.message,
            type: 'info'
          })
          this.dialogFormVisible = false
        }
      }).catch((error) => {
        console.log(error)
      })
    },
    handleDelete (row) {
      this.$confirm('此操作将删除该条记录及相关信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/role/deleteRoleInfo', { params: { key: row.key } })
          .then((res) => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.initTable()
            }
          }).catch((error) => {
            console.log(error)
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
