<template>
  <div>
    <el-row>
      <el-col :span="5">
        <el-tree :props="props" ref="tree" node-key="id" :load="loadNode" lazy highlight-current @node-click="handleNodeClick"></el-tree>
      </el-col>
      <el-col :span="18" :push="1" style="border-left:1px solid #DCDFE6;">
        <div class="container">
          <div class="operation">
            <el-button type="primary" :size="GlobalCss.buttonSize" icon="fa fa-plus" @click="dictionaryAdd"> 新增</el-button>
          </div>
          <div class="refRoleTable" style="margin-left:10px;">
            <template>
              <el-table :data="orgTableData" :size="GlobalCss.buttonSize" border>
                <el-table-column type="index" show-overflow-tooltip min-width="45" align="center"> </el-table-column>
                <el-table-column prop="dataCode" show-overflow-tooltip min-width="100" align="center" label="编码"></el-table-column>
                <el-table-column prop="text" show-overflow-tooltip min-width="100" align="center" label="名称"></el-table-column>
                <el-table-column prop="sort" show-overflow-tooltip min-width="80" sortable align="center" label="排序" width="120"></el-table-column>
                <el-table-column show-overflow-tooltip min-width="120" align="center" label="操作">
                  <template slot-scope="scope">
                    <el-button type="primary" :size="GlobalCss.buttonSize" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button type="danger" :size="GlobalCss.buttonSize" @click="handleRemove(scope.row)">删除
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination @current-change="handleCurrentChange" style="text-align: right;"
                :current-page.sync="form.page" :page-size="form.size" layout="total, prev, pager, next"
                :total="totalCount">
              </el-pagination>
            </template>
          </div>
          <!--添加组织-->
          <el-dialog title="编辑页面" :visible.sync="dialogTableVisible" :close-on-click-modal="false" width="40%" :before-close="handleClose">
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
