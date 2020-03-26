export default {
  data () {
    return {
      tableHeight: ' ',
      dialogVisible: false,
      totalCount: 0,
      businessKey: '',
      scrapForm: {
        applicant: '',
        scrapReason: '',
        applicationName: '',
        applicationTime: '',
        scrapDept: ''
      },
      tableData: []
    }
  },
  mounted () {
    var processObj = JSON.parse(this.$route.params.processInfo)
    if (JSON.stringify(processObj) !== '{}') {
      if (JSON.stringify(processObj.data) !== undefined) {
        this.businessKey = processObj.data.key
      } else {
        this.businessKey = processObj.businessKey
      }
      this.getScrapByKey(this.businessKey)
      this.getRefDevice(this.businessKey)
    }
  },
  methods: {
    getScrapByKey (key) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamScrap/getScrapByKey', { params: { key: key } }).then(res => {
        var resData = res.data
        this.scrapForm = resData
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    getRefDevice (key) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamScrap/getEamLedgerByScrapKey', { params: { key: key } }).then(res => {
        var resData = res.data
        this.tableData.push(resData)
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handlerAfterFlow (v) { // 流程结束数据处理
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamScrap/updateAfterFlow', v).then(res => {
        if (res.data.resultType === 'ok') {
          // window.close()
        }
      }).catch(error => {
        this.$message.error(error)
      })
    }
  }
}
