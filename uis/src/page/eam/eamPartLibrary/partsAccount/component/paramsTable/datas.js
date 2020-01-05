export default {
  name: '',
  data () {
    return {
      master_user: {
        sel: null, // 选中行
        columns: [{
          prop: 'paramName',
          label: '参数名称',
          width: 160
        },
        {
          prop: 'paramValue',
          label: '参数值',
          width: 160
        },
        {
          prop: 'date',
          label: '修改日期',
          width: 160
        },
        {
          prop: 'remark',
          label: '备注'
        }
        ],
        data: []
      }
    }
  },
  methods: {
    add () {
      for (let i of this.master_user.data) {
        if (i.isSet) return this.$message.warning('请先保存当前编辑项')
      }
      let j = {
        'paramName': '',
        'paramValue': '',
        'date': '',
        'remark': '',
        'isSet': true
      }
      this.master_user.data.push(j)
      this.master_user.sel = JSON.parse(JSON.stringify(j))
    },
    saveRow (row, index) { // 保存
      let data = JSON.parse(JSON.stringify(this.master_user.sel))
      for (let k in data) {
        row[k] = data[k] // 将sel里面的value赋值给这一行。ps(for....in..)的妙用，细心的同学发现这里我并没有循环对象row
      }
      row.isSet = false
    },
    editRow (row) { // 编辑
      for (let i of this.master_user.data) {
        if (i.isSet) return this.$message.warning('请先保存当前编辑11项')
      }
      this.master_user.sel = row
      row.isSet = true
    },
    deleteRow (index, rows) { // 删除
      rows.splice(index, 1)
    }
  },
  components: {}
}

// export default {
//   methods: {
//     handleClickAdd: function () {
//       this.$message('这是一条消息提示')
//     },
//     handleClickEdit: function (row) {
//       this.$message('这是一条消息提示')
//     },
//     handleClickDel: function (row) {
//       this.$message('这是一条消息提示')
//     },
//     handleClose (done) {
//       this.$confirm('确认关闭？')
//         .then(_ => {
//           done()
//         })
//         .catch(_ => {})
//     }
//   },

//   data () {
//     return {
//       dialogVisible: false,
//       formLabelAlign: {
//         name: '',
//         value: '',
//         date: '',
//         remark: ''
//       },
//       tableData: [{
//         name: '颜色',
//         value: '灰色',
//         date: '2019-11-22',
//         remark: '灰中有黑色斑点'
//       }, {
//         name: '形状',
//         value: '圆柱形',
//         date: '2019-11-22',
//         remark: '易变形'
//       }, {
//         name: '压力值',
//         value: '1000Pa',
//         date: '2019-11-22',
//         remark: '<=1000Pa'
//       }]
//     }
//   }
// }
