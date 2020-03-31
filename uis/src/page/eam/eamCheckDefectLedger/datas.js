export default {
  data () {
    return {
      tableData: [],
      total: 0,
      systemTree: [],
      professionTree: [],
      analysisData: [],
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
      },
      analysisBean: {
        type: '',
        onlyMajor: true,
        onlyStatusError: false
      }
    }
  },
  mounted () {
    this.initTable()
    this.getAnalysis(this.queryBean.objectType)
    this.inintTree(this.queryBean.objectType)
  },
  computed: {
    tableHeight: function () {
      return this.$store.state.contentHeight - 256
    }
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
      this.getAnalysis(tab.name)
      this.inintTree(tab.name)
      this.initTable()
    },
    handleExpand (data, node) {
      if (node.isLeaf === false) {
        this.queryBean.address = data.id
        this.queryBean.objectKey = 'ALL'
        this.initTable()
        this.getAnalysis(this.queryBean.objectType)
        data.children.forEach(e => {
          if (this.analysisData.length > 0) {
            this.analysisData.forEach(a => {
              if (e.id === a.objectKey && a.count > 0 && a.addressKey === data.id) {
                e.defect = 'MAJOR'
              }
              if (e.id === a.objectKey && a.count === 0 && a.addressKey === data.id) {
                e.defect = 'NONE'
              }
            })
          }
        })
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
    getAnalysis (v) {
      if (v === 'BY_PROFESSIONA') {
        this.analysisBean.type = 'deviceProfessiona'
      } else {
        this.analysisBean.type = 'deviceSystem'
      }
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/defectLedger/getAnalysisByType', { params: this.analysisBean }).then(res => {
        this.analysisData = res.data
      })
    },
    inintTree (v) {
      if (v === 'BY_PROFESSIONA') {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getTreeForDevice', { params: { parentKey: 'deviceAddress', subKey: 'deviceProfessiona' } }).then(res => {
          this.professionTree = res.data
        })
      } else {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getTreeForDevice', { params: { parentKey: 'deviceAddress', subKey: 'deviceSystem' } }).then(res => {
          this.systemTree = res.data
        })
      }
    },
    changePage (v) {
      this.queryBean.page = v
      this.initTable()
    }
  }
}
