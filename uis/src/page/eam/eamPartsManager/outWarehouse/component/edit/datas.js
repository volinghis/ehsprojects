import AddPart from '../addPart/index.vue'
import TablePart from '../tablePart/index.vue'
import OrgSelector from '@/components/org/org-selector/index.vue'
import UserSelector from '@/components/org/user-selector/index.vue'
export default {
  components: {
    AddPart,
    TablePart,
    OrgSelector,
    UserSelector
  },
  data () {
    return {
      selectFlag: '',
      selectWareHouseFlag: '',
      wareHouse: [],
      outType: [],
      show: false,
      showButton: false,
      tableHeight: ' ',
      pDatas: [],
      parts: [],
      flag: '',
      showFlag: '',
      tableData: [],
      partsTable: [],
      totalCount: 0,
      dialogVisible: false,
      form: {
        outWarehouse: '',
        outWarehouseName: '',
        outWarehouseCode: '',
        outBoundDate: '',
        outBoundType: '',
        outBoundTypeName: '',
        receiveEmpCode: '',
        receiveDepartCode: '',
        remark: ''
      },
      rules: {
        outWarehouseCode: [
          { required: true, message: '请输入仓库编码', trigger: 'blur' }
        ],
        outWarehouseName: [
          { required: true, message: '请输入仓库名称', trigger: 'blur' }
        ],
        outBoundType: [
          { required: true, message: '请输入出库类型', trigger: 'blur' }
        ],
        receiveDepartCode: [
          { required: true, message: '请选择部门', trigger: 'blur' }
        ],
        receiveEmpCode: [
          { required: true, message: '请选择领用人', trigger: 'blur' }
        ]
      }
    }
  },
  created: function () {
    var processObj = JSON.parse(this.$route.params.processInfo)
    this.flag = processObj.flag
    if (this.flag === 'add') {
      const d = new Date()
      var datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' '
      this.form.creatDate = datetime
      const user = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
      this.form.founder = user.username
      this.show = false
      this.showButton = true
      this.showFlag = 'add'
      this.getWareHouseAndUseType()
    } else if (this.flag === 'view') {
      if (processObj.key) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getOutWareHouseByKey', { params: { key: processObj.key } }).then(res => {
          this.form = res.data
          this.getPartsAccounts()
          this.getWareHouseAndUseType()
        })
      }
      this.show = true
      this.showButton = false
      this.showFlag = 'view'
    } else if (this.flag === 'edit') {
      if (processObj.key) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getOutWareHouseByKey', { params: { key: processObj.key } }).then(res => {
          this.form = res.data
          this.getPartsAccounts()
          this.getWareHouseAndUseType()
        })
      }
      this.show = false
      this.showButton = true
      this.showFlag = 'edit'
    } else {
      if (processObj.businessKey !== undefined) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/getOutWareHouseByKey', { params: { key: processObj.businessKey } }).then(res => {
          this.form = res.data
          if (processObj.currentStep === '填写单据') {
            this.showFlag = 'edit'
            this.show = false
            this.showButton = true
          } else {
            this.show = true
            this.showFlag = 'view'
            this.showButton = false
          }
          this.getPartsAccounts()
          this.getWareHouseAndUseType()
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
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=outBoundType')
      ]).then(this.$axios.spread(function (wareHouse, outBoundType) {
        // 上面两个请求都完成后，才执行这个回调方法
        that.wareHouse = wareHouse.data
        that.outType = outBoundType.data
      }))
    },
    getPartsAccounts: function () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamPartsExtends/getExtendsByKey', { params: { key: this.form.key } }).then(res => {
        if (res.data.totalCount > 0) {
          this.partsTable = res.data.dataList
          this.totalCount = res.data.totalCount
        }
      })
    },
    tableParams: function (val) {
      this.tableDatas = val
    },
    handleClose: function (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    resetSelect: function () {
      this.dialogVisible = false
    },
    handlerAfterFlow (v) { // 流程结束数据处理
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/updateAfterFlow', v).then(res => {
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
            outWareHouse: this.form,
            partsExtends: this.tableDatas,
            flowProcessInfo: processInfo
          }
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/saveOutWareHouse', requestParam).then(res => {
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
    resetForm: function () {
      this.$refs.form.resetFields()
    },
    overSelect: function () {
      this.dialogVisible = false
      // 将选择的数据传给table表
      this.parts = this.pDatas
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
