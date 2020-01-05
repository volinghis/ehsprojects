export default {
  mounted: function () {
    var hdiv = document.querySelector('.divHeight').offsetHeight
    var hsubmiit = document.querySelector('.submitHeight').offsetHeight
    var hsearch = document.querySelector('.searchHeight').offsetHeight
    var hbutton = document.querySelector('.buttonHeight').offsetHeight
    var hpage = document.querySelector('.pageHeight').offsetHeight
    this.tableHeight = (hdiv - hsubmiit - hsearch - hbutton - hpage - 5) + 'px'
  },
  methods: {
    handleSizeChange: function (val) {
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
    },
    handleCurrentChange: function (val) {
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
    },
    handleClickEdit: function () {
      this.$message({
        message: '恭喜你，这是一条成功消息',
        type: 'success'
      })
    },
    submitForm: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$message({
            message: '恭喜你，这是一条成功消息',
            type: 'success'
          })
        } else {
          this.$message.error('错了哦，这是一条错误消息')
          return false
        }
      })
    },
    handleClose: function (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
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
  },
  data () {
    return {
      tableHeight: ' ',
      drawer: false,
      direction: 'rtl',
      currentPage: 1,
      form: {
        name: '',
        code: '',
        type: '',
        price: '',
        amount: '',
        unit: ''
      },
      rules: {
        price: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        unit: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        amount: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ]
      },
      tableData: [
        {
          id: '12987122',
          name: '阀门',
          type: 'GL_FM_0001',
          amount: '234',
          price: '10',
          unit: '个',
          totalPrice: 234 * 10
        },
        {
          id: '12987122',
          name: '阀门',
          type: 'GL_FM_0001',
          amount: '234',
          price: '10',
          unit: '个',
          totalPrice: 234 * 10
        },
        {
          id: '12987122',
          name: '阀门',
          type: 'GL_FM_0001',
          amount: '234',
          price: '10',
          unit: '个',
          totalPrice: 234 * 10
        },
        {
          id: '12987122',
          name: '阀门',
          type: 'GL_FM_0001',
          amount: '234',
          price: '10',
          unit: '个',
          totalPrice: 234 * 10
        },
        {
          id: '12987122',
          name: '阀门',
          type: 'GL_FM_0001',
          amount: '234',
          price: '10',
          unit: '个',
          totalPrice: 234 * 10
        },
        {
          id: '12987122',
          name: '阀门',
          type: 'GL_FM_0001',
          amount: '234',
          price: '10',
          unit: '个',
          totalPrice: 234 * 10
        },
        {
          id: '12987122',
          name: '阀门',
          type: 'GL_FM_0001',
          amount: '234',
          price: '10',
          unit: '个',
          totalPrice: 234 * 10
        },
        {
          id: '12987122',
          name: '阀门',
          type: 'GL_FM_0001',
          amount: '234',
          price: '10',
          unit: '个',
          totalPrice: 234 * 10
        }
      ]
    }
  }
}
