export default {
  data () {
    return {
      contactsData: [],
      supplierTypes: [],
      supplierlevels: [],
      buttonFlag: true,
      supplierTableData: [],
      totalCount: 0,
      dialogTableVisible: false,
      queryParam: {
        query: '',
        page: 1,
        size: 20
      },
      supplierForm: {
        supplierCode: '',
        supplierName: '',
        supplierType: '',
        sort: '',
        remark: ''
      },
      contarts: {
        sel: null, // 选中行
        data: [],
        columns: [
          {
            prop: 'contartName',
            label: '联系人'
          },
          {
            prop: 'contartTel',
            label: '手机'
          },
          {
            prop: 'contartPosition',
            label: '职位'
          },
          {
            prop: 'contartAdd',
            label: '地址'
          }
        ]
      },
      rules: {
        supplierCode: [
          { required: true, message: '请输入客户编码', trigger: 'blur' }
        ],
        supplierName: [
          { required: true, message: '请输入客户名称', trigger: 'blur' }
        ],
        supplierType: [
          { required: true, message: '请选择客户类型', trigger: 'change' }
        ],
        sort: [
          { required: true, message: '请输入排序号', trigger: 'blur' },
          { type: 'number', message: '请输入正确数字', trigger: 'blur', transform: (value) => Number(value) }
        ]
      }
    }
  },
  mounted () {
    this.getsupplier()
    this.init()
  },
  methods: {
    init () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/basicInfo/supplierManager/getSuppliers', this.queryParam).then((res) => {
        this.supplierTableData = res.data.dataList
        this.totalCount = res.data.totalCount
      })
    },
    getContacts (key) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/basicInfo/contactManager/getContacts', { params: { key } }).then((res) => {
        this.contarts.data = res.data
      })
    },
    getsupplier: function () {
      var that = this
      this.$axios.all([ this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=supplierType'),
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=supplierLevel') ])
        .then(this.$axios.spread(function (p, l) {
          that.supplierTypes = p.data
          // that.supplierlevels = l.data
        }))
    },
    handlAdd: function () {
      this.dialogTableVisible = true
      this.supplierForm = {}
    },
    handleEdit: function (row) {
      this.dialogTableVisible = true
      this.supplierForm = row
      this.getContacts(row.key)
    },
    handleReset: function () {
      this.dialogTableVisible = false
    },
    handleClose: function () {
      this.$confirm('确认关闭？').then(_ => {
        this.dialogTableVisible = false
      }).catch(_ => {})
    },
    changeState: function (e, row, index) {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/basicInfo/supplierManager/changeState', row).then(res => {
        if (res.data.state === 0) {
          this.$message({
            message: '已经切换到启用状态',
            type: 'success'
          })
        } else {
          this.$message({
            message: '已经切换到停用状态',
            type: 'warning'
          })
        }
      }).catch(() => {
        this.$message.error('切换状态失败')
        let newData = row
        newData.state = newData.state === 0 ? 1 : 0// 恢复 原状态
        this.supplierTableData[index] = newData
      })
    },
    handleRemove: function (row) {
      console.log(row.key)
      this.$confirm('此操作将删除该条记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/basicInfo/supplierManager/deleteSupplier', { params: { key: row.key } }).then((res) => {
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
      this.$refs.supplierForm.validate(valid => {
        if (valid) {
          const requestParam = {
            supplier: this.supplierForm,
            contactInfos: this.contarts.data
          }
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/basicInfo/supplierManager/saveSupplier', requestParam).then(res => {
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
      this.form.page = val
    },
    add () {
      for (let i of this.contarts.data) {
        if (i.isSet) return this.$message.warning('请先保存当前编辑项')
      }
      let j = {
        'contartName': '',
        'contartTel': '',
        'contartPosition': '',
        'contartAdd': '',
        'isSet': true
      }
      this.contarts.data.push(j)
      this.contarts.sel = JSON.parse(JSON.stringify(j))
    },
    saveRow (row, index) { // 保存
      let paramData = JSON.parse(JSON.stringify(this.contarts.sel))
      for (let k in paramData) {
        row[k] = paramData[k]
      }
      if (row.contartName === '') {
        return this.$message.warning('联系人不能为控')
      }
      if (row.contartTel === '') {
        return this.$message.warning('电话不能为空')
      }
      row.isSet = false
    },
    editRow (row) { // 编辑
      for (let i of this.contarts.data) {
        if (i.isSet) return this.$message.warning('请先保存当前编辑项')
      }
      this.contarts.sel = row
      row.isSet = true
    },
    deleteRow (index, rows) { // 删除
      rows.splice(index, 1)
    }
  }
}
