export default {
  data () {
    return {
      totalCount: 0,
      loading: true,
      queryParams: {
        query: '',
        page: 1,
        size: 20
      },
      tableData: [],
      multipleSelection: []
    }
  },
  mounted () {
    this.initTable()
  },
  methods: {
    initTable () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/loginLog/getLoginLogList', this.queryParams)
        .then((res) => {
          this.loading = false
          this.tableData = res.data.dataList
          this.totalCount = res.data.totalCount
        })
    },
    handleCurrentChange (val) { // 页面跳转
      this.form.page = val
      this.initTable()
    },
    handleDelete (row) {
      if (this.multipleSelection.length === 0) {
        this.$message({
          type: 'warning',
          message: '请选择要删除项'
        })
      } else {
        var keys = []; var keysStr
        this.multipleSelection.forEach(e => {
          keys.push(e.key)
        })
        keysStr = keys.join(',')
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/loginLog/deleteLoginLog', { params: { keys: keysStr } })
          .then((res) => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.initTable()
            }
          }).catch((error) => {
            this.$message.error(error)
          })
      }
    },
    handleSelectionChange (rows) {
      this.multipleSelection = rows
    }
  }
}
