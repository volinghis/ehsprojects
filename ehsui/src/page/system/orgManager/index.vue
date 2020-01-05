<template>
  <div>
    <el-row>
      <el-col :span="6">
        <!-- <el-card shadow="hover"
                 :style="{height:(this.$store.state.contentHeight -30)+'px'}"> -->
                 <el-input placeholder="输入关键字进行查找"
                    :size="GlobalCss.buttonSize"
                    v-model="filterText">
                  </el-input>
          <el-tree :data="treeData"
                   node-key="id"
                   ref="tree"
                   :props="treeProps"
                   :default-expanded-keys="defaultExpandKeys"
                   @node-click="handleNodeClick"
                   :filter-node-method="filterNode"
                   highlight-current
                   :expand-on-click-node="false">
          </el-tree>
        <!-- </el-card> -->
      </el-col>
      <el-col :span="1"><el-divider direction="vertical"></el-divider></el-col>
      <el-col :span="17">
        <!-- <el-card shadow="hover"
                 :style="{height:(this.$store.state.contentHeight -30)+'px'}"> -->
          <div class="container">
            <div class="operation">
              <el-button type="primary"
                         :size="GlobalCss.buttonSize"
                         @click="orgAdd">新增</el-button>
            </div>
            <div class="refRoleTable">
              <template>
                <el-table :data="orgTableData"
                          :size="GlobalCss.buttonSize"
                          border>
                  <el-table-column prop="dataCode"
                                   align="center"
                                   label="部门编码">
                  </el-table-column>
                  <el-table-column prop="name"
                                   align="center"
                                   label="部门名称">
                  </el-table-column>
                  <!-- <el-table-column prop="name"
                                   align="center"
                                   label="业务权限">
                  </el-table-column> -->
                  <el-table-column align="center"
                                   width="240"
                                   label="操作">
                    <template slot-scope="scope">
                      <!-- <el-button type="warning"
                                 :size="GlobalCss.buttonSize"
                                 @click="handleAuthToOrg(scope.row)">授权</el-button> -->
                      <el-button type="primary"
                                 :size="GlobalCss.buttonSize"
                                 @click="handleEdit(scope.row)">编辑</el-button>
                      <el-button type="danger"
                                 :size="GlobalCss.buttonSize"
                                 @click="handleRemove(scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <el-pagination @current-change="handleCurrentChange"
                style="text-align: right;"
                     background
                     :current-page.sync="form.page"
                     :page-size="form.size"
                     layout="total, prev, pager, next"
                     :total="totalCount">
                </el-pagination>
              </template>
            </div>
            <!--添加组织-->
            <el-dialog title="添加部门" :visible.sync="dialogTableVisible" width="30%" :before-close="handleClose">
              <div>
                <el-form label-position="right" label-width="80px" ref="formLabelAlign" :rules="rules" :model="formLabelAlign" :size="GlobalCss.buttonSize">
                  <el-form-item label="部门编码:" prop="dataCode">
                    <el-input v-model="formLabelAlign.dataCode"></el-input>
                  </el-form-item>
                  <el-form-item label="部门名称:" prop="name">
                    <el-input v-model="formLabelAlign.name"></el-input>
                  </el-form-item>
                  <el-form-item label="排序:" prop="sort">
                    <el-input v-model="formLabelAlign.sort"></el-input>
                  </el-form-item>
                </el-form>
              </div>
              <div slot="footer" class="dialog-footer">
                <el-button @click="handleReset"
                           :size="GlobalCss.buttonSize">取 消</el-button>
                <el-button type="primary"
                           @click="onSubmit"
                           :size="GlobalCss.buttonSize">保 存</el-button>
              </div>
            </el-dialog>
          </div>
        <!-- </el-card> -->
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
