import userForm from '../userForm/index.vue'
import userAuth from '../../userAuth/index.vue'
export default {
  data () {
    return {
      drawer: false,
      exchange: false,
      userKey: '',
      userInfo: '',
      organName: '',
      organKey: '',
      dialogVisible: false,
      tableData: [],
      totalCount: 0,
      editUserForm: {},
      form: {
        query: '',
        page: 1,
        size: 20
      },
      roleTableData: [],
      roleTable: []
    }
  },
  components: {
    userForm,
    userAuth
  },
  props: {
    organizationKey: String,
    organizationName: String,
    organizationChildren: Number,
    totals: Number,
    userDatas: Array
  },
  watch: {
    userDatas: {
      handler (val) {
        if (val) {
          this.tableData = val
        }
      },
      deep: true
    },
    organizationKey: function (newValue, oldValue) {
      this.organizationKey = newValue
    },
    organizationName: function (newValue, oldValue) {
      this.organizationName = newValue
    },
    organizationChildren: function (newValue, oldValue) {
      this.organizationChildren = newValue
    },
    totals: function (newValue, oldValue) {
      this.totalCount = newValue
    }
  },
  // created () {
  //   this.findUserByOrgKey()
  // },
  methods: {
    transferUser: function (rows) {
      console.log(rows)
      if (rows) {
        // rows.forEach(row => {
        //   this.$refs.multipleTable.toggleRowSelection(row)
        // })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    handleSelectionChange: function (val) {
      if (val.length > 0) {
        this.exchange = true
        this.multipleSelection = val
      } else {
        this.exchange = false
      }
    },
    addUser: function () {
      this.userInfo = 'add'
      if (this.organizationName === '') {
        this.$message({
          message: '请选择部门',
          type: 'warning'
        })
        return
      } if (this.organizationChildren > 0) {
        this.$message({
          message: '请选择具体部门',
          type: 'warning'
        })
      } else {
        if (this.$refs.addUserForm !== undefined) {
          this.$refs.addUserForm.form = {}
          this.$refs.addUserForm.form.orgKey = this.organizationKey
          this.$refs.addUserForm.form.orgName = this.organizationName
          this.$refs.addUserForm.form.gender = '男'
        }
        this.organKey = this.organizationKey
        this.organName = this.organizationName
        this.dialogVisible = true
      }
    },
    editUser: function (row) {
      this.dialogVisible = true
      this.editUserForm = row
      this.userInfo = 'edit'
    },
    // findUserByOrgKey: function (key, formParams) { // 查询组织下所有人员
    //   this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgUser/findUserByOrgKey', { params: { orgKey: 'rootOrg', searchData: formParams } }).then(res => {
    //     console.log('key==========' + key)
    //     this.userTableData = res.data.dataList
    //     this.totalCount = res.data.totalCount
    //   }).catch(() => {
    //     this.$message.error('查询出错，请刷新重试！')
    //   })
    // },
    // 当前状态：data,  d:当前数据对象, index:当前序号(数组下标)
    changeState: function (e, row, index) {
      // console.log(row.orgKey)
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/orgUser/changeState', row).then(res => {
        if (res.data.state === 0) {
          this.$message({
            message: '已经切换到启用状态',
            type: 'success'
          })
          this.$emit('findUserByOrgKey', row.orgKey)
        } else {
          this.$message({
            message: '已经切换到停用状态',
            type: 'warning'
          })
          this.$emit('findUserByOrgKey', row.orgKey)
        }
        // TODO ：刷新列表数据
      }).catch(() => {
        this.$message.error('切换状态失败')
        let newData = row
        newData.state = newData.state === 0 ? 1 : 0// 恢复 原状态
        this.tableData[index] = newData
      })
    },
    authorizeUser: function (row) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgUser/findUserRoles', { params: { userKey: row.key } }).then(res => {
        if (res.data != null) {
          this.roleTable = res.data
        }
        this.loading = false
      }).catch(error => {
        this.$message.error(error)
      })
      this.userKey = row.key
      this.drawer = true
    },
    handleView: function (row) {
      this.dialogVisible = true
      this.editUserForm = row
      this.userInfo = 'view'
    },
    onSubmit: function () {
      this.$refs.addUserForm.$refs.form.validate((valid) => {
        if (valid) {
          this.handleSubmit()
        } else {
          return false
        }
      })
    },
    handleSubmit: function () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/orgUser/saveOrgUser', this.$refs.addUserForm.form).then(res => {
        if (res.data.resultType === 'ok') {
          this.$message({
            message: res.data.message,
            type: 'success'
          })
          this.dialogVisible = false
          this.$emit('findUserByOrgKey', this.$refs.addUserForm.form.orgKey)
        }
        if (res.data.resultType === 'error') {
          this.$message({
            message: res.data.message,
            type: 'warning'
          })
          this.$refs.addUserForm.form.dataCode = ''
        }
      })
    },
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
          this.searchUser()
        })
        .catch(_ => { })
    },
    searchUser: function () {
      // this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/orgUser/getAllUser', this.form)
      //   .then((res) => {
      //     this.tableData = res.data.dataList
      //     this.totalCount = res.data.totalCount
      //   })
      // this.tableData = this.userDatas
      this.$emit('getUserBySearch', this.organizationKey, this.form)
    },
    handleCurrentChange (val) { // 页面跳转
      this.form.page = val
      this.searchUser(this.form.page)
    },
    handleDelete (row) {
      this.$confirm('此操作将删除该条记录及相关信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgUser/deleteOrgUser', { params: { key: row.key } })
          .then((res) => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.searchUser()
            }
          }).catch((error) => {
            this.$message.error(error)
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    authResult: function (val) {
      if (val === false) {
        this.drawer = false
      }
    }
  }
}
