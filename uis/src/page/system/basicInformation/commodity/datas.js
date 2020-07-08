export default {
  data () {
    return {
      wareHouses: [],
      contactsData: [],
      supplierTypes: [],
      supplierlevels: [],
      buttonFlag: true,
      commodityTable: [],
      totalCount: 0,
      dialogTableVisible: false,
      queryParam: {
        query: '',
        page: 1,
        size: 20
      },
      commodityForm: {
        wareHouse: '',
        deviceCode: '',
        deviceName: '',
        norm: '',
        leaveFactoryCode: '',
        leaveFactoryDate: '',
        price: '',
        unit: '',
        sort: ''
      },
      rules: {
        wareHouse: [
          { required: true, message: '请选择仓库', trigger: 'change' }
        ],
        deviceCode: [
          { required: true, message: '请输入商品编码', trigger: 'blur' }
        ],
        deviceName: [
          { required: true, message: '请输入商品名称', trigger: 'blur' }
        ],
        norm: [
          { required: true, message: '请输入规格型号', trigger: 'change' }
        ],
        price: [
          { required: true, message: '请输入价格', trigger: 'blur' },
          { type: 'number', message: '请输入正确数字', trigger: 'blur', transform: value => Number(value) }
        ],
        unit: [
          {
            required: true,
            message: '请输入单位（例如：‘个’）',
            trigger: 'blur'
          },
          { min: 1, max: 3, message: '长度在 1 到 3 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    this.init()
    this.getWareHouseAndUseType()
  },
  methods: {
    init () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/basicInfo/commodityManager/getCommodities', this.queryParam).then((res) => {
        this.commodityTable = res.data.dataList
        this.totalCount = res.data.totalCount
      })
    },
    getWareHouseAndUseType: function () {
      var that = this
      this.$axios.all([
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=wareHouse')
      ]).then(this.$axios.spread(function (w) {
        // 上面两个请求都完成后，才执行这个回调方法
        that.wareHouses = w.data
      }))
    },
    handlAdd: function () {
      this.dialogTableVisible = true
      this.commodityForm = {}
    },
    handleEdit: function (row) {
      this.dialogTableVisible = true
      this.commodityForm = row
    },
    handleReset: function () {
      this.dialogTableVisible = false
    },
    handleClose: function () {
      this.$confirm('确认关闭？').then(_ => {
        this.dialogTableVisible = false
      }).catch(_ => {})
    },
    handleRemove: function (row) {
      this.$confirm('此操作将删除该条记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/basicInfo/commodityManager/deleteCommodity', { params: { key: row.key } }).then((res) => {
          if (res.data.resultType === 'ok') {
            this.$message({
              message: res.data.message,
              type: 'success'
            })
          }
          if (res.data.resultType === 'error') {
            this.$message({
              message: res.data.message,
              type: 'warning'
            })
          }
          this.init()
        }).catch((error) => {
          this.$message.error(error)
        })
      })
    },
    handleSubmit: function () {
      this.$refs.commodityForm.validate(valid => {
        if (valid) {
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/basicInfo/commodityManager/saveCommodity', this.commodityForm).then(res => {
            this.$message({
              message: '保存成功',
              type: 'success'
            })
            this.dialogTableVisible = false
            this.init()
          })
        } else {
          this.$message.error('保存失败')
          return false
        }
      })
    },
    handleCurrentChange (val) { // 页面跳转
      this.queryParam.page = val
    }
  }
}
