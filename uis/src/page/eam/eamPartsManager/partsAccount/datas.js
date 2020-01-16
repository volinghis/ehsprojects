export default {
  data () {
    return {
      state: '',
      htable: ' ',
      totalCount: 0,
      paramsDatas: [],
      queryParam: {},
      tableData: [],
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      form: {
        page: 1,
        size: 20
      }
    }
  },
  methods: {
    handleAdd: function () {
      this.$router.push({ name: 'partsAccountEdit', flag: 'edit', replace: true })
    },
    handleDelete: function (row, index) {
      this.$confirm('确定删除该数据?', '温馨提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // this.$refs.multipleTable.removeRow(index)
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
    // getPartsParms: function (val) {
    //   this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/partsParams/getAllPartsParamByKey', { params: { key: val } }).then(res => {
    //     if (res.data.length > 0) {
    //       this.paramsData = res.data
    //     }
    //   })
    // },
    handleView: function (row) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/partsParams/getAllPartsParamByKey', { params: { key: row.key } }).then(res => {
        if (res.data.length > 0) {
          this.paramsDatas = res.data
          this.$router.push({ name: 'partsAccountDetails', params: { data: row, pData: this.paramsDatas, flag: 'view', replace: true } })
        }
      })
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
    handleSizeChange: function (val) {
      this.$message({
        message: '恭喜你，这是一条成功消息111',
        type: 'success'
      })
    },
    handleCurrentChange: function (val) {
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
    },
    querySearch: function (queryString, cb) {
      var restaurants = this.restaurants
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants
      // 调用 callback 返回建议列表的数据
      cb(results)
    },
    createFilter: function (queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },
    getTableData: function () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/partsAccount/getPartsAccountAll', this.form).then(res => {
        this.tableData = res.data.dataList
        this.totalCount = res.data.totalCount
      })
    },
    loadAll: function () {
      // this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamPartsAccount/getSuggestions').then(res => {
      //   this.restaurants = res.data
      // })
    },
    handleSelect: function (item) {
      console.log(item)
    }
  },
  mounted: function () {
    var hcard = document.querySelector('.cardHeight').offsetHeight
    var hfrom = document.querySelector('.fromHeight').offsetHeight
    var hpage = document.querySelector('.pageHeight').offsetHeight
    var hbutton = document.querySelector('.buttonHeight').offsetHeight
    this.htable = (hcard - hfrom - hpage - hbutton - 45) + 'px'
    this.restaurants = this.loadAll()
    this.getTableData()
    this.loadAll()
  }
}
