export default {
  methods: {
    tableHeight: function () {
      return document.body.offsetHeight / 3 - 10
    },
    flushData () {
      var self = this
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/flow/task/findTasks?TIMER=Y', self.pages).then(response => {
        self.pages.total = response.data.totalCount
        self.datas = response.data.dataList
      })
    },
    processNameClick (v) {
      var that = this
      this.GlobalMethods.openFlowWin(v.processPage, v, function () {
        that.flushData()
      })
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
        size: 10,
        total: 0,
        page: 1
      }
    }
  }
}
