export default {
  data () {
    return {
      plans: [],
      dataView: {},
      sessionUser: '',
      timeNow: '',
      oldTime: '',
      dialogVisibleView: false,
      dialogVisible: false,
      queryBean: {
        page: 1,
        size: 20,
        byowner: false,
        effective: false,
        enable: false,
        query: '',
        totalCount: 0
      },
      formDate: {
        key: '',
        oldTime: '',
        newTime: ''
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
    var currUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.sessionUser = currUser
  },
  methods: {
    enableCheck (row) {
      var date = new Date(row.startTime.replace(/-/g, '/'))
      var dateEnd = new Date(row.endTime.replace(/-/g, '/'))
      var now = new Date(this.timeNow.date.replace(/-/g, '/'))
      return date.getTime() <= now.getTime() && now.getTime() <= dateEnd.getTime() && !row.enable
    },
    changeState: function (e, row, index) {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/checks/plan/changeState', row).then(res => {
        if (res.data !== [] && res.data.enable === true) {
          this.$message({
            message: '已经切换到启用状态',
            type: 'success'
          })
        } else {
          this.$message({
            message: '已经切换到停用状态',
            type: 'warning'
          })
        }
      })
    },
    resetTimeCheck (row) {
      var date = new Date(row.startTime.replace(/-/g, '/'))
      var dateEnd = new Date(row.endTime.replace(/-/g, '/'))
      var now = new Date(this.timeNow.date.replace(/-/g, '/'))
      return date.getTime() <= now.getTime() && now.getTime() <= dateEnd.getTime() && row.enable
    },
    delay: function (row) {
      this.dialogVisible = true
      this.formDate.oldTime = row.endTime
      this.formDate.key = row.key
    },
    comply: function (row) {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/checks/plan/sendTask', row).then(res => {
        if (res.data.resultType === 'ok') {
          this.$message({
            message: res.data.message,
            type: 'success'
          })
        } else {
          this.$message({
            message: res.data.message,
            type: 'warning'
          })
        }
      })
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
    handleClick: function (row) {
      this.dialogVisibleView = true
      console.log(row)
      this.dataView = row
    },
    handleSubmit: function () {
      var old = new Date(this.formDate.oldTime.replace(/-/g, '/'))
      if (this.formDate.newTime.getTime() <= old.getTime()) {
        this.$message({
          message: '您选择的延期时间有误，早于原来时间',
          type: 'warning'
        })
        this.formDate.newTime = ''
        return
      }
      this.dialogVisible = false
      var d = new Date(this.formDate.newTime)
      var datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds()
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/checks/plan/delayDate', { params: { key: this.formDate.key, newDate: datetime } }).then(res => {
        if (res.data.resultType === 'ok') {
          this.$message({
            message: res.data.message,
            type: 'success'
          })
          this.flushData()
        } else {
          this.$message.error(res.data.message)
        }
      })
    },
    handleReset: function () {
      this.dialogVisible = false
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
