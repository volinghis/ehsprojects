export default {
  data () {
    return {
      wareHouses: [],
      outTypes: [],
      nowTime: '',
      tableData: [],
      queryBean: {
        page: 1,
        size: 20,
        query: '',
        warehouseNames: 'ALL',
        outBoundTypes: 'ALL',
        flowstatus: 'ALL',
        totalCount: 0
      }
    }
  },
  computed: {
    tableHeight: function () {
      return this.$store.state.contentHeight - 330
    }
  },
  mounted: function () {
    this.getTableData()
    this.getNowTime()
    this.sessionUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.getWareHouseAndUseType()
  },
  methods: {
    handleAdd: function () {
      var that = this
      this.GlobalMethods.openFlowWin('outWarehouseEdit', { processDefineKey: 'EamOutWareHouseFlow', flag: 'add' }, function () {
        that.getTableData()
        that.getWareHouseAndUseType()
      })
    },
    handleClick: function (row) {
      const currentUser = this.sessionUser.username
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getOutWareHouseFlowBean', { params: { key: row.refFlowKey } }).then(res => {
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
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamPartsExtends/getAllOutWareHouseParts', this.queryBean).then(res => {
        this.tableData = res.data.dataList
        this.queryBean.totalCount = res.data.totalCount
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
    },
    getWareHouseAndUseType: function () {
      var that = this
      this.$axios.all([
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=wareHouse'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=outBoundType')
      ]).then(this.$axios.spread(function (wareHouse, outBoundType) {
        // 上面两个请求都完成后，才执行这个回调方法
        that.wareHouses = wareHouse.data
        that.outTypes = outBoundType.data
      }))
    },
    getNowTime: function () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/oper/time/getNow').then(res => {
        this.nowTime = res.data.time
      })
    },
    changePage (v) {
      this.queryBean.page = v
      this.getTableData()
    }
  }
}
