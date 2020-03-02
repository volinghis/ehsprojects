import userTree from './userTree/index.vue'
import userTable from './userTable/index.vue'
export default {
  components: {
    userTree,
    userTable
  },
  watch: {
    filterText (val) {
      this.$refs.tree.filter(val)
    }
  },
  data () {
    return {
      currentOrgKey: '',
      currentOrgName: '',
      userTableData: [],
      organizationKey: '',
      organizationName: '',
      organizationChildren: false,
      defaultExpandKeys: [],
      searchParam: {},
      filterText: '',
      totalCount: 0,
      treeData: [],
      treeProps: {
        children: 'children',
        label: 'label'
      },
      props: {
        label: 'name',
        children: 'children',
        isLeaf: 'leaf'
      }
    }
  },
  mounted () {
    this.initTreeData()
    this.findUserByOrgKey()
  },
  methods: {
    loadNode (node, resolve) {
      if (node.level === 0) {
        this.requestTreeNodeOne(resolve)
      }
      if (node.level === 1) {
        this.requestTreeNode(node, resolve)
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
    handleNodeClick: function (data) {
      this.organizationKey = data.id
      this.organizationName = data.name
      if (data.leaf === false) {
        this.organizationChildren = data.leaf
      } else {
        this.organizationChildren = true
      }
      this.findUserByOrgKey(data.id, this.searchParam)
      this.nodeParentKey = data.id
    },
    findUserByOrgKey: function (key, formParams) { // 查询组织下所有人员
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgUser/findUserByOrgKey', { params: { orgKey: key, searchData: formParams } }).then(res => {
        this.userTableData = res.data.dataList
        this.totalCount = res.data.totalCount
      }).catch(() => {
        this.$message.error('查询出错，请刷新重试！')
      })
    },
    getUserBySearch: function (organizationKey, form) {
      this.searchParam = form
      this.findUserByOrgKey(organizationKey, form)
    }
  }
}
