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
      if (val === 'view') {
        this.buttonFlag = false
        this.partFlag = true
      }
    },
    amountNew: function (val) {
      if (this.priceNew !== undefined) {
        this.totalPriceNew = this.form.price * val
      }
    },
    priceNew: function (val) {
      if (this.amountNew !== undefined) {
        this.totalPriceNew = this.form.amount * val
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
        this.form.totalPrice = this.form.amount * this.form.price
        if (this.form.partsImg) {
          this.getDevicePicture(this.form.partsImg)
          this.key += 1
          if (this.key) {
            this.buttonFlag = false
            // this.partFlag = true
          }
        }
      }
    }
  },
  mounted: function () {
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
    handleAvatarSuccess: function (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
      this.form.partsImg = res.entityKey
    },
    uploadMs: function (v) {
      this.form.maintenancesStandard = v
    },
    uploadSynopsis: function (v) {
      this.form.synopsis = v
    },
    uploadOm: function (v) {
      this.form.operationManual = v
    },
    beforeAvatarUpload: function (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
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
      partFlag: false,
      buttonFlag: true,
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
        warningValue: 0,
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