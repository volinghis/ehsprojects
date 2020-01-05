<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'10px',background:'#fff'}"
       class="cardHeight">
    <div class="fromHeight"
         style="margin: 0px 0px;">
      <div style="width:25%;float:left;">
        <el-autocomplete class="inline-input"
                         :size="GlobalCss.controlSize"
                         style="width:100%;"
                         v-model="state"
                         :fetch-suggestions="querySearch"
                         placeholder="可搜索编号，名称，类型"
                         @select="handleSelect">
          <el-button slot="append"
                     icon="el-icon-search"
                     @click="() => (queryParam = {})">
          </el-button>
        </el-autocomplete>
      </div>
      <div class="operatorHeight"
           style="float:right;">
        <el-button type="primary"
                   icon="el-icon-plus"
                   class="buttonHeight"
                   :size="GlobalCss.controlSize"
                   @click="handleAdd()">新增</el-button>
        <el-button type="success"
                   icon="el-icon-download"
                   class="buttonHeight"
                   :size="GlobalCss.controlSize"
                   @click="exportExcel()">导出</el-button>
      </div>
    </div>
    <template>
      <el-table :data="tableData"
                resizable
                border
                highlight-current-row
                @row-dblclick="handleClick"
                :size="GlobalCss.buttonSize"
                ref="multipleTable"
                class="tableHeight"
                :max-height="htable">
        <el-table-column type="selection"
                         align="center"
                         width="50"></el-table-column>
        <el-table-column type="index"
                         align="center"
                         width="50"></el-table-column>
        <el-table-column prop="code"
                         align="center"
                         label="出库编号"
                         width="160"></el-table-column>
        <el-table-column prop="date"
                         align="center"
                         label="出库日期"></el-table-column>
        <el-table-column prop="type"
                         align="center"
                         label="出库类型"></el-table-column>
        <el-table-column prop="department"
                         align="center"
                         label="领用部门"></el-table-column>
        <el-table-column prop="person"
                         align="center"
                         label="领用人"></el-table-column>
        <el-table-column prop="warehouse"
                         align="center"
                         label="出库仓库"></el-table-column>
        <el-table-column prop="responsible"
                         align="center"
                         label="出库人"
                         width="100"></el-table-column>
        <el-table-column prop="status"
                         align="center"
                         label="申请状态">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status === '申请中' ? 'danger' : 'success'"
                    disable-transitions>{{scope.row.status}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="remark"
                         align="center"
                         label="备注"
                         width="150"></el-table-column>
        <el-table-column fixed="right"
                         align="center"
                         label="操作"
                         width="160">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)"
                       type="danger"
                       :size="GlobalCss.controlSize">删除</el-button>
            <el-button @click="handleEdit(scope.row)"
                       type="primary"
                       :size="GlobalCss.controlSize">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="text-align:right;">
        <el-pagination class="pageHeight"
                       background
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page.sync="currentPage"
                       :page-sizes="[10, 20, 50]"
                       :page-size="10"
                       layout="total, prev, pager, next, jumper"
                       :total="total">
        </el-pagination>
      </div>
    </template>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
