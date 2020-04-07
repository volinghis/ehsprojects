<template>
  <div class="loginLog-table">
    <div class="search-wrapper">
      <div style="float:left;margin-bottom:5px;">
        <el-input :size="GlobalCss.controlSize" v-model="queryParams.query" placeholder="请输入账号或姓名">
          <el-button slot="append" icon="el-icon-search" @click="initTable"></el-button>
        </el-input>
      </div>
      <div class="operation" style="float:right">
        <el-button type="primary" icon="fa fa-trash-o pull-left" @click="handleDelete" :size="GlobalCss.buttonSize">批量删除
        </el-button>
      </div>
    </div>
    <div class="table-container">
      <el-table :data="tableData" v-loading="loading" :size="GlobalCss.buttonSize" border
        @selection-change="handleSelectionChange" style="width: 100%">
        <el-table-column type="selection" show-overflow-tooltip min-width="100" align="center" width="45"></el-table-column>
        <el-table-column prop="name" show-overflow-tooltip min-width="80" align="center" label="用户名"></el-table-column>
        <el-table-column prop="account" show-overflow-tooltip min-width="80" align="center" label="登录账号"></el-table-column>
        <el-table-column prop="ip" show-overflow-tooltip min-width="80" align="center" label="ip地址"></el-table-column>
        <el-table-column prop="browser" show-overflow-tooltip min-width="80" align="center" label="浏览器"></el-table-column>
        <el-table-column prop="system" show-overflow-tooltip min-width="80" align="center" label="操作系统"></el-table-column>
        <el-table-column prop="time" show-overflow-tooltip min-width="100" align="center" label="登陆时间"></el-table-column>
      </el-table>
      <el-pagination @current-change="handleCurrentChange" :current-page.sync="queryParams.page"
        :page-size="queryParams.size" layout="total, prev, pager, next" :total="totalCount">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import datas from './datas'
export default datas

</script>
<style lang="sass" scoped>
  @import './styles.scss'

</style>
