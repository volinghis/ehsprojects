export default {
  data () {
    return {
      repair: {
        result: '',
        note: '',
        question: '',
        objectKey: '',
        objectType: '',
        description: '',
        userType: '',
        userName: '',
        orgName: ''
      },
      deviceAddresses: [],
      activeNames: ['rep', 'def', 'rev'],
      rules: {
        name: [
          { required: true, message: '请输入任务名称', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {

  },
  methods: {
    outerInfoShow () {
      return this.repair.userType === 'OUTER'
    }
  },

  mounted () {
    var that = this
    this.$axios.all([
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceAddress'),
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceSystem')
    ]).then(this.$axios.spread(function (time, deviceAddress, deviceSystem) {
      // 上面两个请求都完成后，才执行这个回调方法
      that.deviceAddresses = deviceAddress.data
    }))
  }
}
