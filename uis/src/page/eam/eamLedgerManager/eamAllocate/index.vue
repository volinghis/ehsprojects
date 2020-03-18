<template>
  <div>
    <div class="ehs_form_item_message">
      1)该列表展示所有设备调拨信息。<br />2)可以根据调拨名称或调拨编号进行查询。<br />3)点击调拨编号可以查看详情。
    </div>
    <div class="table-search-wrapper">
      <el-input placeholder="请输入报废编号"
                size="small"
                v-model="queryParam.query">
        <el-button slot="append"
                   icon="el-icon-search"></el-button>
      </el-input>
    </div>
    <div class="operate">
      <el-button type="primary"
                 :size="GlobalCss.buttonSize"
                 icon="fa fa-plus pull-left"
                 @click="handleAllocate">调拨申请</el-button>
      <el-button type="danger"
                 :size="GlobalCss.buttonSize"
                 icon="fa fa-trash-o pull-left"
                 @click="handleDelete">批量删除</el-button>
    </div>
    <div class="table-list">
      <template>
        <el-table :data="tableData"
                  style="width: 100%"
                  border
                  @select="onChange"
                  size="medium">
          <el-table-column type="selection"
                           width="50"> </el-table-column>
          <el-table-column prop="allocateNum"
                           width="150"
                           align="center"
                           sortable
                           label="调拨编号">
            <template slot-scope="scope">
              <el-link type="primary"
                       @click="handleViewClick(scope.row)">{{ scope.row.allocateNum}}</el-link>
            </template></el-table-column>
          <el-table-column prop="applicationName"
                           sortable
                           align="center"
                           label="申请名称"></el-table-column>
          <el-table-column prop="applicationTime"
                           sortable
                           align="center"
                           label="申请时间"></el-table-column>
          <el-table-column prop="applicant"
                           sortable
                           align="center"
                           label="申请人"></el-table-column>
          <el-table-column prop="installLocation"
                           sortable
                           align="center"
                           label="调出位置"></el-table-column>
          <el-table-column prop="targetPositionName"
                           sortable
                           align="center"
                           label="调入位置"></el-table-column>
          <el-table-column prop="allocateReason"
                           sortable
                           align="center"
                           label="调拨原因"></el-table-column>
          <el-table-column prop="status"
                           sortable
                           align="center"
                           label="申请状态">
            <template slot-scope="scope">
              <div slot="reference">
                <el-tag :size="GlobalCss.buttonSize"
                        :type="scope.row.status === '已结束' ? 'danger' : 'success'">{{ scope.row.status }}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="currentStepPerson"
                           sortable
                           label="审核人"
                           align="center"></el-table-column>
          <el-table-column prop="allocateDate"
                           sortable
                           align="center"
                           label="审核日期"></el-table-column>
        </el-table>
        <div class="pagination"
             style="float:right;margin-top:12px;">
          <el-pagination background
                         layout="total, prev, pager, next"
                         :current-page="queryParam.page"
                         :page-size="queryParam.size"
                         :total="totalCount"
                         @current-change="handlePageChange"></el-pagination>
        </div>
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
