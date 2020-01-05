export default {
  data () {
    return {
      roleTableData: [],
      innerDrawer: false,
      tableData: [],
      totalCount: 0,
      loading: true,
      multipleSelection: [],
      searchForm: {
        query: '',
        page: 1,
        size: 20
      }
    }
  },
  props: {
    user_key: String,
    roleTable: Array
  },
  watch: {
    user_key: function (newValue, oldValue) {
      this.user_key = newValue
    },
    roleTable: {
      handler (newValue, oldValue) {
        if (newValue) {
          this.roleTableData = newValue
        }
      },
      deep: true
    }
  },
  mounted () {
    this.initRoleTable()
  },
  methods: {
    handleRomoveAuth: function (row) {
      this.$confirm('是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var roleBeans = []
        roleBeans.push({ roleType: 'ROLE', roleKey: row.key })
        this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/orgUser/deleteUserRole', { userKey: this.user_key, roleList: roleBeans })
          .then((res) => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.innerDrawer = false
              this.$emit('authResult', false)
            }
          }).catch((error) => {
            this.$message.error(error)
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    authRole: function () {
      this.innerDrawer = true
      this.initRoleTable()
    },
    handleSubmit: function () {

    },
    selectOver: function () {
      const roles = this.multipleSelection
      var roleBeans = []
      for (let i = 0; i < roles.length; i++) {
        const e = roles[i]
        roleBeans.push({ roleType: 'ROLE', roleKey: e.key })
      }
      if (roles.length <= 0) {
        this.$message({
          message: '请选择角色',
          type: 'warning'
        })
      } else {
        this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/orgUser/saveUserRole', { userKey: this.user_key, roleList: roleBeans }).then(res => {
          if (res.data.resultType === 'ok') {
            this.$message({
              message: res.data.message,
              type: 'success'
            })
            this.innerDrawer = false
            this.$emit('authResult', false)
            this.searchForm.query = ''
          } else {
            this.$message.error(res.data.message)
            this.$emit('authResult', false)
            this.searchForm.query = ''
          }
        }).catch(error => {
          this.$message.error(error)
        })
      }
    },
    handleCloseDrawer (done) {
      this.$confirm('尚未保存，确定关闭吗？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    handleCurrentChange () {
    },
    initRoleTable () { // 获取所有待选角色
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgUser/findAllRolesByUserKey', { params: { userKey: this.user_key, query: this.searchForm } }).then(res => {
        this.tableData = res.data.dataList
        this.totalCount = res.data.totalCount
        this.loading = false
      }).catch(error => {
        this.$message.error(error)
      })
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    }
  }
}
