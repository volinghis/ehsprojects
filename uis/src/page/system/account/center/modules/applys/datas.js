export default {
  methods: {
    flushData () {
      var self = this
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/flow/task/findApplys', self.pages).then(response => {
        self.pages.total = response.data.totalCount
        self.datas = response.data.dataList
      })
    },
    processNameClick (v) {
      this.GlobalMethods.openFlowWin(v.processPage, { processInstanceId: v.processInstanceId, businessKey: v.businessKey })
    },
    changePage (v) {
      this.pages.page = v
      this.flushData()
    }

  },
  mounted () {
    this.flushData()
  },
  data () {
    return {
      datas: [],
      pages: {
        size: 20,
        total: 0,
        page: 1
      }
    }
  }
}
