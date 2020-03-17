<template>
  <div>
    <div class="topPanel">
      <div class="ehs_form_item_message">
        1)列表显示本人需要执行的和审批过的。<br>2)支持手动添加工作，如发现缺陷或处理检修等可以进行添加。<br>3)工作情况一旦提交，除非被驳回否则不允许修改。
      </div>
     <div class="queryBodys" >
        <el-input placeholder="请输入任务名称或者计划名称" v-model="queryBean.query">
          <template slot="append">
            <el-button type="primary" :size="GlobalCss.buttonSize" icon="el-icon-search" @click="flushData()">
            </el-button>
          </template>
        </el-input>

      </div>
      <div class="rightButtons">
        <el-button type="primary" :size="GlobalCss.buttonSize" @click="add()">添加工作</el-button>
      </div>
    </div>
    <el-table :data="plans" border :size="GlobalCss.buttonSize" :height="tableHeight" @sort-change="sortchange">
      <el-table-column type="index" align="center" width="50" fixed="left" label="序号"></el-table-column>
      <el-table-column align="center" prop="name" sortable="custom" label="任务名称">
        <template slot-scope="scope">
          <el-link type="primary">{{scope.row.name}}</el-link>
        </template>

      </el-table-column>
      <el-table-column align="center" prop="year" width="200" sortable="custom" label="计划名称"></el-table-column>
      <el-table-column align="center" prop="ownerName" width="120" sortable="custom" label="计划创建人"></el-table-column>
      <el-table-column align="center" prop="ownerCreationTime" width="160" sortable="custom" label="计划创建时间">
      </el-table-column>
      <el-table-column align="center" prop="startTime" width="100" sortable="custom" label="审批状态"></el-table-column>
      <el-table-column align="center" prop="endTime" width="160" sortable="custom" label="提交人"></el-table-column>
      <el-table-column align="center" prop="enableLabel" width="160" sortable="custom" label="任务创建时间">
      </el-table-column>
      <el-table-column align="center" width="60" fixed="right" label="操作">
      </el-table-column>
    </el-table>
    <div class="bottomPanel">
      <el-pagination style="float:right" :current-page="queryBean.page" @current-change="changePage"
        :page-size="queryBean.size" layout="total, prev, pager, next" :total="queryBean.totalCount">
      </el-pagination>
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
