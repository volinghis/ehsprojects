<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="4" :style="{height:(this.$store.state.contentHeight-60)+'px'}">
        <!-- <el-card style="margin-left:10px;"> -->
        <el-tabs v-model="queryBean.objectType" @tab-click="handleClick">
          <el-tab-pane label="按专业" name="BY_PROFESSIONA">
            <el-tree :data="treeData" :props="defaultProps" :expand-on-click-node="false" @node-expand="handleExpand"
              @node-click="handleNodeClick">
              <span class="custom-tree-node" slot-scope="{ node, data}">
                <i :class="data.defect==='MAJOR'?'major-color':(data.defect==='NONE'?'other-color':'normal-color')"></i>
                <span style="margin-left:5px;">{{ node.label }}</span>
              </span>
            </el-tree>
          </el-tab-pane>
          <el-tab-pane label="按系统" name="BY_SYSTEM">
            <el-tree :data="treeData" :props="defaultProps" :expand-on-click-node="false" @node-expand="handleExpand"
              @node-click="handleNodeClick">
              <span class="custom-tree-node" slot-scope="{ node, data}">
                <i :class="data.defect==='MAJOR'?'major-color':(data.defect==='NONE'?'other-color':'normal-color')"></i>
                <span style="margin-left:5px;">{{ node.label }}</span>
              </span>
            </el-tree>
          </el-tab-pane>
        </el-tabs>
        <!-- </el-card> -->
      </el-col>
      <el-col :span="20">
        <!-- <el-card style="margin-right:10px;"> -->
        <div class="queryBodys">
          <el-form ref="queryForm" style="width:800px;" label-suffix="：" label-position="left" size="mini"
            label-width="100px" :inline-message="true" :status-icon="true" class="demo-queryForm">
            <el-form-item label="缺陷状态">
              <el-radio-group v-model="queryBean.status" @change="initTable()">
                <el-radio border label="ALL">全部</el-radio>
                <el-radio border label="OK">已处理</el-radio>
                <el-radio border label="ERROR">未处理</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="缺陷等级">
              <el-radio-group v-model="queryBean.level" @change="initTable()">
                <el-radio border label="ALL">全部</el-radio>
                <el-radio border label="NORMAL">一般缺陷</el-radio>
                <el-radio border label="MAJOR">重大缺陷</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-form>
        </div>
        <div class="ehs_form_item_message">
          1)列表显示所有的检修任务执行结果。<br>2)可以根据结果或执行人快速查找。<br>3)左侧树中&nbsp;<i class="major-color"/>&nbsp;重大缺陷，&nbsp;<i class="normal-color"/>&nbsp;一般缺陷，&nbsp;<i class="other-color"/>&nbsp;暂无缺陷。
        </div>
        <div class="table-list" :style="{height:(this.$store.state.contentHeight-240)+'px'}">
          <el-table :data="tableData" border  :size="GlobalCss.buttonSize">
            <el-table-column type="index" align="center" width="50" label="序号"></el-table-column>
            <el-table-column prop="objectKey" align="center" label="缺陷对象"></el-table-column>
            <el-table-column prop="deviceAddress" align="center" label="设备位置"></el-table-column>
            <el-table-column prop="question" align="center" label="缺陷描述"></el-table-column>
            <el-table-column prop="level" align="center" label="缺陷等级">
              <template slot-scope="scope">
                <span>{{scope.row.level==='NORMAL' ? '一般缺陷':'重大缺陷'}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="status" align="center" width="140" label="缺陷状态">
              <template slot-scope="scope">
                <el-tag :type="scope.row.status === 'OK' ? 'success' : 'danger'" disable-transitions>{{ scope.row.status === 'OK' ? '已解决' : '未解决' }}</el-tag>
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
