export default {
  data () {
    return {
      dhUrl: require('@/assets/dongheng.svg'),
      avatarUrl: '',
      sessionUser: {}
    }
  },
  mounted () {
    var currUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.sessionUser = currUser
    if (currUser.avatar !== null) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + currUser.avatar, { responseType: 'blob' }).then((res) => {
        this.avatarUrl = URL.createObjectURL(res.data)
      })
    } else {
      this.avatarUrl = require('@/assets/logo.svg')
    }
  },
  methods: {
    logout () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/login/doLogout').then(res => {
        if (res.data.resultType === 'ok') {
          sessionStorage.removeItem(this.GlobalVars.userToken)
          location.reload()
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
