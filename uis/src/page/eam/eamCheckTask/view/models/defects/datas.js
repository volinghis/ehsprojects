export default {
  props: {
    'dataRow': Object
  },

  data () {
    return {
      defect: {
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
      return s.deviceAddressName
    },
    objectNameGet (s) { // 这个vId也就是value值
      return s.objectName
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
      that.defect = Object.assign(that.defect, that.dataRow)
      if (that.defect.deviceAddress) {
        that.defect.deviceAddressName = that.deviceAddresses.find((item) => { // 这里的userList就是上面遍历的数据源
          return item.key === that.defect.deviceAddress// 筛选出匹配数据
        }).text
      }
      if (that.defect.objectKey) {
        that.defect.objectName = that.objects.find((item) => { // 这里的userList就是上面遍历的数据源
          return item.key === that.defect.objectKey// 筛选出匹配数据
        }).text
      }
    }))
  }
}
