
export default {

  methods: {

    doneProcess (stepKey) {
      this.processInfo.vars = this.vars
      this.processInfo.vars.taskId = this.processInstance.activeTaskId
      if (this.processInfo.flowStartActivityId === this.processInfo.flowCurrentStep) {
        this.startFlow()
      } else {
        var url = this.GlobalVars.globalServiceServlet + '/flow/handle/sendProcess'
        if (stepKey === 'END') {
          url = this.GlobalVars.globalServiceServlet + '/flow/handle/endProcess'
        } else {
          if (!this.vars.taskAssignee) {
            this.$message.error('请选择流程处理人！')
            return
          }
        }
        this.$axios.post(url, this.processInfo)
          .then(res => {
            // 成功了, 更新数据(成功)
            this.$message.success('提交成功')
            this.$refs.flowContent.handlerAfterFlow(this.processInfo)
            window.close()
          }).catch(function () {
            this.$message.error('提交异常')
          }).then(function () {
          })
      }
    },
    startFlow () {
      if (!this.vars.taskAssignee) {
        this.$message.error('请选择流程处理人！')
        return
      }
      this.processInfo.vars = this.vars
      this.$refs.flowContent.handerSubmit(this.processInfo)
    },
    cancelProcess () {
      this.processInfo.vars = this.vars
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/flow/handle/cancelProcess', this.processInfo)
        .then(res => {
        // 成功了, 更新数据(成功)
          this.$message.success('撤销流程成功')
          window.close()
        }).catch(function () {
          this.$message.error('撤销流程异常')
        }).then(function () {
        })
    },
    rejectProcess () {
      this.processInfo.vars = this.vars
      this.processInfo.vars.taskId = this.processInstance.activeTaskId
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/flow/handle/rejectProcess', this.processInfo)
        .then(res => {
        // 成功了, 更新数据(成功)
          this.$message.success('驳回流程成功')
          window.close()
        }).catch(function () {
          this.$message.error('驳回流程异常')
        }).then(function () {
        })
    },
    userSelectorChange (v) {
      this.vars.taskAssignee = v
    },
    prevStep () { // 返回上一步
      this.$refs.flowContent.prevStep()
    },
    show () {
      this.isShow = false
    }

  },
  mounted () {
    var flowInfo = JSON.parse(this.$route.params.processInfo)
    this.$axios.post(this.GlobalVars.globalServiceServlet + '/flow/flowInfo/getProcessDefine', flowInfo)
      .then(res => {
        this.processDefineInfo = res.data
      })
    this.$axios.post(this.GlobalVars.globalServiceServlet + '/flow/flowInfo/getProcessComments', flowInfo)
      .then(res => {
        this.comments = res.data
      })
    this.$axios.post(this.GlobalVars.globalServiceServlet + '/flow/flowInfo/getProcessInfo', flowInfo)
      .then(res => {
        this.processInfo = res.data
      })
    this.$axios.post(this.GlobalVars.globalServiceServlet + '/flow/flowInfo/getProcessInstance', flowInfo)
      .then(res => {
        this.processInstance = res.data
      })
  },
  data () {
    return {
      processInfo: { },
      vars: { taskAssignee: '', taskComment: '', taskId: '' },
      processDefineInfo: { start: false, currentStepNum: 0 },
      processInstance: {},
      comments: [],
      isShow: true
    }
  }
}
