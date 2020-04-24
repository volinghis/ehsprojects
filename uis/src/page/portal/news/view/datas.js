export default {
  data () {
    return {
      data: {
        newsTitle: '',
        newsContent: '',
        creationTime: ''
      }
    }
  },
  mounted () {
    this.getNews(this.$router.currentRoute.query.key)
  },
  methods: {
    getNews (key) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/portal/web/news/getNewsByKey', { params: { key: key } }).then(res => {
        this.data = res.data
      })
    }
  }
}
