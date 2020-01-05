<template>
  <div>
    <el-button @click="authRole"
               type="primary"
               :size="GlobalCss.controlSize"
               style="margin-left:15px;margin-bottom:5px;">授 权</el-button>
    <div class="refRoleTable"
         style="margin:0px 15px;">
      <el-table :data="roleTableData"
                ref="roleTableData"
                :size="GlobalCss.controlSize"
                border>
        <el-table-column prop="dataCode"
                         align="center"
                         label="角色编码">
        </el-table-column>
        <el-table-column prop="name"
                         align="center"
                         label="角色名称">
        </el-table-column>
        <el-table-column prop="remark"
                         align="center"
                         label="备注">
        </el-table-column>
        <el-table-column align="center"
                         label="操作">
          <template slot-scope="scope">
            <el-button @click="handleRomoveAuth(scope.row)"
                       type="warning"
                       :size="GlobalCss.buttonSize">移除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-drawer title="选择角色"
               :destroy-on-close="true"
               :append-to-body="true"
               :before-close="handleCloseDrawer"
               :visible.sync="innerDrawer">
      <div>
        <el-divider></el-divider>
        <!-- Form -->
        <div style="margin:0px 10px">
          <div class="search-wrapper"
               style="width:42%;">
            <el-form ref="searchForm"
                     :model="searchForm"
                     :inline="true">
              <el-input :size="GlobalCss.controlSize"
                        v-model="searchForm.query"
                        placeholder="请输入角色编码或名称"
                        style="margin:5px 0px;">
                <el-button slot="append"
                           @click="initRoleTable"
                           icon="el-icon-search"></el-button>
              </el-input>
            </el-form>
          </div>
          <div class="table-container">
            <el-table :data="tableData"
                      border
                      v-loading="loading"
                      @selection-change="handleSelectionChange"
                      :size="GlobalCss.controlSize">
              <el-table-column type="selection"
                               align="center"
                               width="45">
              </el-table-column>
              <el-table-column prop="name"
                               align="center"
                               width="90"
                               label="角色名称">
              </el-table-column>
              <el-table-column prop="dataCode"
                               align="center"
                               width="90"
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
            </el-table>
            <el-pagination @current-change="handleCurrentChange"
                           style="float:right;"
                           background
                           :current-page.sync="searchForm.page"
                           :page-size="searchForm.size"
                           layout="total, prev, pager, next"
                           :total="totalCount">
            </el-pagination>
          </div>
          <br><br>
          <el-divider></el-divider>
          <div slot="footer"
               class="dialog-footer">
            <el-button @click="innerDrawer = false"
                       :size="GlobalCss.buttonSize">取 消</el-button>
            <el-button type="primary"
                       @click="selectOver"
                       :size="GlobalCss.buttonSize">确 定</el-button>
          </div>
        </div>
      </div>
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
