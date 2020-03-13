export default {
  data () {
    return {
      plans: [],
      timeNow: '',
      queryBean: {
        page: 1,
        size: 20,
        byowner: false,
        effective: false,
        enable: false,
        query: '',
        totalCount: 0
      }
    }
  },
  computed: {
    tableHeight: function () {
      return this.$store.state.contentHeight - 350// - document.querySelector('.topPanel').offsetHeight - document.querySelector('.bottomPanel').offsetHeight
    }
  },
  mounted () {
    var that = this
    this.$axios.all([
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/oper/time/getNow')
    ]).then(this.$axios.spread(function (time) {
      // 上面两个请求都完成后，才执行这个回调方法
      that.timeNow = time.data
      that.flushData()
    }))
  },
  methods: {
    enableCheck (row) {
      var date = new Date(row.startTime.replace(/-/g, '/'))
      var dateEnd = new Date(row.endTime.replace(/-/g, '/'))
      var now = new Date(this.timeNow.date.replace(/-/g, '/'))
      return date.getTime() <= now.getTime() && now.getTime() <= dateEnd.getTime() && !row.enable
    },
    resetTimeCheck (row) {
      var date = new Date(row.startTime.replace(/-/g, '/'))
      var dateEnd = new Date(row.endTime.replace(/-/g, '/'))
      var now = new Date(this.timeNow.date.replace(/-/g, '/'))
      return date.getTime() <= now.getTime() && now.getTime() <= dateEnd.getTime() && row.enable
    },

    sortchange (v) {
      var cl = v.prop
      if (cl === 'enableLabel') {
        cl = 'enable'
      } else if (cl === 'ownerName') {
        cl = 'owner'
      }
      this.queryBean.sort = { prop: cl, order: v.order }
      this.flushData()
    },
    add () {
      this.$router.push({ name: 'eamCheckPlanEdit' })
    },

    changePage (v) {
      this.queryBean.page = v
      this.flushData()
    },
    flushData () {
      this.queryBean.page = 1
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/checks/plan/getAllPlans', this.queryBean)
        .then(res => {
          this.plans = res.data.dataList
          this.queryBean.totalCount = res.data.totalCount
        })
    }
  }
}
