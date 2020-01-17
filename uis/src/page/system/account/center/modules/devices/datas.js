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
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getListNotPage').then(res => {
        console.log(res.data)
        this.datas = res.data
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    deviceClick (val) {
      this.$router.push({ name: 'eamLedgerEdit', params: { flag: 'edit', data: val } })
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
