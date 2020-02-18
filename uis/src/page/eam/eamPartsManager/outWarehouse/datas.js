export default {
  methods: {
    handleAdd: function () {
      this.GlobalMethods.openFlowWin('outWarehouseEdit', { processDefineKey: 'EamOutWareHouseFlow', flag: 'add', replace: true }, function () {
        this.getTableData()
      })
    },
    handleClick: function (row) {
      const currentUser = this.sessionUser.userName
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getOutWareHouseFlowBean', { params: { key: row.wareHouseKey } }).then(res => {
        const entityProcessInfo = res.data
        if (entityProcessInfo.currentUser === currentUser && entityProcessInfo.currentStep === entityProcessInfo.startActivityId) {
          this.GlobalMethods.openFlowWin(entityProcessInfo.editPage, { processInstanceId: entityProcessInfo.instanceId, flag: 'edit', key: row.wareHouseKey, replace: true })
        } else {
          this.GlobalMethods.openFlowWin(entityProcessInfo.viewPage, { processInstanceId: entityProcessInfo.instanceId, flag: 'view', key: row.wareHouseKey, replace: true })
        }
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handleCurrentChange: function (val) {
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
    },
    objectSpanMethod ({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 0) {
        const _row = this.setTable(this.tableData).one[rowIndex]
        const _col = _row > 0 ? 1 : 0
        return {
          rowspan: _row,
          colspan: _col
        }
      }
      if (columnIndex === 1) {
        const _row = this.setTable(this.tableData).two[rowIndex]
        const _col = _row > 0 ? 1 : 0
        return {
          rowspan: _row,
          colspan: _col
        }
      }
    },
    setTable (tableData) {
      let spanOneArr = []
      let spanTwoArr = []
      let concatOne = 0
      let concatTwo = 0
      this.tableData.forEach((item, index) => {
        if (index === 0) {
          spanOneArr.push(1)
          spanTwoArr.push(1)
        } else {
          if (item.wareHouseName === tableData[index - 1].wareHouseName) {
            // 第一列需合并相同内容的判断条件
            spanOneArr[concatOne] += 1
            spanOneArr.push(0)
          } else {
            spanOneArr.push(1)
            concatOne = index
          }
          if (item.wareHouseCode === tableData[index - 1].wareHouseCode) {
            // 第二列和需合并相同内容的判断条件
            spanTwoArr[concatTwo] += 1
            spanTwoArr.push(0)
          } else {
            spanTwoArr.push(1)
            concatTwo = index
          }
        }
      })
      return {
        one: spanOneArr,
        two: spanTwoArr
      }
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
    handleSelect: function (item) {
      console.log(item)
    },
    loadAll: function () {
      // this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getSuggestions').then(res => {
      //   this.restaurants = res.data
      // })
    },
    getTableData: function () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamPartsExtends/getAllOutWareHouseParts', this.form).then(res => {
        if (res.data.totalCount > 0) {
          res.data.dataList.forEach(element => {
            element.forEach(e => {
              this.tableData.push(Object.assign(e))
              this.totalCount = this.tableData.length
            })
          })
        }
      })
    }
  },
  mounted: function () {
    var hcard = document.querySelector('.cardHeight').offsetHeight
    var hfrom = document.querySelector('.fromHeight').offsetHeight
    var hpage = document.querySelector('.pageHeight').offsetHeight
    var hbutton = document.querySelector('.buttonHeight').offsetHeight
    this.htable = (hcard - hfrom - hpage - hbutton - 25) + 'px'
    this.restaurants = this.loadAll()
    this.loadAll()
    this.getTableData()
    this.sessionUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
  },
  data () {
    return {
      state: '',
      htable: ' ',
      total: 0,
      fit: 'cover',
      currentPage: 1,
      totalCount: 0,
      tableData: [],
      queryParam: {},
      customColor: '#409eff',
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      form: {
        query: '',
        page: 1,
        size: 20
      }
    }
  }
}
