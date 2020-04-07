export default {
  data () {
    return {
      wareHouses: [],
      paramsDatas: [],
      tableData: [],
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      queryBean: {
        query: '',
        page: 1,
        size: 10,
        totalCount: 0,
        warehouseNames: 'ALL',
        reserve: 'ALL'
      }
    }
  },
  methods: {
    getWareHouseAndUseType: function () {
      var that = this
      this.$axios.all([
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=wareHouse')
      ]).then(this.$axios.spread(function (wareHouse) {
        that.wareHouses = wareHouse.data
      }))
    },
    handleAdd: function () {
      this.$router.push({ name: 'partsAccountEdit', flag: 'edit', replace: true })
    },
    handleDelete: function (row, index) {
      this.$confirm('确定删除该数据?', '温馨提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/partsAccount/deletePartsAccount', { params: { key: row.key } }).then(res => {
          if (res.data.resultType === 'ok') {
            this.$message({
              message: res.data.message,
              type: 'success'
            })
            this.getTableData()
          } else {
            this.$message.error(res.data.message)
          }
        })
      }).catch(e => e)
    },
    handleView: function (row) {
      this.$router.push({ name: 'partsAccountDetails', params: { data: row, flag: 'view', replace: true } })
    },
    handleEdit: function (row) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/partsParams/getAllPartsParamByKey', { params: { key: row.key } }).then(res => {
        if (res.data.length > 0) {
          this.paramsDatas = res.data
          this.$router.push({ name: 'partsAccountEdit', params: { data: row, pData: this.paramsDatas, flag: 'edit', replace: true } })
        }
      })
    },
    exportExcel: function () {
      this.$message('正在导出，请稍等···')
    },
    getTableData: function () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/partsAccount/getPartsAccountAll', this.queryBean).then(res => {
        this.tableData = res.data.dataList
        this.queryBean.totalCount = res.data.totalCount
      })
    }
  },
  computed: {
    tableHeight: function () {
      return this.$store.state.contentHeight - 240
    }
  },
  mounted: function () {
    this.getTableData()
    this.getWareHouseAndUseType()
  }
}
