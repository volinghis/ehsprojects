<template>
  <div class="role-table">
    <div class="search-wrapper">
      <div style="float:left;">
        <el-input :size="GlobalCss.controlSize"
                  style="margin:5px 0px;"
                  v-model="form.query"
                  placeholder="请输入角色编码或名称">
          <el-button slot="append"
                     icon="el-icon-search"
                     @click="initTable"></el-button>
        </el-input>
      </div>
      <div class="operation"
           style="float:right">
        <el-button type="primary"
                   icon="fa fa-plus pull-left"
                   @click="handleRoleAdd"
                   :size="GlobalCss.buttonSize">添加</el-button>
      </div>
    </div>
    <div class="table-container">
      <el-table :data="tableData"
                v-loading="loading"
                border
                style="width: 100%">
        <el-table-column type="index"
                         align="center"
                         width="50">
        </el-table-column>
        <el-table-column prop="name"
                         align="center"
                         label="角色姓名">
        </el-table-column>
        <el-table-column prop="dataCode"
                         align="center"
                         label="角色编码">
        </el-table-column>
        <el-table-column prop="remark"
                         align="center"
                         label="备注">
        </el-table-column>
        <el-table-column prop="creationTime"
                         align="center"
                         label="创建时间">
        </el-table-column>
        <!-- <el-table-column prop="updateTime"
                           label="更新时间">
          </el-table-column> -->
        <el-table-column fixed="right"
                         label="操作"
                         align="center"
                         width="180">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)"
                       type="primary"
                       :size="GlobalCss.buttonSize"
                       icon="fa fa-pencil pull-left">编辑</el-button>
            <el-button type="danger"
                       @click="handleDelete(scope.row)"
                       icon="fa fa-trash-o pull-left"
                       :size="GlobalCss.buttonSize">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination @current-change="handleCurrentChange"
                     :current-page.sync="form.page"
                     :page-size="form.size"
                     layout="total, prev, pager, next"
                     :total="totalCount">
      </el-pagination>
    </div>

    <!-- Form -->
    <el-dialog title="角色添加"
               width="30%"
               :visible.sync="dialogFormVisible">
      <el-form :model="roleForm"
               ref="roleForm"
               size="small"
               :rules="rules"
               label-width="80px">
        <el-form-item label="角色名称"
                      prop="name">
          <el-input v-model="roleForm.name"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色编码"
                      prop="dataCode">
          <el-input v-model="roleForm.dataCode"></el-input>
        </el-form-item>
        <el-form-item label="描述"
                      prop="remark">
          <el-input v-model="roleForm.remark"
                    type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialogFormVisible = false"
                   :size="GlobalCss.buttonSize">取 消</el-button>
        <el-button type="primary"
                   @click="onSubmit"
                   :size="GlobalCss.buttonSize">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="sass" scoped>
@import './styles.scss'
</style>
