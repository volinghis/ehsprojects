export default {
  props: {
    'dataRow': Object
  },
  data () {
    return {
      multipleSelection: {},
      tableRadio: {},
      wareflag: '',
      partsTable: [],
      overAmount: '',
      dialogTableVisible: false,
      partsData: {
        deleted: false,
        wareHouse: '',
        wareHouseName: '',
        deviceName: '',
        deviceCode: '',
        amount: '',
        receivePerson: '',
        receivePersonName: '',
        receiveOrg: ''
      },
      queryBean: {
        flag: '',
        query: '',
        page: 1,
        size: 20,
        totalCount: 0
      },
      objects: [],
      wareHouseNames: [],
      partsDataRules: {
        wareHouse: [
          { required: true, message: '请选择仓库', trigger: 'change' }
        ],
        deviceName: [
          { required: true, message: '请选择备件', trigger: 'change' }
        ],
        amount: [
          { required: true, message: '请输入数量', trigger: 'blur' },
          { type: 'number', message: '请输入正确数字', trigger: 'blur', transform: value => Number(value) }
        ],
        receivePerson: [
          { required: true, message: '请选择领用人', trigger: 'change' }
        ]
      }
    }
  },
  computed: {

  },
  methods: {
    overSelect () {
      if (this.multipleSelection) {
        this.partsData.deviceName = this.multipleSelection.deviceName
        this.overAmount = this.multipleSelection.dummyAmount
        this.partsData.wareHouseName = this.multipleSelection.wareHouseName
        this.partsData.deviceCode = this.multipleSelection.deviceCode
        this.dialogTableVisible = false
      }
    },
    resetSelect () {
      this.dialogTableVisible = false
    },
    userSelectChange () {
      var node = this.$refs.userSelect.getCheckedNodes()
      this.partsData.receivePersonName = node[0].label // 用户名称
      this.partsData.receiveOrg = node[0].parent.label // 部门名称
    },
    selectParts (v) {
      if (this.wareflag === '') {
        this.$message({
          message: '请先选择仓库',
          type: 'warning'
        })
      } else {
        this.getAllParts(this.wareflag)
        this.dialogTableVisible = true
      }
    },
    selectWareHouse (v) {
      this.wareflag = v
    },
    changePage (v) {
      this.queryBean.page = v
      this.getAllParts()
    },
    getCurrentRow (row) {
      this.multipleSelection = row
    },
    handleSelectionChange: function (val) {
      this.multipleSelection = val
    },
    getAllParts: function (v) {
      this.queryBean.flag = v
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/partsAccount/getAllPartsAccount', this.queryBean).then(res => {
        this.partsTable = res.data.dataList
        this.queryBean.totalCount = res.data.totalCount
      })
    },
    randomString (len) {
      len = len || 64
      var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678' /** **默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
      var maxPos = $chars.length
      var pwd = ''
      for (var i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos))
      }
      return pwd
    },
    cancelForm () {
      if (this.dataRow) {
        this.$parent.$parent.$parent.$parent.$parent.$refs['partsTable'].toggleRowExpansion(this.dataRow)
      }
      this.$parent.$parent.$parent.partsAdd = false
    },

    submitForm () {
      this.$refs['partsData'].validate((valid) => {
        if (valid) {
          if (this.partsData.amount > this.overAmount) {
            this.$message({
              message: '您输入的数量已经超过库存数量，请重新输入',
              type: 'warning'
            })
            this.partsData.amount = ''
            return
          }
          if (!this.dataRow) {
            this.partsData.key = this.randomString()
            this.$parent.$parent.$parent.partsInner(this.partsData)
            this.$parent.$parent.$parent.partsAdd = false
          } else {
            this.$parent.$parent.$parent.$parent.$parent.partsDataUpdate(this.partsData)
            this.$parent.$parent.$parent.$parent.$parent.$refs['partsTable'].toggleRowExpansion(this.dataRow)
          }
        }
      })
    }
  },

  mounted () {
    var that = this
    this.$axios.all([
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=wareHouse')
    ]).then(this.$axios.spread(function (warehouse) {
      that.wareHouseNames = warehouse.data
      // 上面两个请求都完成后，才执行这个回调方法
      if (that.dataRow) {
        // that.objectTypeChange(that.dataRow.objectType)
        that.partsData = Object.assign(that.partsData, that.dataRow)
      }
    }))
  }
}
