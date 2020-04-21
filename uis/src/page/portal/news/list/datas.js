export default {
  data () {
    return {
      dataCode: '',
      newsData: [],
      queryParam: {
        dataCode: '',
        query: '',
        page: 1,
        size: 20,
        totalCount: 0
      }
    }
  },
  created () {
    this.queryParam.dataCode = this.$router.currentRoute.query.dataCode
    this.initNewsData()
  },
  methods: {
    initNewsData () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/portal/web/news/getAllNews', this.queryParam).then(res => {
        this.newsData = res.data.dataList
        this.queryParam.totalCount = res.data.totalCount
      })
    },
    handleView: function (row) {
      this.$router.push({ name: 'newsView', query: { data: row } })
    },
    handleCurrentChange (val) {
      this.queryParam.page = val
      this.initNewsData()
    }
  }
}
