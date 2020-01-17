export default {
  methods: {
    handleAdd: function () {
      // this.$router.push({ name: 'eamInspectionEdit' })
      this.GlobalMethods.openFlowWin('eamInspectionEdit', { processDefineKey: 'EamInspectionTaskFlow' })
      // this.GlobalMethods.openFlowWin(v.processPage, v, function () {
      //   that.flushData()
      // })
    },
    handleDelete: function () {
      this.$message({
        message: '恭喜你，删除成功',
        type: 'success'
      })
    },
    handleClick: function (row) {
      this.$router.push({ name: 'eamInspectionDetails' })
    },
    handleEdit: function (row) {
      this.$router.push({ name: 'eamInspectionEdit' })
    },
    exportExcel: function () {
      this.$message('正在导出，请稍等···')
    },
    search: function () {
    },
    getAllTask: function () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamInspection/getAllTask', this.form).then(res => {
        console.log(res.data)
        this.tableData = res.data.dataList
        this.totalCount = res.data.totalCount
      })
    }
  },
  mounted: function () {
    // var hcard = document.querySelector('.cardHeight').offsetHeight
    // var hfrom = document.querySelector('.fromHeight').offsetHeight
    // var hpage = document.querySelector('.pageHeight').offsetHeight
    // var hbutton = document.querySelector('.buttonHeight').offsetHeight
    // this.htable = (hcard - hfrom - hpage - hbutton - 25) + 'px'
    this.getAllTask()
  },
  data () {
    return {
      htable: ' ',
      tableData: [],
      queryParam: {},
      totalCount: 0,
      customColor: '#409eff',
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      form: {
        page: 1,
        size: 20,
        query: ''
      }
    }
  }
}
