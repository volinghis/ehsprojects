export default {
  data () {
    return {
      repairsAdd: false,
      defectsAdd: false,
      deviceAddresses: [],
      objects: [],
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
    },
    addressNameGet (s) { // 这个vId也就是value值
      let obj = {}
      obj = this.deviceAddresses.find((item) => { // 这里的userList就是上面遍历的数据源
        return item.key === s.row.deviceAddress// 筛选出匹配数据
      })
      return obj.text
    },
    objectNameGet (s) { // 这个vId也就是value值
      let obj = {}
      obj = this.objects.find((item) => { // 这里的userList就是上面遍历的数据源
        return item.key === s.row.objectKey// 筛选出匹配数据
      })
      return obj.text
    },
    handerSubmit (p) {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          this.ruleForm.flowProcessInfo = p
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/checks/task/saveTask', this.ruleForm).then(res => {
            if (res.data.resultType === 'ok') {
              this.$message.success({ message: res.data.message,
                onClose: function () {
                  window.close()
                } })
            } else {
              this.$message.error(res.data.message)
            }
          })
        }
      })
    }
  },

  mounted () {
    var that = this
    var flowInfo = JSON.parse(this.$route.params.processInfo)
    if (flowInfo.businessKey) {
      this.$axios.all([
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/checks/plan/getTask?key=' + flowInfo.businessKey),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/checks/defects/getDefectsByTaskKey?taskKey=' + flowInfo.businessKey),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/checks/repairs/getRepairsByTaskKey?taskKey=' + flowInfo.businessKey),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceAddress'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceProfessiona'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceSystem')
      ]).then(this.$axios.spread(function (res, defects, repairs, deviceAddresses, pros, syss) {
        // 上面两个请求都完成后，才执行这个回调方法
        that.deviceAddresses = deviceAddresses.data
        that.objects = pros.data
        that.objects = that.objects.concat(syss.data)
        that.ruleForm = Object.assign(that.ruleForm, res.data)
        if (!that.ruleForm.result) {
          that.init()
        }
        that.ruleForm.eamCheckDefect = defects.data
        that.ruleForm.eamCheckRepair = repairs.data
      }))
    } else {
      this.$axios.all([

        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceAddress'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceProfessiona'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceSystem')
      ]).then(this.$axios.spread(function (deviceAddresses, pros, syss) {
        // 上面两个请求都完成后，才执行这个回调方法
        that.deviceAddresses = deviceAddresses.data
        that.objects = pros.data
        that.objects = that.objects.concat(syss.data)
        that.init()
      }))
    }
  }
}
