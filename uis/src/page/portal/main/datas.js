export default {
  data () {
    return {
      dhUrl: require('@/assets/logo.png'),
      picList: [],
      queryBean: {
        page: 1,
        size: 5,
        query: ''
      }
    }
  },
  mounted () {
    this.getPicList()
  },
  methods: {
    findUrl (item) {
      return this.GlobalVars.globalServiceServlet + '/portal/data/file/downloadFile?resoureMenuKey=ALL&fileId=' + item.fileId
    },
    getPicList () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/portal/web/pictures/getPicturesList', this.queryBean)
        .then(res => {
          this.picList = res.data.dataList
          console.log(this.picList)
          // resData.forEach(async e => {
          //   let res = await this.$axios.get(this.GlobalVars.globalServiceServlet + '/portal/data/file/downloadFile?fileId=' + e.fileId, { responseType: 'blob' })
          //   console.log(URL.createObjectURL(res.data))
          //   this.picList.push(URL.createObjectURL(res.data))
          // })
        })
    },
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
