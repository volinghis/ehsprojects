import ParamsTable from '../../../components/paramsTable'
import PastInspectors from '../../../components/pastInspectors'
import ChildEamTable from '../../../components/childEamTable'
import UserSelector from '@components/org/user-selector/index'
export default {
  name: 'eamAccountPrintEdit',
  components: {
    ParamsTable,
    PastInspectors,
    ChildEamTable,
    UserSelector
  },
  data () {
    return {
      paramsTableDatas: [],
      inspectorsDatas: [],
      relatedKyes: '',
      deviceKey: '',
      form: {
        deviceName: '',
        deviceNum: '',
        purchaseTime: '',
        runDate: '',
        profession: '',
        installLocation: '',
        textarea: '',
        completePoint: 0,
        person: ''
      },
      rules: {
        deviceName: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        deviceModel: [
          { required: true, message: '请输入设备型号', trigger: 'blur' }
        ],
        profession: [
          { required: true, message: '请选择设备专业', trigger: 'blur' }
        ],
        person: [
          { required: true, message: '请选择责任人', trigger: 'blur' }
        ],
        runDate: [
          { required: true, message: '请选择投运日期', trigger: 'blur' }
        ],
        installLocation: [
          { required: true, message: '请输入设备位置', trigger: 'blur' }
        ]
      },
      fileList: [{
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }, {
        name: 'foo.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }]
    }
  },
  mounted: function () {
    const deviceData = this.$route.params.data
    if (this.$route.params.flag === 'edit') {
      this.form = deviceData
      this.deviceKey = deviceData.key
    }
  },
  methods: {
    handleAvatarSuccess: function (res, file) {
    },
    getParamsTable (data) { // 获取参数表中的数据
      this.paramsTableDatas.push(data)
    },
    getInspectors (data) { // 获取历任点检员表中的数据
      this.inspectorsDatas.push(data)
    },
    getRelatedKeys (data) {
      this.relatedKyes = data
    },
    submitForm: function (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          const reqBean = {
            eamLedger: this.form,
            deviceKeys: this.relatedKyes,
            paramsList: this.paramsTableDatas,
            inspectorsList: this.inspectorsDatas
          }
          console.log(reqBean)
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/saveEamLedger', reqBean).then(res => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.$refs[formName].resetFields() // 表单重置（暂不能重置全部项）
              this.$router.push({ name: 'eamLedger' }) // 保存成功后页面跳转
            }
          }).catch(error => {
            this.$message({ message: error })
          })
        } else {
          return false
        }
      })
    },
    handleRemove (file, fileList) {
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
