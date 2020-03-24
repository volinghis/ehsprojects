import UploadForm from './modules/uploadForm'
import AutoComplete from '../components/autocomplete.vue'
export default {
  components: {
    UploadForm,
    AutoComplete
  },
  data () {
    return {
      queryParam: {
        size: 20,
        page: 1,
        query: '',
        nodeKey: ''
      },
      nodeLabel: '',
      defaultExpandKeys: [],
      mainHeight: 0,
      tableData: [],
      total: 0,
      treeData: [],
      suggestions: [],
      viewVisible: false,
      pdfSrc: '',
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  mounted: function () {
    this.initTreeData()
    this.initTable()
    var h = document.querySelector('.searchCol').offsetHeight
    this.mainHeight = this.$store.state.contentHeight - h - 8
  },
  methods: {
    handleViewClick: function (scope) {
      this.viewVisible = true
      this.pdfSrc = this.GlobalVars.globalServiceServlet + '/data/file/viewFile?fileId=' + scope.fileId + '&resoureMenuKey=' + this.$store.state.resourceMenuKey
    },
    handleDownLoadClick: function (scope) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + scope.fileId, { responseType: 'blob' }).then((res) => {
        let fileName = scope.name
        let blob = new Blob([res.data])
        if (window.navigator.msSaveOrOpenBlob) {
          navigator.msSaveBlob(blob, fileName)
        } else {
          var link = document.createElement('a')
          link.href = window.URL.createObjectURL(blob)
          link.download = fileName
          link.click()
          window.URL.revokeObjectURL(link.href)
        }
      })
    },
    findTypeUrl (fileType) {
      if (fileType === 'txt') {
        return require('@/assets/images/fileType/txt.svg')
      } else if (fileType === 'doc' || fileType === 'docx') {
        return require('@/assets/images/fileType/word.svg')
      } else if (fileType === 'xls' || fileType === 'xlsx') {
        return require('@/assets/images/fileType/excel.svg')
      } else if (fileType === 'png' || fileType === 'jpg' || fileType === 'jpeg') {
        return require('@/assets/images/fileType/img.svg')
      } else if (fileType === 'pdf') {
        return require('@/assets/images/fileType/pdf.svg')
      } else if (fileType === 'pptx') {
        return require('@/assets/images/fileType/ppt.svg')
      } else {
        return require('@/assets/images/fileType/unknow.svg')
      }
    },
    handlePageChange: function (v) {
      this.queryParam.page = v
      this.initTable()
    },
    handleNodeClick: function (n) {
      this.nodeLabel = n.label
      this.queryParam.nodeKey = n.id
      this.initTable()
    },
    handleQuery () {
      this.initTable()
    },
    flushData () {
      this.initTable()
    },
    openAddForm () { // 打开子组件弹窗
      var nodeKey = this.queryParam.nodeKey
      if (nodeKey) {
        this.$refs.upForm.openForm(nodeKey, this.nodeLabel)
      } else {
        this.$message({
          message: '请选择文件类型',
          type: 'warning'
        })
      }
    },
    initTable () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/dataBase/getFileInfoList', this.queryParam).then(res => {
        this.tableData = res.data.dataList
        this.total = res.data.totalCount
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    initTreeData: function () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/dataBase/getFileCategoriesForTree').then(res => {
        this.treeData = res.data
        this.defaultExpandKeys = [this.treeData[0].id]
      })
    },
    handleSelect (item) {
      this.queryParam.query = item.key
    }
  }
}
