<template>
  <div>
    <div class="topPanel">
      <div class="ehs_form_item_message">
        1)列表显示本人创建的，需要本人执行的，以及开放给所有人的计划。<br>2)非被人创建的的计划仅能查看。<br>3)对于有效期(当前时间小于等于结束时间)内的计划可以进行启动停止或延期操作。<br>4)立即执行功能可以快捷执行计划，如计划没有达到执行频率点，需要执行计划可采用此方法。<br>5)通常计划创建之后，我们不建议您再进行延期变动，如有此需求，建议重新建立计划。
      </div>
      <div class="queryBodys">
        <el-input placeholder="请输入计划名称" v-model="queryBean.query">
          <template slot="append">
            <el-button type="primary" :size="GlobalCss.buttonSize" icon="el-icon-search" @click="flushData()">
            </el-button>
          </template>
        </el-input>
        <el-checkbox v-model="queryBean.byowner" @change="flushData()" class="ehs-note-span">我创建的计划</el-checkbox>
        <el-checkbox v-model="queryBean.effective" @change="flushData()" class="ehs-note-span">处于有效期的计划</el-checkbox>
        <el-checkbox v-model="queryBean.enable" @change="flushData()" class="ehs-note-span">处于启用状态的计划</el-checkbox>
      </div>
      <div class="rightButtons">
        <el-button type="primary" :size="GlobalCss.buttonSize" @click="add()">新增</el-button>
      </div>
    </div>
    <el-table :data="plans" border :size="GlobalCss.buttonSize" :height="tableHeight" @sort-change="sortchange">
      <el-table-column type="index" align="center" width="50" fixed="left" label="序号"></el-table-column>
      <el-table-column align="center" prop="name" sortable="custom" label="计划名称">
        <template slot-scope="scope">
          <el-link type="primary">{{scope.row.name}}</el-link>
        </template>

      </el-table-column>
      <el-table-column align="center" prop="year" width="100" sortable="custom" label="计划年度"></el-table-column>
      <el-table-column align="center" prop="ownerName" width="160" sortable="custom" label="创建人"></el-table-column>
      <el-table-column align="center" prop="ownerCreationTime" width="160" sortable="custom" label="创建时间">
      </el-table-column>
      <el-table-column align="center" prop="startTime" width="160" sortable="custom" label="开始时间"></el-table-column>
      <el-table-column align="center" prop="endTime" width="160" sortable="custom" label="结束时间"></el-table-column>
      <el-table-column align="center" prop="enableLabel" width="100" sortable="custom" label="状态">
      </el-table-column>
      <el-table-column align="center" width="210" fixed="right" label="操作"><template slot-scope="scope">
          <el-button type="primary" :size="GlobalCss.buttonSize"  v-if="enableCheck(scope.row)">启用</el-button>
          <el-button type="primary" :size="GlobalCss.buttonSize"  v-if="!enableCheck(scope.row)">停止</el-button>
          <el-button type="info" :size="GlobalCss.buttonSize"  v-if="resetTimeCheck(scope.row)">延期</el-button>
          <el-button type="warning" :size="GlobalCss.buttonSize"  v-if="resetTimeCheck(scope.row)">执行</el-button>
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
