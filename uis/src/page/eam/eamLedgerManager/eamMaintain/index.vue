<template>
  <div>
    <span style="margin-left:10px;">查询：</span>
    <div class="table-search-wrapper">
      <el-input placeholder="根据设备名称查询"
                size="small"
                v-model="queryParam.name">
        <template slot="append">
          <el-button type="primary"
                     :size="GlobalCss.buttonSize"
                     icon="el-icon-search"
                     @click="initTable()">
          </el-button>
        </template>
      </el-input>
    </div>
    <div class="queryBodys">
      <el-form ref="ruleForm"
               style="width:700px;"
               label-suffix="："
               label-position="left"
               size="mini"
               label-width="100px"
               :inline-message="true"
               :status-icon="true"
               class="demo-ruleForm">
        <el-form-item label="投运时长">
          <el-radio-group v-model="queryParam.time"
                          @change="initTable()">
            <el-radio border
                      label="ALL">全部</el-radio>
            <el-radio border
                      label="Y">&lt; 1年</el-radio>
            <el-radio border
                      label="LTY">&lt; 3年</el-radio>
            <el-radio border
                      label="GTY">&gt;= 3年</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="设备位置">
          <el-select v-model="form.addresse"
                     clearable
                     size="small"
                     style="width:60%"
                     @clear="initTable"
                     @change="addressChange"
                     placeholder="请选择设备位置">
            <el-option v-for="item in deviceAddresses"
                       :key="item.key"
                       :label="item.text"
                       :value="item.key">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="checkScopeType"
                      label="系统/专业">
          <el-radio-group v-model="checkScopeType"
                          @change="checkScopeTypeChange">
            <el-radio label="BY_SYSTEM">按系统</el-radio>
            <el-radio label="BY_PROFESSIONA">按专业</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="checkScope">
          <el-radio-group v-model="checkScope"
                          @change="selectChange">
            <el-radio v-for="item in checkScopes"
                      :key="item.key"
                      :label="item.text"
                      :value="item.key"
                      border
                      size="mini">{{item.text}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </div>
    <div class="ehs_form_item_message">
      1)该列表显示展示所有设备信息。<br />2)在该页面可以进行查询和设备更新操作。<br />3)可以根据设备位置，系统和专业快捷查询。
    </div>
    <div class="operate">
      <el-button type="primary"
                 :size="GlobalCss.buttonSize"
                 @click="handleAdd"
                 icon="fa fa-plus pull-left">新增</el-button>
      <!-- <el-button type="success"
                 :size="GlobalCss.buttonSize"
                 @click="handleExport"
                 icon="fa fa-download pull-left">导出</el-button> -->
    </div>
    <div class="table-list">
      <el-table :data="tableData"
                style="width: 100%"
                border
                :height="tableHeight"
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
                         sortable
                         align="center"
                         label="设备编号"
                         width="150"></el-table-column>
        <el-table-column prop="deviceName"
                         sortable
                         align="center"
                         label="设备名称"></el-table-column>
        <el-table-column prop="deviceModel"
                         sortable
                         align="center"
                         label="规格型号"></el-table-column>
        <!-- <el-table-column prop="completePoint"
                         label="资料完整度"
                         align="center"
                         width="150"
                         sortable>
          <template slot-scope="scope">
            <el-progress :percentage="scope.row.completePoint"
                         :color="customColorMethod"></el-progress>
          </template>
        </el-table-column> -->
        <el-table-column prop="factoryName"
                         label="生产厂家"
                         sortable
                         align="center"
                         :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="installLocationName"
                         label="安装位置"
                         sortable
                         align="center"></el-table-column>
        <el-table-column prop="deviceSystem"
                         label="设备系统"
                         sortable
                         align="center"></el-table-column>
        <el-table-column prop="profession"
                         label="设备专业"
                         sortable
                         align="center"></el-table-column>
        <el-table-column prop="leaveDate"
                         label="出厂日期"
                         sortable
                         align="center"></el-table-column>
        <el-table-column prop="runDate"
                         label="启用日期"
                         sortable
                         align="center"></el-table-column>
        <el-table-column prop="person"
                         v-if="show"></el-table-column>
        <el-table-column prop="personName"
                         label="负责人"
                         sortable
                         align="center"></el-table-column>
        <!-- <el-table-column prop="deviceStatus"
                         label="设备状态"
                         sortable
                         align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.deviceStatus === '正常' ? 'success' : 'warning'"
                    disable-transitions
                    :size="GlobalCss.buttonSize">{{scope.row.deviceStatus}}</el-tag>
          </template>
        </el-table-column> -->
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
