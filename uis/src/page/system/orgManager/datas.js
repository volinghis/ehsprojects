export default {
  watch: {
    filterText (val) {
      this.$refs.tree.filter(val)
    }
  },
  data () {
    return {
      node: {},
      resolve: [],
      nodeOne: {},
      resolveOne: [],
      filterText: '',
      nodeParentKey: '',
      orgList: Array,
      treeData: [],
      defaultExpandKeys: [],
      orgTableData: [],
      totalCount: 0,
      dialogTableVisible: false,
      // treeProps: {
      //   children: 'children',
      //   label: 'label'
      // },
      props: {
        label: 'name',
        children: 'children',
        isLeaf: 'leaf'
      },
      form: {
        page: 1,
        size: 20
      },
      formLabelAlign: {
        dataCode: '',
        parentKey: '',
        name: '',
        sort: ''
      },
      rules: {
        dataCode: [
          { required: true, message: '请输入部门编码', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入部门名称', trigger: 'blur' }
        ],
        sort: [
          { required: true, message: '请输入排序号', trigger: 'blur' },
          { type: 'number', message: '请输入正确数字', trigger: 'blur', transform: (value) => Number(value) }
        ]
      }
    }
  },
  mounted () {
    this.initTreeData()
    this.findOrgsByParentKey()
  },
  methods: {
    loadNode (node, resolve) {
      if (node.level === 0) {
        this.nodeOne = node
        this.resolveOne = resolve
        this.requestTreeNodeOne(resolve)
      }
      if (node.level >= 1) {
        this.requestTreeNode(node, resolve)
        this.node = node
        this.resolve = resolve
      }
    },
    requestTreeNodeOne (resolve) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgManager/getTreeLazyNode').then(res => {
        resolve(res.data)
      })
    },
    requestTreeNode (node, resolve) {
      if (node) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgManager/getTreeLazyNode', { params: { id: node.data.id } }).then(res => {
          resolve(res.data)
        })
      }
    },
    filterNode (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    initTreeData: function () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgManager/getAllForTree').then(res => {
        this.treeData = res.data
        this.defaultExpandKeys = [this.treeData[0].id]
      })
    },
    // orgCodeValidation: function (d) {
    //   this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgManager/orgValidation', { params: { dataCode: d.dataCode, key: d.key } }).then(res => {
    //     if (res.data.resultType === 'error') {
    //       this.$message.error(res.data.message)
    //       this.form.dataCode = ''
    //     }
    //     if (res.data.resultType === 'ok') {
    //       this.$message({
    //         message: res.data.message,
    //         type: 'success'
    //       })
    //     }
    //   })
    // },
    handleNodeClick: function (data) {
      this.findOrgsByParentKey(data.id)
      this.nodeParentKey = data.id
    },
    findOrgsByParentKey: function (key) { // 查询组织下所有组织
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgManager/findOrgsByParentKey', { params: { orgParentKey: key } }).then(res => {
        this.orgTableData = res.data.dataList
        this.totalCount = res.data.totalCount
      }).catch(() => {
        this.$message.error('查询出错，请刷新重试！')
      })
    },
    orgAdd: function () {
      const node = this.nodeParentKey
      if (node === '') {
        this.$message({
          message: '请选择部门',
          type: 'warning'
        })
      } else {
        this.dialogTableVisible = true
        this.formLabelAlign = {}
        this.formLabelAlign.parentKey = node
      }
    },
    handleAuthToOrg: function (row) {

    },
    handleEdit: function (row) {
      this.dialogTableVisible = true
      this.formLabelAlign = row
    },
    handleReset: function () {
      this.dialogTableVisible = false
      this.findOrgsByParentKey()
    },
    handleClose: function () {
      this.$confirm('确认关闭？')
        .then(_ => {
          this.dialogTableVisible = false
          this.findOrgsByParentKey()
        })
        .catch(_ => {})
    },
    handleRemove: function (row) {
      this.$confirm('此操作将删除此部门, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgManager/deleteOrgInfo', { params: { key: row.key } })
          .then((res) => {
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
            this.findOrgsByParentKey(row.parentKey)
            this.nodeOne.childNodes = []// 把存起来的node的子节点清空，不然会界面会出现重复树！
            this.loadNode(this.nodeOne, this.resolveOne)// 再次执行懒加载的方法
          }).catch((error) => {
            this.$message.error(error)
          })
      })
    },
    onSubmit () {
      this.$refs.formLabelAlign.validate((valid) => {
        if (valid) {
          this.handleSubmit()
        } else {
          return false
        }
      })
    },
    handleSubmit: function () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/organization/saveOrg', this.formLabelAlign).then(res => {
        if (res.data.resultType === 'ok') {
          this.$message({
            message: `部门${this.formLabelAlign.name}保存成功`,
            type: 'success'
          })
          this.dialogTableVisible = false
          this.findOrgsByParentKey(this.formLabelAlign.parentKey)
          this.$refs.formLabelAlign.resetFields()
          this.nodeOne.childNodes = []// 把存起来的node的子节点清空，不然会界面会出现重复树！
          this.loadNode(this.nodeOne, this.resolveOne)// 再次执行懒加载的方法
        }
        if (res.data.resultType === 'error') {
          this.$message({
            message: res.data.message,
            type: 'warning'
          })
          this.formLabelAlign.dataCode = ''
        }
      })
    },
    handleCurrentChange (val) { // 页面跳转
      this.form.page = val
      this.findOrgsByParentKey()
    }
  }
}
