import addPart from '../addPart/index.vue'
import tablePart from '../tablePart/index.vue'
export default {
  components: {
    tablePart,
    addPart
  },
  data () {
    return {
      key: '',
      obj: {},
      wareHouse: [],
      enterWareHouseData: {},
      inType: [],
      showButton: false,
      show: false,
      showFlag: '',
      selectFlag: '',
      selectWareHouseFlag: '',
      parts: [],
      pDatas: [],
      partsTable: [],
      tableDatas: [],
      dialogVisible: false,
      totalCount: 0,
      form: {
        warehouse: '',
        warehouseName: '',
        warehouseCode: '',
        inboundType: '',
        inboundTypeName: '',
        inboundDate: '',
        founder: '',
        remark: ''
      },
      rules: {
        warehouseCode: [
          { required: true, message: '请输入入库编码', trigger: 'blur' }
        ],
        warehouse: [
          { required: true, message: '请选择入库仓库', trigger: 'blur' }
        ],
        inboundType: [
          { required: true, message: '请选择入库类型', trigger: 'blur' }
        ],
        principal: [
          { required: true, message: '请输入入库人', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    var processObj = JSON.parse(this.$route.params.processInfo)
    this.flag = processObj.flag
    if (this.flag === 'add') {
      const user = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
      this.form.founder = user.username
      this.show = false
      this.showButton = true
      this.showFlag = 'add'
      this.getWareHouseAndUseType()
    } else if (this.flag === 'view') {
      if (processObj.key != null) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/getEnterWareHouseByKey', { params: { key: processObj.key } }).then(res => {
          this.form = res.data
          this.getPartsAccounts()
        })
      }
      this.getWareHouseAndUseType()
      this.show = true
      this.showButton = false
      this.showFlag = 'view'
    } else if (this.flag === 'edit') {
      if (processObj.key != null) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/getEnterWareHouseByKey', { params: { key: processObj.key } }).then(res => {
          this.form = res.data
          this.getPartsAccounts()
        })
      }
      this.getWareHouseAndUseType()
      this.show = false
      this.showButton = true
      this.showFlag = 'edit'
    } else {
      if (processObj.businessKey !== undefined) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/getEnterWareHouseByKey', { params: { key: processObj.businessKey } }).then(res => {
          this.form = res.data
          this.getPartsAccounts()
          this.getWareHouseAndUseType()
          if (processObj.currentStep === '填写单据') {
            this.showFlag = 'edit'
            this.show = false
            this.showButton = true
          } else {
            this.show = true
            this.showFlag = 'view'
            this.showButton = false
          }
        })
      }
    }
  },
  methods: {
    selectWareHouse: function (v) {
      this.key += 1
      this.selectWareHouseFlag = v
    },
    handlerSelect: function () {
      if (this.selectWareHouseFlag) {
        this.dialogVisible = true
        this.selectFlag = this.selectWareHouseFlag
        this.key += 1
      } else {
        this.$message({
          message: '请先选择仓库',
          type: 'warning'
        })
      }
    },
    getWareHouseAndUseType: function () {
      var that = this
      this.$axios.all([
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=wareHouse'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=inBoundType')
      ]).then(this.$axios.spread(function (wareHouse, inBoundType) {
        // 上面两个请求都完成后，才执行这个回调方法
        that.wareHouse = wareHouse.data
        that.inType = inBoundType.data
      }))
    },
    handleClose: function (done) {
      this.$confirm('确认关闭？').then(_ => {
        done()
      }).catch(_ => { })
    },
    getPartsAccounts: function () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamPartsExtends/getExtendsByKey', { params: { key: this.form.key } }).then(res => {
        if (res.data.totalCount > 0) {
          this.partsTable = res.data.dataList
          this.totalCount = res.data.totalCount
        }
      })
    },
    handlerAfterFlow (v) { // 流程结束数据处理
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/updateAfterFlow', v).then(res => {
        if (res.data.resultType === 'ok') {
          window.close()
        }
      }).catch(error => {
        this.$message.error(error)
      })
    },
    handerSubmit: function (processInfo) {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.tableDatas === undefined) {
            this.tableDatas = this.partsTable
          }
          const requestParam = {
            enterWareHouse: this.form,
            partsExtends: this.tableDatas,
            flowProcessInfo: processInfo
          }
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/saveEnterWareHouse', requestParam).then(res => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              setTimeout(() => {
                window.close()
              }, 1000)
            } else {
              this.$message.error(res.data.message)
            }
          })
        } else {
          this.$message.error('验证数据失败，请重新确认数据填写')
          return false
        }
      })
    },
    tableParams: function (val) {
      this.tableDatas = val
    },
    resetSelect: function () {
      this.dialogVisible = false
      this.parts = []
    },
    handlerAdd: function () {
      var str = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0
        var v = c === 'x' ? r : (r & 0x3 | 0x8)
        return v.toString(16)
      })
      let partObj = {
        id: '',
        key: str,
        fileId: '',
        partsImg: '',
        deviceName: '',
        deviceCode: '',
        norm: '',
        materialCode: '',
        materialType: '',
        manufacturer: '',
        leaveFactoryCode: '',
        leaveFactoryDate: '',
        warningValue: '',
        supplier: '',
        price: '',
        amount: '',
        unit: '',
        totalPrice: 0,
        remark: ''
      }
      this.obj = partObj
    },
    overSelect: function () {
      this.dialogVisible = false
      // 将选择的数据传给table表
      this.parts = this.pDatas
    },
    resetForm: function () {
      this.$refs.form.resetFields()
    },
    partsData: function (multipleSelection) {
      if (multipleSelection.length > 11) {
        this.$message({
          message: '最多添加10条备件数据',
          type: 'warning'
        })
      }
      this.pDatas = multipleSelection
    }
  }
}
