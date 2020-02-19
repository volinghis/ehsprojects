import ParamsTable from '../../../components/paramsTable'
import PastInspectors from '../../../components/pastInspectors'
import ChildEamTable from '../../../components/childEamTable'
import UserSelector from '@components/org/user-selector/index'
import FileUpload from '@components/upload/index'
export default {
  name: 'eamAccountPrintEdit',
  components: {
    ParamsTable,
    PastInspectors,
    ChildEamTable,
    UserSelector,
    FileUpload
  },
  data () {
    return {
      paramsTableDatas: [],
      inspectorsDatas: [],
      relatedKyes: '',
      deviceKey: '',
      imgUrl: '',
      form: {
        deviceName: '',
        deviceNum: '',
        purchaseTime: '',
        runDate: '',
        profession: '',
        installLocation: '',
        textarea: '',
        completePoint: 0,
        person: '',
        personName: '',
        fileId: '',
        remarks: '',
        deviceImg: '',
        operationManual: '',
        maintenancesStandard: '',
        synopsis: ''
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
      }
    }
  },
  created: function () {
    var processObj = JSON.parse(this.$route.params.processInfo)
    var resData = processObj.data
    if (resData !== undefined) {
      this.deviceKey = resData.key
      this.getDevicePicture(resData.deviceImg)
      console.log(this.form)
      this.form = resData
    }
  },
  methods: {
    handleAvatarSuccess  (res, file) {
      this.imgUrl = URL.createObjectURL(file.raw)
      this.form.deviceImg = res.entityKey
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
    getDevicePicture (deviceImg) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + deviceImg, { responseType: 'blob' }).then(res => {
        var resData = res.data
        this.imgUrl = URL.createObjectURL(resData)
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    userSelectChange () {
      var node = this.$refs.userSelect.getCheckedNodes()
      this.form.personName = node[0].label // 用户名称
    },
    standardChange (v) {
      this.form.maintenancesStandard = v
    },
    operationManualChange (v) {
      this.form.operationManual = v
    },
    synopsisChange (v) {
      this.form.synopsis = v
    },
    handerSubmit (process) {
      this.$refs.form.validate(valid => {
        if (valid) {
          const reqBean = {
            eamLedgerLast: this.form,
            eamLedger: this.form,
            deviceKeys: this.relatedKyes,
            paramsList: this.paramsTableDatas,
            inspectorsList: this.inspectorsDatas,
            flowProcessInfo: process
          }
          console.log(reqBean)
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/saveEamLedger', reqBean).then(res => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message,
                type: 'success'
              })
              // 流程开始后回调
              window.close()
              this.$router.push({ name: 'eamMaintain' }) // 保存成功后页面跳转
            }
          }).catch(error => {
            this.$message({ message: error })
          })
        } else {
          return false
        }
      })
    }
  }
}
