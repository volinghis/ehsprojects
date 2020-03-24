import editPart from '../editPart/index.vue'
export default {
  data () {
    return {
      tPrice: 0,
      partDatas: [],
      tableData: [],
      select: [],
      flagBotton: false,
      flagMark: '',
      partsFormEdit: {},
      totalCount: 0,
      tableHeight: ' ',
      drawer: false,
      direction: 'rtl',
      currentPage: 1,
      form: {
        page: 1,
        size: 20
      }
    }
  },
  components: {
    editPart
  },
  props: {
    parts: Array,
    partsTable: Array,
    totalCounts: Number,
    flag: String
  },
  watch: {
    totalCounts: function (val) {
      this.totalCount = val
    },
    flag: function (val) {
      if (val === 'view') {
        this.flagMark = 'view'
        this.flagBotton = false
      } else {
        this.flagMark = 'edit'
        this.flagBotton = true
      }
    },
    partsTable: {
      handler (val) {
        this.tableData = val
        this.select.push.apply(this.select, val)
        this.unique(this.select)
        this.tableData = this.select
      }
    },
    parts: {
      handler (newName) {
        var newVal = []
        newName.forEach(e => {
          e.key = ''
          newVal.push(e)
        })
        this.select.push.apply(this.select, newVal)
        this.unique(this.select)
        this.tableData = this.select
        this.partDatas = newVal
      }
    },
    deep: true
  },
  created: function () {
    if (this.flag !== 'view') {
      this.flagBotton = true
    }
  },
  methods: {
    unique: function (arr) {
      return arr.filter(function (item, index, arr) {
        // 当前元素，在原始数组中的第一个索引==当前索引值，否则返回当前元素
        return arr.indexOf(item, 0) === index
      })
    },
    handleEdit: function (row) {
      this.drawer = true
      this.$nextTick(() => {
        this.partsFormEdit = row
        this.flagMark = 'edit'
      })
    },
    handleClick: function (row) {
      this.drawer = true
      this.$nextTick(() => {
        this.partsFormEdit = row
        this.flagMark = 'view'
      })
    },
    handleDel: function (index, rows) {
      rows.splice(index, 1)
    },
    // handleClose: function (done) {
    //   this.$confirm('确认关闭？').then(_ => {
    //     this.$refs.partData.$refs.form.validate(valid => {
    //       if (valid) {
    //         this.drawer = false
    //       } else {
    //         this.drawer = true
    //       }
    //     })
    //   })
    // },
    handleClose: function (done) {
      this.$confirm('检测到未保存的内容，是否在离开页面前保存修改？', '确认信息', {
        distinguishCancelAndClose: true,
        confirmButtonText: '保存',
        cancelButtonText: '放弃编辑'
      }).then(() => {
        this.$refs.partData.$refs.form.validate(valid => {
          if (valid) {
            this.drawer = false
            this.$message({
              type: 'info',
              message: '保存成功'
            })
          } else {
            this.drawer = true
            this.$message({
              type: 'warning',
              message: '备件信息填写不完整'
            })
          }
        })
      }).catch(action => {
        this.drawer = false
        this.$message({
          type: 'info',
          message: action === 'cancel'
            ? '放弃保存并离开页面'
            : '停留在当前页面'
        })
      })
    },
    saveForm: function () {
      this.$refs.partData.$refs.form.validate(valid => {
        if (valid) {
          this.drawer = false
          this.tableData.forEach((e, index) => {
            if (e.id === this.$refs.partData.form.id) {
              this.tableData.splice(index, 1)
              if (this.$refs.partData.form.leaveFactoryDate !== '') {
                var d = new Date(this.$refs.partData.form.leaveFactoryDate)
                var datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' '
                this.$refs.partData.form.leaveFactoryDate = datetime
              }
              let tableDataNew = []
              tableDataNew.push(this.$refs.partData.form)
              this.select.push.apply(this.select, tableDataNew)
              this.tableData = this.select
              this.$emit('tableParams', this.tableData)
              return this.tableData
            }
          })
        }
      })
    },
    getSummaries: function (param) {
      const { columns, data } = param
      const sums = []
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '合计'
          return
        }
        const values = data.map(item => Number(item[column.property]))
        if (!values.every(value => isNaN(value))) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr)
            if (!isNaN(value)) {
              return prev + curr
            } else {
              return prev
            }
          }, 0)
          sums[index] += ''
        } else {
          sums[index] = ''
        }
      })
      return sums
    }
  }
}
