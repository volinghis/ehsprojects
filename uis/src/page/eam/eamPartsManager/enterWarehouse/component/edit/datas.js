import addPart from '../addPart/index.vue'
import tablePart from '../tablePart/index.vue'
export default {
  components: {
    tablePart,
    addPart
  },
  data () {
    return {
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
    if (this.$route.params.data != null) {
      this.form = this.$route.params.data
    }
    this.parts = this.$route.params.pData
    this.flag = this.$route.params.flag
    // console.log(this.flag)
    if (this.flag === 'add') {
      // console.log(this.$refs.table._data)
      // console.log(this.$refs.table.tableData.length)
      if (this.$refs.table.tableData.length > 1) {
        // console.log('hhhhhhh')
        this.$refs.table.tableData = []
      }
    }
    if (this.flag === 'edit') {
      this.getPartsAccounts()
    }
  },
  methods: {
    handleClose: function (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => { })
    },
    getPartsAccounts: function () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamPartsExtends/getExtendsByKey', { params: { key: this.form.key } }).then(res => {
        if (res.data.totalCount > 0) {
          // this.parts = res.data.dataList
          this.partsTable = res.data.dataList
          this.totalCount = res.data.totalCount
        }
      })
    },
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
          // console.log(this.parts)
          const requestParam = {
            enterWareHouse: this.form,
            // partsAccounts: this.parts
            partsExtends: this.tableDatas
          }
          console.log(requestParam)
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamEnterWareHouse/saveEnterWareHouse', requestParam).then(res => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.$router.push({ name: 'enterWarehouse', replace: true })
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
