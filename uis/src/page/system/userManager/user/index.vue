<template>
  <div>
    <el-row>
      <el-col :span="5" style="border-right:1px solid #DCDFE6;margin-right:15px;" :style="{height:(this.$store.state.contentHeight-10)+'px'}">
        <!-- <el-input placeholder="输入关键字进行查找" :size="GlobalCss.buttonSize" style="width: 100%" v-model="filterText"></el-input> -->
        <!-- <el-tree :data="treeData" node-key="id" ref="tree" :props="treeProps" :default-expanded-keys="defaultExpandKeys"
          @node-click="handleNodeClick" :filter-node-method="filterNode" highlight-current :expand-on-click-node="false">
        </el-tree> -->
        <el-tree :props="props" :load="loadNode" lazy highlight-current @node-click="handleNodeClick"></el-tree>
      </el-col>
      <!-- <el-col :span="1" >
        <el-divider direction="vertical" ></el-divider>
      </el-col> -->
      <el-col :span="18">
        <userTable @getUserBySearch="getUserBySearch" @findUserByOrgKey="findUserByOrgKey"
          :organizationKey="organizationKey" :organizationName="organizationName"
          :organizationChildren="organizationChildren" :totals="totalCount" :userDatas="userTableData">
        </userTable>
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
