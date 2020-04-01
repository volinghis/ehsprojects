export default {

  methods: {
    tableHeight: function () {
      return document.body.offsetHeight / 3 - 10
    },
    flushData () {
      var self = this
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/notify/message/findAllByUser?TIMER=Y', self.pages).then(response => {
        self.pages.total = response.data.totalCount
        self.notices = response.data.dataList
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
      notices: [],
      pages: {
        size: 20,
        total: 0,
        page: 1
      }
    }
  }
}
