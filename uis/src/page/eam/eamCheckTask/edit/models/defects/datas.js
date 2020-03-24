export default {
  props: {
    'dataRow': Object
  },

  data () {
    return {
      defect: {
        status: '',
        files: '',
        question: '',
        objectKey: '',
        objectType: '',
        deleted: false,
        deviceAddress: '',
        key: '',
        level: '',
        devices: ''
      },
      objects: [],
      deviceAddresses: [],
      pros: [],
      syss: [],
      defectRules: {
        deviceAddress: [
          { required: true, message: '请选择设备位置', trigger: 'change' }
        ],
        question: [
          { required: true, message: '请输入缺陷描述', trigger: 'blur' }
        ],
        level: [
          { required: true, message: '请选择缺陷等级', trigger: 'change' }
        ],
        objectKey: [
          { required: true, message: '请选择检修对象', trigger: 'change' }
        ],

        status: [
          { required: true, message: '请选择缺陷状态', trigger: 'change' }
        ]
      }
    }
  },
  computed: {

  },
  methods: {

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
        this.$parent.$parent.$parent.$parent.$parent.$refs['defectTable'].toggleRowExpansion(this.dataRow)
      }
      this.$parent.$parent.$parent.defectsAdd = false
    },

    submitForm () {
      this.$refs['defect'].validate((valid) => {
        if (valid) {
          if (this.defect.userType === 'OWNER') {
            this.defect.userName = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken)).username
            this.defect.orgName = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken)).orgName
          }
          if (!this.dataRow) {
            this.defect.key = this.randomString()
            this.$parent.$parent.$parent.defectInner(this.defect)
            this.$parent.$parent.$parent.defectsAdd = false
          } else {
            this.$parent.$parent.$parent.$parent.$parent.defectUpdate(this.defect)
            this.$parent.$parent.$parent.$parent.$parent.$refs['defectTable'].toggleRowExpansion(this.dataRow)
          }
        }
      })
    },
    objectTypeChange (v) {
      this.defect.objectKey = ''
      this.defect.objectName = ''
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
        that.defect = Object.assign(that.defect, that.dataRow)
      }
    }))
  }
}
