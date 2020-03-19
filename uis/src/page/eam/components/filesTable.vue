<template>
  <div class="filesTable">
     <span style="display:inline-block;float:left">关联文件</span>
    <div class="operate"
         v-if="!isDisable">
      <div class="select-wrap">
        <span>文件类别：</span>
        <el-select v-model="value"
                   @change="selectChang"
                   ref="selector"
                   :size="GlobalCss.buttonSize"
                   placeholder="请选择文件类别">
          <el-option v-for="item in options"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
      </div>
      <div class="upload-wrap">
        <el-upload :action="GlobalVars.globalServiceServlet + '/data/file/fileUpload'+ '?tt=' + Math.random()+ '&resoureMenuKey=' + $store.state.resourceMenuKey"
                   ref="upload"
                   :data="paramData"
                   :disabled="disabled"
                   :on-success="uploadSucess"
                   :show-file-list="false">
          <el-button :size="GlobalCss.buttonSize"
                     @click="handleClickUpload"
                     type="primary"
                     icon="fa fa-cloud-upload pull-left">点击上传</el-button>
        </el-upload>
      </div>
    </div>
    <div class="tableClass">
      <el-table :data="filesTableData"
                border
                :size="GlobalCss.buttonSize"
                style="width: 100%">
        <el-table-column type="index"
                         align="center"></el-table-column>
        <el-table-column prop="name"
                         align="center"
                         label="文件名称">
        </el-table-column>
        <el-table-column prop="type"
                         align="center"
                         label="文件类型">
        </el-table-column>
        <el-table-column prop="fileSize"
                         align="center"
                         label="文件大小">
        </el-table-column>
        <el-table-column prop="categoriesName"
                         align="center"
                         label="类别">
        </el-table-column>
        <el-table-column fixed="right"
                         align="center"
                         label="操作">
          <template slot-scope="scope">
            <el-button @click="handleViewClick(scope.row)"
                       type="primary"
                       :size="GlobalCss.buttonSize">预览</el-button>
            <el-button type="danger"
                       v-if="!isDisable"
                       :size="GlobalCss.buttonSize"
                       @click="handleDelete(scope.row,scope.$index)">删除</el-button>
            <el-button type="warning"
                       @click="handleDownLoadClick(scope.row)"
                       :size="GlobalCss.buttonSize">下载</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <template>
      <el-dialog title="文件预览"
                 :visible.sync="viewVisible"
                 width="50%"
                 height="100%"
                 destroy-on-close>
        <embed id="myObj"
               :src="pdfSrc"
               type="application/pdf"
               width="100%"
               height="500px;" />
      </el-dialog>
    </template>
  </div>
</template>

<script>
export default {
  data () {
    return {
      filesTableData: [],
      options: [],
      value: '',
      label: '',
      disabled: false,
      paramData: {},
      pdfSrc: '',
      viewVisible: false,
      temp: ''
    }
  },
  props: {
    deviceKey: String,
    fileId: String,
    isDisable: Boolean
  },
  mounted () {
    this.getOptions()
    this.getFileList(this.fileId)
  },
  methods: {
    getOptions () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/dataBase/getFileCategories').then(res => {
        this.options = res.data
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    getFileList (fileId) {
      if (fileId !== '') {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/fileList?fileIds=' + fileId).then(res => {
          this.filesTableData = res.data
        }).catch(error => {
          this.$message({ message: error })
        })
      }
    },
    selectChang (v) {
      const option = this.options.find((n) => n.value === v)
      this.label = option.label
      this.paramData.categories = v
      this.paramData.categoriesName = option.label
      this.value = v
    },
    handleClickUpload () {
      if (this.value === '') {
        this.$message({ message: '请先选择文件类别', type: 'warning' })
        this.disabled = true
        setTimeout(() => {
          this.disabled = false
        }, 0)
      }
    },
    handleViewClick: function (scope) {
      var url = this.GlobalVars.globalServiceServlet + '/data/file/viewFile?fileId=' + scope.fileId + '&resoureMenuKey=' + this.$store.state.resourceMenuKey
      this.pdfSrc = url
      this.viewVisible = true
    },
    uploadSucess (res, file, fileList) {
      var f = {}
      f.name = file.name
      f.fileSize = this.bytesToSize(file.size)
      f.type = file.name.substring(file.name.indexOf('.') + 1, file.name.length)
      f.categoriesName = this.label
      f.fileId = res.entityKey
      this.filesTableData.push(f)

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
      this.temp = temp
      this.$emit('getFileId', temp)
    },
    handleDelete (row, index) {
      if (row.key === undefined) { // 未持久化的参数数据可直接删除
        this.filesTableData.splice(index, 1)
        var c = this.temp.split(',')
        var d = ''
        for (var i = 0; i < c.length; i++) {
          d += c[i] === row.fileId ? '' : ',' + c[i]
        }
        d = d.length > 0 ? d.substring(1) : ''
        this.$emit('getFileId', d)
      } else {
        if (this.deviceKey !== '') {
          this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/removeRefFile', { params: { deviceKey: this.deviceKey, key: row.fileId } }).then(res => {
            if (res.data.resultType === 'ok') {
              this.filesTableData.splice(index, 1)
              this.$emit('removedFileId', row.fileId)
              this.$message({
                message: res.data.message,
                type: 'success'
              })
            }
          }).catch(error => {
            this.$message({ message: error })
          })
        }
      }
    },
    handleDownLoadClick: function (scope) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + scope.fileId, { responseType: 'blob' }).then((res) => {
        let fileName = scope.name
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
    bytesToSize (bytes) {
      if (bytes === 0) return '0 B'
      var k = 1024
      var sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB']
      var i = Math.floor(Math.log(bytes) / Math.log(k))
      return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i]
    }
  }
}
</script>
<style lang="scss" scoped>
.tableClass {
  margin: 10px 0px;
  text-align: center;
}
.operate {
  float: right;
}
.select-wrap {
  float: left;
}
.upload-wrap{
  margin-left: 8px;
  margin-bottom: 8px;
  float: right;
}
</style>
