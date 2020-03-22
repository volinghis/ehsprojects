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
        size: 20,
        query: '',
        totalCount: 0,
        wareHouseNames: 'ALL',
        inBoundTypes: 'ALL',
        flowstatus: 'ALL'
      }
    }
  },
  computed: {
    tableHeight: function () {
      return this.$store.state.contentHeight - 355
    }
  },
  mounted: function () {
    var that = this
    that.getTableData()
    that.getWareHouseAndUseType()
    that.sessionUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
  },
  methods: {
    handleAdd: function () {
      var that = this
      this.GlobalMethods.openFlowWin('enterWarehouseEdit', { processDefineKey: 'EamEnterWareHouseFlow', flag: 'add' }, function () {
        this.getTableData()
        that.getWareHouseAndUseType()
      })
    },
    handleClick (row) { // 查看
      const currentUser = this.sessionUser.username
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/getEnterWareHouseFlowBean', { params: { key: row.wareHouseKey } }).then(res => {
        const entityProcessInfo = res.data
        if (entityProcessInfo.currentUser === currentUser && entityProcessInfo.currentStep === entityProcessInfo.startActivityId) {
          this.GlobalMethods.openFlowWin(entityProcessInfo.editPage, { processInstanceId: entityProcessInfo.instanceId, flag: 'edit', key: row.wareHouseKey })
        } else {
          this.GlobalMethods.openFlowWin(entityProcessInfo.viewPage, { processInstanceId: entityProcessInfo.instanceId, flag: 'view', key: row.wareHouseKey })
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
    tableRowClassName ({ row, rowIndex }) {
      var date = new Date(row.creationTime.replace(/-/g, '/'))
      if (this.nowTime >= (date.getTime() + 604800) && row.status !== '已结束') {
        return 'ehs-message-info-error'
      } else if (row.status === '填写单据') {
        return 'ehs-message-info-yellow'
      }
      return ''
    }
  }
}
