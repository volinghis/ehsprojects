<template>
  <div>
    <el-row>
      <el-col :span="5"
              style="border-right:1px solid #DCDFE6;"
              :style="{height:(this.$store.state.contentHeight-10)+'px'}">
        <el-tabs v-model="activeName"
                 @tab-click="handleClick">
          <el-tab-pane label="按专业"
                       name="first">
            <el-tree :data="dataFirst"
                     :props="defaultProps"
                     @node-click="handleFirstNodeClick"></el-tree>
          </el-tab-pane>
          <el-tab-pane label="按系统"
                       name="second">
            <el-tree :data="dataSecond"
                     :props="defaultProps"
                     @node-click="handleSecondNodeClick"></el-tree>
          </el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :span="19">
        <div class="ehs_form_item_message">
          1)该列表显示展示所有设备信息。<br />2)在该页面只能进行查询和查看设备详情操作。
        </div>
        <div class="table-search-wrapper">
          <el-input placeholder="根据设备名称查询"
                    v-model="queryParam.query">
            <template slot="append">
              <el-button type="primary"
                         :size="GlobalCss.buttonSize"
                         icon="el-icon-search"
                         @click="initTable()">
              </el-button>
            </template>
          </el-input>
        </div>
        <!-- <div class="operate">
      <el-button type="success"
                 :size="GlobalCss.buttonSize"
                 @click="handleExport"
                 icon="fa fa-download pull-left">导出</el-button>
    </div> -->
        <div class="table-list">
          <template>
            <el-table :data="tableData"
                      style="width: 100%"
                      border
                      highlight-current-row
                      @current-change="handleCurrentChange"
                      :size="GlobalCss.buttonSize">
              <el-table-column type="index"
                               align="center"
                               width="50"
                               fixed="left"></el-table-column>
              <el-table-column prop="deviceImg"
                               label="图片"
                               align="center"
                               width="60">
                <template slot-scope="scope">
                  <el-image class="table-td-deviceImg"
                            style="width: 36px; height: 36px"
                            :src="scope.row.deviceImg===''?'':GlobalVars.globalServiceServlet + '/data/file/viewFile?fileId=' + scope.row.deviceImg + '&resoureMenuKey=' + $store.state.resourceMenuKey">
                    <div slot="error"
                         class="image-slot">
                      <i class="fa fa-file-picture-o fa-3x"></i>
                    </div>
                  </el-image>
                </template>
              </el-table-column>
              <el-table-column prop="deviceNum"
                               align="center"
                               label="设备编号"
                               sortable
                               width="150">
                <template slot-scope="scope">
                  <el-link type="primary"
                           @click="handleLink(scope.row)">{{scope.row.deviceNum}}</el-link>
                </template></el-table-column>
              <el-table-column prop="deviceName"
                               align="center"
                               sortable
                               label="设备名称"></el-table-column>
              <el-table-column prop="deviceModel"
                               align="center"
                               sortable
                               label="规格型号"></el-table-column>
              <el-table-column prop="completePoint"
                               label="资料完整度"
                               align="center"
                               sortable>
                <template slot-scope="scope">
                  <el-progress :percentage="scope.row.completePoint"
                               :color="customColorMethod"></el-progress>
                </template>
              </el-table-column>
              <el-table-column prop="factoryName"
                               label="生产厂家"
                               align="center"
                               sortable
                               :show-overflow-tooltip="true"></el-table-column>
              <el-table-column prop="installLocationName"
                               sortable
                               label="安装位置"
                               align="center"></el-table-column>
              <template v-if="activeName==='second'">
                <el-table-column prop="deviceSystem"
                                 label="设备系统"
                                 sortable
                                 align="center"></el-table-column>
              </template>
              <template v-if="activeName==='first'">
                <el-table-column prop="profession"
                                 label="设备专业"
                                 sortable
                                 align="center"></el-table-column>
              </template>
              <el-table-column prop="leaveDate"
                               sortable
                               label="出厂日期"
                               align="center"></el-table-column>
              <el-table-column prop="runDate"
                               label="启用日期"
                               sortable
                               align="center"></el-table-column>
              <el-table-column prop="personName"
                               sortable
                               label="负责人"
                               align="center"></el-table-column>
              <el-table-column prop="deviceStatus"
                               sortable
                               label="设备状态"
                               align="center">
                <template slot-scope="scope">
                  <el-tag :type="scope.row.deviceStatus === '正常' ? 'success' : 'warning'"
                          disable-transitions>{{ scope.row.deviceStatus }}</el-tag>
                </template>
              </el-table-column>
            </el-table>
            <div class="tableFooter">
              <div class="pagination">
                <el-pagination background
                               @current-change="changePage"
                               :current-page="queryParam.page"
                               :page-size="queryParam.size"
                               layout="total, prev, pager, next, jumper"
                               :total="total">
                </el-pagination>
              </div>
            </div>
          </template>
        </div>
      </el-col>
    </el-row>

    <!-- <div class="refTabs">
      <el-tabs v-model="activeName"
               type="border-card"
               @tab-click="handleClick">
        <el-tab-pane label="检修记录"
                     name="first">
          <repair-record :tableId="tableId"></repair-record>
        </el-tab-pane>
        <el-tab-pane label="关联备件"
                     name="second">关联备件</el-tab-pane>
      </el-tabs>
    </div> -->
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
