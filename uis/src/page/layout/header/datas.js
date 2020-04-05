export default {
  data () {
    return {
      dhUrl: require('@/assets/dongheng.svg'),
      avatarUrl: '',
      sessionUser: JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    }
  },

  methods: {
    userImg () {
      var currUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
      if (currUser.avatar !== null) {
        return this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?resoureMenuKey=ALL&fileId=' + currUser.avatar
      } else {
        return '@/assets/logo.svg'
      }
    },
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
