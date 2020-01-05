<template>
  <div :style="{
      height: $store.state.contentHeight + 'px',
      padding: '10px',
      background: '#fff'
    }"
       class="cardHeight">
    <div class="fromHeight"
         style="margin: 0px 0px;">
      <div style="width:25%;float:left;">
        <el-autocomplete class="inline-input"
                         style="width:100%;"
                         v-model="state"
                         :size="GlobalCss.controlSize"
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
                ref="multipleTable"
                resizable
                border
                highlight-current-row
                class="tableHeight"
                @row-dblclick="view"
                :max-height="htable"
                :size="GlobalCss.buttonSize">
        <el-table-column fixed="left"
                         type="selection"
                         width="50"
                         align="center">
        </el-table-column>
        <el-table-column fixed="left"
                         type="index"
                         width="50"
                         align="center">
        </el-table-column>
        <el-table-column
                         prop="deviceCode"
                         label="备件编号"
                         width="110"
                         align="center">
        </el-table-column>
        <el-table-column prop="deviceImg"
                         label="图片"
                         width="70"
                         align="center">
          <template slot-scope="scope">
            <el-image style="width: 30px; height: 30px"
                      :src="scope.row.deviceImg"
                      :preview-src-list="[scope.row.deviceImg]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column prop="deviceName"
                         label="备件名称"
                         width="120"
                         align="center">
        </el-table-column>
        <el-table-column prop="completePoint"
                         label="资料完整度"
                         width="160"
                         sortable
                         align="center">
          <template slot-scope="scope">
            <el-progress :percentage="scope.row.completePoint"
                         :color="customColors"
                         align="center"></el-progress>
          </template>
        </el-table-column>
        <el-table-column prop="norm"
                         label="规格型号"
                         width="160"
                         align="center">
        </el-table-column>
        <el-table-column prop="ascriptionDevice"
                         label="归属设备"
                         width="120"
                         :show-overflow-tooltip="true"
                         align="center">
        </el-table-column>
        <el-table-column prop="materialCategory"
                         label="物资类别"
                         width="100"
                         align="center">
        </el-table-column>
        <el-table-column prop="amount"
                         label="数量"
                         width="80"
                         sortable
                         align="center">
        </el-table-column>
        <el-table-column prop="warningValue"
                         label="预警值"
                         width="80"
                         align="center">
        </el-table-column>
        <el-table-column fixed="right"
                         label="操作"
                         width="150"
                         align="center">
          <template slot-scope="scope">
            <el-button type="danger"
                       class="buttonHeight"
                       :size="GlobalCss.controlSize"
                       @click="handleDelete(scope.row, scope.$index)">删除</el-button>
            <el-button @click="handleEdit(scope.row)"
                       type="primary"
                       :size="GlobalCss.controlSize">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div>
        <div style="text-align:right;">
          <el-pagination class="pageHeight"
                         background
                         @size-change="handleSizeChange"
                         @current-change="handleCurrentChange"
                         :current-page.sync="currentPage"
                         :page-size="20"
                         layout="total, prev, pager, next, jumper"
                         :total="total">
          </el-pagination>
        </div>
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
