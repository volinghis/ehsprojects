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
      console.log('index====' + index)
      // if (index === 'home') {
      //   this.$router.push({ name: 'portalhome' })
      // }
      switch (index) {
        case 'home':
          this.$router.push({ name: 'portalhome' })
          break
        case '2':
          this.$router.push({ name: 'newsList', params: { dataCode: 'MENU_COMP_NEWS' } })
          break
        case '3':
          this.$router.push({ name: 'newsList', params: { dataCode: 'MENU_COMP_NOTICE' } })
          break
        case '4':
          this.$router.push({ name: 'newsList', params: { dataCode: 'MENU_PROFESSIONAL_NEWS' } })
          break
        case '5':
          this.$router.push({ name: 'newsList', params: { dataCode: 'MENU_TRADE_NEWS' } })
          break
        case '6':
          this.$router.push({ name: 'newsList', params: { dataCode: 'MENU_BIDS_NOTICE' } })
          break
        case '7':
          this.$router.push({ name: 'newsList', params: { dataCode: 'MENU_MEDIA_NEWS' } })
          break

        default:
          break
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
