<template>
  <div>
    <div class="ehs_form_item_message">
      1)该列表显示展示所有设备信息。<br />2)在该页面可以进行查询和设备更新操作。
    </div>
    <div class="table-search-wrapper">
      <el-input placeholder="请输入设备名称或编号"
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
    <div class="operate">
      <el-button type="primary"
                 :size="GlobalCss.buttonSize"
                 @click="handleAdd"
                 icon="fa fa-plus pull-left">新增</el-button>
      <el-button type="success"
                 :size="GlobalCss.buttonSize"
                 @click="handleExport"
                 icon="fa fa-download pull-left">导出</el-button>
    </div>
    <div class="table-list">
      <el-table :data="tableData"
                style="width: 100%"
                border
                :size="GlobalCss.buttonSize">
        <el-table-column type="index"
                         align="center"
                         width="50"
                         fixed="left"></el-table-column>
        <!-- <el-table-column prop="deviceImg"
                               label="图片"
                               align="center"
                               width="60">
                <template slot-scope="scope">
                  <el-image class="table-td-deviceImg"
                            style="width: 36px; height: 36px"
                            :src="findUrl(scope.row.deviceImg,callback)">
                    <div slot="error"
                         class="image-slot">
                      <i class="fa fa-file-picture-o fa-3x"></i>
                    </div>
                  </el-image>
                </template>
              </el-table-column> -->
        <el-table-column prop="deviceNum"
                         align="center"
                         label="设备编号"
                         width="150"></el-table-column>
        <el-table-column prop="deviceName"
                         align="center"
                         label="设备名称"></el-table-column>
        <el-table-column prop="deviceModel"
                         align="center"
                         label="规格型号"></el-table-column>
        <el-table-column prop="completePoint"
                         label="资料完整度"
                         align="center"
                         width="150"
                         sortable>
          <template slot-scope="scope">
            <el-progress :percentage="scope.row.completePoint"
                         :color="customColorMethod"></el-progress>
          </template>
        </el-table-column>
        <el-table-column prop="factoryName"
                         label="生产厂家"
                         align="center"
                         :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="profession"
                         label="专业"
                         align="center"></el-table-column>
        <el-table-column prop="installLocation"
                         label="安装位置"
                         align="center"></el-table-column>
        <el-table-column prop="leaveDate"
                         label="出厂日期"
                         align="center"></el-table-column>
        <el-table-column prop="runDate"
                         label="启用日期"
                         align="center"></el-table-column>
        <el-table-column prop="person"
                         v-if="show"></el-table-column>
        <el-table-column prop="personName"
                         label="负责人"
                         align="center"></el-table-column>
        <el-table-column prop="deviceStatus"
                         label="设备状态"
                         align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.deviceStatus === '正常' ? 'success' : 'warning'"
                    disable-transitions
                    :size="GlobalCss.buttonSize">{{scope.row.deviceStatus}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column fixed="right"
                         align="center"
                         label="操作"
                         width="170">
          <template slot-scope="scope">
            <el-button type="warning"
                       @click="handleEditClick(scope.row)"
                       :size="GlobalCss.buttonSize">编辑</el-button>
            <el-button type="danger"
                       :size="GlobalCss.buttonSize"
                       @click="handleDelete(scope.row)">删除</el-button>
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
