export default {
  data () {
    return {
      repairsAdd: false,
      defectsAdd: false,
      ruleForm: {
        result: '',
        name: '',
        description: '',
        eamCheckRepair: [],
        eamCheckDefect: [],
        eamCheckReserveUsed: []
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
    transDefectLevel (v) {
      if (v.row.level === 'NORMAL') {
        return '一般缺陷'
      }
      return '重大缺陷'
    },
    transDefectStatus (v) {
      if (v.row.status === 'ERROR') {
        return '未解决'
      }
      return '已解决'
    },
    handlesearch: function (list) { // 第30行调用了该方法
      if (!list) {
        list = []
      }
      return list.filter(item => {
        // filter()对象遍历,true 返回对象参数值,如果多条数据,自动使用数组拼接
        if (!item.deleted) {
          /// /字符串索引有关键字值,返回true
          return item
        }
      })
    },
    remove (s) {
      s.row.deleted = true
    },
    repairInner (v) {
      if (!this.ruleForm.eamCheckRepair) {
        this.ruleForm.eamCheckRepair = []
      }
      this.ruleForm.eamCheckRepair.push(v)
    },
    repairUpdate (newRow) {
      if (this.ruleForm.eamCheckRepair) {
        this.ruleForm.eamCheckRepair.forEach(element => {
          if (element.key === newRow.key) {
            element = Object.assign(element, newRow)
          }
        })
      }
    },
    repairsMethod () {
      this.repairsAdd = true
    },
    defectInner (v) {
      if (!this.ruleForm.eamCheckDefect) {
        this.ruleForm.eamCheckDefect = []
      }
      this.ruleForm.eamCheckDefect.push(v)
    },
    defectUpdate (newRow) {
      if (this.ruleForm.eamCheckDefect) {
        this.ruleForm.eamCheckDefect.forEach(element => {
          if (element.key === newRow.key) {
            element = Object.assign(element, newRow)
          }
        })
      }
    },
    defectsMethod () {
      this.defectsAdd = true
    },
    normalExecute () {
      return this.ruleForm.result === 'NORMAL'
    },
    init () {
      this.ruleForm.result = 'NORMAL'
    },
    transResult (row) {
      if (row.result === 'OK') {
        return '已解决'
      } else {
        return '未解决'
      }
    },
    transExecutor (row) {
      return row.userName + '(' + row.orgName + ')'
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
        if (!that.ruleForm.result) {
          that.init()
        }
      }))
    } else {
      this.init()
    }
  }
}
