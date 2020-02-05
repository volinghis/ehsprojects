<template>
  <div>
    <el-card shadow="hover">
      <el-row>
        <el-col :md="24">
          <el-row type="flex"
                  justify="space-between">
            <el-col :span="8">
              <div class="table-search-wrapper">
                <el-autocomplete v-model="queryParam.query"
                                 :fetch-suggestions="querySearch"
                                 @select="handleSelect"
                                 size="small"
                                 style="margin-bottom:10px;width:100%;"
                                 placeholder="可查询设备名称设备编号">
                  <el-button slot="append"
                             type="primary"
                             @click="handleQuery"
                             icon="el-icon-search">搜索</el-button>
                </el-autocomplete>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="operate">
                <el-button type="success"
                           :size="GlobalCss.buttonSize"
                           @click="handleExport"
                           icon="fa fa-download pull-left">导出</el-button>
              </div>
            </el-col>
          </el-row>

          <div class="table-list">
            <template>
              <el-table :data="tableData"
                        style="width: 100%"
                        border
                        @row-dblclick="handldbClick"
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
                              style="width: 30px; height: 30px"
                              :src="scope.row.deviceImg"
                              :preview-src-list="[scope.row.deviceImg]"></el-image>
                  </template>
                </el-table-column>
                <el-table-column prop="deviceNum"
                                 align="center"
                                 label="设备编号"
                                 width="150"></el-table-column>
                <el-table-column prop="deviceName"
                                 align="center"
                                 label="设备名称"
                                 width="120"></el-table-column>
                <el-table-column prop="deviceModel"
                                 align="center"
                                 label="规格型号"
                                 width="120"></el-table-column>
                <el-table-column prop="completePoint"
                                 label="资料完整度"
                                 align="center"
                                 width="160"
                                 sortable>
                  <template slot-scope="scope">
                    <el-progress :percentage="scope.row.completePoint"
                                 :color="customColorMethod"></el-progress>
                  </template>
                </el-table-column>
                <el-table-column prop="factoryName"
                                 label="生产厂家"
                                 align="center"
                                 width="150"
                                 :show-overflow-tooltip="true"></el-table-column>
                <el-table-column prop="profession"
                                 label="专业"
                                 align="center"
                                 width="120"></el-table-column>
                <el-table-column prop="installLocation"
                                 label="安装位置"
                                 align="center"
                                 width="120"></el-table-column>
                <el-table-column prop="leaveDate"
                                 label="出厂日期"
                                 align="center"
                                 width="120"></el-table-column>
                <el-table-column prop="runDate"
                                 label="启用日期"
                                 align="center"
                                 width="120"></el-table-column>
                <el-table-column prop="purchaseTime"
                                 align="center"
                                 label="采购时间"
                                 width="100"></el-table-column>
                <!-- <el-table-column prop="warranty"
                                 label="保修期"
                                 align="center"
                                 width="100"></el-table-column>
                <el-table-column prop="repairFrequency"
                                 align="center"
                                 label="检修频率"
                                 width="100"></el-table-column> -->
                <el-table-column prop="buyingPrice"
                                 label="采购价格"
                                 align="center"
                                 width="100"></el-table-column>
                <el-table-column prop="person"
                                 label="负责人"
                                 align="center"
                                 width="100"></el-table-column>
                <el-table-column prop="deviceStatus"
                                 label="设备状态"
                                 align="center"
                                >
                  <template slot-scope="scope">
                    <el-tag :type="scope.row.deviceStatus === '正常' ? 'success' : 'warning'"
                            disable-transitions>{{scope.row.deviceStatus}}</el-tag>
                  </template>
                </el-table-column>
              </el-table>
              <div class="tableFooter">
                <div class="pagination"
                     style="text-align:right;margin-top:12px;">
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
    </el-card>
    <div class="refTabs">
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
    </div>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style  lang="scss" scoped>
@import "./styles.scss";
</style>
