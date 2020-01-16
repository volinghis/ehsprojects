<template>
  <div class="tableClass">
    <el-row>
      <el-col :span="24">
        <el-table size="mini"
                  :data="eam_Inspectors.data"
                  border
                  style="width: 100%"
                  highlight-current-row>
          <el-table-column type="index"
                           align="center"></el-table-column>
          <el-table-column prop="name"
                           label="姓名"
                           align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.isSet">
                <user-select @change="handleChange"
                             ref="userSelect"></user-select>
              </span>
              <span v-else>{{scope.row['name']}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="serverTime"
                           label="担任时间"
                           width="240"
                           align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.isSet">
                <el-date-picker v-model="eam_Inspectors.sel['serverTime']"
                                value-format="yyyy-MM-dd"
                                type="date"
                                size="mini"
                                placeholder="选择日期">
                </el-date-picker>
              </span>
              <span v-else>{{scope.row['serverTime']}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="serveTime"
                           label="离任时间"
                           width="240"
                           align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.isSet">
                <el-date-picker v-model="eam_Inspectors.sel['departureTime']"
                                type="date"
                                value-format="yyyy-MM-dd"
                                size="mini"
                                placeholder="选择日期">
                </el-date-picker>
              </span>
              <span v-else>{{scope.row['departureTime']}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="department"
                           align="center"
                           label="部门">
            <template slot-scope="scope">
              <span v-if="scope.row.isSet">
                <el-input size="mini"
                          :readonly="true"
                          placeholder="根据用户自动填写"
                          v-model="eam_Inspectors.sel['department']"></el-input>
              </span>
              <span v-else>{{scope.row['department']}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="remark"
                           align="center"
                           label="备注">
            <template slot-scope="scope">
              <span v-if="scope.row.isSet">
                <el-input size="mini"
                          placeholder="请输入内容"
                          v-model="eam_Inspectors.sel['remark']"></el-input>
              </span>
              <span v-else>{{scope.row['remark']}}</span>
            </template>
          </el-table-column>
          <template v-if="!isDisable" >
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
      </el-col>
      <el-col :span="24">
        <div class="el-table-add-row"
             v-if="!isDisable"
             style="width: 100%;color:#409EFF;cursor:pointer;"
             @click="add()"><span>+ 添加历任点检员</span></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import UserSelect from '@components/org/user-selector/index.vue'
export default {
  components: {
    UserSelect
  },
  props: {
    deviceKey: String,
    isDisable: Boolean
  },
  name: '',
  data () {
    return {
      nameLab: '',
      eam_Inspectors: {
        sel: null, // 选中行
        data: []
      }
    }
  },
  watch: {
    nameLab (value) { // 部门动态赋值
      this.eam_Inspectors.sel['department'] = this.$refs.userSelect.getCheckedNodes()[0].parent.data.label
    },
    deviceKey: function (val) {
      this.getInspectorsDataByKey(val)
    }
  },
  methods: {
    getInspectorsDataByKey (val) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getInspectorsByKey', { params: { key: val } }).then(res => {
        this.eam_Inspectors.data = res.data
      }).catch(error => {
        this.$message.message({ message: error })
      })
    },
    handleChange (value) {
      const node = this.$refs.userSelect.getCheckedNodes()[0]
      this.eam_Inspectors.sel['name'] = node.data.label
      this.nameLab = node.data.label
    },
    add () {
      for (let i of this.eam_Inspectors.data) {
        if (i.isSet) return this.$message.warning('请先保存当前编辑项')
      }
      let j = {
        'name': '',
        'serverTime': '',
        'departureTime': '',
        'department': '',
        'remark': '',
        'isSet': true
      }
      this.eam_Inspectors.data.push(j)
      this.eam_Inspectors.sel = JSON.parse(JSON.stringify(j))
    },
    saveRow (row, index) { // 保存
      let data = JSON.parse(JSON.stringify(this.eam_Inspectors.sel))
      for (let k in data) {
        row[k] = data[k] // 将sel里面的value赋值给这一行
      }
      this.$emit('getInspectors', data)
      row.isSet = false
    },
    editRow (row) { // 编辑
      for (let i of this.eam_Inspectors.data) {
        if (i.isSet) return this.$message.warning('请先保存当前编辑')
      }
      this.eam_Inspectors.sel = row
      row.isSet = true
    },
    deleteRow (rows, index) { // 删除
      if (rows.key === undefined) { // 未持久化的历任点检员数据可直接删除
        this.eam_Inspectors.data.splice(index, 1)
      } else {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/deleteInspectors', { params: { key: rows.key } }).then(res => {
          if (res.data.resultType === 'ok') {
            this.eam_Inspectors.data.splice(index, 1)
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
          this.$message.message({ message: error })
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.el-table-add-row {
  margin-top: 10px;
  width: 100%;
  height: 34px;
  border: 1px dashed #c1c1cd;
  border-radius: 1px;
  justify-content: center;
  display: flex;
  line-height: 34px;
}
.tableClass {
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
}
.el-table th > .cell {
  text-align: center;
}
.el-table td div {
  text-align: center;
}
.buttonAdd {
  float: left;
  margin-top: 10px;
  margin-bottom: 3px;
}
.item-block span {
  font-weight: bold;
}
</style>
