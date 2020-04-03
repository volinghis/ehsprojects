<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="4" :style="{height:(this.$store.state.contentHeight-60)+'px'}">
        <!-- <el-card style="margin-left:10px;"> -->
        <el-tabs v-model="queryBean.objectType" @tab-click="handleClick">
          <el-tab-pane label="按专业" name="BY_PROFESSIONA">
            <el-tree :data="professionTree" :props="defaultProps" @node-click="handleProfessionNodeClick"></el-tree>
          </el-tab-pane>
          <el-tab-pane label="按系统" name="BY_SYSTEM">
            <el-tree :data="systemTree" :props="defaultProps" @node-click="handleSystemNodeClick"></el-tree>
          </el-tab-pane>
        </el-tabs>
        <!-- </el-card> -->
      </el-col>
      <el-col :span="20">
        <!-- <el-card style="margin-right:10px;"> -->
        <div class="queryBodys">
          <el-form ref="queryForm" style="width:800px;" label-suffix="：" label-position="left" size="mini" label-width="100px" :inline-message="true" :status-icon="true" class="demo-queryForm">
            <el-form-item label="检修结论">
              <el-radio-group v-model="queryBean.result" @change="initTable()">
                <el-radio border label="ALL">全部</el-radio>
                <el-radio border label="OK">已解决</el-radio>
                <el-radio border label="ERROR">未解决</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="检修执行人">
              <el-radio-group v-model="queryBean.userType" @change="initTable()">
                <el-radio border label="ALL">全部</el-radio>
                <el-radio border label="OWNER">我检修的</el-radio>
                <el-radio border label="OUTER">外部人员</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-form>
        </div>
        <div class="ehs_form_item_message">
          1)列表显示所有的检修任务执行结果。<br>2)可以根据结果或执行人快速查找
        </div>
        <div class="table-list">
          <el-table :data="tableData" border :height="tableHeight" :size="GlobalCss.buttonSize">
            <el-table-column type="index" align="center" width="50" label="序号"></el-table-column>
            <el-table-column prop="objectKey" align="center" label="检修对象"></el-table-column>
            <el-table-column prop="deviceAddress" align="center" label="设备位置"></el-table-column>
            <el-table-column prop="question" align="center" label="问题描述"></el-table-column>
            <el-table-column prop="userName" align="center" label="检修执行人"></el-table-column>
            <el-table-column prop="result" align="center" width="140" label="检修结论">
              <template slot-scope="scope">
                <el-tag :type="scope.row.result === 'OK' ? 'success' : 'danger'" disable-transitions>{{ scope.row.result === 'OK' ? '已解决' : '未解决' }}</el-tag>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="tableFooter">
          <div class="pagination">
            <el-pagination background @current-change="changePage" :current-page="queryBean.page"
              :page-size="queryBean.size" layout="total, prev, pager, next, jumper" :total="total">
            </el-pagination>
          </div>
        </div>
        <!-- </el-card> -->
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
