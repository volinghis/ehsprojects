import ParamsTable from '../paramsTable/index.vue'
import ModifyRecord from '../modifyRecord/index.vue'
import FileUpload from '@components/upload/index'
export default {
  components: {
    paramsTable: ParamsTable,
    modifyRecord: ModifyRecord,
    FileUpload
  },
  data () {
    return {
      eamItem: {},
      imageUrl: '',
      flag: '',
      partFlag: false,
      paramsData: [],
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      fileList: []
    }
  },
  mounted: function () {
    this.eamItem = this.$route.params.data
    this.flag = this.$route.params.flag
    if (this.flag) {
      this.partFlag = true
    }
    this.getDevicePicture(this.eamItem.partsImg)
  },
  methods: {
    handlePrint: function () {
    },
    handleBack: function () {
      this.$router.go(-1)
    },
    getDevicePicture: function (partsImg) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + partsImg, { responseType: 'blob' }).then(res => {
        var resData = res.data
        this.imageUrl = URL.createObjectURL(resData)
      }).catch(error => {
        this.$message({ message: error })
      })
    }
  }
}
