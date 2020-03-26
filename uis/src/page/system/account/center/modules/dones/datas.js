export default {
  methods: {
    flushData () {
      var self = this
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/flow/task/findDones?TIMER=Y', self.pages).then(response => {
        self.pages.total = response.data.totalCount
        self.datas = response.data.dataList
      })
    },
    processNameClick (v) {
      this.GlobalMethods.openFlowWin(v.processPage, v)
    },
    changePage (v) {
      this.pages.page = v
      this.flushData()
    }
  },
  mounted () {
    var timer = setInterval(this.flushData, 2000)
    this.$once('hook:beforeDestroy', () => {
      clearInterval(timer)
    })
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
