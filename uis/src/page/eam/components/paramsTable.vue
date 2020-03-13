<template>
  <div class="tableClass">
    <span style="display:inline-block;float:left">设备参数</span>
    <div class="operate">
      <el-button type="primary"
                 v-if="!isDisable"
                 icon="fa fa-plus pull-left"
                 @click="add()"
                 :size="GlobalCss.buttonSize">添加设备参数</el-button>
    </div>
    <el-table :size="GlobalCss.buttonSize"
              :data="eam_params.data"
              border
              style="width: 100%"
              highlight-current-row>
      <el-table-column type="index"
                       align="center"></el-table-column>
      <el-table-column v-for="(item,index) in eam_params.columns"
                       :label="item.label"
                       :prop="item.prop"
                       :key="index"
                       align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.isSet">
            <el-input :size="GlobalCss.buttonSize"
                      placeholder="请输入内容"
                      v-model="eam_params.sel[item.prop]"></el-input>
          </span>
          <span v-else>{{scope.row[item.prop]}}</span>
        </template>
      </el-table-column>
      <template v-if="!isDisable">
        <el-table-column label="操作"
                         align="center"
                         width="220">
          <template slot-scope="scope">
            <el-button type="success"
                       :size="GlobalCss.buttonSize"
                       @click.stop="saveRow(scope.row,scope.$index)"
                       v-if="scope.row.isSet">保存
            </el-button>
            <el-button type="primary"
                       :size="GlobalCss.buttonSize"
                       @click="editRow(scope.row,scope.$index)">
              编辑
            </el-button>
            <el-button type="danger"
                       :size="GlobalCss.buttonSize"
                       @click="deleteRow(scope.row,scope.$index)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </template>
    </el-table>
  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
      eam_params: {
        sel: null, // 选中行
        columns: [{
          prop: 'paramName',
          label: '参数名称'
        },
        {
          prop: 'paramValue',
          label: '参数值'
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
  props: {
    deviceKey: String,
    isDisable: Boolean
  },
  watch: {
    deviceKey: function (val) {
      this.getParamsDataByKey(val)
    }
  },
  methods: {
    getParamsDataByKey (val) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getEamParamsByKey', { params: { key: val } }).then(res => {
        this.eam_params.data = res.data
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    add () {
      for (var i of this.eam_params.data) {
        if (i.isSet) {
          return this.$message.warning('请先保存当前编辑项')
        }
      }
      let j = {
        'paramName': '',
        'paramValue': '',
        'remark': '',
        'isSet': true
      }
      this.eam_params.data.push(j)
      this.eam_params.sel = JSON.parse(JSON.stringify(j))
    },
    saveRow (row, index) { // 保存
      let data = JSON.parse(JSON.stringify(this.eam_params.sel))
      if (data.paramName === '' && data.paramValue === '') {
        this.$message.warning('请输入参数名和参数值')
        return
      }
      row.paramValue = data.paramValue
      row.paramName = data.paramName
      row.remark = data.remark
      this.$emit('getParamsTable', data)

      row.isSet = false
    },
    editRow (row) { // 编辑
      for (var i of this.eam_params.data) {
        if (i.isSet) return this.$message.warning('请先保存当前编辑')
      }
      this.eam_params.sel = row
      row.isSet = true
    },
    deleteRow (rows, index) { // 删除
      if (rows.key === undefined) { // 未持久化的参数数据可直接删除
        this.eam_params.data.splice(index, 1)
      } else {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/deleteEamParams', { params: { key: rows.key } }).then(res => {
          if (res.data.resultType === 'ok') {
            this.eam_params.data.splice(index, 1)
            this.$message({
              message: res.data.message,
              type: 'success'
            })
          } else {
            this.$message({
              message: res.data.message,
              type: 'info'
            })
          }
        }).catch(error => {
          this.$message({ message: error })
        })
      }
    }
  },
  components: {}
}
</script>

<style lang="scss" scoped>
.tableClass {
  margin-top: 10px;
  text-align: center;
}
.operate {
  margin-bottom: 8px;
  float: right;
}
</style>
