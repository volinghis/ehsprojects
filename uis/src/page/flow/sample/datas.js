export default {
  data () {
    return {
      flowSample: {
        content: ''
      },
      businessKey: ''
    }
  },
  mounted () {
    var flowInfo = JSON.parse(this.$route.params.processInfo)
    this.businessKey = flowInfo.businessKey
  },
  methods: {
    handerSubmit: function (process) {
      var current = this
      current.$axios.post(current.GlobalVars.globalServiceServlet + '/flow/sample/save', { flowSample: this.flowSample, flowProcessInfo: process })
        .then(res => {
        // 成功了, 更新数据(成功)
          this.$message.success('提交流程成功')
        // window.close()
        }).catch(function () {
          this.$message.error('提交流程异常')
        }).then(function () {
        })
    }
  }
}
