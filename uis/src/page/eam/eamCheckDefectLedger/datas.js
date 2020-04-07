export default {
  data () {
    return {
      tableData: [],
      total: 0,
      treeData: [],
      proAnalysisData: [],
      SysAnalysisData: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      queryBean: {
        page: 1,
        size: 10,
        address: 'ALL',
        objectKey: 'ALL',
        objectType: 'BY_PROFESSIONA',
        status: 'ALL',
        level: 'ALL'
      }
    }
  },
  mounted () {
    var that = this
    this.$axios.all([
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/defectLedger/getAnalysisByType', { params: { type: 'deviceProfessiona', onlyMajor: true, onlyStatusError: true } }),
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/defectLedger/getAnalysisByType', { params: { type: 'deviceSystem', onlyMajor: true, onlyStatusError: true } })
    ]).then(this.$axios.spread(function (deviceProfessiona, deviceSystem) {
      that.proAnalysisData = deviceProfessiona.data
      that.SysAnalysisData = deviceSystem.data
    }))
    that.initTable()
    that.inintTree(this.queryBean.objectType)
  },
  methods: {
    initTable () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/defectLedger/getDefectLedgerList', this.queryBean)
        .then(res => {
          this.tableData = res.data.dataList
          this.total = res.data.totalCount
        }).catch(error => {
          this.$message({ message: error })
        })
    },
    handleClick (tab) {
      this.queryBean.address = 'ALL'
      this.queryBean.objectType = tab.name
      this.queryBean.objectKey = 'ALL'
      this.inintTree(tab.name)
      this.initTable()
    },
    handleExpand (data, node) {
      if (node.isLeaf === false) {
        this.queryBean.address = data.id
        this.queryBean.objectKey = 'ALL'
        this.initTable()
      }
    },
    handleNodeClick (v) {
      if (v.children === null) {
        this.queryBean.objectKey = v.id
        this.queryBean.address = v.pid
        this.initTable()
      }
      return false
    },
    inintTree (v) {
      if (v === 'BY_PROFESSIONA') {
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
    changePage (v) {
      this.queryBean.page = v
      this.initTable()
    }
  }
}
