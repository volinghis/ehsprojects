import ParamsTable from '../../../components/paramsTable'
import PastInspectors from '../../../components/pastInspectors'
import ChildEamTable from '../../../components/childEamTable'
import FilesTable from '../../../components/filesTable'
export default {
  name: 'eamAccountPrintDetail',
  components: {
    ParamsTable,
    PastInspectors,
    ChildEamTable,
    FilesTable
  },
  data () {
    return {
      deviceKey: '',
      imgUrl: '',
      fileList: [],
      eamInfos: {}
    }
  },
  created: function () {
    var resData = this.$route.params.data
    this.getDevicePicture(resData.deviceImg)
    this.eamInfos = resData
    console.log(this.eamInfos)
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
