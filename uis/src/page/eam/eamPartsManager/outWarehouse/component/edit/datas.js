import addPart from '../addPart/index.vue'
import tablePart from '../tablePart/index.vue'
import orgSelect from '@/components/org/org-selector/index.vue'
import userSelect from '@/components/org/user-selector/index.vue'
export default {
  components: {
    tablePart,
    addPart,
    orgSelect,
    userSelect
  },
  data () {
    return {
      tableHeight: ' ',
      pDatas: [],
      parts: [],
      flag: '',
      tableData: [],
      partsTable: [],
      totalCount: 0,
      dialogVisible: false,
      form: {
        outWarehouseName: '',
        outWarehouseCode: '',
        outBoundType: '',
        receivDepart: '',
        receivEmp: '',
        outBoundDate: '',
        creatDate: '',
        remark: ''
      },
      rules: {
        outWarehouseName: [
          { required: true, message: '请输入仓库名称', trigger: 'blur' }
        ],
        outBoundType: [
          { required: true, message: '请选择出库类型', trigger: 'blur' }
        ],
        supplier: [
          { required: true, message: '请输入设备型号', trigger: 'blur' }
        ],
        receivDepart: [
          { required: true, message: '请选择部门', trigger: 'blur' }
        ],
        receivEmp: [
          { required: true, message: '请选择领用人', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    // this.getPartsAccounts()
    const d = new Date()
    var datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' '
    this.form.creatDate = datetime
    const user = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.form.founder = user.username
    if (this.$route.params.data != null) {
      this.form = this.$route.params.data
    }
    this.parts = this.$route.params.pData
    this.flag = this.$route.params.flag
    console.log(this.flag)
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
    getPartsAccounts: function () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamPartsExtends/getExtendsByKey', { params: { key: this.form.key } }).then(res => {
        if (res.data.totalCount > 0) {
          // this.parts = res.data.dataList
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
    handerSubmit: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
          console.log(valid)
          const requestParam = {
            outWareHouse: this.form,
            partsExtends: this.tableDatas
          }
          console.log(requestParam)
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamOutWarehouse/saveOutWareHouse', requestParam).then(res => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.$router.push({ name: 'outWarehouse', replace: true })
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
    handleExceed: function (files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      )
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
