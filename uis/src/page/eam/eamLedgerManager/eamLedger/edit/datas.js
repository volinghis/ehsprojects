import ParamsTable from '../../../components/paramsTable'
import PastInspectors from '../../../components/pastInspectors'
import EamList from '../../../components/eamList'
export default {
  name: 'eamAccountPrintEdit',
  components: {
    ParamsTable,
    PastInspectors,
    EamList
  },
  data () {
    return {
      dialogTableVisible: false,
      paramsTableDatas: [],
      deviceKey: '',
      form: {
        deviceName: '',
        deviceNum: '',
        purchaseTime: '',
        runDate: '',
        factoryName: '',
        person: '',
        textarea: '',
        deviceStatus: '正常',
        completePoint: 0
      },
      rules: {
        deviceName: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        deviceModel: [
          { required: true, message: '请输入设备型号', trigger: 'change' }
        ]
      },
      tableData: [
        {
          deviceNum: '2019100001',
          deviceName: '锅炉',
          installLocation: '锅炉专业',
          deviceModel: '6314-2RS',
          purchaseTime: '2016-05-02',
          brand: '西安东恒'
        },
        {
          deviceNum: '2019100001',
          deviceName: '锅炉',
          installLocation: '锅炉专业',
          deviceModel: '6314-2RS',
          purchaseTime: '2016-05-02',
          brand: '西安东恒'
        },
        {
          deviceNum: '2019100001',
          deviceName: '锅炉',
          installLocation: '锅炉专业',
          deviceModel: '6314-2RS',
          purchaseTime: '2016-05-02',
          brand: '西安东恒'
        },
        {
          deviceNum: '2019100001',
          deviceName: '锅炉',
          installLocation: '锅炉专业',
          deviceModel: '6314-2RS',
          purchaseTime: '2016-05-02',
          brand: '西安东恒'
        }
      ],
      fileList: [{
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }, {
        name: 'food2.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }]
    }
  },
  mounted: function () {
    const deviceData = this.$route.params.data
    if (this.$route.params.flag === 'edit') {
      this.form = deviceData
      this.deviceKey = deviceData.key
    } else {
      this.getCurrentUser() // 只在新增的时候获取当前用户名称
    }
  },
  methods: {
    getCurrentUser () {
      const user = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgUser/findOrgUserByAccount', { params: { account: user.account } }).then(res => {
        this.form.person = res.data.name
      }).catch(error => {
        console.log(error)
      })
    },
    handleAvatarSuccess: function (res, file) {
    },
    getParamsTable (data) {
      this.paramsTableDatas.push(data)
    },
    deleteParamsTable (index) {
      this.paramsTableDatas.splice(index, 1)
    },
    submitForm: function (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          const reqBean = {
            eamLedger: this.form,
            paramsList: this.paramsTableDatas
          }
          console.log(reqBean)
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/saveEamLedger', reqBean).then(res => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              this.$refs[formName].resetFields() // 表单重置（暂不能重置全部项）
            }
          }).catch(error => {
            console.log(error)
          })
        } else {
          return false
        }
      })
    },
    handleRemove (file, fileList) {
    },
    // 删除
    handleDeleteClick () {

    },
    // 编辑
    handleEditClick () {

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
    handlechange (res) {
      console.log(res)
    }
  }
}
