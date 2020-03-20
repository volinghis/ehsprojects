<template>
  <div>
    <div class="topPanel">

      <div class="queryBodys">
        <el-form ref="ruleForm" style="width:700px;" label-suffix="：" label-position="left" size="mini" label-width="80px" :inline-message="true" :status-icon="true"
          class="demo-ruleForm">
          <el-form-item label="任务时间">
            <el-radio-group v-model="queryBean.times" @change="flushData()">
               <el-radio border label="ALL">全部</el-radio>
              <el-radio border label="TODAY">今天</el-radio>
              <el-radio border label="THREE">最近三天</el-radio>
              <el-radio border label="SEVEN">最近七天</el-radio>
            </el-radio-group>
          </el-form-item>
            <el-form-item label="任务分类">
            <el-radio-group v-model="queryBean.owners" @change="flushData()">
               <el-radio border label="ALL">全部</el-radio>
              <el-radio border label="EXECUTE">我执行的任务</el-radio>
              <el-radio border label="APPROVE">我审批的任务</el-radio>
            </el-radio-group>
          </el-form-item>
           <el-form-item label="检修记录">
            <el-radio-group v-model="queryBean.checks" @change="flushData()">
               <el-radio border label="ALL">全部</el-radio>
              <el-radio border label="INCLUDE">有</el-radio>
              <el-radio border label="EXCLUDE">无</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="缺陷申报" >
            <el-radio-group v-model="queryBean.defects" @change="flushData()">
               <el-radio border label="ALL">全部</el-radio>
             <el-radio border label="INCLUDE">有</el-radio>
              <el-radio border label="EXCLUDE">无</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="备件使用" >
            <el-radio-group v-model="queryBean.revers" @change="flushData()">
               <el-radio border label="ALL">全部</el-radio>
             <el-radio border label="INCLUDE">有</el-radio>
              <el-radio border label="EXCLUDE">无</el-radio>
            </el-radio-group>
          </el-form-item>
           <el-form-item label="审批状态" >
            <el-radio-group v-model="queryBean.flowstatus" @change="flushData()">
               <el-radio border label="ALL">全部</el-radio>
               <el-radio border label="DRAFT">未开始</el-radio>
              <el-radio border label="APPROVE">审批中</el-radio>
               <el-radio border label="END">已结束</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
      </div>
      <div class="ehs_form_item_message">
        1)列表显示本人需要执行的和审批过的。<br>2)支持手动添加工作，如发现缺陷或处理检修等可以进行添加。<br>3)工作情况一旦提交，除非被驳回否则不允许修改。
      </div>
      <div class="rightButtons">
        <el-button type="primary" :size="GlobalCss.buttonSize" @click="add()">添加工作</el-button>
      </div>
    </div>
    <el-table :data="tasks" border :size="GlobalCss.buttonSize" :height="tableHeight" @sort-change="sortchange">
      <el-table-column type="index" align="center" width="50" fixed="left" label="序号"></el-table-column>
      <el-table-column align="center" prop="name" sortable="custom" label="任务名称">
        <template slot-scope="scope">
          <el-link type="primary" v-if="viewEnable(scope.row)">{{scope.row.name}}</el-link>
          <span v-if="!viewEnable(scope.row)">{{scope.row.name}}</span>
        </template>

      </el-table-column>
      <el-table-column align="center" prop="eamCheckPlan.name" width="200" sortable="custom" label="计划名称">
      </el-table-column>
      <el-table-column align="center" prop="eamCheckPlan.ownerName" width="120" sortable="custom" label="计划创建人">
      </el-table-column>
      <el-table-column align="center" prop="eamCheckPlan.ownerCreationTime" width="160" sortable="custom"
        label="计划创建时间">
      </el-table-column>
      <el-table-column align="center" prop="flowProcessInfo.flowCurrentStep" width="100" sortable="custom" label="审批状态">
         <template slot-scope="scope">
          <span>{{transFlow(scope.row)}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="userName" width="160" sortable="custom" label="提交人"></el-table-column>
      <el-table-column align="center" prop="ownerCreationTime" width="160" sortable="custom" label="任务创建时间">
      </el-table-column>
      <el-table-column align="center" width="80" fixed="right" label="操作">
         <template slot-scope="scope">
             <el-button type="primary"  @click="execute(scope.row)" size="mini" title="执行" v-if="executeEnable(scope.row)" icon="el-icon-edit" circle></el-button>
             <el-button type="primary"  @click="approve(scope.row)" size="mini" title="处理" v-if="approveEnable(scope.row)" icon="el-icon-check" circle></el-button>
        </template>
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
