import UploadForm from './modules/uploadForm'
import AutoComplete from '../components/autocomplete.vue'
import pdf from 'vue-pdf'
export default {
  components: {
    UploadForm,
    pdf,
    AutoComplete
  },
  data () {
    return {
      queryParam: {
        size: 10,
        page: 1,
        query: '',
        nodeKey: ''
      },
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
      var url = this.GlobalVars.globalServiceServlet + '/data/file/viewFile?fileId=' + scope.fileId + '&resoureMenuKey=' + this.$store.state.resourceMenuKey
      this.pdfSrc = url
      // this.pdfSrc = pdf.createLoadingTask(url)
      this.viewVisible = true
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
        return require('@/assets/images/fileType/txt.png')
      } else if (fileType === 'doc' || fileType === 'docx') {
        return require('@/assets/images/fileType/word.png')
      } else if (fileType === 'xls' || fileType === 'xlsx') {
        return require('@/assets/images/fileType/excle.png')
      } else if (fileType === 'png' || fileType === 'jpg' || fileType === 'jpeg') {
        return require('@/assets/images/fileType/img.png')
      }
    },
    handlePageChange: function (v) {
      this.queryParam.page = v
      this.initTable()
    },
    handleNodeClick: function (n) {
      this.queryParam.nodeKey = n.id
      console.log(this.queryParam)
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
        this.$refs.upForm.openForm(nodeKey)
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
