export default {

  methods: {
    flushData () {
      var self = this
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/notify/message/findAllByUser', self.pages).then(response => {
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
