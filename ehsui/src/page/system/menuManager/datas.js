import LeftRolesTable from './moduls/leftRolesTable'
export default {
  components: {
    LeftRolesTable
  },
  data () {
    return {
      treeData: [],
      tableData: [],
      currentMenuKey: '',
      selectRoles: [],
      dialogTableVisible: false,
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  mounted () {
    this.initTree()
  },
  methods: {
    initTree () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/menu/menuDatas', { params: { menuAuth: true } }).then(res => {
        this.treeData = res.data
        console.log(this.treeData)
      }).catch(error => {
        console.log(error)
      })
    },
    handleNodeClick (data) {
      this.currentMenuKey = data.key
      this.findRolesByMenu(data.key)
    },
    findRolesByMenu (key) { // 查询菜单所有的角色权限
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/menu/findMenuRoles', { params: { menuKey: key } }).then(res => {
        this.tableData = res.data
      }).catch(error => {
        console.log(error)
      })
    },
    handleRomoveAuth (row) {
      this.$confirm('是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var roleBeans = []
        roleBeans.push({ roleType: 'ROLE', roleKey: row.key })
        this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/menu/deleteMenuRole', { menuKey: this.currentMenuKey, roleList: roleBeans })
          .then((res) => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.findRolesByMenu(this.currentMenuKey)
            }
          }).catch((error) => {
            console.log(error)
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handleAuthToMenu () {
      const node = this.$refs.tree.getCurrentNode()
      if (node === null) {
        this.$message({
          message: '请选择菜单',
          type: 'warning'
        })
      } else {
        this.dialogTableVisible = true
      }
    },
    handleSubmit () {
      const roles = this.selectRoles
      console.log(roles)
      var roleBeans = []
      for (let i = 0; i < roles.length; i++) {
        const e = roles[i]
        roleBeans.push({ roleType: 'ROLE', roleKey: e.key })
      }
      if (roles.length <= 0) {
        this.$message({
          message: '请选择角色',
          type: 'warning'
        })
      } else {
        this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/menu/saveMenuRole', { menuKey: this.currentMenuKey, roleList: roleBeans }).then(res => {
          if (res.data.resultType === 'ok') {
            this.$message({
              message: res.data.message,
              type: 'success'
            })
            this.findRolesByMenu(this.currentMenuKey)
            this.dialogTableVisible = false
          } else {
            alert('失败')
          }
        }).catch(error => {
          console.log(error)
        })
      }
    },
    handleSelect (data) {
      this.selectRoles = data
    }
  }
}
