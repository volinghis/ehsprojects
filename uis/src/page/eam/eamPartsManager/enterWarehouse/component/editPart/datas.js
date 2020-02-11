import partEdit from '../../../partsBaseInfo/component/edit/index.vue'
export default {
  components: {
    partEdit
  },
  props: {
    partsForm: Object
  },
  watch: {
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
      console.log(val)
      this.form.leaveFactoryDate = val
    },
    partsForm: {
      handler (val) {
        // this.form = val
        this.form = JSON.parse(JSON.stringify(val))
        // this.partsForm = val
        // this.$nextTick(() => {
        //   this.form = val
        // })
      }
    }
  },
  mounted: function () {
    JSON.parse(JSON.stringify(this.partsForm))
    this.form = this.partsForm
    const user = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    console.log(user.username)
    this.form.founder = user.username
    if (this.form) {
      this.flag = 'edit'
    } else {
      this.flag = 'add'
    }
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
      // console.log(file)
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    handleRemove: function (file, fileList) {
      // console.log(file, fileList)
    },
    handlePreview: function (file) {
      // console.log(file)
    },
    handleExceed: function (files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      )
    },
    beforeRemove: function (file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
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
    }
  },
  data () {
    return {
      flag: '',
      paramsData: [],
      priceNew: 0,
      amountNew: 0,
      totalPriceNew: 0,
      imageUrl: '',
      form: {
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
      fileList: [
        // {
        //   name: 'food.jpeg',
        //   url:
        //     'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        // },
        // {
        //   name: 'food2.jpeg',
        //   url:
        //     'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        // }
      ]
    }
  }
}
