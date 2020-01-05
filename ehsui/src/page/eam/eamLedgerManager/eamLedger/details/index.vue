<template>
  <div style="padding:0px 20px;">
    <el-form ref="form"
             :model="form"
             :rules="rules"
             label-position="top"
             label-width="80px">
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="item-block">
            <div class="item-title">设备图片</div>
            <el-image style="width: 150px; height: 150px"
                      :src="eamInfos.deviceImg"
                      fit="fill"></el-image>
          </div>
          <!--检修质量标准-->
          <div class="item-block">
            <div class="item-title">检修质量标准</div>
            <el-upload class="upload-demo"
                       action="https://jsonplaceholder.typicode.com/posts/"
                       :on-preview="handlePreview"
                       :on-remove="handleRemove"
                       :before-remove="beforeRemove"
                       multiple
                       :limit="3"
                       :on-exceed="handleExceed"
                       :file-list="fileList">
              <el-button :size="GlobalCss.buttonSize"
                         type="primary"
                         icon="el-icon-upload">上传文件</el-button>
            </el-upload>
          </div>
          <!--设备说明书-->
          <div class="item-block">
            <div class="item-title">设备说明书</div>
            <el-upload class="upload-demo"
                       action="https://jsonplaceholder.typicode.com/posts/"
                       :on-preview="handlePreview"
                       :on-remove="handleRemove"
                       :before-remove="beforeRemove"
                       multiple
                       :limit="3"
                       :on-exceed="handleExceed"
                       :file-list="fileList">
              <el-button :size="GlobalCss.buttonSize"
                         type="primary"
                         icon="el-icon-upload">上传文件</el-button>
            </el-upload>
          </div>
          <!--设备操作手册-->
          <div class="item-block">
            <div class="item-title">设备操作手册</div>
            <el-upload class="upload-demo"
                       action="https://jsonplaceholder.typicode.com/posts/"
                       :on-preview="handlePreview"
                       :on-remove="handleRemove"
                       :before-remove="beforeRemove"
                       multiple
                       :limit="3"
                       :on-exceed="handleExceed"
                       :file-list="fileList">
              <el-button :size="GlobalCss.buttonSize"
                         type="primary"
                         icon="el-icon-upload">上传文件</el-button>
            </el-upload>
          </div>

          <div class="item-block">
            <div class="item-title">资料完整度</div>
            <el-row>
              <el-card>
                <el-col :span="12">
                  <el-progress type="circle"
                               :percentage="eamInfos.complete"
                               :stroke-width="10"></el-progress>
                </el-col>
                <el-col :span="12">
                  <p> 当前设备资料的完整度为{{eamInfos.complete}}%，请尽快完善资料</p>
                </el-col>
              </el-card>
            </el-row>
          </div>
        </el-col>
        <el-col :span="18">
           <div class="item-block right">
            <div class="item-title">设备基本信息</div>
            <div class="contents">
              <el-row>
                <el-col :span="12">
                  <span class="info-title">设备名称</span>:&nbsp;<span class="info-content">{{ eamInfos.deviceName }}</span>
                   </el-col>
                  <el-col :span="12">
                  <span class="info-title">设备编码</span>:&nbsp;<span class="info-content">{{ eamInfos.deviceNum }}</span>
                  </el-col>
                  <el-col :span="12">
                  <span class="info-title">设备型号</span>:&nbsp;<span class="info-content">{{ eamInfos.deviceModel }}</span>
                  </el-col>
                  <el-col :span="12">
                  <span class="info-title">出厂编号</span>:&nbsp;<span class="info-content">{{ eamInfos.leaveNum }}</span>
                  </el-col>
                  <el-col :span="12">
                  <span class="info-title">生产厂家</span>:&nbsp;<span class="info-content">{{ eamInfos.factoryName }}</span>
                  </el-col>
                  <el-col :span="12">
                  <span class="info-title">出厂日期</span>:&nbsp;<span class="info-content">{{ eamInfos.leaveDate }}</span>
                  </el-col>
                  <el-col :span="12">
                  <span class="info-title">投运日期</span>:&nbsp;<span class="info-content">{{ eamInfos.runDate }}</span>
                  </el-col>
                  <el-col :span="12">
                  <span class="info-title">创建人:</span>:&nbsp;<span class="info-content">{{ eamInfos.person }}</span>
                </el-col>
             </el-row>
            </div>
          </div>
          <!--设备参数-->
          <div class="item-block right">
            <div class="item-title">设备主要参数</div>
            <el-divider></el-divider>
            <params-table></params-table>
          </div>
          <div class="item-block right">
            <div class="item-title">定期工作标准</div>
            <el-divider></el-divider>
            <el-input type="textarea"
                      :rows="5"
                      maxlength="300"
                      show-word-limit
                      placeholder="请输入定期工作标准"
                      v-model="form.textarea">
            </el-input>
          </div>
          <!--历任点检员-->
          <div class="item-block right">
            <div class="item-title">历任点检员</div>
            <el-divider></el-divider>
            <past-inspectors></past-inspectors>
          </div>
        </el-col>
      </el-row>
      <el-form-item style="text-align: center;">
        <el-button :size="GlobalCss.buttonSize"
                   type="primary"
                   @click="$router.go(-1)">返回</el-button>
        <el-button :size="GlobalCss.buttonSize"
                   @click="handlePrint">打印</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss'
</style>
