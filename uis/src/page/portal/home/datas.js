import loginForm from '../login/loginForm'
var logs = []
export default {
  data () {
    return {
      activeName: 'news',
      allNewsData: [],
      compNewsData: [],
      compNoticeData: [],
      visiteLogs: [],
      queryParam: {
        age: 1,
        size: 12
      }
    }
  },
  components: {
    loginForm
  },
  mounted () {
    this.visiteLogs = JSON.parse(localStorage.getItem('visiteLogs'))
    this.initData()
    this.getAllNewsData()
  },
  methods: {
    initData () {
      var that = this
      this.$axios.all([
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/portal/web/news/getNewsList?dataCode=MENU_COMP_NEWS'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/portal/web/news/getNewsList?dataCode=MENU_COMP_NOTICE')
      ]).then(this.$axios.spread(function (compNews, compNotice) {
        that.compNewsData = compNews.data.dataList
        that.compNoticeData = compNotice.data.dataList
      }))
    },
    getAllNewsData () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/portal/web/news/getALLNewsList', this.queryParam).then(res => {
        this.allNewsData = res.data.dataList
      })
    },
    goNewsList (v) {
      this.$router.push({ name: 'newsList', query: { dataCode: v } })
    },
    handleView (row) {
      // 记录路由到浏览历史
      logs = logs.filter(item => item.title !== row.newsTitle)
      let vLog = { 'title': row.newsTitle, 'path': 'newsView', query: row.key }
      logs.unshift(vLog)
      localStorage.setItem('visiteLogs', JSON.stringify(logs))
      this.$router.push({ name: 'newsView', query: { key: row.key } })
    }
  }
}
