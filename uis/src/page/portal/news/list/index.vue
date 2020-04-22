<template>
  <div style="margin-left:10px;">
    <div>
      <el-table :data="newsData" style="width: 100%" >
        <el-table-column prop="newsTitle" label="标题" min-width="350">
          <template slot-scope="scope">
            <el-link type="primary" @click="handleView(scope.row)">{{scope.row.newsTitle}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="creationTime" label="发布日期" min-width="100" align="center"></el-table-column>
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
