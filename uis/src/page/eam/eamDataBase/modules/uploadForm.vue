<template>
  <div class="dialogForm">
    <el-dialog title="新增设备资料" width="30%" :visible.sync="dialogFormVisible">
      <el-form :model="form" ref="form">
        <el-form-item label="选择设备:" :label-width="formLabelWidth">
          <AutoComplete @handleSelect="handleSelect" :isShow="false"></AutoComplete>
        </el-form-item>
        <el-form-item label="上传资料:" :label-width="formLabelWidth">
          <file-upload :propUploadValue="form.fileId" :paramData="paramData"  @change="handleChange"></file-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false" :size="GlobalCss.buttonSize">取 消</el-button>
        <el-button
          type="primary"
          @click="handleSubmit"
          :size="GlobalCss.buttonSize">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import FileUpload from '@components/upload/index'
import AutoComplete from '../../components/autocomplete.vue'
export default {
  components: {
    FileUpload,
    AutoComplete
  },
  data () {
    return {
      dialogFormVisible: false,
      formLabelWidth: '100px',
      paramData: {
        categories: ''
      },
      form: {
        fileId: '',
        entityKey: '',
        category: ''
      }
    }
  },
  mounted () {
  },
  methods: {
    openForm (val) { // 打开弹窗
      this.form.category = val
      this.paramData.categories = val
      this.dialogFormVisible = true
    },
    handleSelect (item) {
      this.form.entityKey = item.key
    },
    handleChange (val) {
      this.form.fileId = val
    },
    handleSubmit () {
      this.$refs.form.validate(valid => {
        if (valid) {
          console.log(this.form)
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/dataBase/updateDataFileInfo', this.form).then(res => {
            if (res.data.resultType === 'ok') {
              this.dialogFormVisible = false
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
</script>
 <style lang="scss" scoped>
</style>
