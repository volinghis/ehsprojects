import constructKeys from '../../commom/utils.js'
export default {
  components: {
    constructKeys
  },
  data () {
    return {
      queryParam: {
        page: 1,
        size: 10,
        query: ''

      },
      form: {},
      selections: [],
      tableData: [],
      totalCount: 0,
      sessionUser: {}
    }
  },
  mounted: function () {
    this.sessionUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.getScrapEamList()
  },
  methods: {
    getScrapEamList () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamScrap/getScrapEamList', this.queryParam).then(res => {
        this.tableData = res.data.dataList
        this.totalCount = res.data.totalCount
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handleViewClick (row) { // 查看
      const currentUser = this.sessionUser.userName
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamScrap/getScrapFlowBean', { params: { key: row.key } }).then(res => {
        const entityProcessInfo = res.data
        if (entityProcessInfo.currentUser === currentUser && entityProcessInfo.currentStep === entityProcessInfo.startActivityId) {
          this.GlobalMethods.openFlowWin(entityProcessInfo.editPage, { processInstanceId: entityProcessInfo.instanceId, data: row })
        } else {
          this.GlobalMethods.openFlowWin(entityProcessInfo.viewPage, { processInstanceId: entityProcessInfo.instanceId, data: row })
        }
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    tableRowClassName ({ row, rowIndex }) {
      console.log(row, rowIndex)
      if (rowIndex === 0) {
        return 'warning-row'
      } else if (rowIndex === 1) {
        return 'danger-row'
      }
      return ''
    },
    handleDelete: function () {
      var _this = this.selections
      if (_this.length <= 0) {
        this.$message({
          message: '请选择一条记录',
          type: 'warning'
        })
      } else {
        var keys = constructKeys.handlerArrayDatas(_this)
        this.handleDeleteFun(keys)
      }
    },
    onChange: function (row) {
      this.selections = row
    },
    handlePageChange: function (page) {
      this.queryParam.page = page
      this.getScrapEamList()
    },
    // 新增操作
    handleScrap: function () {
      var that = this
      this.GlobalMethods.openFlowWin('eamScrapBaseForm', { processDefineKey: 'EamScrapFlow' }, function () {
        that.getScrapEamList()
      })
    },
    handleDeleteFun (keys) {
      this.$confirm('是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamScrap/deleteEamScrap', { params: { keys: keys } }).then(res => {
          if (res.data.resultType === 'ok') {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getScrapEamList()
          } else {
            this.$message({
              type: 'info',
              message: '删除失败!'
            })
          }
        }).catch(error => {
          this.$message({ message: error })
        })
      })
    }
  }
}
