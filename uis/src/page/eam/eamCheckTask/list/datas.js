
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
        executeResult: 'ALL',
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
      return this.$store.state.contentHeight - 420// - document.querySelector('.topPanel').offsetHeight - document.querySelector('.bottomPanel').offsetHeight
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
      if ((!row.flowProcessInfo) || (!row.flowProcessInfo.flowCurrentStep) || row.flowProcessInfo.flowCurrentStep === 'DRAFT') {
        return '未开始'
      }
      return row.flowProcessInfo.flowCurrentStepName
    },
    viewEnable (row) {
      return !!row.flowProcessInfo
    },
    approve (row) {
      var that = this
      if (row.flowProcessInfo.flowStartActivityId === row.flowProcessInfo.flowCurrentStep) {
        this.GlobalMethods.openFlowWin(row.flowProcessInfo.flowEditPage, row.flowProcessInfo, function () {
          that.flushData()
        })
      } else {
        this.GlobalMethods.openFlowWin(row.flowProcessInfo.flowViewPage, row.flowProcessInfo, function () {
          that.flushData()
        })
      }
    },
    view (row) {
      var that = this
      this.GlobalMethods.openFlowWin(row.flowProcessInfo.flowViewPage, row.flowProcessInfo, function () {
        that.flushData()
      })
    },
    execute (row) {
      var that = this
      var processInfo = {}
      processInfo.processDefineKey = 'EamCheckTask'
      processInfo.businessEntityKey = row ? row.key : ''
      this.GlobalMethods.openFlowWin('eamCheckTaskEdit', processInfo, function () {
        that.flushData()
      })
    },
    executeEnable (row) {
      if (((!row.flowProcessInfo) || row.flowProcessInfo.flowCurrentStep === 'DRAFT') && row.user === JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken)).userKey) {
        return true
      }
      return false
    },
    approveEnable (row) {
      if (row.flowProcessInfo && row.flowProcessInfo.flowCurrentStep !== 'DRAFT' && row.flowProcessInfo.flowCurrentUser === JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken)).userKey) {
        return true
      }
      return false
    },
    sortchange (v) {
      var cl = v.prop
      this.queryBean.sort = { prop: cl, order: v.order }
      this.flushData()
    },
    add () {
      this.execute()
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
            var applyList = []
            var flowInfoApplyList = []
            for (var i = 0; i < this.tasks.length; i++) {
              var t = this.tasks[i]
              if (t.planKey) {
                applyList.push([t, this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/checks/plan/getPlan?key=' + t.planKey)])
              }
              if (t.flowProcessInfoKey) {
                flowInfoApplyList.push([t, this.$axios.get(this.GlobalVars.globalServiceServlet + '/flow/flowProcessInfo/findFlowProcessInfoByKey?key=' + t.flowProcessInfoKey)])
              }
            }
            this.$axios.all(
              applyList.map(e => {
                return e[1]
              })
            ).then(function (resArr) {
              resArr.forEach(function (ress, k) {
                applyList[k][0].eamCheckPlan = ress.data
              })
            })
            this.$axios.all(
              flowInfoApplyList.map(e => {
                return e[1]
              })
            ).then(function (resArr) {
              resArr.forEach(function (ress, k) {
                flowInfoApplyList[k][0].flowProcessInfo = ress.data
              })
            })
          }
          this.queryBean.totalCount = res.data.totalCount
        })
    }
  }
}
