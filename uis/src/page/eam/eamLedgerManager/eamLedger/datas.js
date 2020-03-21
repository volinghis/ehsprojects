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
        name: '',
        address: 'ALL',
        profession: 'ALL',
        deviceSystem: 'ALL',
        complete: 'ALL',
        status: 'ALL',
        time: 'ALL'
      },
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      imgUrl: '',
      form: {},
      activeName: 'first',
      total: 0,
      tableData: [],
      tableId: '',
      dataFirst: [],
      dataSecond: []
    }
  },
  created: function () {
    this.initTable()
    this.inintTree(this.activeName)
  },
  methods: {
    initTable () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getLastList', this.queryParam).then(res => {
        this.tableData = res.data.dataList
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
    handleClick (tab) {
      this.activeName = tab.name
      this.inintTree()
      this.initTable()
    },
    handleFirstNodeClick (v) {
      if (v.children !== null) {
        this.queryParam.address = v.id
        this.queryParam.profession = 'ALL'
      } else {
        this.queryParam.profession = v.label
        this.queryParam.address = v.pid
      }
      this.initTable()
    },
    handleSecondNodeClick (v) {
      if (v.children !== null) {
        this.queryParam.address = v.id
        this.queryParam.deviceSystem = 'ALL'
      } else {
        this.queryParam.deviceSystem = v.label
        this.queryParam.address = v.pid
      }
      this.initTable()
    },
    inintTree () {
      if (this.activeName === 'first') {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getTreeForDevice', { params: { parentKey: 'deviceAddress', subKey: 'deviceProfessiona' } }).then(res => {
          this.dataFirst = res.data
        })
      } else {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getTreeForDevice', { params: { parentKey: 'deviceAddress', subKey: 'deviceSystem' } }).then(res => {
          this.dataSecond = res.data
        })
      }
    },
    handleLink (row) {
      this.$router.push({ name: 'eamLedgerDetail', params: { data: row } })
    },
    handleExport () {
      this.$message({
        showClose: true,
        message: '准备导出',
        type: 'warning'
      })
    },
    changePage (val) { // 页码跳转
      this.queryParam.page = val
      this.initTable()
    }
  }
}
