import addPart from '../addPart/index.vue'
import tablePart from '../tablePart/index.vue'
export default {
  components: {
    tablePart,
    addPart
  },
  data () {
    return {
      obj: {},
      show: false,
      showFlag: '',
      parts: [],
      pDatas: [],
      partsTable: [],
      tableDatas: [],
      tableHeight: ' ',
      dialogVisible: false,
      totalCount: 0,
      form: {
        warehouseName: '',
        warehouseCode: '',
        supplier: '',
        inboundType: '',
        inboundDate: '',
        founder: '',
        remark: ''
      },
      rules: {
        warehouseCode: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        warehouseName: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        inboundType: [
          { required: true, message: '请选择入库类型', trigger: 'blur' }
        ],
        supplier: [
          { required: true, message: '请输入供应商', trigger: 'blur' }
        ],
        principal: [
          { required: true, message: '请输入入库人', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    const user = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.form.founder = user.username
    var processObj = JSON.parse(this.$route.params.processInfo)
    if (processObj.data != null) {
      if (processObj.flag === 'view') {
        this.form = processObj.data
      }
    } else {
      if (processObj.businessKey !== undefined) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/getEnterWareHouseByKey', { params: { key: processObj.businessKey } }).then(res => {
          this.form = res.data
          this.getPartsAccounts()
        })
      }
    }
    // this.parts = this.$route.params.pData
    this.flag = processObj.flag
    if (this.flag === 'add') {
      if (this.$refs.table.tableData.length > 1) {
        this.$refs.table.tableData = []
      }
      this.show = true
      this.showFlag = 'add'
    } else {
      this.show = false
      this.showFlag = 'view'
      this.getPartsAccounts()
    }
  },
  methods: {
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
          const requestParam = {
            enterWareHouse: this.form,
            // partsAccounts: this.parts
            partsExtends: this.tableDatas,
            flowProcessInfo: processInfo
          }
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/saveEnterWareHouse', requestParam).then(res => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              // this.$router.push({ name: 'enterWarehouse', replace: true })
              window.close()
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
        key: str,
        deviceName: '',
        deviceCode: '',
        norm: '',
        materialCode: '',
        materialType: '',
        manufacturer: '',
        leaveFactoryCode: '',
        leaveFactoryDate: '',
        warningValue: '',
        founder: '',
        supplier: '',
        price: 0,
        amount: 0,
        unit: '',
        totalPrice: 0
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
