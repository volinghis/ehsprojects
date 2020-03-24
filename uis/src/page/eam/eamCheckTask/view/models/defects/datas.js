export default {
  props: {
    'dataRow': Object
  },

  data () {
    return {
      repair: {
        status: '',
        fiels: '',
        question: '',
        objectKey: '',
        deviceAddress: '',
        devices: '',
        level: ''
      },
      objects: [],
      deviceAddresses: []
    }
  },
  computed: {

  },
  methods: {
    transDefectLevel (v) {
      if (v.level === 'NORMAL') {
        return '一般缺陷'
      }
      return '重大缺陷'
    },
    transDefectStatus (v) {
      if (v.status === 'ERROR') {
        return '未解决'
      }
      return '已解决'
    },

    addressNameGet (s) { // 这个vId也就是value值
      let obj = {}
      obj = this.deviceAddresses.find((item) => { // 这里的userList就是上面遍历的数据源
        return item.key === s.deviceAddress// 筛选出匹配数据
      })
      return obj.text
    },
    objectNameGet (s) { // 这个vId也就是value值
      let obj = {}
      obj = this.objects.find((item) => { // 这里的userList就是上面遍历的数据源
        return item.key === s.objectKey// 筛选出匹配数据
      })
      return obj.text
    }
  },

  mounted () {
    var that = this
    this.$axios.all([
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceAddress'),
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceProfessiona'),
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceSystem')
    ]).then(this.$axios.spread(function (deviceAddress, pro, sys) {
      that.objects = pro.data
      that.objects = that.objects.concat(sys.data)
      // 上面两个请求都完成后，才执行这个回调方法
      that.deviceAddresses = deviceAddress.data
      that.repair = Object.assign(that.repair, that.dataRow)
    }))
  }
}
