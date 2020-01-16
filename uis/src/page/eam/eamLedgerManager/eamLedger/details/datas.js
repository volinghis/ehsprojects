import ParamsTable from '../../../components/paramsTable'
import PastInspectors from '../../../components/pastInspectors'
import ChildEamTable from '../../../components/childEamTable'
export default {
  name: 'eamAccountPrintDetail',
  components: {
    ParamsTable,
    PastInspectors,
    ChildEamTable
  },
  data () {
    return {
      deviceKey: '',
      form: {
        deviceName: '',
        deviceNum: '',
        purchaseTime: '',
        runDate: '',
        factoryName: '',
        person: '',
        textarea: '',
        completePoint: 0,
        deviceStatus: '正常'
      },
      rules: {
        deviceName: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        deviceModel: [
          { required: true, message: '请输入设备型号', trigger: 'change' }
        ],
        person: [
          { required: true, message: '请输入创建人', trigger: 'change' }
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
      ],
      eamInfos: {}
    }
  },
  mounted: function () {
    this.eamInfos = this.$route.params.data
    this.deviceKey = this.$route.params.data.key
  },
  methods: {
    handleAvatarSuccess: function (res, file) {

    },
    handlePrint: function () {
    },
    back () {
      // this.$router.push({ name: 'eamLedger' })
      this.$router.go(-1)
    },
    handleRemove (file, fileList) {
    },
    handlePreview (file) {
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
