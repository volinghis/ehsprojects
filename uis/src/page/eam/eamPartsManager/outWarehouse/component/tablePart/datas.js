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
      console.log('val==============' + val)
      if (val === 'view') {
        this.flagMark = 'view'
        this.flagBotton = false
      } else {
        console.log('编辑')
        this.flagMark = 'edit'
        this.flagBotton = true
      }
    },
    partsTable: {
      handler (val) {
        this.tableData = val
        this.select.push.apply(this.select, val)
        this.unique(this.select)
        this.select.filter((item, index, self) => self.indexOf(item) === index)
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
    },
    saveForm: function () {
      this.$refs.partData.$refs.form.validate(valid => {
        if (valid) {
          this.drawer = false
          this.tableData.forEach((e, index) => {
            if (e.key === this.$refs.partData.form.key) {
              this.tableData.splice(index, 1)
              this.$refs.partData.form.totalPrice = this.$refs.partData.form.amount * this.$refs.partData.form.price
              var d = new Date(this.$refs.partData.form.leaveFactoryDate)
              var datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' '
              this.$refs.partData.form.leaveFactoryDate = datetime
              console.log(this.$refs.partData.form)
              this.tableData.push(this.$refs.partData.form)
              this.$emit('tableParams', this.tableData)
              return this.tableData
            }
          })
        }
      })
    },
    handleClose: function (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          this.drawer = false
          this.partsFormEdit = {}
        })
        .catch(_ => { })
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