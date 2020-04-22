export default {
  data () {
    return {
      dataCode: '',
      newsData: [],
      queryParam: {
        dataCode: '',
        query: '',
        page: 1,
        size: 10,
        totalCount: 0
      }
    }
  },
  created () {
    // 得到菜单的dataCode
    console.log(this.$router.currentRoute.name)
    this.queryParam.dataCode = this.$router.currentRoute.name
    this.initNewsData()
  },
  methods: {
    initNewsData () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/web/news/getAllNews', this.queryParam).then(res => {
        this.newsData = res.data.dataList
        this.queryParam.totalCount = res.data.totalCount
      })
    },
    handleAdd () {
      this.$router.push({ name: 'MENU_COMP_NEWS_EDIT', params: { code: this.queryParam.dataCode, flag: 'add' } })
    },
    handleEdit (row) {
      this.$router.push({ name: 'MENU_COMP_NEWS_EDIT', params: { data: row, flag: 'edit' } })
    },
    handleView: function (row) {
      this.$router.push({ name: 'MENU_COMP_NEWS_EDIT', params: { data: row, flag: 'view' } })
    },
    handleDelete (row) {
      this.$confirm('此操作将删除该条记录及相关信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/web/news/deleteNews', { params: { key: row.key } })
          .then((res) => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.initNewsData()
            }
          }).catch((error) => {
            this.$message(error)
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handleCurrentChange (val) {
      this.queryParam.page = val
      this.initNewsData()
    }
  }
}
