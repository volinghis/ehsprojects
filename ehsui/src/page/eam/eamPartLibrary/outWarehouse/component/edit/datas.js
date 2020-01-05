// import paramsTable from '../paramsTable/index.vue'
import addPart from '../../../../components/addPart/index.vue'
import tablePart from '../../../../components/tablePart/index.vue'
export default {
  components: {
    // 'paramsTable': paramsTable,
    'tablePart': tablePart,
    'addPart': addPart
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
        remark: ''
      },
      partForm: {
        name: '',
        code: '',
        type: ''
      },
      rules: {
        warehouseName: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        inboundType: [
          { required: true, message: '请选择入库类型', trigger: 'blur' }
        ],
        supplier: [
          { required: true, message: '请输入设备型号', trigger: 'blur' }
        ],
        principal: [
          { required: true, message: '请输入经办人', trigger: 'change' }
        ]
      },
      tableData: [{
        code: 'FM-0001',
        name: '阀门',
        completion: 80,
        type: '阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }]
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
    handleRemoveL: function (file, fileList) {
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
    }
  }
}
