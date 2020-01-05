export default {
  methods: {
    handleAdd: function () {
      this.$router.push({ name: '411' })
    },
    handleDelete: function () {
      this.$message({
        message: '恭喜你，删除成功',
        type: 'success'
      })
    },
    handleClick: function (row) {
      this.$router.push({ name: '411' })
    },
    handleEdit: function (row) {
      this.$router.push({ name: '411' })
    },
    exportExcel: function () {
      this.$message('正在导出，请稍等···')
    },
    toggleAdvanced: function () {
      this.advanced = !this.advanced
    },
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
    }
  },
  mounted: function () {
    var hcard = document.querySelector('.cardHeight').offsetHeight
    var hfrom = document.querySelector('.fromHeight').offsetHeight
    var hpage = document.querySelector('.pageHeight').offsetHeight
    var hbutton = document.querySelector('.buttonHeight').offsetHeight
    this.htable = (hcard - hfrom - hpage - hbutton - 25) + 'px'
  },
  data () {
    return {
      htable: ' ',
      currentPage1: 1,
      // 高级搜索 展开/关闭
      advanced: false,
      // 查询参数
      queryParam: {},
      customColor: '#409eff',
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      tableData: [{
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'FM-0001',
        name: '阀门',
        completion: 80,
        type: '阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌阿萨大大大大',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '进行中'
      }, {
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '已完成'
      }, {
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '未执行'
      }, {
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '已作废'
      }, {
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '已完成'
      }, {
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'QYFM-0001',
        name: '气压阀门',
        completion: 60,
        type: '气压阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '已完成'
      }, {
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'FDJ-0001',
        name: '发电机',
        completion: 100,
        type: '发电机',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '已完成'
      }, {
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'FM-0002',
        name: '阀门',
        completion: 70,
        type: '阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '已完成'
      }, {
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'FM-0002',
        name: '阀门',
        completion: 70,
        type: '阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '已完成'
      }, {
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'FM-0002',
        name: '阀门',
        completion: 70,
        type: '阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '已完成'
      }, {
        deviceImg: 'https://i.loli.net/2019/11/07/2STd8zUw6x5hWaM.jpg',
        code: 'GL-0002',
        name: '锅炉',
        completion: 90,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10',
        status: '已完成'
      }]
    }
  }
}
