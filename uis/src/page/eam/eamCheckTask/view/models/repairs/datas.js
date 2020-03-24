export default {
  props: {
    'dataRow': Object
  },

  data () {
    return {
      repair: {
        result: '',
        note: '',
        question: '',
        objectKey: '',
        deviceAddress: '',
        userName: '',
        orgName: ''
      },
      objects: [],
      deviceAddresses: []
    }
  },
  computed: {

  },
  methods: {
    transResult (row) {
      if (row.result === 'OK') {
        return '已解决'
      } else {
        return '未解决'
      }
    },
    transExecutor (row) {
      return row.userName + '(' + row.orgName + ')'
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
      that.repair = Object.assign(that.repair, that.dataRow)
      if (that.repair.deviceAddress) {
        that.repair.deviceAddressName = that.deviceAddresses.find((item) => { // 这里的userList就是上面遍历的数据源
          return item.key === that.repair.deviceAddress// 筛选出匹配数据
        }).text
      }
      if (that.repair.objectKey) {
        that.repair.objectName = that.objects.find((item) => { // 这里的userList就是上面遍历的数据源
          return item.key === that.repair.objectKey// 筛选出匹配数据
        }).text
      }
    }))
  }
}
