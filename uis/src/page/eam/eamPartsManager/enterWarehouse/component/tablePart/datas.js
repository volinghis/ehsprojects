import editPart from '../editPart/index.vue'
export default {
  data () {
    return {
      editPartFlag: '',
      flag: false,
      tPrice: 0,
      tableData: [],
      select: [],
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
    objPart: Object,
    showFlag: String
  },
  watch: {
    showFlag: function (val) {
      if (val === 'view') {
        this.flag = false
      } else {
        this.flag = true
      }
    },
    objPart: {
      handler: function (val) {
        this.tableData.push(val)
        this.select = this.tableData
      }
    },
    totalCounts: function (val) {
      this.totalCount = val
    },
    partsTable: {
      handler (val) {
        this.tableData = val
        this.select.push.apply(this.select, val)
        this.tableData = this.select
      }
    },
    parts: {
      handler (val) {
        this.select.push.apply(this.select, val)
        this.tableData = this.select
      }
    },
    deep: true
  },
  methods: {
    handleEdit: function (row) {
      this.drawer = true
      this.$nextTick(() => {
        this.partsFormEdit = row
        this.editPartFlag = 'edit'
      })
    },
    handleClick: function (row) {
      this.drawer = true
      this.$nextTick(() => {
        this.partsFormEdit = row
        this.editPartFlag = 'view'
      })
    },
    handleDel: function (index, rows) {
      rows.splice(index, 1)
      this.tableData = rows
      this.$emit('tableParams', this.tableData)
    },
    saveForm: function () {
      this.$refs.partData.$refs.form.validate(valid => {
        if (valid) {
          this.drawer = false
          this.tableData.forEach((e, index) => {
            if (e.key === this.$refs.partData.form.key) {
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
              this.tableData.forEach(element => {
                element.key = ''
              })
              this.$emit('tableParams', this.tableData)
              return this.tableData
            }
          })
        }
      })
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
        if (this.$refs.partData.form.id === '') {
          this.tableData.forEach((e, index) => {
            if (e.key === this.$refs.partData.form.key) {
              this.tableData.splice(index, 1)
              this.drawer = false
            }
          })
        } else {
          this.drawer = false
        }
        this.$message({
          type: 'info',
          message: action === 'cancel'
            ? '放弃保存并离开页面'
            : '停留在当前页面'
        })
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
