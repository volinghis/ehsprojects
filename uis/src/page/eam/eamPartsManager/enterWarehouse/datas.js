export default {
  data () {
    return {
      tasks: [],
      warehouses: [],
      inTypes: [],
      nowTime: '',
      tableData: [],
      queryBean: {
        page: 1,
        size: 10,
        query: '',
        totalCount: 0,
        wareHouseNames: 'ALL',
        inBoundTypes: 'ALL',
        flowstatus: 'ALL'
      }
    }
  },
  computed: {
    // tableHeight: function () {
    //   return this.$store.state.contentHeight - 330
    // }
  },
  mounted: function () {
    var that = this
    that.getNowTime()
    that.getTableData()
    that.getWareHouseAndUseType()
    that.sessionUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
  },
  methods: {
    handleAdd: function () {
      var that = this
      this.GlobalMethods.openFlowWin('enterWarehouseEdit', { processDefineKey: 'EamEnterWareHouseFlow', flag: 'add' }, function () {
        that.getTableData()
        that.getWareHouseAndUseType()
      })
    },
    handleClick (row) { // 查看
      const currentUser = this.sessionUser.username
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/getEnterWareHouseFlowBean', { params: { key: row.refFlowKey } }).then(res => {
        const entityProcessInfo = res.data
        if (entityProcessInfo.currentUser === currentUser && entityProcessInfo.currentStep === entityProcessInfo.startActivityId) {
          this.GlobalMethods.openFlowWin(entityProcessInfo.editPage, { processInstanceId: entityProcessInfo.instanceId, flag: 'edit', key: row.refFlowKey })
        } else {
          this.GlobalMethods.openFlowWin(entityProcessInfo.viewPage, { processInstanceId: entityProcessInfo.instanceId, flag: 'view', key: row.refFlowKey })
        }
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    getTableData: function () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamPartsExtends/getAllEnterWareHouseParts', this.queryBean).then(res => {
        this.tableData = res.data.dataList
        this.queryBean.totalCount = res.data.totalCount
      })
    },
    changePage (v) {
      this.queryBean.page = v
      this.getTableData()
    },
    getWareHouseAndUseType: function () {
      var that = this
      this.$axios.all([
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=wareHouse'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=inBoundType')
      ]).then(this.$axios.spread(function (wareHouse, inBoundType) {
        // 上面两个请求都完成后，才执行这个回调方法
        that.warehouses = wareHouse.data
        that.inTypes = inBoundType.data
      }))
    },
    exportExcel: function (row) {
      this.$message({
        message: '导出数据中，请稍等',
        type: 'success'
      })
    },
    getNowTime: function () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/oper/time/getNow').then(res => {
        this.nowTime = res.data.time
      })
    },
    tableRowClassName ({ row, rowIndex }) {
      if (row != null) {
        var date = new Date(row.creationTime.replace(/-/g, '/'))
        if (this.nowTime >= (date.getTime() + 604800000) && row.status !== 'END') {
          return 'ehs-message-info-error'
        } else if (row.status === 'usertask1') {
          return 'ehs-message-info-yellow'
        }
        return ''
      }
    }
  }
}
