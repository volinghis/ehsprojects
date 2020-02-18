import FileUpload from '@components/upload/index'
export default {
  components: {
    FileUpload
  },
  props: {
    partsForm: Object,
    flag: String
  },
  watch: {
    flag: function (val) {
      console.log('flagMark')
      console.log(val)
    },
    amountNew: function (val) {
      if (this.priceNew !== undefined) {
        this.totalPriceNew = this.priceNew * val
      }
    },
    priceNew: function (val) {
      if (this.amountNew !== undefined) {
        this.totalPriceNew = this.priceNew * val
      }
    },
    totalPriceNew: function (val) {
      this.form.totalPrice = val
    },
    factoryDateBlur: function (val) {
      this.form.leaveFactoryDate = val
    },
    partsForm: {
      handler (val) {
        this.form = val
        this.partFlag = false
        if (this.form.partsImg) {
          this.getDevicePicture(this.form.partsImg)
          this.key += 1
        }
      }
    }
  },
  mounted: function () {
    JSON.parse(JSON.stringify(this.partsForm))
    this.form = this.partsForm
    const user = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.form.founder = user.username
  },
  methods: {
    amountBlur: function (e) {
      this.amountNew = e.target.value
    },
    priceBlur: function (e) {
      this.priceNew = e.target.value
    },
    factoryDateBlur: function (e) {
      this.partsForm.leaveFactoryDate = e.displayValue
    },
    getDevicePicture: function (partsImg) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + partsImg, { responseType: 'blob' }).then(res => {
        var resData = res.data
        this.imageUrl = URL.createObjectURL(resData)
      }).catch(error => {
        this.$message({ message: error })
      })
    }
  },
  data () {
    return {
      key: 0,
      partFlag: true,
      paramsData: [],
      priceNew: 0,
      amountNew: 0,
      totalPriceNew: 0,
      imageUrl: '',
      form: {
        partsImg: '',
        maintenancesStandard: '',
        synopsis: '',
        operationManual: '',
        deviceName: '',
        deviceCode: '',
        norm: '',
        materialCode: '',
        materialType: '',
        manufacturer: '',
        leaveFactoryCode: '',
        leaveFactoryDate: '',
        warningValue: '',
        founder: '',
        supplier: '',
        price: 0,
        amount: 0,
        unit: '',
        totalPrice: 0
      },
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      rules: {
        price: [
          { required: true, message: '请输入价格', trigger: 'blur' },
          { type: 'number', message: '请输入正确数字', trigger: 'blur', transform: value => Number(value) }
        ],
        unit: [
          {
            required: true,
            message: '请输入单位（例如：‘个’）',
            trigger: 'blur'
          },
          { min: 1, max: 3, message: '长度在 1 到 3 个字符', trigger: 'blur' }
        ],
        amount: [
          { required: true, message: '请输入数量', trigger: 'blur' },
          {
            type: 'number',
            message: '请输入正确数字',
            trigger: 'blur',
            transform: value => Number(value)
          }
        ],
        deviceName: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        deviceCode: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        norm: [
          { required: true, message: '请输入设备型号', trigger: 'blur' }
        ],
        materialCode: [
          { required: true, message: '请输入物资编码', trigger: 'blur' }
        ],
        materialType: [
          { required: true, message: '请输入物资类别', trigger: 'blur' }
        ],
        warningValue: [
          { required: true, message: '请输入预警值', trigger: 'blur' }
        ]
      },
      fileList: []
    }
  }
}
