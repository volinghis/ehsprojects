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
      activeName: 'BY_PROFESSIONA',
      total: 0,
      tableData: [],
      tableId: '',
      treeData: [],
      proAnalysisData: [],
      SysAnalysisData: []
    }
  },
  mounted: function () {
    var that = this
    this.$axios.all([
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/defectLedger/getAnalysisByType', { params: { type: 'deviceProfessiona', onlyMajor: true, onlyStatusError: true } }),
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/defectLedger/getAnalysisByType', { params: { type: 'deviceSystem', onlyMajor: true, onlyStatusError: true } })
    ]).then(this.$axios.spread(function (deviceProfessiona, deviceSystem) {
      that.proAnalysisData = deviceProfessiona.data
      that.SysAnalysisData = deviceSystem.data
      that.inintTree()
      that.initTable()
    }))
  },
  computed: {
    tableHeight: function () {
      return this.$store.state.contentHeight - 360
    }
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
      this.queryParam.address = 'ALL'
      this.queryParam.deviceSystem = 'ALL'
      this.queryParam.profession = 'ALL'
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
      if (this.activeName === 'BY_PROFESSIONA') {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getTreeForDevice', { params: { parentKey: 'deviceAddress', subKey: 'deviceProfessiona' } }).then(res => {
          this.initCallBack(res.data, this.proAnalysisData)
        })
      } else {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getTreeForDevice', { params: { parentKey: 'deviceAddress', subKey: 'deviceSystem' } }).then(res => {
          this.initCallBack(res.data, this.SysAnalysisData)
        })
      }
    },
    initCallBack (treeVal, analysisVal) {
      treeVal.forEach(s => { // 位置
        var temp = []
        var len = s.children.length
        s.children.forEach(e => { // 系统
          if (analysisVal.length > 0) {
            analysisVal.forEach(a => {
              if (e.id === a.objectKey && a.count > 0 && a.addressKey === s.id) {
                e.defect = 'MAJOR'
                temp.push('MAJOR')
              }
              if (e.id === a.objectKey && a.count === 0 && a.addressKey === s.id) {
                e.defect = 'NONE'
                s.defect = 'NONE'
                temp.push('NONE')
              }
            })
          }
        })
        if (temp.indexOf('MAJOR') === 0) {
          s.defect = 'MAJOR'
        } else if (temp.length < len) {
          s.defect = 'NORMAL'
        }
      })
      this.treeData = treeVal
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
