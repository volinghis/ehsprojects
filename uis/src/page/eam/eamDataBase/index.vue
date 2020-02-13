<template>
  <div>
    <el-row>
      <el-col class="searchCol">
        <el-card>
          <el-form label-position="right"
                   :inline="true"
                   label-width="80px">
            <el-form-item label="资料类别:">
              <el-select v-model="queryParam.useStatus"
                         size="small"
                         style="width:100%;"
                         placeholder="请选择">
                <el-option value="0"
                           label="说明书"></el-option>
                <el-option value="1"
                           label="质保卡"></el-option>
                <el-option value="2"
                           label="操作手册"></el-option>
                <el-option value="3"
                           label="维修经验"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="文件名称:">
              <el-input v-model="queryParam.fileName"
                        size="small" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary"
                         :size="GlobalCss.buttonSize"
                         @click="$refs.table.refresh(true)"
                         icon="el-icon-search">查询</el-button>
              <el-button :size="GlobalCss.buttonSize"
                         @click="() => (queryParam = {})">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
    <el-row >
      <el-col :span="6">
        <el-card shadow="hover"
                 :style="{height:mainHeight+'px'}">
          <div slot="header"
               class="clearfix">
            <label>设备结构树</label>
          </div>
          <el-tree :data="treeData"
                   node-key="id"
                    ref="tree"
                   :default-expanded-keys="defaultExpandKeys"
                   :props="defaultProps"
                   @node-click="handleNodeClick">
            <span class="custom-tree-node"
                  slot-scope="{ node, data }">
              <i :class="data.className"></i>
              <span style="margin-left:5px;">{{ node.label }}</span>
            </span></el-tree>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card shadow="hover"
                 :style="{height:mainHeight+'px'}">
          <div slot="header"
               class="clearfix">
            <label>文件列表</label>
          </div>
          <div class="operate">
            <el-button type="primary"
                       :size="GlobalCss.buttonSize"
                       @click="dialogFormVisible=true"
                       icon="el-icon-plus">添加</el-button>
          </div>
          <div class="table-list">
            <el-table :data="tableData"
                      size="medium">
              <el-table-column type="index"
                               width="50"></el-table-column>
              <el-table-column prop="type"
                               label="类型"
                               width="80">
                <template slot-scope="scope">
                  <el-image class="table-td-deviceImg"
                            style="width: 30px; height: 30px"
                            :src="scope.row.deviceImg"
                            :preview-src-list="[scope.row.deviceImg]"></el-image>
                </template>
                <!-- <i class="fa fa-file-excel-o fa-3x" style="color:#1cd4d4"></i> -->
              </el-table-column>
              <el-table-column prop="fileName"
                               label="文件名称"></el-table-column>

              <el-table-column prop="refDevice"
                               label="关联设备"></el-table-column>
              <el-table-column prop="category"
                               label="资料类别">
              </el-table-column>
              <el-table-column prop="person"
                               label="上传人"></el-table-column>
              <el-table-column prop="uploadTime"
                               sortable
                               label="上传时间"></el-table-column>
              <el-table-column fixed="right"
                               align="center"
                               width="180"
                               label="操作">
                <template slot-scope="scope">
                  <el-button @click="handleViewClick(scope.row)"
                             type="primary"
                             icon="el-icon-camera"
                             :size="GlobalCss.buttonSize">预览</el-button>
                  <el-button type="success"
                             icon="el-icon-download"
                             @click="handleDownLoadClick(scope.row)"
                             :size="GlobalCss.buttonSize">下载</el-button>
                </template>
              </el-table-column>
            </el-table>
            <div class="pagination"
                 style="text-align:right;margin-top:12px;">
              <el-pagination background
                             layout="total, prev, pager, next"
                             :current-page="1"
                             :page-size="10"
                             :total="100"
                             @current-change="handlePageChange"></el-pagination>
            </div>
            <!--新增弹框-->
            <div class="dialogForm">
              <el-dialog title="新增设备资料"
                         width="30%"
                         :visible.sync="dialogFormVisible">
                <el-form :model="form">
                  <el-form-item label="资料类别:"
                                :label-width="formLabelWidth">
                    <el-select v-model="queryParam.useStatus"
                               size="small"
                               style="width:320px;"
                               placeholder="请选择">
                      <el-option value="0"
                                 label="说明书"></el-option>
                      <el-option value="1"
                                 label="质保卡"></el-option>
                      <el-option value="2"
                                 label="操作手册"></el-option>
                      <el-option value="3"
                                 label="维修经验"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="上传资料:"
                                :label-width="formLabelWidth">
                    <file-upload v-model="fileDatas"></file-upload>
                  </el-form-item>
                </el-form>
                <div slot="footer"
                     class="dialog-footer">
                  <el-button @click="dialogFormVisible = false" :size="GlobalCss.buttonSize">取 消</el-button>
                  <el-button type="primary"
                             @click="dialogFormVisible = false" :size="GlobalCss.buttonSize">确 定</el-button>
                </div>
              </el-dialog>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
