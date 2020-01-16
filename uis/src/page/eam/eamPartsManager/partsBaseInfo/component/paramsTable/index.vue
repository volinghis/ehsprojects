<template>
  <div class="tableClass">
    <el-row>
      <el-col :span="24">
        <el-table :size="GlobalCss.controlSize"
                  border
                  ref="part_parameters"
                  :data="part_parameters.data"
                  style="width: 100%"
                  highlight-current-row>
          <el-table-column type="index"
                           align="center"></el-table-column>
          <el-table-column v-for="(item,index) in part_parameters.columns"
                           :label="item.label"
                           :prop="item.prop"
                           :width="item.width"
                           :key="index"
                           align="center">
            <template slot-scope="scope">
              <span v-if="scope.row.isSet">
                <el-input :size="GlobalCss.controlSize"
                          placeholder="请输入内容"
                          v-model="part_parameters.sel[item.prop]"></el-input>
              </span>
              <span v-else>{{scope.row[item.prop]}}</span>
            </template>
          </el-table-column>
          <template  v-show="buttonFlag">
          <el-table-column label="操作"
                           width="150"
                           align="center">
            <template slot-scope="scope">
              <template v-if="scope.row.isSet">
                <el-button :size="GlobalCss.controlSize"
                           type="success"
                           v-show="buttonFlag"
                           style="cursor: pointer;"
                           @click.stop="saveRow(scope.row,scope.$index)">保存</el-button>
              </template>
              <template v-else>
                <el-button :size="GlobalCss.controlSize"
                           type="primary"
                           v-show="buttonFlag"
                           style="cursor: pointer;"
                           @click.stop="editRow(scope.row,scope.$index)">编辑</el-button>
              </template>
              <el-button :size="GlobalCss.controlSize"
                         type="danger"
                         v-show="buttonFlag"
                         style="cursor: pointer;"
                         @click="deleteRow(scope.$index,part_parameters.data)">删除</el-button>
            </template>
          </el-table-column>
          </template>
        </el-table>
      </el-col>
      <el-col :span="24">
        <div class="el-table-add-row"
             v-show="buttonFlag"
             @click="add()"><span style="width: 100%;color:#409EFF">+ 新增参数</span></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>

<style lang="scss" scoped>
@import "./styles.scss";
</style>
