export default {
  methods: {
    findUrl (v) {
      return require('@/assets/images/medals/medal-' + v + '.svg')
    },
    flushData () {
      var self = this
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/scores/userscore/getAllUserScoresByMonth', self.pages).then(response => {
        self.pages.total = response.data.totalCount
        self.ranks = response.data.dataList

        console.log(self.ranks)
      })
    }
  },
  mounted () {
    this.flushData()
  },
  data () {
    return {
      pages: {
        page: 1,
        size: 10,
        total: 0
      },
      ranks: []
    }
  }
}
