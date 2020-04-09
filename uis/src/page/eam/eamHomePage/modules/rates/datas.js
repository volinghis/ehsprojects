export default {
  methods: {
    transNum (v) {
      return v > 5 ? 5 : v
    },
    flushData () {
      var self = this
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/scores/userscore/getUserScores').then(response => {
        self.rates = response.data
      })
    }
  },
  mounted () {
    this.flushData()
  },
  data () {
    return {
      rates: []
    }
  }
}
