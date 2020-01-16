import paramsTable from '../paramsTable/index.vue'
export default {
  components: {
    paramsTable
  },
  data () {
    return {
      flag: '',
      partsParamKey: '',
      paramsData: [],
      eamItem: {},
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
        founder: ''
      },
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      rules: {
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
        {
          name: 'food.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        },
        {
          name: 'food2.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }
      ]
    }
  },
  mounted () {
    const user = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.form.founder = user.account
    if (this.$route.params.data != null) {
      this.form = this.$route.params.data
    }
    this.paramsData = this.$route.params.pData
    this.flag = this.$route.params.flag
  },
  methods: {
    handleAvatarSuccess: function (res, file) {
      console.log(file)
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    submitForm: function (formName) {
      this.$refs.form.validate(valid => {
        if (valid) {
          const requestParam = {
            partsAccounts: this.form,
            partsParams: this.$refs.partParams.part_parameters.data
          }
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/partsAccount/savePartsAccount', requestParam).then(res => {
            this.$message({
              message: '保存备件成功',
              type: 'success'
            })
            this.$router.push({ name: 'partsAccount' })
          })
        } else {
          this.$message.error('保存失败')
          return false
        }
      })
    },
    resetForm: function (formName) {
      this.$refs['form'].resetFields()
      const user = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
      this.form.founder = user.account
    },
    handleRemove: function (file, fileList) {
      console.log(file, fileList)
    },
    handlePreview: function (file) {
      console.log(file)
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
  }
}
