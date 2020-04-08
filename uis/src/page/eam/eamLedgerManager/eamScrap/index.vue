<template>
  <div>
    <!-- <el-card style="margin:0px 10px 10px 10px;"> -->
    <div class="queryBodys">
      <el-form ref="ruleForm"
               style="width:700px;"
               label-suffix="："
               label-position="left"
               size="mini"
               label-width="100px"
               :inline-message="true"
               :status-icon="true">
        <el-form-item label="查询">
          <el-input placeholder="请输入报废名称"
                    size="small"
                    style="width:60%;"
                    v-model="queryParam.query">
            <el-button slot="append"
                       icon="el-icon-search" @click="getScrapEamList"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item label="设备状态">
          <el-radio-group v-model="queryParam.status"
                          @change="getScrapEamList()">
            <el-radio border
                      label="ALL">全部</el-radio>
            <el-radio border
                      label="APPROVL">审批中</el-radio>
            <el-radio border
                      label="END">已结束</el-radio>
            <el-radio border
                      label="REJECT">已驳回</el-radio>
            <el-radio border
                      label="OVERDUE">已逾期</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </div>
    <div class="ehs_form_item_message">
      1)该列表展示所有设备报废信息。<br />2)可以根据报废名称进行模糊查询。<br />3)点击报废编号可以查看详情。<br />4)申请日期起超过7天未处理为逾期。
    </div>
    <div class="operate">
      <el-button type="primary"
                 :size="GlobalCss.buttonSize"
                 icon="fa fa-plus pull-left"
                 @click="handleScrap">报废申请</el-button>
      <el-button type="danger"
                 :size="GlobalCss.buttonSize"
                 icon="fa fa-trash-o pull-left"
                 @click="handleDelete">批量删除</el-button>
    </div>

    <div class="table-list">
      <template>
        <el-table :data="tableData"
                  border
                  :row-class-name="tableRowClassName"
                  size="medium"
                  @select="onChange">
          <el-table-column type="selection"
                           align="center"
                           width="50"></el-table-column>
          <el-table-column prop="scrapNum"
                           sortable
                           align="center"
                           width="150"
                           label="报废编号">
            <template slot-scope="scope">
              <el-link type="primary"
                       @click="handleViewClick(scope.row)">{{ scope.row.scrapNum}}</el-link>
            </template></el-table-column>
          <el-table-column prop="applicationName"
                           sortable
                           align="center"
                           label="申请名称"></el-table-column>
          <el-table-column prop="applicationTime"
                           align="center"
                           sortable
                           label="申请时间"></el-table-column>
          <el-table-column prop="applicant"
                           sortable
                           align="center"
                           label="申请人"></el-table-column>
          <el-table-column prop="scrapDept"
                           sortable
                           align="center"
                           label="报废部门"></el-table-column>
          <el-table-column prop="scrapReason"
                           sortable
                           align="center"
                           label="报废原因"></el-table-column>
          <el-table-column prop="status"
                           sortable
                           label="状态"
                           align="center">
            <template slot-scope="scope">
              <div slot="reference">
                <el-tag :size="GlobalCss.buttonSize"
                        :type="scope.row.status === '已结束' ? 'danger' : (scope.row.status==='已驳回'? 'warning' :'primary' )">{{ scope.row.status}}</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="currentStepPerson"
                           sortable
                           label="审核人"
                           align="center"></el-table-column>
          <el-table-column prop="scrapDate"
                           align="center"
                           sortable
                           label="审核日期"></el-table-column>
        </el-table>
        <div class="pagination"
             style="float:right;margin-top:12px;">
          <el-pagination layout="total, prev, pager, next"
                         :current-page="queryParam.page"
                         :page-size="queryParam.size"
                         :total="totalCount"
                         @current-change="handlePageChange"></el-pagination>
        </div>
      </template>
    </div>
    <!-- </el-card> -->
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
