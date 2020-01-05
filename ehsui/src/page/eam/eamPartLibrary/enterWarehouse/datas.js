export default {
  methods: {
    handleAdd: function () {
      this.$router.push({ name: '321' })
    },
    handleClick: function (row) {
      this.$router.push({ name: '321' })
    },
    handleDelete: function (row) {
      confirm('确定删除此数据吗？')
    },
    exportExcel: function (row) {
      this.$message({
        message: '导出数据中，请稍等',
        type: 'success'
      })
    },
    handleEdit: function (row) {
      this.$router.push({ name: '321' })
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
    getTableData: function () {
      this.Axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/getList').then(res => {
        this.tableData = res.data
        this.total = res.data.length
      })
    },
    loadAll: function () {
      this.Axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/getSuggestions').then(res => {
        this.restaurants = res.data
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
  },
  data () {
    return {
      state: '',
      total: 0,
      htable: ' ',
      currentPage: 1,
      // 查询参数
      queryParam: {},
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      tableData: []
    }
  }
}
