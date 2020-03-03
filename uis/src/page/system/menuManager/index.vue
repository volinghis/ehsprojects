<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="6">
        <el-tree :data="treeData" ref="tree" :props="defaultProps" default-expand-all highlight-current @node-click="handleNodeClick"></el-tree>
      </el-col>
      <el-col :span="18">
        <div class="container">
          <div class="operation">
            <el-button type="primary" :size="GlobalCss.buttonSize" @click="handleAuthToMenu">授权</el-button>
          </div>
          <div class="refRoleTable">
            <template>
              <el-table :data="tableData" :size="GlobalCss.controlSize" border style="width: 100%">
                <el-table-column prop="name" align="center" label="角色名称"></el-table-column>
                <el-table-column prop="dataCode" align="center" label="角色编码"></el-table-column>
                <el-table-column align="center" label="操作">
                  <template slot-scope="scope">
                    <el-button @click="handleRomoveAuth(scope.row)" type="warning" :size="GlobalCss.buttonSize">移除</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </div>
          <!--授权弹框-->
          <el-dialog title="角色授权" destroy-on-close :visible.sync="dialogTableVisible">
            <el-divider></el-divider>
            <left-roles-table :currentMenuKey="currentMenuKey" :dialogTableVisible="dialogTableVisible" @handleSelect="handleSelect" />
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
  .operation {
    margin-bottom: 10px;
  }
/deep/.el-dialog__body{
    padding: 0px 20px;
    text-align: center;
}
/deep/.el-divider--horizontal{
    margin: 12px 0;
}
/deep/.el-dialog__footer{
    text-align: center;
}
</style>
