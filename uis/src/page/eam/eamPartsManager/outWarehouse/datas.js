export default {
  methods: {
    handleAdd: function () {
      // this.$router.push({ name: 'outWarehouseEdit', params: { flag: 'add', replace: true } })
      this.GlobalMethods.openFlowWin('outWarehouseEdit', { processDefineKey: 'EamOutWareHouseFlow', flag: 'add', replace: true }, function () {
        this.getTableData()
      })
    },
    handleClick: function (row) {
      const currentUser = this.sessionUser.userName
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getOutWareHouseFlowBean', { params: { key: row.key } }).then(res => {
        const entityProcessInfo = res.data
        if (entityProcessInfo.currentUser === currentUser && entityProcessInfo.currentStep === entityProcessInfo.startActivityId) {
          this.GlobalMethods.openFlowWin(entityProcessInfo.editPage, { processInstanceId: entityProcessInfo.instanceId, flag: 'edit', data: row, replace: true })
        } else {
          this.GlobalMethods.openFlowWin(entityProcessInfo.viewPage, { processInstanceId: entityProcessInfo.instanceId, flag: 'view', data: row, replace: true })
        }
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handleEdit: function (row) {
      this.$router.push({ name: 'outWarehouseEdit', params: { data: row, flag: 'edit', replace: true } })
    },
    handleDelete: function (row) {
      confirm('确定删除此数据吗？')
    },
    handleSizeChange: function (val) {
      this.$message({
        message: '恭喜你，这是一条成功消息',
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
    handleSelect: function (item) {
      console.log(item)
    },
    loadAll: function () {
      // this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getSuggestions').then(res => {
      //   this.restaurants = res.data
      // })
    },
    getTableData: function () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getList', this.form).then(res => {
        this.tableData = res.data.dataList
        this.total = res.data.totalCount
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
