<template>
  <div style="margin-right:20px;">
    <el-form ref="form" :model="form" :rules="rules" label-position="right" label-width="90px" :size="GlobalCss.controlSize">
      <el-row style="margin-left:20px;">
        <el-col :span="6">
          <div class="item-block">
            <!-- <div class="item-title">设备图片</div> -->
            <el-upload class="avatar-uploader"
              :action="GlobalVars.globalServiceServlet + '/data/file/fileUpload'+ '?tt=' + Math.random()+ '&resoureMenuKey=' + $store.state.resourceMenuKey"
              :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </div>
        </el-col>
        <el-col :span="18">
          <div class="item-block">
            <!-- <div class="item-title">备件基本信息</div> -->
            <!-- <el-divider></el-divider> -->
            <el-row >
              <el-col :span="8">
                <el-form-item label="备件编码:" prop="deviceCode">
                  <el-input v-model="form.deviceCode" placeholder="请填写备件编码" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="物资编码:" prop="materialCode">
                  <el-input v-model="form.materialCode" placeholder="请填写物资编码" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="单价:" prop="price">
                  <el-input v-model="form.price" @blur="priceBlur($event)" placeholder="请填写单价" :disabled="partFlag">
                  </el-input>
                </el-form-item>
                <el-form-item label="预警值:" prop="warningValue">
                  <el-input v-model="form.warningValue"  placeholder="请填写预警值" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="供应商:">
                  <el-input v-model="form.supplier" placeholder="请填写供应商" :disabled="partFlag"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="备件名称:" prop="deviceName">
                  <el-input v-model="form.deviceName" placeholder="请填写备件名称" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="物资类别:" prop="materialType">
                  <el-input v-model="form.materialType" placeholder="请填写物资类别" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="单位:" prop="unit">
                  <el-input v-model="form.unit" placeholder="请填写单位" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="数量:" prop="amount">
                  <el-input v-model="form.amount" placeholder="请填写单位" @blur="amountBlur($event)" :disabled="partFlag">
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="规格型号:" prop="norm">
                  <el-input v-model="form.norm" placeholder="请填写备件型号" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="生产厂家:" prop="manufacturer">
                  <el-input v-model="form.manufacturer" placeholder="请填写生产厂家" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="出厂编号:" prop="leaveFactoryCode">
                  <el-input v-model="form.leaveFactoryCode" placeholder="请填写生产编号" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="出厂日期:" prop="leaveFactoryDate">
                  <el-date-picker v-model="form.leaveFactoryDate" type="date" style="width:100%;" placeholder="选择日期"
                    :disabled="partFlag"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
          </div>
          <!-- <el-divider></el-divider> -->
          <!--设备参数-->
          <!-- <el-row>
            <div class="item-block">
              <span style="color:black;">备件主要参数</span>
              <params-table  ref="partParams" :flag="flag" :paramsData="paramsData" ></params-table>
            </div>
          </el-row>
          <el-divider></el-divider> -->
          <!-- <el-row :gutter="20" style="margin-bottom:10px;">
            <el-col :span="12">
              <div class="item-block">
                <span>资料完整度</span>
                <el-card shadow="never" style="margin-top:10px;">
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-progress type="circle" :color="customColors" :percentage="form.complete" :stroke-width="7">
                      </el-progress>
                    </el-col>
                    <el-col :span="16"  style="padding-left: 50px;padding-right: 0px;" >
                      <span style="color:#606266;font-size: small;font-weight: normal;" >当前设备资料的完整度为{{ form.complete }}%，请尽快完善资料</span>
                    </el-col>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row> -->
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="备 注:" prop="remark">
            <el-input v-model="form.remark" style="width:100%;" type="textarea" :rows="2" maxlength="300"
              show-word-limit placeholder="请填写内容" :disabled="partFlag"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <div style="margin-left:20px;">
          <FilesTable :fileId="form.fileId" :deviceKey="deviceKey" @getFileId="allFileId" :isDisable="false"></FilesTable>
        </div>
      </el-row>
      <!-- <el-divider></el-divider> -->
    </el-form>
  </div>
</template>

<script>
import datas from './datas'
export default datas

</script>
<style lang="scss" scoped>
  @import "./styles.scss";

</style>
