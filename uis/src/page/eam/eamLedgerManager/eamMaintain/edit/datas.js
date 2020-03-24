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
      deviceKey: '',
      imgUrl: '',
      deviceAddress: [],
      deviceSystem: [],
      deviceProfession: [],
      form: {
        deviceName: '',
        deviceNum: '',
        runDate: '',
        actoryName: '',
        ifnstallLocation: '',
        installLocationName: '',
        completePoint: 0,
        person: '',
        personName: '',
        deviceSystem: '',
        profession: '',
        fileId: '',
        remarks: '',
        deviceImg: '',
        refDeviceKey: ''
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
        ],
        profession: [
          { required: true, message: '请选择设备专业', trigger: 'blur' }
        ],
        deviceSystem: [
          { required: true, message: '请选择设备系统', trigger: 'blur' }
        ],
        factoryName: [
          { required: true, message: '请输入生产厂家', trigger: 'blur' }
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
    this.getDeviceAddress()
    this.getDeviceSystem()
    this.getDevciceProfession()
  },
  methods: {
    handleAvatarSuccess  (res, file) {
      this.imgUrl = URL.createObjectURL(file.raw)
      this.form.deviceImg = res.entityKey
    },
    beforeAvatarUpload (file) {
      const isPNG = file.type === 'image/png'
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG && !isPNG) {
        this.$message.error('上传头像图片只能是 JPG或PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return (isJPG || isPNG) && isLt2M
    },
    getParamsTable (data) { // 获取参数表中的数据
      this.paramsTableDatas.push(data)
    },
    getInspectors (data) { // 获取历任点检员表中的数据
      this.inspectorsDatas.push(data)
    },
    getRelatedKeys (data) {
      console.log(data)
      this.form.refDeviceKey = data
    },
    allFileId (v) {
      if (this.form.fileId !== '') {
        this.form.fileId += ',' + v
      } else {
        this.form.fileId = v
      }
    },
    removedFileId (v) {
      var temp = this.form.fileId
      if (temp !== '') {
        var l = temp.split(',').filter(t => {
          return t !== v
        })
        this.form.fileId = l.join(',')
      } else {
        this.form.fileId = ''
      }
    },
    removedEamKey (v) {
      var t = this.form.refDeviceKey
      if (t !== '') {
        var r = t.split(',').filter(t => {
          return t !== v
        })
        this.form.refDeviceKey = r.join(',')
      } else {
        this.form.refDeviceKey = ''
      }
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
    getDeviceAddress () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceAddress').then(res => {
        this.deviceAddress = res.data
      })
    },
    getDeviceSystem () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceSystem').then(res => {
        this.deviceSystem = res.data
      })
    },
    getDevciceProfession () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/dataDictionaryManager/findDatasByParentKey?parentKey=deviceProfessiona').then(res => {
        this.deviceProfession = res.data
      })
    },
    systemChang (v) {
      console.log(this.form.deviceSystem)
    },
    handerSubmit (process) {
      this.$refs.form.validate(valid => {
        if (valid) {
          const reqBean = {
            eamLedgerLast: this.form,
            eamLedger: this.form,
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
