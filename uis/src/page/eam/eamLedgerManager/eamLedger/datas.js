import RepairRecord from '../../components/repairRecord'
import AutoComplete from '../../components/autocomplete.vue'
export default {
  components: {
    RepairRecord,
    AutoComplete
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
      activeName: 'first',
      total: 0,
      tableData: [],
      tableId: ''
    }
  },
  mounted: function () {
    this.initTable()
  },
  methods: {
    initTable () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getLastList', this.queryParam).then(res => {
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
    handleSelect (item) {
      this.queryParam.query = item.value
    },
    changePage (val) { // 页码跳转
      this.queryParam.page = val
      this.initTable()
    }
  }
}
