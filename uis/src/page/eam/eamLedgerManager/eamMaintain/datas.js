export default {
  components: {
  },
  data () {
    return {
      queryParam: {
        size: 10,
        page: 1,
        name: '',
        address: 'ALL',
        profession: 'ALL',
        deviceSystem: 'ALL',
        time: 'ALL'
      },
      show: false,
      form: {},
      activeName: 'first',
      total: 0,
      tableData: [],
      tableId: '',
      deviceAddresses: [],
      checkScopeType: 'BY_SYSTEM',
      checkScope: '',
      checkScopes: ''
    }
  },
  created: function () {
    this.initTable()
    this.getDeviceAddress()
    this.checkScopeTypeChange()
  },
  methods: {
    initTable () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getList', this.queryParam).then(res => {
        this.tableData = res.data.dataList
        this.total = res.data.totalCount
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    getDeviceAddress () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceAddress').then(res => {
        this.deviceAddresses = res.data
      })
    },
    selectChange () {
      if (this.checkScopeType === 'BY_SYSTEM') {
        this.queryParam.deviceSystem = this.checkScope
      } else {
        this.queryParam.profession = this.checkScope
      }
      this.initTable()
    },
    addressChange (v) {
      this.queryParam.address = v
      this.initTable()
    },
    checkScopeTypeChange (v) {
      if (v === 'BY_PROFESSIONA') {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceProfessiona').then(res => {
          this.checkScopes = res.data
        })
      } else {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceSystem').then(res => {
          this.checkScopes = res.data
        })
      }
      this.checkScope = ''
      this.queryParam.deviceSystem = 'ALL'
      this.queryParam.profession = 'ALL'
      this.initTable()
    },
    customColorMethod: function (percentage) { // 资料完整度颜色
      if (percentage < 40) {
        return '#909399'
      } else if (percentage < 70 && percentage > 40) {
        return '#e6a23c'
      } else {
        return '#67c23a'
      }
    },
    // 编辑
    handleEditClick: function (scope) {
      if (scope.deviceStatus !== '正常') {
        this.$message({
          type: 'warning',
          message: '流程中的设备或已报废设备不可编辑'
        })
        return
      }
      var _this = this
      this.GlobalMethods.openFlowWin('eamLedgerEdit', { processDefineKey: 'EamLedgerUpdateFlow', data: scope }, function () {
        _this.initTable()
      })
    },
    handleQuery () {
      this.initTable()
    },
    // 新增进入流程
    handleAdd () {
      var _this = this
      this.GlobalMethods.openFlowWin('eamLedgerEdit', { processDefineKey: 'EamLedgerUpdateFlow' }, function () {
        _this.initTable()
      })
    },
    handleExport () {
      this.$message({
        showClose: true,
        message: '准备导出',
        type: 'warning'
      })
    },
    handleDelete (row) {
      this.$confirm('此操作将删除相关联设备, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/deleteEamLedgerByKey', { params: { key: row.key } }).then(res => {
          if (res.data.resultType === 'ok') {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.initTable()
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
    },
    changePage (val) { // 页码跳转
      this.queryParam.page = val
      this.initTable()
    }
  }
}
