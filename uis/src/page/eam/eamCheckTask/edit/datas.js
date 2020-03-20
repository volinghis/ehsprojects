export default {
  data () {
    return {
      ruleForm: {
        result: '',
        name: '',
        description: ''
      },
      activeNames: ['rep', 'def', 'rev'],
      rules: {
        name: [
          { required: true, message: '请输入任务名称', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {

  },
  methods: {
    normalExecute () {
      return this.ruleForm.result === 'NORMAL'
    }
  },

  mounted () {
    var that = this
    var flowInfo = JSON.parse(this.$route.params.processInfo)
    if (flowInfo.businessEntityKey) {
      this.$axios.all([
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/checks/plan/getTask?key=' + flowInfo.businessEntityKey)
      ]).then(this.$axios.spread(function (res) {
        // 上面两个请求都完成后，才执行这个回调方法
        that.ruleForm = Object.assign(that.ruleForm, res.data)
        that.ruleForm.result = 'NORMAL'
      }))
    }
  }
}
