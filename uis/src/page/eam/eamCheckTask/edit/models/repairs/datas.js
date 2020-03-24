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
        objectType: '',
        deleted: false,
        deviceAddress: '',
        key: '',
        userType: '',
        userName: '',
        orgName: ''
      },
      objects: [],
      deviceAddresses: [],
      pros: [],
      syss: [],
      activeNames: ['rep', 'def', 'rev'],
      repaiRules: {
        deviceAddress: [
          { required: true, message: '请选择设备位置', trigger: 'change' }
        ],
        userName: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        orgName: [
          { required: true, message: '请输入公司', trigger: 'blur' }
        ],

        userType: [
          { required: true, message: '请选择检修执行人', trigger: 'change' }
        ],
        objectKey: [
          { required: true, message: '请选择检修对象', trigger: 'change' }
        ],

        result: [
          { required: true, message: '请选择检修结论', trigger: 'change' }
        ]
      }
    }
  },
  computed: {

  },
  methods: {
    outerInfoShow () {
      return this.repair.userType === 'OUTER'
    },
    randomString (len) {
      len = len || 64
      var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678' /** **默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
      var maxPos = $chars.length
      var pwd = ''
      for (var i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos))
      }
      return pwd
    },
    cancelForm () {
      if (this.dataRow) {
        this.$parent.$parent.$parent.$parent.$parent.$refs['repairTable'].toggleRowExpansion(this.dataRow)
      }
      this.$parent.$parent.$parent.repairsAdd = false
    },

    submitForm () {
      this.$refs['repair'].validate((valid) => {
        if (valid) {
          if (this.repair.userType === 'OWNER') {
            this.repair.userName = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken)).username
            this.repair.orgName = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken)).orgName
          }
          if (!this.dataRow) {
            this.repair.key = this.randomString()
            this.$parent.$parent.$parent.repairInner(this.repair)
            this.$parent.$parent.$parent.repairsAdd = false
          } else {
            this.$parent.$parent.$parent.$parent.$parent.repairUpdate(this.repair)
            this.$parent.$parent.$parent.$parent.$parent.$refs['repairTable'].toggleRowExpansion(this.dataRow)
          }
        }
      })
    },
    objectTypeChange (v) {
      this.repair.objectKey = ''
      this.repair.objectName = ''
      if (v === 'BY_PROFESSIONA') {
        this.objects = this.pros
      } else {
        this.objects = this.syss
      }
    }
  },

  mounted () {
    var that = this
    this.$axios.all([
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceAddress'),
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceProfessiona'),
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceSystem')
    ]).then(this.$axios.spread(function (deviceAddress, pro, sys) {
      that.pros = pro.data
      that.syss = sys.data
      // 上面两个请求都完成后，才执行这个回调方法
      that.deviceAddresses = deviceAddress.data
      if (that.dataRow) {
        that.objectTypeChange(that.dataRow.objectType)
        that.repair = Object.assign(that.repair, that.dataRow)
      }
    }))
  }
}
