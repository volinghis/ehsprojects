export default {
  data () {
    return {
      tableHeight: ' ',
      dialogVisible: false,
      totalCount: 0,
      businessKey: '',
      allocateForm: {
        applicant: '',
        allocateReason: '',
        applicationName: '',
        applicationTime: '',
        allocateDept: ''
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
      this.getAllocateByKey(this.businessKey)
      this.getRefDevice(this.businessKey)
    }
  },
  methods: {
    getAllocateByKey (key) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamAllocate/getAllocateByKey', { params: { key: key } }).then(res => {
        var resData = res.data
        this.allocateForm = resData
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    getRefDevice (key) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamAllocate/getEamLedgerByAllocateKey', { params: { key: key } }).then(res => {
        var resData = res.data
        var temp = {}
        Object.assign(temp, resData.allocate, resData.table)
        this.tableData.push(temp)
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handlerAfterFlow (v) { // 流程结束数据处理
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamAllocate/updateAfterAllocateFlow', v).then(res => {
        if (res.data.resultType === 'ok') {
          window.close()
        }
      }).catch(error => {
        this.$message.error(error)
      })
    }
  }
}
