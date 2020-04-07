<template>
  <div>
    <el-row>
      <el-col :span="5" style="border-right:1px solid #DCDFE6;">
        <el-tree :data="treeData" ref="tree" :props="defaultProps" default-expand-all highlight-current
          @node-click="handleNodeClick"></el-tree>
      </el-col>
      <!-- <el-col :span="1">
        <el-divider direction="vertical"></el-divider>
      </el-col> -->
      <el-col :span="18" :push="1">
        <div class="container">
          <div class="operation">
            <el-button type="primary" :size="GlobalCss.buttonSize" @click="handleAuthToMenu">授权</el-button>
          </div>
          <div class="refRoleTable">
            <template>
              <el-table :data="tableData" :size="GlobalCss.controlSize" border style="width: 100%">
                <el-table-column prop="name" show-overflow-tooltip min-width="100" align="center" label="角色名称"></el-table-column>
                <el-table-column prop="dataCode" show-overflow-tooltip min-width="100" align="center" label="角色编码"></el-table-column>
                <el-table-column show-overflow-tooltip min-width="60" align="center" label="操作">
                  <template slot-scope="scope">
                    <el-button @click="handleRomoveAuth(scope.row)" type="warning" :size="GlobalCss.buttonSize">移除
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </div>
          <!--授权弹框-->
          <el-dialog title="角色授权" destroy-on-close :visible.sync="dialogTableVisible" :close-on-click-modal="false">
            <el-divider></el-divider>
            <left-roles-table :currentMenuKey="currentMenuKey" :dialogTableVisible="dialogTableVisible"
              @handleSelect="handleSelect" />
            <el-divider></el-divider>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogTableVisible = false" :size="GlobalCss.buttonSize">取 消</el-button>
              <el-button type="primary" @click="handleSubmit" :size="GlobalCss.buttonSize">确 定</el-button>
            </div>
          </el-dialog>
        </div>
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
