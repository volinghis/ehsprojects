export default {
  data () {
    return {
      dhUrl: require('@/assets/logo.png')
    }
  },

  methods: {
    menuSelect (index, leaf, e) {
      if (index === 'home') {
        this.$router.push({ name: 'portalhome' })
      }
      // if (leaf) {
      //   var lis = document.querySelectorAll('li')
      //   if (lis) {
      //     for (var k = 0; k < lis.length; k++) {
      //       lis[k].className = ''
      //     }
      //   }
      //   var el = e.target
      //   var i = 0
      //   while (el.tagName.toUpperCase() !== 'NAV') {
      //     if (el.tagName.toUpperCase() === 'LI') {
      //       el.className = 'li-active'
      //     }
      //     el = el.parentElement
      //     i++
      //     if (i > 10) {
      //       break
      //     }
      //   }
      //   this.$router.push({ name: index })
      // }
    }
  }
}
