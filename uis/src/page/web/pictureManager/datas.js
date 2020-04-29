export default {
  data () {
    return {
      tableData: [],
      dialogVisible: false,
      viewUrl: '',
      queryBean: {
        page: 1,
        size: 5,
        query: ''
      }
    }
  },
  created () {
    this.getPicturesList()
  },
  methods: {
    getPicturesList () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/web/pictures/getPicturesList', this.queryBean)
        .then(res => {
          var resData = res.data.dataList
          this.tableData = resData
        })
    },
    handleRemove (row) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/web/pictures/deletePictureInfo', { params: { key: row.key } })
        .then(res => {
          this.getPicturesList()
        }).catch(error => {
          console.log(error)
          this.$message(error)
        })
    },
    handleSuccess (res, file) {
      const t = this.tableData
      let maxOrder
      if (t.length !== 0) {
        maxOrder = t[t.length - 1].picOrder
      } else {
        maxOrder = 1
      }
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/web/pictures/savePictureInfo', { params: { fileId: res.entityKey, fileName: file.name, order: maxOrder + 1 } })
        .then(res => {
          this.getPicturesList()
        }).catch(error => {
          this.$message(error)
        })
    },
    handleRowDown (row, index) {
      var _this = this
      if (index >= _this.tableData.length - 1) {
        _this.$message({
          type: 'warning',
          message: '已经是最后第一条，不可下移'
        })
      } else {
        let toRow = _this.tableData[index + 1]
        this.handleUpdateMove(row, toRow)
      }
    },
    handleRowUp (row, index) {
      var _this = this
      if (index > 0) {
        let toRow = _this.tableData[index - 1]
        this.handleUpdateMove(row, toRow)
      } else {
        _this.$message({
          type: 'warning',
          message: '已经是第一条，不可上移'
        })
      }
    },
    handleUpdateMove (srcRow, toRow) {
      let moveBean = {
        srcKey: srcRow.key,
        toKey: toRow.key,
        srcOrder: srcRow.picOrder,
        toOrder: toRow.picOrder
      }
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/web/pictures/updateMovedRow', moveBean)
        .then(res => {
          if (res.data.resultType === 'ok') {
            this.getPicturesList()
          }
        })
    },
    beforeUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isPNG = file.type === 'image/png'
      const isLt1M = file.size / 1024 / 1024 < 1
      if (!isJPG && !isPNG) {
        this.$message.error('上传图片只能是 JPG或PNG 格式!')
      }
      if (!isLt1M) {
        this.$message.error('上传图片大小不能超过 1MB!')
      }
      return (isJPG || isPNG) && isLt1M
    }
  }
}
