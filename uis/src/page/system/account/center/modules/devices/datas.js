export default {
  methods: {
    findColor (v) {
      if (v <= 30) {
        return 'exception'
      } else if (v > 30 && v <= 70) {
        return 'warning'
      } else {
        return 'success'
      }
    },
    inintTable () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getListNotPage').then(res => {
        this.datas = res.data
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    deviceClick (val) {
      this.GlobalMethods.openFlowWin('eamLedgerEdit', { processDefineKey: 'EamLedgerUpdateFlow', flag: 'edit', data: val }, function () {
        this.inintTable()
      })
    }

  },
  mounted () {
    this.inintTable()
  },
  data () {
    return {
      datas: []
    }
  }
}
