<template>
  <div>
    <el-row>
      <el-col :span="5" style="border-right:1px solid #DCDFE6;" :style="{height:(this.$store.state.contentHeight-10)+'px'}">
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
          </span>
        </el-tree>
      </el-col>
      <el-col :span="18" :push="1">
        <el-row>
          <el-col class="searchCol">
            <el-row type="flex"
                    md="24"
                    justify="space-between">
              <el-col :span="8">
                <div class="search-wrapper">
                  <el-input v-model="queryParam.query"
                            placeholder="请输入文件名"
                            :size="GlobalCss.buttonSize">
                    <el-button slot="append"
                               icon="el-icon-search"
                               @click="handleQuery"></el-button>
                  </el-input>
                </div>
              </el-col>
              <el-col :span="8">
                <div class="operate">
                  <el-button type="primary"
                             :size="GlobalCss.buttonSize"
                             @click="openAddForm"
                             icon="el-icon-plus">添加</el-button>
                </div>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
        <el-row>
          <div class="table-list">
            <el-table :data="tableData"
                      :size="GlobalCss.buttonSize"
                      border>
              <el-table-column type="index"
                               width="50"
                               align="center"></el-table-column>
              <el-table-column prop="type"
                               align="center"
                               label="类型"
                               width="60">
                <template slot-scope="scope">
                  <el-image class="table-td-deviceImg"
                            style="width: 30px; height: 30px"
                            :src="findTypeUrl(scope.row.type)"></el-image>
                </template>
              </el-table-column>
              <el-table-column prop="name"
                               label="文件名称"
                               align="center"></el-table-column>
              <el-table-column prop="categoriesName"
                               label="资料类别"
                               align="center"></el-table-column>
              <el-table-column prop="ownerName"
                               label="上传人"
                               width="100"
                               align="center"></el-table-column>
              <el-table-column prop="creationTime"
                               sortable
                               label="上传时间"
                               align="center"></el-table-column>
              <el-table-column fixed="right"
                               align="center"
                               width="180"
                               label="操作">
                <template slot-scope="scope">
                  <el-button @click="handleViewClick(scope.row)"
                             type="primary"
                             icon="el-icon-camera"
                             :size="GlobalCss.buttonSize">预览</el-button>
                  <el-button type="warning"
                             icon="el-icon-download"
                             @click="handleDownLoadClick(scope.row)"
                             :size="GlobalCss.buttonSize">下载</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="pagination"
               style="text-align:right;margin-top:12px;">
            <el-pagination background
                           layout="total, prev, pager, next"
                           :current-page="queryParam.page"
                           :page-size="queryParam.size"
                           :total="total"
                           @current-change="handlePageChange"></el-pagination>
          </div>
        </el-row>

        <!--新增弹框-->
        <UploadForm ref="upForm"
                    @flushData="flushData"></UploadForm>

        <!-- 文件预览弹窗-->
        <template>
          <el-dialog title="文件预览"
                     :visible.sync="viewVisible"
                     width="50%"
                     height="100%"
                     destroy-on-close>
            <iframe id="MyIframe"
                    :src="pdfSrc"
                    width="100%"
                    height="600px;" />
          </el-dialog>
        </template>
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
