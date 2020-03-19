import utils from '../../commom/utils.js'
export default {
  components: {
    utils
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
      sessionUser: {},
      curTime: ''
    }
  },
  mounted: function () {
    this.sessionUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.getAllocateEamList()
    this.$axios.get(this.GlobalVars.globalServiceServlet + '/oper/time/getNow').then(res => {
      this.curTime = res.data.time
    })
  },
  methods: {
    getAllocateEamList () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamAllocate/getAllocateEamList', this.queryParam).then(res => {
        this.tableData = res.data.dataList
        this.totalCount = res.data.totalCount
      }).catch(() => {
      })
    },
    handleDelete: function () {
      var _this = this.selections
      if (_this.length <= 0) {
        this.$message({
          message: '请选择一条记录',
          type: 'warning'
        })
      } else {
        var keys = utils.handlerArrayDatas(_this)
        this.handleDeleteFun(keys)
      }
    },
    onChange: function (row) {
      this.selections = row
    },
    handlePageChange: function (v) {
      this.queryParam.page = v
      this.this.getAllocateEamList()
    },
    tableRowClassName ({ row, rowIndex }) {
      var time = utils.getDiffDays(row.applicationTime, this.curTime)
      if (row.status === '填写单据') {
        return 'ehs-message-info-yellow'
      } else if (time >= 7) {
        return 'danger-row'
      }
      return ''
    },
    handleViewClick (row) { // 查看
      const currentUser = this.sessionUser.userName
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamAllocate/getAllocateFlowBean', { params: { key: row.key } }).then(res => {
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
    // 新增操作
    handleAllocate: function () {
      var that = this
      this.GlobalMethods.openFlowWin('eamAllocateBaseForm', { processDefineKey: 'EamAllocateFlow', flag: 'edit' }, function () {
        that.getAllocateEamList()
      })
    },
    handleDeleteFun (keys) {
      this.$confirm('是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamAllocate/deleteEamAllocate', { params: { keys: keys } }).then(res => {
          if (res.data.resultType === 'ok') {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getAllocateEamList()
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
