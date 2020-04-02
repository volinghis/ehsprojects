<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="5" :style="{height:(this.$store.state.contentHeight-65)+'px'}">
        <el-card style="margin-left:10px;">
        <el-tree :props="props" :load="loadNode" lazy highlight-current @node-click="handleNodeClick"></el-tree>
        </el-card>
      </el-col>
      <!-- <el-col :span="18" :push="1"> -->
      <el-col :span="19">
        <el-card style="margin-right:10px;">
        <userTable @getUserBySearch="getUserBySearch" @findUserByOrgKey="findUserByOrgKey"
          :organizationKey="organizationKey" :organizationName="organizationName"
          :totals="totalCount" :userDatas="userTableData">
        </userTable>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import datas from './datas.js'
export default datas
</script>

<style lang="scss" scoped>
  @import "./styles.scss";
</style>
