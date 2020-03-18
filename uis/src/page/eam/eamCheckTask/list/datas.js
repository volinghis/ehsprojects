export default {
  data () {
    return {
      tasks: [],
      timeNow: '',
      queryBean: {
        page: 1,
        size: 20,
        times: 'ALL',
        owners: 'ALL',
        checks: 'ALL',
        defects: 'ALL',
        revers: 'ALL',
        flowstatus: 'ALL',
        totalCount: 0
      }
    }
  },
  computed: {
    tableHeight: function () {
      return this.$store.state.contentHeight - 450// - document.querySelector('.topPanel').offsetHeight - document.querySelector('.bottomPanel').offsetHeight
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
    transFlow (row) {
      if ((!row.flowProcessInfo) || !row.flowProcessInfo.flowCurrentStep) {
        return '未开始'
      }
      return row.flowProcessInfo.flowCurrentStepName
    },

    sortchange (v) {
      var cl = v.prop

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
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/checks/task/getAllTasks', this.queryBean)
        .then(res => {
          this.tasks = res.data.dataList
          if (this.tasks) {
            for (var i = 0; i < this.tasks.length; i++) {
              var t = this.tasks[i]
              if (t.planKey) {
                this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/checks/plan/getPlan?key=' + t.planKey).then(res => {
                  t.eamCheckPlan = res.data
                })
              }
              if (t.flowProcessInfoKey) {
                this.$axios.get(this.GlobalVars.globalServiceServlet + '/flow/flowProcessInfo/findFlowProcessInfoByKey?key=' + t.flowProcessInfoKey).then(res => {
                  t.flowProcessInfo = res.data
                })
              }
            }
          }
          this.queryBean.totalCount = res.data.totalCount
        })
    }
  }
}
