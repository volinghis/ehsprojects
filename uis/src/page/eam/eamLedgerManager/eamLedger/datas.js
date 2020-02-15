import RepairRecord from '../../components/repairRecord'
export default {
  components: {
    RepairRecord
  },
  data () {
    return {
      queryParam: {
        size: 10,
        page: 1,
        query: ''
      },
      imgUrl: '',
      form: {},
      suggestions: [],
      activeName: 'first',
      total: 0,
      tableData: [],
      tableId: ''
    }
  },
  mounted: function () {
    this.initTable()
    this.loadSuggestions()
  },
  methods: {
    initTable () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getLastList', this.queryParam).then(res => {
        // this.tableData = res.data.dataList
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
    getDevicePicture (resArr) {
      for (let i = 0; i < resArr.length; i++) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + resArr[i].deviceImg, { responseType: 'blob' }).then(res => {
          resArr[i].imgUrl = URL.createObjectURL(res.data)
          this.tableData.push(resArr[i])
          console.log(this.tableData)
        }).catch(error => {
          this.$message({ message: error })
        })
      }
    },
    handldbClick: function (row) {
      // 详情查看
      this.$router.push({ name: 'eamLedgerDetail', params: { data: row } })
    },
    handleClick: function (tab, event) {
    },
    handleSizeChange: function () {
    },
    handleQuery () {
      this.initTable()
    },
    handleCurrentChange (val) { // 联动检修记录
      this.tableId = val.id
    },
    handleExport () {
      this.$message({
        showClose: true,
        message: '准备导出',
        type: 'warning'
      })
    },
    querySearch (queryString, cb) {
      var suggestions = this.suggestions
      var results = queryString ? suggestions.filter(this.createStateFilter(queryString)) : suggestions
      // 调用 callback 返回建议列表的数据
      clearTimeout(this.timeout)
      this.timeout = setTimeout(() => {
        cb(results)
      }, 1000 * Math.random())
    },
    createStateFilter (queryString) {
      return (suggestion) => {
        return (suggestion.value.toLowerCase().indexOf(queryString.toLowerCase()) !== -1)
      }
    },
    handleSelect (item) {
      this.queryParam.query = item.value
    },
    loadSuggestions () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getSuggestions').then(res => {
        this.suggestions = res.data
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    changePage (val) { // 页码跳转
      this.queryParam.page = val
      this.initTable()
    }
  }
}
