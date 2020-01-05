
export default {

  methods: {
    startFlow () {
      if (!this.flowOper) {
        this.$message.error('请选择流程处理人！')
      }
      this.$refs.flowContent.handerSubmit(1)
    },
    userSelectorChange (v) {
      this.flowOper = v
    }
  },
  mounted () {
    console.log(this.$router.currentRoute)
    console.log(location.href)
    var processDefineKey = JSON.parse(this.$route.params.processInfo).processDefineKey
    this.$axios.get(this.GlobalVars.globalServiceServlet + '/flow/flowInfo/getProcessByProcessDefineKey?processDefineKey=' + processDefineKey)
      .then(res => {
        this.processDefineInfo = res.data
      })
    console.log(processDefineKey)
  },
  data () {
    return {
      processDefineInfo: {},
      flowOper: '',
      activities: [{
        content: '张三（提交）',
        comments: '同意',
        timestamp: '2018-04-12 20:46',
        size: 'large',
        type: 'primary',
        icon: 'el-icon-more'
      }, {
        content: '李四（驳回）',
        comments: '不合格',
        timestamp: '2018-04-03 20:46',
        color: '#0bbd87'
      }, {
        content: '张三（提交）',
        comments: '同意',
        timestamp: '2018-04-03 20:46',
        size: 'large'
      }, {
        content: '李四（通过）',
        comments: '同意',
        timestamp: '2018-04-03 20:46'
      }]
    }
  }
}
