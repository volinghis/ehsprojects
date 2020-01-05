<template>
  <div class="tableClass">
    <el-row>
      <el-col :span="24">
        <el-table size="mini"
                  :data="master_user.data"
                  style="width: 100%"
                  highlight-current-row>
          <el-table-column type="index"
                           align="center"></el-table-column>
          <!-- <el-table-column v-for="(item,index) in master_user.columns"
                           :label="item.label"
                           :prop="item.prop"
                           :key="index"
                           align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.isSet">
                <el-input size="mini"
                          placeholder="请输入内容"
                          v-model="master_user.sel[item.prop]"></el-input>
              </span>
              <span v-else>{{scope.row[item.prop]}}</span>
            </template>
          </el-table-column> -->
          <el-table-column prop="name"
                           label="姓名"
                           align="center">
            align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.isSet">
                <user-select></user-select>
              </span>
              <span v-else>{{scope.row['departureTime']}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="serveTime"
                           label="担任时间"
                           width="230"
                           align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.isSet">
                <el-date-picker v-model="master_user.sel['serveTime']"
                                type="date"
                                size="mini"
                                placeholder="选择日期">
                </el-date-picker>
              </span>
              <span v-else>{{scope.row['serveTime']}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="departureTime"
                           label="离任时间"
                           width="230"
                           align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.isSet">
                <el-date-picker v-model="master_user.sel['departureTime']"
                                type="date"
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
                          placeholder="请输入内容"
                          v-model="master_user.sel['department']"></el-input>
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
                          v-model="master_user.sel['remark']"></el-input>
              </span>
              <span v-else>{{scope.row['remark']}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作"
                           width="220">
            <template slot-scope="scope">
              <el-button type="success"
                         :size="GlobalCss.buttonSize"
                         @click.stop="saveRow(scope.row,scope.$index)">
                <!-- {{scope.row.isSet?'保存':"修改"}} -->保存
              </el-button>
              <el-button type="primary"
                         :size="GlobalCss.buttonSize"
                         @click="editRow(scope.row,scope.$index)">
                编辑
              </el-button>
              <el-button type="warning"
                         :size="GlobalCss.buttonSize"
                         @click="deleteRow(scope.$index,master_user.data)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="24">
        <div class="el-table-add-row"
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
  name: '',
  data () {
    return {
      master_user: {
        sel: null, // 选中行
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
