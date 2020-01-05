export default {
  methods: {
    handlePrint: function () {
    },
    handleBack: function () {
      this.$router.go(-1)
    }
  },
  data () {
    return {
      gridData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区'
      }],
      partData: [
        { title: '申请名称', content: '增压风机冷却油泵领用申请' },
        { title: '领用编号', content: '0107001006012' },
        { title: '申请人', content: '张工' },
        { title: '申请日期', content: '2019-11-20 12:41:50' },
        { title: '申请部门', content: '设备部' },
        { title: '备注', content: '2号机组缺少因此领用设备。' }
      ]
    }
  }
}
