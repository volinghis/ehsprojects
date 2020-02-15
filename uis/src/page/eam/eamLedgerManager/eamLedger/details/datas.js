import ParamsTable from '../../../components/paramsTable'
import PastInspectors from '../../../components/pastInspectors'
import ChildEamTable from '../../../components/childEamTable'
import FileUpload from '@components/upload/index'
export default {
  name: 'eamAccountPrintDetail',
  components: {
    ParamsTable,
    PastInspectors,
    ChildEamTable,
    FileUpload
  },
  data () {
    return {
      deviceKey: '',
      imgUrl: '',
      fileList: [],
      eamInfos: {}
    }
  },
  mounted: function () {
    var resData = this.$route.params.data
    this.getDevicePicture(resData.deviceImg)
    this.eamInfos = resData
    this.deviceKey = resData.refKey
  },
  methods: {
    handlePrint: function () {
    },
    back () {
      this.$router.go(-1)
    },
    getDevicePicture (fileId) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + fileId, { responseType: 'blob' }).then(res => {
        this.imgUrl = URL.createObjectURL(res.data)
      }).catch(error => {
        this.$message({ message: error })
      })
    }
  }
}
