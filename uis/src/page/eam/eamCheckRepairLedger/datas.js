export default {
  data () {
    return {
      tableData: [],
      total: 0,
      systemTree: [],
      professionTree: [],
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
        result: 'ALL',
        userType: 'ALL'
      }
    }
  },
  mounted () {
    this.inintTree(this.queryBean.objectType)
    this.initTable()
  },
  computed: {
    tableHeight: function () {
      return this.$store.state.contentHeight - 256
    }
  },
  methods: {
    initTable () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/repairLedger/getRepairLedgerList', this.queryBean)
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
    handleProfessionNodeClick (v) {
      if (v.children !== null) {
        this.queryBean.address = v.id
        this.queryBean.objectKey = 'ALL'
      } else {
        this.queryBean.objectKey = v.id
        this.queryBean.address = v.pid
      }
      this.initTable()
    },
    handleSystemNodeClick (v) {
      if (v.children !== null) {
        this.queryBean.address = v.id
        this.queryBean.objectKey = 'ALL'
      } else {
        this.queryBean.objectKey = v.id
        this.queryBean.address = v.pid
      }
      this.initTable()
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
