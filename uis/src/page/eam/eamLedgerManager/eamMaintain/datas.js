import AutoComplete from '../../components/autocomplete.vue'
export default {
  components: {
    AutoComplete
  },
  data () {
    return {
      queryParam: {
        size: 10,
        page: 1,
        query: ''
      },
      show: false,
      form: {},
      activeName: 'first',
      total: 0,
      tableData: [],
      tableId: ''
    }
  },
  created: function () {
    this.initTable()
  },
  methods: {
    initTable () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getList', this.queryParam).then(res => {
        // this.tableData = res.data.dataList
        this.tableData = []
        this.getDevicePicture(res.data.dataList)
        this.total = res.data.totalCount
      }).catch(error => {
        this.$message({ message: error })
      })
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
      var _this = this
      this.GlobalMethods.openFlowWin('eamLedgerEdit', { processDefineKey: 'EamLedgerUpdateFlow', data: scope }, function () {
        _this.initTable()
      })
    },
    getDevicePicture (resArr) {
      for (let i = 0; i < resArr.length; i++) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + resArr[i].deviceImg, { responseType: 'blob' }).then(res => {
          resArr[i].imgUrl = URL.createObjectURL(res.data)
          this.tableData.push(resArr[i])
        }).catch(error => {
          this.$message({ message: error })
        })
      }
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
    handleSelect (item) {
      this.queryParam.query = item.value
    },
    changePage (val) { // 页码跳转
      this.queryParam.page = val
      this.initTable()
    }
  }
}
