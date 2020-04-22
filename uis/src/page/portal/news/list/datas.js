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
  watch: {
    '$route' (to, from) { // 监听路由是否变化
      if (to.query.dataCode !== this.queryParam.dataCode) {
        this.queryParam.dataCode = to.query.dataCode
        this.initNewsData()// 重新加载数据
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
