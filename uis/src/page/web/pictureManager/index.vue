<template>
  <div>
    <!-- <div class="operate">
      <el-button type="primary"
                 size="small"
                 icon="fa fa-paper-plane pull-left">发布</el-button>
    </div> -->
    <el-upload :action="GlobalVars.globalServiceServlet + '/data/file/fileUpload'+ '?tt=' + Math.random()+ '&resoureMenuKey=' + $store.state.resourceMenuKey"
               :on-success="handleSuccess"
               :before-upload="beforeUpload"
               :show-file-list="false"
               list-type="text">
      <el-button size="small"
                 type="primary"
                 icon="fa fa-cloud-upload pull-left">点击上传</el-button>
      <div slot="tip"
           class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
    <!-- table -->
    <el-table :data="tableData"
              :show-header="true"
              style="width: 100%;margin-top:20px;">
      <!-- <el-table-column type="index"
                       align="center"></el-table-column> -->
      <el-table-column prop="fileId"
                       align="center">
        <template slot-scope="scope">
          <img :src="scope.row.fileId===''?'':GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + scope.row.fileId + '&resoureMenuKey=' + $store.state.resourceMenuKey"
               min-width="70"
               height="70" />
        </template>
      </el-table-column>
      <el-table-column prop="picName"
                       align="center"
                       min-width="150">
      </el-table-column>
      <!-- <el-table-column prop="picOrder"
                       align="center"
                       min-width="100">
      </el-table-column> -->
      <el-table-column show-overflow-tooltip
                       min-width="120"
                       align="center">
        <template slot-scope="scope">
          <el-tooltip content="上移"
                      placement="top">
            <el-button type="primary"
                       circle
                       icon="el-icon-arrow-up"
                       :size="GlobalCss.buttonSize"
                       @click="handleRowUp(scope.row,scope.$index)"></el-button>
          </el-tooltip>
          <el-tooltip content="下移"
                      placement="bottom">
            <el-button type="primary"
                       circle
                       icon="el-icon-arrow-down"
                       :size="GlobalCss.buttonSize"
                       @click="handleRowDown(scope.row,scope.$index)"></el-button>
          </el-tooltip>
           <el-tooltip content="删除"
                      placement="right">
          <el-button type="danger"
                     circle
                     icon="el-icon-delete"
                     :size="GlobalCss.buttonSize"
                     @click="handleRemove(scope.row)"></el-button>
                     </el-tooltip>
        </template>
      </el-table-column>

    </el-table>
    <!-- 图片预览窗口 -->
    <el-dialog title="预览"
               :visible.sync="dialogVisible"
               width="50%" >
      <img :src="viewUrl"
           width="100%"/>
    </el-dialog>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
.operate {
  float: right;
  margin-bottom: 15px;
}
</style>
