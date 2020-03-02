<template>
  <div>
    <el-card shadow="hover">
      <el-row type="flex" justify="space-between">
        <el-col :span="8">
          <div class="table-search-wrapper">
            <el-input placeholder="请输入报废编号" size="small" v-model="queryParam.query">
              <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="operate">
            <el-button
              type="primary"
              :size="GlobalCss.buttonSize"
              icon="fa fa-plus pull-left"
              @click="handleScrap"
            >报废申请</el-button>
            <el-button
              type="danger"
              :size="GlobalCss.buttonSize"
              icon="fa fa-trash-o pull-left"
              @click="handleDelete"
            >批量删除</el-button>
          </div>
        </el-col>
      </el-row>

      <div class="table-list">
        <template>
          <el-table :data="tableData" style="width: 100%" border size="medium" @row-dblclick="handleViewClick" @select="onChange">
            <el-table-column type="selection" width="50"></el-table-column>
            <el-table-column prop="scrapNum" width="160" label="报废编号"></el-table-column>
            <el-table-column prop="applicationName" label="申请名称"></el-table-column>
            <el-table-column prop="applicationTime" label="申请时间"></el-table-column>
            <el-table-column prop="scrapDate" label="报废日期"></el-table-column>
            <el-table-column prop="applicant" label="申请人"></el-table-column>
            <el-table-column prop="scrapDept" label="报废部门"></el-table-column>
            <el-table-column prop="scrapReason" label="报废原因"></el-table-column>
            <el-table-column prop="status" label="状态" align="center">
              <template slot-scope="scope">
                <div slot="reference">
                 <el-tag :size="GlobalCss.buttonSize"
                          :type="scope.row.status === '已结束' ? 'danger' : 'success'">{{ scope.row.status}}</el-tag>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="currentStepPerson" label="审核人" align="center"></el-table-column>
          </el-table>
          <div class="pagination" style="float:right;margin-top:12px;">
            <el-pagination
              background
              layout="total, prev, pager, next"
              :current-page="queryParam.page"
              :page-size="queryParam.size"
              :total="totalCount"
              @current-change="handlePageChange"
            ></el-pagination>
          </div>
        </template>
      </div>
    </el-card>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
.operate {
  margin-bottom: 20px;
  float: right;
}
.el-card {
  border: 1px solid #ffffff;
}
// 搜索按钮样式
/deep/.el-input-group__append {
  background-color: #409eff;
  border-color: #409eff;
  color: #ffffff;
}
</style>
