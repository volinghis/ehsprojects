<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'10px',background:'#fff'}"
       class="cardHeight">
    <div class="fromHeight"
         style="margin: 0px 0px;">
      <div style="float:left;">
        <el-form :model="form">
          <el-input :size="GlobalCss.controlSize"
                    v-model="form.query"
                    placeholder="请输入任务编码或者任务名称"
                    style="margin:5px 0px;">
            <el-button slot="append"
                       @click="search"
                       icon="el-icon-search"></el-button>
          </el-input>
        </el-form>
      </div>
      <div class="operatorHeight">
        <el-button type="primary"
                   icon="el-icon-plus"
                   class="buttonHeight"
                   :size="GlobalCss.buttonSize"
                   @click="handleAdd">新增</el-button>
      </div>
      <template>
        <el-table :data="tableData"
                  border
                  ref="multipleTable"
                  resizable
                  highlight-current-row
                  class="tableHeight"
                  :max-height="htable"
                  :size="GlobalCss.buttonSize">
          <el-table-column fixed="left"
                           type="selection"
                           width="50"
                           align="center"> </el-table-column>
          <el-table-column fixed="left"
                           type="index"
                           width="50"
                           align="center"> </el-table-column>
          <el-table-column fixed="left"
                           prop="inspTaskCode"
                           label="任务编号"
                           width="200"
                           align="center"> </el-table-column>
          <el-table-column prop="inspTaskName"
                           label="任务名称"
                           width="200"
                           align="center"> </el-table-column>
          <!-- <el-table-column  prop="completion" label="资料完整度" width="160" sortable>
            <template slot-scope="scope">
                <el-progress :percentage="scope.row.completion" :color="customColors" ></el-progress>
            </template>
        </el-table-column> -->
          <el-table-column prop="startDate"
                           label="开始时间"
                           width="240"
                           :show-overflow-tooltip="true"
                           align="center"> </el-table-column>
          <el-table-column prop="overDate"
                           label="结束时间"
                           width="240"
                           align="center"> </el-table-column>
          <el-table-column prop="responsibleDept"
                           label="责任部门"
                           width="150"
                           align="center"> </el-table-column>
          <el-table-column prop="responsiblePerson"
                           label="责任人"
                           width="120"
                           align="center"> </el-table-column>
          <!-- <el-table-column prop="amount"
                           label="设备数量"
                           width="100"
                           sortable
                           align="center"> </el-table-column> -->
          <el-table-column prop="status"
                           label="任务状态"
                           align="center">
            <template slot-scope="scope">
              <div slot="reference">
                <el-tag size="mini"
                        v-if="(scope.row.status === '负责人审核')"
                        type="primary">{{ scope.row.status}}</el-tag>
                <el-tag size="mini"
                        v-else-if="(scope.row.status  === '已完成')"
                        type="success">{{ scope.row.status}}</el-tag>
                <el-tag size="mini"
                        v-else-if="(scope.row.status  === '未执行')"
                        type="danger">{{ scope.row.status}}</el-tag>
                <el-tag size="mini"
                        v-else-if="(scope.row.status === '已作废')"
                        type="info">{{ scope.row.status}}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column fixed="right"
                           label="操作"
                           width="150"
                           align="center">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)"
                         type="info"
                         :size="GlobalCss.controlSize">作废</el-button>
              <el-button @click="handleEdit(scope.row)"
                         type="primary"
                         :size="GlobalCss.controlSize">延期</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination class="pageHeight"
        style="float:right;"
                       background
                       :current-page.sync="form.page"
                       :page-size="form.size"
                       layout="total, prev, pager, next"
                       :total="totalCount">
        </el-pagination>
      </template>
    </div>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
