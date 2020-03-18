<template>
  <div class="dialogForm">
    <el-dialog title="新增设备资料" width="30%" :visible.sync="dialogFormVisible">
      <el-form :model="form" ref="form">
        <el-form-item label="上传资料:" :label-width="formLabelWidth">
          <file-upload :propUploadValue="form.fileId" :paramData="paramData"  @change="handleChange" ref="myUpload" ></file-upload>
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
export default {
  components: {
    FileUpload
  },
  data () {
    return {
      dialogFormVisible: false,
      formLabelWidth: '100px',
      paramData: {
        categories: '',
        categoriesName: ''
      },
      form: {
        fileId: '',
        entityKey: '',
        category: ''
      }
    }
  },
  methods: {
    openForm (nodeKey, nodeLabel) { // 打开弹窗
      if (this.$refs.myUpload !== undefined) {
        this.$refs.myUpload.$children[0].clearFiles()// 清理文件列表暂时的处理
      }
      this.form.category = nodeKey
      this.paramData.categories = nodeKey
      this.paramData.categoriesName = nodeLabel
      this.dialogFormVisible = true
    },
    handleChange (val) {
      this.form.fileId = val
    },
    handleSubmit () {
      var fileId = this.form.fileId
      if (fileId) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/dataBase/saveDataFileInfo', { params: { fileId: fileId } }).then(res => {
          if (res.data.resultType === 'ok') {
            this.dialogFormVisible = false
            this.$emit('flushData')
          }
        }).catch(error => {
          this.$message({ message: error })
        })
      } else {
        this.$message({
          message: '请先上传文件',
          type: 'warning'
        })
        return false
      }
    }
  }
}
</script>
 <style lang="scss" scoped>
</style>
