import ParamsTable from '../../../components/paramsTable'
import PastInspectors from '../../../components/pastInspectors'
import ChildEamTable from '../../../components/childEamTable'
import FilesTable from '../../../components/filesTable'
import UserSelector from '@components/org/user-selector/index'
import FileUpload from '@components/upload/index'
export default {
  name: 'eamAccountPrintEdit',
  components: {
    ParamsTable,
    PastInspectors,
    ChildEamTable,
    UserSelector,
    FileUpload,
    FilesTable
  },
  data () {
    return {
      paramsTableDatas: [],
      inspectorsDatas: [],
      relatedKyes: '',
      deviceKey: '',
      fileIds: '',
      imgUrl: '',
      options: '',
      form: {
        deviceName: '',
        deviceNum: '',
        runDate: '',
        factoryName: '',
        switchVal: true,
        installLocation: '',
        installLocationName: '',
        completePoint: 0,
        person: '',
        personName: '',
        fileId: '',
        remarks: '',
        deviceImg: ''
      },
      rules: {
        deviceName: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        deviceModel: [
          { required: true, message: '请输入设备型号', trigger: 'blur' }
        ],
        person: [
          { required: true, message: '请选择责任人', trigger: 'blur' }
        ],
        runDate: [
          { required: true, message: '请选择投运日期', trigger: 'blur' }
        ],
        installLocation: [
          { required: true, message: '请选择设备位置', trigger: 'blur' }
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
      this.form = resData
    }
    this.handleSwitchChange(this.form.switchVal)
  },
  methods: {
    handleAvatarSuccess  (res, file) {
      this.imgUrl = URL.createObjectURL(file.raw)
      this.form.deviceImg = res.entityKey
    },
    beforeAvatarUpload (file) {
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
    getParamsTable (data) { // 获取参数表中的数据
      this.paramsTableDatas.push(data)
    },
    getInspectors (data) { // 获取历任点检员表中的数据
      this.inspectorsDatas.push(data)
    },
    getRelatedKeys (data) {
      this.relatedKyes = data
    },
    allFileId (v) {
      this.fileIds = v
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
    handleSwitchChange (v) {
      if (v) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceProfessiona').then(res => {
          this.options = res.data
        })
      } else {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceSystem').then(res => {
          this.options = res.data
        })
      }
    },
    handerSubmit (process) {
      this.$refs.form.validate(valid => {
        if (valid) {
          const reqBean = {
            eamLedgerLast: this.form,
            eamLedger: this.form,
            fileIds: this.fileIds,
            deviceKeys: this.relatedKyes,
            paramsList: this.paramsTableDatas,
            inspectorsList: this.inspectorsDatas,
            flowProcessInfo: process
          }
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
