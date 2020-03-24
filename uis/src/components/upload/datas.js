export default {
  props: {
    'disabled': { type: Boolean, default: false },
    'multiple': { type: Boolean, default: true },
    'propUploadValue': String,
    'message': { type: String, default: '' }
  },
  model: {
    prop: 'propUploadValue',
    event: 'change'
  },
  watch: {
    'propUploadValue': function (v) {
      var that = this
      if (v) {
        that.$axios.get(that.GlobalVars.globalServiceServlet + '/data/file/fileList?fileIds=' + v)
          .then((res) => {
            if (res.data) {
              for (var i = 0; i < res.data.length; i++) {
                this.files.push({ name: res.data[i].name, uid: res.data[i].fileId, status: 'success' })
              }
            }
          })
      }
    }
  },
  data () {
    return { files: [], tempFiles: [] }
  },
  mounted () {

  },
  methods: {
    change (v) {
      this.$emit('change', v)
    },
    onPreview (file) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + file.uid, { responseType: 'blob' }).then((res) => {
        let fileName = file.name
        let blob = new Blob([res.data])
        if (window.navigator.msSaveOrOpenBlob) {
          navigator.msSaveBlob(blob, fileName)
        } else {
          var link = document.createElement('a')
          link.href = window.URL.createObjectURL(blob)
          link.download = fileName
          link.click()
          window.URL.revokeObjectURL(link.href)
        }
      })
    },
    uploadSuccess (res, file, fileList) {
      var temp = ''
      for (var i = 0; i < fileList.length; i++) {
        if (fileList[i].uid === file.uid) {
          fileList[i].uid = res.entityKey
        }
        temp = temp + fileList[i].uid
        if (i < fileList.length - 1) {
          temp = temp + ','
        }
      }
      this.tempFiles = fileList.concat()
      this.change(temp)
    },
    beforeUpload (file) {
      for (var i = 0; i < this.tempFiles.length; i++) {
        if (this.tempFiles[i].name === file.name) {
          this.$message({
            message: '文件已存在',
            type: 'error'
          })
          return false
        }
      }
      return true
    },
    uploadRemove (file, fileList) {
      var temp = ''
      for (var i = 0; i < fileList.length; i++) {
        temp = temp + fileList[i].uid
        if (i < fileList.length - 1) {
          temp = temp + ','
        }
      }
      this.tempFiles = fileList.concat()
      this.change(temp)
    }
  }
}
