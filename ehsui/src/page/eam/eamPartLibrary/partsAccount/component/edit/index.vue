<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'10px',background:'#fff'}">
    <el-card shadow="never">
      <el-form ref="form"
               :model="form"
               :rules="rules"
               inline="inline"
               label-position="right"
               label-width="80px"
               :size="GlobalCss.controlSize">
        <el-row :gutter="20">
          <el-col :span="5"
                  :sm="5"
                  :xs="5">
            <div class="item-block">
              <span>设备图片</span>
              <!-- <el-upload class="upload-demo"
                         drag
                         :before-upload="beforeAvatarUpload"
                         :on-success="handleAvatarSuccess"
                         action=""
                         :limit="1"
                         multiple>
                <img v-if="imageUrl"
                     :src="imageUrl"
                     class="avatar">
                <i v-else
                   class="el-icon-upload"></i>
                <div class="el-upload__text">
                  将图片拖到此处，或<em>点击上传</em>
                </div>
                <div class="el-upload__tip"
                     slot="tip">
                  只能上传jpg/png文件，且不超过500kb
                </div>
              </el-upload> -->
              <el-upload class="avatar-uploader"
                         action="https://jsonplaceholder.typicode.com/posts/"
                         :show-file-list="false"
                         :on-success="handleAvatarSuccess"
                         :before-upload="beforeAvatarUpload">
                <img v-if="imageUrl"
                     :src="form.deviceImg"
                     class="avatar">
                <i v-else
                   class="el-icon-plus avatar-uploader-icon"></i>
                <div slot="tip"
                     class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
            </div>
            <!--检修质量标准-->
            <div class="item-block">
              <span>检修质量标准</span>
              <el-upload class="upload-demo"
                         action=""
                         :on-preview="handlePreview"
                         :on-remove="handleRemove"
                         :before-remove="beforeRemove"
                         multiple
                         :limit="3"
                         :on-exceed="handleExceed"
                         :file-list="fileList">
                <el-button :size="GlobalCss.controlSize"
                           plain
                           type="primary"
                           icon="el-icon-upload">上传文件</el-button>
              </el-upload>
            </div>
            <!--设备说明书-->
            <div class="item-block">
              <span>设备说明书</span>
              <el-upload class="upload-demo"
                         action=""
                         :on-preview="handlePreview"
                         :on-remove="handleRemove"
                         :before-remove="beforeRemove"
                         multiple
                         :limit="3"
                         :on-exceed="handleExceed"
                         :file-list="fileList">
                <el-button :size="GlobalCss.controlSize"
                           plain
                           type="primary"
                           icon="el-icon-upload">上传文件</el-button>
              </el-upload>
            </div>
            <!--设备操作手册-->
            <div class="item-block">
              <span>设备操作手册</span>
              <el-upload class="upload-demo"
                         action=""
                         :on-preview="handlePreview"
                         :on-remove="handleRemove"
                         :before-remove="beforeRemove"
                         multiple
                         :limit="3"
                         :on-exceed="handleExceed"
                         :file-list="fileList">
                <el-button :size="GlobalCss.controlSize"
                           plain
                           type="primary"
                           icon="el-icon-upload">上传文件</el-button>
              </el-upload>
            </div>
          </el-col>
          <el-col :span="19"
                  :sm="19"
                  :xs="19">
            <div class="item-block">
              <span>备件基本信息</span>
              <el-divider></el-divider>
              <el-row :gutter="10">
                <el-col :span="8">
                  <el-form-item label="备件名称:"
                                prop="name">
                    <el-input v-model="form.name"
                              prefix-icon="el-icon-edit"
                              placeholder="请填写备件名称"></el-input>
                  </el-form-item>
                  <el-form-item label="物资编码:"
                                prop="materialCode">
                    <el-input v-model="form.materialCode"
                              prefix-icon="el-icon-edit"
                              placeholder="请填写物资编码"></el-input>
                  </el-form-item>
                  <el-form-item label="预警值:"
                                prop="warningValue">
                    <el-input v-model="form.warningValue"
                              prefix-icon="el-icon-edit"></el-input>
                  </el-form-item>
                  <el-form-item label="数量:"
                                prop="amount">
                    <el-input v-model="form.amount"
                              prefix-icon="el-icon-edit"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="备件编码:"
                                prop="code">
                    <el-input v-model="form.code"
                              prefix-icon="el-icon-edit"
                              placeholder="不用填写，系统自动生成"></el-input>
                  </el-form-item>

                  <el-form-item label="物资类别:"
                                prop="materialCategory">
                    <el-input v-model="form.materialCategory"
                              placeholder="请填写物资类别"
                              prefix-icon="el-icon-edit"></el-input>
                  </el-form-item>
                  <el-form-item label="生产厂家:"
                                prop="factoryName">
                    <el-input v-model="form.factoryName"
                              prefix-icon="el-icon-edit"
                              placeholder="请输入生产厂家"></el-input>
                  </el-form-item>
                   <el-form-item label="创建人:"
                                prop="person">
                    <el-input v-model="form.person"
                              prefix-icon="el-icon-edit"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="规格型号:"
                                prop="norm">
                    <el-input v-model="form.norm"
                              prefix-icon="el-icon-edit"
                              placeholder="请填写备件型号"></el-input>
                  </el-form-item>
                  <el-form-item label="出厂编号:"
                                prop="leaveCode">
                    <el-input v-model="form.leaveCode"
                              prefix-icon="el-icon-edit"
                              placeholder="请输入生产编号"></el-input>
                  </el-form-item>
                  <el-form-item label="出厂日期:"
                                prop="leaveDate">
                    <el-date-picker v-model="form.leaveDate"
                                    type="date"
                                    style="width:100%;"
                                    placeholder="选择日期"></el-date-picker>
                  </el-form-item>
                  <el-form-item label="创建时间:"
                                prop="date">
                    <el-input v-model="form.date"
                              prefix-icon="el-icon-edit"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <!--设备参数-->
            <el-row>
              <div class="item-block">
                <span>备件主要参数</span>
                <params-table></params-table>
              </div>
            </el-row>
            <el-divider></el-divider>
            <el-row :gutter="20">
              <el-col :span="12"
                      :sm="12"
                      :xs="12">
                <div class="item-block">
                  <span>资料完整度</span>
                  <el-card shadow="never"
                           style="margin-top:10px;">
                    <el-row :gutter="20">
                      <el-col :span="8"
                              :sm="8"
                              :xs="8">
                        <el-progress type="circle"
                                     :color="customColors"
                                     :percentage="form.complete"
                                     :stroke-width="7">
                        </el-progress>
                      </el-col>
                      <el-col :span="16"
                              :sm="16"
                              :xs="16"
                              style="padding-left: 50px;padding-right: 0px;">
                        <span style="color:#606266;font-size: small;font-weight: normal;">当前设备资料的完整度为{{form.complete}}%，请尽快完善资料</span>
                      </el-col>
                    </el-row>
                  </el-card>
                </div>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </el-form>
      <el-divider></el-divider>
      <div style="text-align: center;">
        <el-button :size="GlobalCss.controlSize"
                   type="primary"
                   @click="submitForm('form')">提&nbsp;&nbsp;交</el-button>
        <el-button :size="GlobalCss.controlSize"
                   @click="resetForm('form')">重&nbsp;&nbsp;置</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
