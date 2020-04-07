<template>
  <div class="role-table">
    <div class="search-wrapper">
      <div style="float:left;">
        <el-input :size="GlobalCss.controlSize" v-model="form.query" placeholder="请输入角色编码或名称">
          <el-button slot="append" icon="el-icon-search" @click="initTable"></el-button>
        </el-input>
      </div>
      <div class="operation" style="float:right">
        <el-button type="primary" icon="fa fa-plus pull-left" @click="handleRoleAdd" :size="GlobalCss.buttonSize">添加</el-button>
      </div>
    </div>
    <div class="table-container">
      <el-table :data="tableData" v-loading="loading" :size="GlobalCss.buttonSize" border style="width: 100%">
        <el-table-column type="index" show-overflow-tooltip min-width="50" align="center"></el-table-column>
        <el-table-column prop="name" show-overflow-tooltip min-width="100" align="center" label="角色姓名"></el-table-column>
        <el-table-column prop="dataCode" show-overflow-tooltip min-width="100" align="center" label="角色编码"></el-table-column>
        <el-table-column prop="remark" show-overflow-tooltip min-width="100" align="center" label="备注"></el-table-column>
        <el-table-column prop="creationTime" show-overflow-tooltip min-width="100" align="center" label="创建时间"></el-table-column>
        <!-- <el-table-column prop="updateTime" label="更新时间"></el-table-column> -->
        <el-table-column fixed="right" label="操作" show-overflow-tooltip min-width="120" align="center">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.row)" type="primary" :size="GlobalCss.buttonSize" >编辑</el-button>
            <el-button type="danger" @click="handleDelete(scope.row)" :size="GlobalCss.buttonSize">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination @current-change="handleCurrentChange" :current-page.sync="form.page" :page-size="form.size"
        layout="total, prev, pager, next" :total="totalCount">
      </el-pagination>
    </div>

    <!-- Form -->
    <el-dialog title="角色添加" width="40%" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
      <el-divider></el-divider>
      <el-form :model="roleForm" ref="roleForm" size="small" :rules="rules" label-width="100px">
        <el-form-item label="角色名称：" prop="name">
          <el-input v-model="roleForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色编码：" prop="dataCode">
          <el-input v-model="roleForm.dataCode"></el-input>
        </el-form-item>
        <el-form-item label="备注：" prop="remark">
          <el-input v-model="roleForm.remark" type="textarea" maxlength="100" show-word-limit></el-input>
        </el-form-item>
      </el-form>
      <el-divider></el-divider>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false" :size="GlobalCss.buttonSize">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :size="GlobalCss.buttonSize">确 定</el-button>
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
