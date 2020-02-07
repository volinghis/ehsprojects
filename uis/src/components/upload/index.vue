<template>

  <el-upload
  class="upload-panel"
  :action="GlobalVars.globalServiceServlet + '/data/file/fileUpload'+ '?tt=' + Math.random()+ '&resoureMenuKey=' + $store.state.resourceMenuKey"
  :multiple="multiple"
  :disabled="disabled"
  :on-success="uploadSuccess"
  :on-remove="uploadRemove"
  :before-upload="beforeUpload"
  :on-preview="onPreview"
  :limit="20"
  :file-list="files">
  <el-button :size="GlobalCss.buttonSize" type="primary">点击上传</el-button>
  <div slot="tip" class="el-upload__tip">{{message}}</div>
</el-upload>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="sass" scoped>
  @import './styles.scss'
</style>
