export default {
  props: {
    paramsData: Array,
    flag: String
  },
  watch: {
    paramsData: {
      handler (newValue, oldValue) {
        if (newValue) {
          this.part_parameters.data = newValue
        }
      },
      deep: true
    },
    flag (val) {
      if (val === 'view') {
        this.buttonFlag = false
      } else {
        this.buttonFlag = true
      }
    }
  },
  data () {
    return {
      buttonFlag: '',
      part_parameters: {
        sel: null, // 选中行
        data: [],
        columns: [
          {
            prop: 'paramName',
            label: '参数名称',
            width: 250
          },
          {
            prop: 'paramVal',
            label: '参数值',
            width: 250
          },
          {
            prop: 'remark',
            label: '备注'
          }
        ]
      }
    }
  },
  methods: {
    add () {
      for (let i of this.part_parameters.data) {
        if (i.isSet) return this.$message.warning('请先保存当前编辑项')
      }
      let j = {
        'paramName': '',
        'paramVal': '',
        'remark': '',
        'isSet': true
      }
      this.part_parameters.data.push(j)
      this.part_parameters.sel = JSON.parse(JSON.stringify(j))
    },
    saveRow (row, index) { // 保存
      let paramData = JSON.parse(JSON.stringify(this.part_parameters.sel))
      for (let k in paramData) {
        row[k] = paramData[k]
      }
      if (row.paramName === '') {
        return this.$message.warning('参数名称不能为空')
      }
      if (row.paramVal === '') {
        return this.$message.warning('参数值不能为空')
      }
      row.isSet = false
    },
    editRow (row) { // 编辑
      for (let i of this.part_parameters.data) {
        if (i.isSet) return this.$message.warning('请先保存当前编辑项')
      }
      this.part_parameters.sel = row
      row.isSet = true
    },
    deleteRow (index, rows) { // 删除
      rows.splice(index, 1)
    }
  }
}
