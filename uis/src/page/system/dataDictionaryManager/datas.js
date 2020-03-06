export default {
  data () {
    return {
      nodeLevel: 0,
      node: {},
      resolve: [],
      nodeParentKey: '',
      orgList: Array,
      treeData: [],
      defaultExpandKeys: [],
      orgTableData: [],
      totalCount: 0,
      dialogTableVisible: false,
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
        parentCode: '',
        text: '',
        sort: ''
      },
      rules: {
        dataCode: [
          { required: true, message: '请输入字典编码', trigger: 'blur' }
        ],
        text: [
          { required: true, message: '请输入字典名称', trigger: 'blur' }
        ],
        sort: [
          { required: true, message: '请输入排序号', trigger: 'blur' },
          { type: 'number', message: '请输入正确数字', trigger: 'blur', transform: (value) => Number(value) }
        ]
      }
    }
  },
  mounted () {
    this.findDatasByParentKey()
  },
  methods: {
    loadNode (node, resolve) {
      if (node.level === 0) {
        this.requestTreeNodeOne(resolve)
      }
      if (node.level >= 1) {
        this.requestTreeNode(node, resolve)
        this.node = node
        this.resolve = resolve
      }
    },
    requestTreeNodeOne (resolve) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/getTreeLazyNode').then(res => {
        resolve(res.data)
      })
    },
    requestTreeNode (node, resolve) {
      if (node) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/getTreeLazyNode', { params: { id: node.data.id } }).then(res => {
          resolve(res.data)
        })
      }
    },
    handleNodeClick: function (data, node) {
      this.findDatasByParentKey(data.id)
      this.nodeParentKey = data.id
      this.nodeLevel = node.level
    },
    findDatasByParentKey: function (key) { // 查询组织下所有组织
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentCode', { params: { parentCode: key } }).then(res => {
        this.orgTableData = res.data.dataList
        this.totalCount = res.data.totalCount
      }).catch(() => {
        this.$message.error('查询出错，请刷新重试！')
      })
    },
    dictionaryAdd: function () {
      const node = this.nodeParentKey
      const level = this.nodeLevel
      if (node === '') {
        this.$message({
          message: '请选择节点',
          type: 'warning'
        })
      } else if (level >= 2) {
        this.$message({
          message: '该节点下不可添加',
          type: 'warning'
        })
      } else {
        this.dialogTableVisible = true
        this.formLabelAlign = {}
        this.formLabelAlign.parentCode = node
      }
    },
    handleEdit: function (row) {
      this.dialogTableVisible = true
      this.formLabelAlign = row
    },
    handleReset: function () {
      this.dialogTableVisible = false
      this.findDatasByParentKey()
    },
    handleClose: function () {
      this.$confirm('确认关闭？')
        .then(_ => {
          this.dialogTableVisible = false
          this.findDatasByParentKey()
        })
        .catch(_ => {})
    },
    handleRemove: function (row) {
      this.$confirm('此操作将删除该条记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/deleteDataDictionary', { params: { key: row.key } }).then((res) => {
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
          this.findDatasByParentKey()
          this.loadNode(this.node, this.resolve)
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
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/saveDataDictionary', this.formLabelAlign).then(res => {
        if (res.data.resultType === 'ok') {
          this.$message({
            message: res.data.message,
            type: 'success'
          })
          this.dialogTableVisible = false
          this.findDatasByParentKey(this.formLabelAlign.parentCode)
          this.$refs.formLabelAlign.resetFields()
          this.loadNode(this.node, this.resolve)
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
      this.findDatasByParentKey()
    }
  }
}
