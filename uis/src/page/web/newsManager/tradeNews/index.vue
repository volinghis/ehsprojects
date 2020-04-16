<template>
  <div style="margin-left:10px;">
    <div>
      <div style="float:left;">
        <el-input :size="GlobalCss.controlSize" v-model="queryParam.query" placeholder="请输入标题名称">
          <el-button slot="append" @click="initNewsData" icon="el-icon-search"></el-button>
        </el-input>
      </div>
      <div style="float:right;margin-bottom: 5px;">
        <el-button type="primary" :size="GlobalCss.controlSize" icon="fa fa-plus" @click="handleAdd"> 新增</el-button>
      </div>
    </div>
    <div>
      <el-table :data="newsData" style="width: 100%" size="mini" border>
        <el-table-column prop="newsTitle" label="标题" min-width="350" align="center">
          <template slot-scope="scope">
            <el-link type="primary" @click="handleView(scope.row)">{{scope.row.newsTitle}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="creationTime" label="发布日期" min-width="100" align="center"></el-table-column>
        <el-table-column label="操作" min-width="110" align="center">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)" type="primary" size="mini">编辑</el-button>
            <el-button @click="handleDelete(scope.row)" type="danger" size="mini">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination @current-change="handleCurrentChange" style="float:right;" :current-page.sync="queryParam.page"
      :page-size="queryParam.size" layout="total, prev, pager, next" :total="queryParam.totalCount">
    </el-pagination>
    </div>
  </div>
</template>
<script>
import datas from './datas.js'
export default datas

</script>

<style lang="scss" scoped>
  @import "./styles.scss";

</style>
