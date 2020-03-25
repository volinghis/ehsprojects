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
    if (resData !== undefined) {
      this.getDevicePicture(resData.deviceImg)
      this.eamInfos = resData
      this.deviceKey = resData.refKey
    } else {
      var processObj = JSON.parse(this.$route.params.processInfo)
      console.log(processObj)
      this.getEamLedgerByEntityKey(processObj.businessKey)
    }
  },
  methods: {
    handlePrint: function () {
    },
    back () {
      this.$router.go(-1)
    },
    getEamLedgerByEntityKey (val) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getEamLedgerByEntityKey?key=' + val).then(res => {
        var data = res.data
        this.getDevicePicture(data.deviceImg)
        this.eamInfos = data
        this.deviceKey = data.refKey
      }).catch(error => {
        this.$message({ message: error })
      })
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
