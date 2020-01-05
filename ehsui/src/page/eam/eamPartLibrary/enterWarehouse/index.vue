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
                ref="multipleTable"
                class="tableHeight"
                @row-dblclick="handleClick"
                :max-height="htable"
                :size="GlobalCss.buttonSize">
        <el-table-column fixed
                         type="selection"
                         width="50"
                         align="center"></el-table-column>
        <el-table-column fixed
                         type="index"
                         width="50"
                         align="center"></el-table-column>
        <el-table-column prop="code"
                         label="入库编号"
                         width="150"
                         align="center"></el-table-column>
        <el-table-column prop="date"
                         label="入库日期"
                         width="120"
                         align="center"></el-table-column>
        <el-table-column prop="completion"
                         label="资料完整度"
                         width="160"
                         sortable
                         align="center">
          <template slot-scope="scope">
            <el-progress :percentage="scope.row.completion"
                         :color="customColors"></el-progress>
          </template>
        </el-table-column>
        <el-table-column prop="type"
                         label="入库类型"
                         width="100"
                         align="center"></el-table-column>
        <el-table-column prop="supplier"
                         label="供应商"
                         width="120"
                         align="center"></el-table-column>
        <el-table-column prop="warehouse"
                         label="所在仓库"
                         width="100"
                         align="center"></el-table-column>
        <el-table-column prop="wareHousePerson"
                         label="入库人"
                         width="90"
                         align="center"></el-table-column>
        <el-table-column prop="remark"
                         label="备注"
                         width="160"
                         align="center"></el-table-column>
        <el-table-column fixed="right"
                         label="操作"
                         width="150"
                         align="center">
          <template slot-scope="scope">
            <el-button @click="handleDelete(scope.row)"
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
