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
      eamInfos: {},
      backFlag: ''
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
      this.deviceKey = processObj.businessKey
      this.backFlag = 'flow'
      this.getEamLedgerByEntityKey(processObj.businessKey)
    }
  },
  methods: {
    goBack () {
      if (this.backFlag === 'flow') {
        window.close()
      } else {
        this.$router.go(-1)
      }
    },
    getEamLedgerByEntityKey (val) {
      if (this.backFlag === 'flow') { // 通过流程进行查看
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getEamLedgerByKey?key=' + val).then(res => {
          this.callBack(res.data)
        }).catch(error => {
          this.$message({ message: error })
        })
      } else { // 直接进行查看
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getEamLedgerByEntityKey?key=' + val).then(res => {
          this.callBack(res.data)
        }).catch(error => {
          this.$message({ message: error })
        })
      }
    },
    callBack (data) {
      this.getDevicePicture(data.deviceImg)
      this.eamInfos = data
      this.deviceKey = data.refKey
    },
    getDevicePicture (fileId) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + fileId, { responseType: 'blob' }).then(res => {
        this.imgUrl = URL.createObjectURL(res.data)
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handlerAfterFlow (v) { // 流程结束数据处理
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/updateEamLedgerAfterFlow', v).then(res => {
        if (res.data.resultType === 'ok') {
          window.close()
        }
      }).catch(error => {
        this.$message.error(error)
      })
    }
  }
}
