// import word from '../../../assets/word.png'
// import excle from '../../../assets/excle.png'
// import pdf from '../../../assets/pdf.png'
// import txt from '../../../assets/txt.png'
// import PPT from '../../../assets/PPT.png'
import FileUpload from '@components/upload/index'
export default {
  components: {
    FileUpload
  },
  data () {
    return {
      queryParam: {},
      form: {},
      dialogFormVisible: false,
      defaultExpandKeys: [],
      formLabelWidth: '100px',
      mainHeight: 0,
      tableData: [],
      treeData: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  mounted: function () {
    this.initTreeData()
    var h = document.querySelector('.searchCol').offsetHeight
    this.mainHeight = this.$store.state.contentHeight - h - 8
  },
  methods: {
    handleViewClick: function (scope) {
      // 预览
    },
    handleDownLoadClick: function (scope) {
      alert('准备下载当前资料')
    },
    handlePageChange: function () {
    },
    handleNodeClick: function (data) {
    },
    initTreeData: function () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgManager/getAllForTree').then(res => {
        this.treeData = res.data
        this.defaultExpandKeys = [this.treeData[0].id]
      })
    }
  }
}
