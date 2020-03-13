export default {
  data () {
    var that = this
    return {
      years: [],
      timeNow: {},
      deviceAddresses: [],
      defaultStartTime: Date,
      defaultEndTime: Date,
      checkScopes: [],
      pickerOptionsStartTime: {
        disabledDate (time) {
          if (that.ruleForm.year === that.timeNow.year) {
            return time.getTime() < new Date(that.timeNow.year, that.timeNow.month - 1, that.timeNow.day, 0, 0, 0).getTime()
          } else {
            return time.getTime() < new Date(that.ruleForm.year, 0, 1, 0, 0, 0).getTime()
          }
        }
      },
      pickerOptionsEndTime: {
        disabledDate (time) {
          if (that.ruleForm.startTime) {
            if (that.ruleForm.startTime instanceof Date) {
              return time.getTime() <= that.ruleForm.startTime.getTime()
            } else {
              var date = new Date(that.ruleForm.startTime.replace(/-/g, '/'))
              return time.getTime() <= date.getTime()
            }
          }
        }
      },
      ruleForm: {
        name: '',
        rate: '',
        year: '',
        deviceAddress: '',
        checkScope: [],
        checkScopeStr: '',
        checkScopeType: '',
        startTime: '',
        endTime: '',
        enable: true,
        checkor: '',
        viewType: '',
        notes: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入计划名称', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],

        startTime: [
          { type: 'date', required: true, message: '请选择开始日期', trigger: 'change' }
        ],
        endTime: [
          { type: 'date', required: true, message: '请选择结束日期', trigger: 'change' }
        ],
        checkScope: [
          { type: 'array', required: true, message: '请至少选择一个巡检范围', trigger: 'change' }
        ],
        deviceAddress: [
          { required: true, message: '请选择设备位置', trigger: 'change' }
        ],
        checkor: [
          { required: true, message: '请选择执行部门', trigger: 'change' }
        ]
      }
    }
  },
  mounted () {
    var that = this
    this.$axios.all([
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/oper/time/getNow'),
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceAddress'),
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceSystem')
    ]).then(this.$axios.spread(function (time, deviceAddress, deviceSystem) {
      // 上面两个请求都完成后，才执行这个回调方法
      that.timeNow = time.data
      that.years.push({ value: that.timeNow.year, label: that.timeNow.year })
      that.years.push({ value: that.timeNow.year + 1, label: that.timeNow.year + 1 })
      that.ruleForm.year = that.timeNow.year
      that.defaultStartTime = new Date(that.timeNow.year, that.timeNow.month - 1, that.timeNow.day, 0, 0, 0)
      that.defaultEndTime = new Date(that.timeNow.year, that.timeNow.month - 1, that.timeNow.day + 1, 0, 0, 0)
      that.deviceAddresses = deviceAddress.data
      that.checkScopes = deviceSystem.data
    }))
    that.ruleForm.rate = 'DAY'
    that.ruleForm.checkScopeType = 'BY_SYSTEM'
    that.ruleForm.viewType = 'ORG'
  },
  methods: {
    startTimeChange (v) {
      this.ruleForm.endTime = null
      this.defaultEndTime = new Date(this.ruleForm.startTime.getFullYear(), this.ruleForm.startTime.getMonth(), this.ruleForm.startTime.getDate() + 1, 0, 0, 0)
    },
    checkScopeTypeChange (v) {
      if (v === 'BY_PROFESSIONA') {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceProfessiona').then(res => {
          this.checkScopes = res.data
        })
      } else {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceSystem').then(res => {
          this.checkScopes = res.data
        })
      }
    },
    yearChange (v) {
      if (v === this.timeNow.year) {
        this.ruleForm.startTime = null
        this.ruleForm.endTime = null
        this.defaultStartTime = new Date(this.timeNow.year, this.timeNow.month - 1, this.timeNow.day, 0, 0, 0)
        this.defaultEndTime = new Date(this.timeNow.year, this.timeNow.month - 1, this.timeNow.day + 1, 0, 0, 0)
      } else {
        this.ruleForm.startTime = null
        this.ruleForm.endTime = null
        this.defaultStartTime = new Date(v, 0, 1, 0, 0, 0)
        this.defaultEndTime = new Date(v, 0, 2, 0, 0, 0)
      }
    },
    submitForm (formName) {
      var that = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var loading = this.$loading()
          this.$confirm('是否确认创建?', '提示').then(() => {
            this.ruleForm.checkScopeStr = this.ruleForm.checkScope.join(',')
            this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/checks/plan/savePlan', this.ruleForm)
              .then(res => {
                if (res.data.resultType === 'ok') {
                  // 成功了, 更新数据(成功)
                  this.$message.success({ message: '创建计划成功，2秒后返回列表',
                    onClose: function () {
                      loading.close()
                      that.$router.push({ name: 'eamCheckPlan' })
                    } })
                } else {
                  this.$message.error('创建计划失败！')
                  loading.close()
                }
              }).catch(function () {
                this.$message.error('创建计划失败！')
                loading.close()
              })
          }).catch(() => {
            loading.close()
          })
        } else {
          return false
        }
      })
    },
    returnPage () {
      this.$router.push({ name: 'eamCheckPlan' })
    }
  }
}
