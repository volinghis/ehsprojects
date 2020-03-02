import paramsTable from '../paramsTable/index.vue'
import eamlist from '../../../../components/eamList.vue'
import org from '@components/org/org-selector/index.vue'
import user from '@components/org/user-selector/index.vue'
export default {
  components: {
    'paramsTable': paramsTable,
    'eamlist': eamlist,
    'org': org,
    'user': user
  },
  data () {
    return {
      tableHeight: ' ',
      dialogVisible: false,
      devices: [],
      deviceSet: [],
      tableData: [],
      form: {
        inspTaskCode: '',
        inspTaskName: '',
        inspTaskType: '',
        founder: '',
        responsibleDept: '',
        responsiblePerson: '',
        startDate: '',
        overDate: '',
        workingStandard: '',
        remark: ''
      },
      partForm: {
        name: '',
        code: '',
        type: ''
      },
      rules: {
        inspTaskCode: [
          { required: true, message: '请输入计划名称', trigger: 'blur' }
        ],
        inspTaskName: [
          { required: true, message: '请输入计划名称', trigger: 'blur' }
        ],
        inspTaskType: [
          { required: true, message: '请选择计划类型', trigger: 'blur' }
        ],
        responsibleDept: [
          { required: true, message: '请选择责任部门', trigger: 'blur' }
        ],
        responsiblePerson: [
          { required: true, message: '请选择责任人', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    const user = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))
    this.form.founder = user.username
  },
  methods: {
    handlerSelect: function (val) {
      this.devices = val
    },
    overSelect: function () {
      this.deviceSet = this.devices
      this.dialogVisible = false
    },
    overReset: function () {
      this.dialogVisible = false
    },
    handleClose: function (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    changeSelectOrg: function (val) {
      this.form.responsibleDept = val
    },
    changeSelectUser: function (val) {
      this.form.responsiblePerson = val
    },
    handerSubmit: function (processInfo) {
      this.$refs.form.validate(valid => {
        if (valid) {
          const reParams = {
            flowProcessInfo: processInfo,
            inspectionTask: this.form,
            inspectionDevice: this.deviceSet
          }
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamInspection/saveTask', reParams).then(res => {
            if (res.data.resultType === 'ok') {
              this.$message({
                message: res.data.message
              })
              this.$router.push({ name: 'eamInspection' })
              window.close()
            } else {
              this.$message.error(res.data.message)
            }
          })
        } else {
          this.$message.error('验证数据失败，请确认数据')
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
    }
  }
}
