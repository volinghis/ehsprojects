<template>
  <div style="margin-left:10px;">
    <div>
      <div style="float:left;">
        <el-input :size="GlobalCss.controlSize" v-model="form.query" placeholder="请输入工号或者名字">
          <el-button slot="append" @click="searchUser" icon="el-icon-search"></el-button>
        </el-input>
      </div>
      <div style="float:right;margin-bottom: 5px;">
        <el-button type="primary" :size="GlobalCss.controlSize" icon="fa fa-exchange" @click="transferUser"> 调岗</el-button>
        <el-button type="primary" :size="GlobalCss.controlSize" icon="fa fa-plus" @click="addUser"> 新增</el-button>
      </div>
    </div>
    <el-table :data="tableData" ref="multipleTable" border :size="GlobalCss.controlSize" @row-dblclick="handleView" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40"></el-table-column>
      <!-- <el-table-column type="index" show-overflow-tooltip min-width="45" align="center"> </el-table-column> -->
      <el-table-column prop="dataCode" label="工号" show-overflow-tooltip min-width="60" align="center" > </el-table-column>
      <el-table-column prop="name" label="姓名" show-overflow-tooltip min-width="50" align="center"> </el-table-column>
      <!-- <el-table-column prop="positionName" label="职务" show-overflow-tooltip min-width="70" align="center"> </el-table-column> -->
      <el-table-column prop="gender" label="性别" show-overflow-tooltip min-width="40" align="center">
        <template slot-scope="scope">
          <el-tag size="small" v-if="scope.row.gender ==='男'">{{ scope.row.gender }}</el-tag>
          <el-tag size="small" v-else type="danger">{{ scope.row.gender }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="orgName" label="所属部门" show-overflow-tooltip min-width="60" align="center"> </el-table-column>
      <!-- <el-table-column prop="telephone" label="手机" show-overflow-tooltip min-width="90" align="center"> </el-table-column> -->
      <el-table-column prop="state" label="启用状态" show-overflow-tooltip min-width="60" align="center">
        <template slot-scope="scope">
          <el-tooltip :content="scope.row.state===0 ? '启用中':'停用中'" placement="left">
            <el-switch @change="changeState($event,scope.row,scope.$index)" v-model="scope.row.state"
              active-color="#13ce66" inactive-color="#ff4949" :active-value="0" :inactive-value="1">
            </el-switch>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="操作" show-overflow-tooltip min-width="160" align="center">
        <template slot-scope="scope">
          <el-button type="warning" :size="GlobalCss.controlSize" @click="authorizeUser(scope.row)" style="color:#E6A23C">授权</el-button>
          <el-button type="primary" :size="GlobalCss.controlSize" @click="editUser(scope.row)">编辑</el-button>
          <el-button type="danger" :size="GlobalCss.controlSize" @click="handleDelete(scope.row)" style="color:#F56C6C">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @current-change="handleCurrentChange" style="float:right;" :current-page.sync="form.page"
      :page-size="form.size" layout="total, prev, pager, next" :total="totalCount">
    </el-pagination>
    <el-dialog title="员工信息" :visible.sync="dialogVisible" :destroy-on-close="true" width="50%" :close-on-click-modal="false" :before-close="handleClose">
      <user-form ref="addUserForm" :userFlag="userFlag" :organName="organName" :organKey="organKey" :editUserForm="editUserForm"></user-form>
      <!-- <el-divider></el-divider> -->
      <span slot="footer" v-show="buttonShow" class="dialog-footer">
        <el-button @click="dialogVisible = false" :size="GlobalCss.controlSize">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :size="GlobalCss.controlSize" style="margin-left:15px;">提 交</el-button>
      </span>
    </el-dialog>
    <el-drawer title="用户授权" :destroy-on-close="true" :visible.sync="drawer" size="40%">
      <el-divider></el-divider>
      <userAuth @authResult="authResult" :user_key="userKey" :roleTable="roleTable"></userAuth>
    </el-drawer>
    <el-dialog title="调岗" :destroy-on-close="true" :visible.sync="drawerTransfer" :close-on-click-modal="false" width="30%">
      <el-divider></el-divider>
       <el-tree node-key="id" :props="props" :load="loadNode" lazy highlight-current accordion @node-click="nodeClick"></el-tree>
       <el-divider></el-divider>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeTransfer" :size="GlobalCss.controlSize">取 消</el-button>
        <el-button type="primary" @click="saveTransfer" :size="GlobalCss.controlSize">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import datas from './datas.js'
export default datas

</script>

<style lang="scss" scoped>
  @import "./styles.scss";

</style>
