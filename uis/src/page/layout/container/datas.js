
export default {
  methods: {
    flushNotices () {
      var self = this
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/notify/message/findByUserOfNotRead?TIMER=Y').then(response => {
        if (response.data && response.data.length > 0) {
          self.$notify({
            title: response.data[0].title,
            message: response.data[0].content,
            position: 'bottom-right'
          })
        }
      })
    },
    resetPaddingStyle () {
      let boWidth = 0
      if (document.body.offsetWidth > parseInt(this.GlobalCss.serviceMaxWidth)) {
        boWidth = (document.body.offsetWidth - parseInt(this.GlobalCss.serviceMaxWidth)) / 2
        boWidth = boWidth < 10 ? 10 : boWidth
      } else {
        boWidth = 10
      }
      let header = document.querySelectorAll('.el-header')
      if (header) {
        for (let i = 0; i < header.length; i++) {
          header[i].style.paddingLeft = boWidth + 'px'
          header[i].style.paddingRight = boWidth + 'px'
        }
      }
      document.querySelector('.el-main').style.marginLeft = boWidth + 'px'
      document.querySelector('.el-main').style.marginRight = boWidth + 'px'
    }
  },
  mounted () {
    setInterval(this.flushNotices, 5000)
    this.resetPaddingStyle()
    this.$store.dispatch(this.GlobalVars.computeWindowSizeMethodName)
    window.onresize = () => {
      this.$store.dispatch(this.GlobalVars.computeWindowSizeMethodName)
      this.resetPaddingStyle()
    }
  }
}
