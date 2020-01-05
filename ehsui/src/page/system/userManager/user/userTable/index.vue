<template>
  <div>
    <div>
      <div style="float:left;">
        <el-form :model="form">
          <el-input :size="GlobalCss.controlSize"
                    v-model="form.query"
                    placeholder="请输入工号或者名字"
                    style="margin:5px 0px;">
            <el-button slot="append"
                       @click="searchUser"
                       icon="el-icon-search"></el-button>
          </el-input>
        </el-form>
      </div>
      <div style="float:right">
        <el-button type="primary"
                   :size="GlobalCss.controlSize"
                   icon="fa fa-plus"
                   @click="addUser">新 增</el-button>
      </div>
    </div>
    <el-table :data="tableData"
              border
              :size="GlobalCss.controlSize"
              @row-dblclick="handleInfo"
              style="width: 100%">
      <el-table-column type="index"
                       align="center"
                       width="50">
      </el-table-column>
      <el-table-column prop="dataCode"
                       label="工号"
                       align="center"
                       width="100">
      </el-table-column>
      <el-table-column prop="name"
                       label="姓名"
                       align="center"
                       width="100">
      </el-table-column>
      <el-table-column prop="position"
                       label="职务"
                       align="center"
                       width="120">
      </el-table-column>
      <el-table-column prop="gender"
                       label="性别"
                       align="center"
                       width="90">
        <template slot-scope="scope">
          <div slot="reference">
            <el-tag size="small"
                    v-if="scope.row.gender ==='男'">{{ scope.row.gender }}</el-tag>
            <el-tag size="small"
                    v-else
                    type="danger">{{ scope.row.gender }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="orgName"
                       label="所属部门"
                       align="center"
                       width="120">
      </el-table-column>
      <el-table-column prop="telephone"
                       label="手机"
                       align="center"
                       width="140">
      </el-table-column>
      <el-table-column prop="position"
                       label="职务"
                       align="center"
                       width="124">
      </el-table-column>
      <el-table-column prop="state"
                       label="启用状态"
                       align="center"
                       width="100">
        <template slot-scope="scope">
          <el-tooltip :content="scope.row.state===0 ? '启用中':'停用中'"
                      placement="left">
            <el-switch @change="changeState($event,scope.row,scope.$index)"
                       v-model="scope.row.state"
                       active-color="#13ce66"
                       inactive-color="#ff4949"
                       :active-value="0"
                       :inactive-value="1">
            </el-switch>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       align="center"
                       width="240">
        <template slot-scope="scope">
          <el-button type="warning"
                     :size="GlobalCss.controlSize"
                     @click="authorizeUser(scope.row)"
                     style="color:#E6A23C">授权</el-button>
          <el-button type="primary"
                     :size="GlobalCss.controlSize"
                     @click="editUser(scope.row)">编辑</el-button>
          <el-button type="danger"
                     :size="GlobalCss.controlSize"
                     @click="handleDelete(scope.row)"
                     style="color:#F56C6C">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @current-change="handleCurrentChange"
                   style="float:right;"
                   background
                   :current-page.sync="form.page"
                   :page-size="form.size"
                   layout="total, prev, pager, next"
                   :total="totalCount">
    </el-pagination>
    <el-dialog title="员工信息"
               :visible.sync="dialogVisible"
               :destroy-on-close="true"
               width="40%">
      <user-form ref="addUserForm"
                 class="userForm"
                 :organName="organName"
                 :organKey="organKey"
                 :editUserForm="editUserForm"></user-form>
      <el-divider></el-divider>
      <span slot="footer"
            v-show="userInfo"
            class="dialog-footer">
        <el-button @click="dialogVisible = false"
                   :size="GlobalCss.controlSize">取 消</el-button>
        <el-button type="primary"
                   @click="onSubmit"
                   :size="GlobalCss.controlSize"
                   style="margin-left:15px;">提 交</el-button>
      </span>
    </el-dialog>
    <el-drawer title="用户授权"
               :destroy-on-close="true"
               :visible.sync="drawer"
               size="40%">
      <el-divider></el-divider>
      <userAuth @authResult="authResult"
                :user_key="userKey"
                :roleTable="roleTable"></userAuth>
    </el-drawer>
  </div>
</template>
<script>
import datas from './datas.js'
export default datas
</script>

<style lang="scss" scoped>
@import "./styles.scss";
</style>
