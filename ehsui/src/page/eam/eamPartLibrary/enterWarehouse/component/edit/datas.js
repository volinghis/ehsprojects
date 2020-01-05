import tablePart from '../../../../components/tablePart/index.vue'
import addPart from '../../../../components/addPart/index.vue'
export default {
  components: {
    tablePart,
    addPart
  },
  data () {
    return {
      tableHeight: ' ',
      dialogVisible: false,
      form: {
        warehouseName: '',
        warehouseCode: '',
        supplier: '',
        inboundType: '',
        principal: '',
        factoryCode: '',
        runDate: '',
        leaveFactoryDate: '',
        person: '',
        date: '',
        remark: ''
      },
      rules: {
        warehouseName: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        inboundType: [
          { required: true, message: '请选择入库类型', trigger: 'blur' }
        ],
        supplier: [
          { required: true, message: '请输入供应商', trigger: 'blur' }
        ],
        principal: [
          { required: true, message: '请输入入库人', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleAvatarSuccess: function (res, file) {
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    handleClose: function (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    submitForm: function (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$message({
            message: '恭喜你，这是一条成功消息',
            type: 'success'
          })
        } else {
          this.$message.error('错了哦，这是一条错误消息')
          return false
        }
      })
    },
    search: function (formName) {
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
    },
    resetForm: function (formName) {
      this.$refs['form'].resetFields()
    },
    handleRemove: function (file, fileList) {
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
    },
    handlePreview: function (file) {
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
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
    selectPerson: function () {
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
    }
  }
}
