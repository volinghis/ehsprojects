export default {
  data () {
    return {
      sessionUser: {}
    }
  },
  mounted () {
    this.sessionUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
  },
  methods: {
    logout () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/login/doLogout').then(res => {
        if (res.data.resultType === 'ok') {
          sessionStorage.removeItem(this.GlobalVars.userToken)
          this.$router.push({ name: 'login' })
        }
      })
    },
    menuSelect (index, leaf, e) {
      if (leaf) {
        var lis = document.querySelectorAll('li')
        if (lis) {
          for (var k = 0; k < lis.length; k++) {
            lis[k].className = ''
          }
        }
        var el = e.target
        var i = 0
        while (el.tagName.toUpperCase() !== 'NAV') {
          if (el.tagName.toUpperCase() === 'LI') {
            el.className = 'li-active'
          }
          el = el.parentElement
          i++
          if (i > 10) {
            break
          }
        }
        this.$router.push({ name: index })
      }
    }
  }
}
