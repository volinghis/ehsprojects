<template>
  <div>
    <el-row>
      <el-col :span="5" style="border-right:1px solid #DCDFE6;margin-right:15px;" :style="{height:(this.$store.state.contentHeight-10)+'px'}">
        <el-tree :props="props" :load="loadNode" lazy highlight-current @node-click="handleNodeClick"></el-tree>
      </el-col>
      <el-col :span="18">
        <div class="container">
          <div class="operation">
            <el-button type="primary" :size="GlobalCss.buttonSize" icon="fa fa-plus" @click="dictionaryAdd"> 新增</el-button>
          </div>
          <div class="refRoleTable">
            <template>
              <el-table :data="orgTableData" :size="GlobalCss.buttonSize" border>
                <el-table-column type="index" align="center" width="45"> </el-table-column>
                <el-table-column prop="dataCode" align="center" label="编码"></el-table-column>
                <el-table-column prop="text" align="center" label="名称"></el-table-column>
                <el-table-column prop="sort" align="center" label="排序" width="120"></el-table-column>
                <el-table-column align="center" width="200" label="操作">
                  <template slot-scope="scope">
                    <el-button type="primary" :size="GlobalCss.buttonSize" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button type="danger" :size="GlobalCss.buttonSize" @click="handleRemove(scope.row)">删除
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination @current-change="handleCurrentChange" style="text-align: right;" background
                :current-page.sync="form.page" :page-size="form.size" layout="total, prev, pager, next"
                :total="totalCount">
              </el-pagination>
            </template>
          </div>
          <!--添加组织-->
          <el-dialog title="添加部门" :visible.sync="dialogTableVisible" width="40%" :before-close="handleClose">
            <el-divider></el-divider>
            <div>
              <el-form label-position="right" label-width="80px" ref="formLabelAlign" :rules="rules" :model="formLabelAlign" :size="GlobalCss.buttonSize">
                <el-form-item label="编码:" prop="dataCode">
                  <el-input v-model="formLabelAlign.dataCode"></el-input>
                  <!-- <el-input v-model="formLabelAlign.dataCode" @blur.prevent="orgCodeValidation(formLabelAlign)"></el-input> -->
                </el-form-item>
                <el-form-item label="名称:" prop="text">
                  <el-input v-model="formLabelAlign.text"></el-input>
                </el-form-item>
                <el-form-item label="排序:" prop="sort">
                  <el-input v-model="formLabelAlign.sort"></el-input>
                </el-form-item>
              </el-form>
            </div>
            <el-divider></el-divider>
            <div slot="footer" class="dialog-footer">
              <el-button @click="handleReset" :size="GlobalCss.buttonSize">取 消</el-button>
              <el-button type="primary" @click="onSubmit" :size="GlobalCss.buttonSize">保 存</el-button>
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
