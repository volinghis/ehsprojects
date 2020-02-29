<template>
  <div style="padding:0px 20px;">
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="item-block">
            <div class="item-title">设备图片</div>
            <img style="width: 170px; height: 170px" v-if="imageUrl" :src="imageUrl" class="avatar">
          </div>
          <!--检修质量标准-->
          <div class="item-block">
            <div class="item-title">检修质量标准</div>
            <file-upload :propUploadValue="eamItem.maintenancesStandard" v-if="partFlag" :disabled="true"></file-upload>
          </div>
          <!--设备说明书-->
          <div class="item-block">
            <div class="item-title">设备说明书</div>
            <file-upload :propUploadValue="eamItem.synopsis" v-if="partFlag" :disabled="true"></file-upload>
          </div>
          <!--设备操作手册-->
          <div class="item-block">
            <div class="item-title">设备操作手册</div>
            <file-upload :propUploadValue="eamItem.operationManual" v-if="partFlag" :disabled="true"></file-upload>
          </div>
        </el-col>
        <el-col :span="18">
          <div class="item-block right">
            <div class="item-title">备件基本信息</div>
            <el-divider></el-divider>
            <div class="contents">
              <el-row>
                <el-col :span="8">
                  <span class="info-title">备件名称</span>:&nbsp;<span class="info-content">{{ eamItem.deviceName }}</span>
                </el-col>
                <el-col :span="8">
                  <span class="info-title">备件编码</span>:&nbsp;<span class="info-content">{{ eamItem.deviceCode }}</span>
                </el-col>
                <el-col :span="8">
                  <span class="info-title">规格型号</span>:&nbsp;<span class="info-content">{{ eamItem.norm }}</span>
                </el-col>
                <el-col :span="8">
                  <span class="info-title">物资类型</span>:&nbsp;<span class="info-content">{{ eamItem.materialType }}</span>
                </el-col>
                <el-col :span="8">
                  <span class="info-title">物资编码</span>:&nbsp;<span class="info-content">{{ eamItem.materialCode }}</span>
                </el-col>
                <el-col :span="8">
                  <span class="info-title">生产厂家</span>:&nbsp;<span class="info-content">{{ eamItem.manufacturer }}</span>
                </el-col>
                <el-col :span="8">
                  <span class="info-title">出厂编号</span>:&nbsp;<span class="info-content">{{ eamItem.leaveFactoryCode }}</span>
                </el-col>
                <el-col :span="8">
                  <span class="info-title">出厂日期</span>:&nbsp;<span class="info-content">{{ eamItem.leaveFactoryDate }}</span>
                </el-col>
                <el-col :span="8">
                  <span class="info-title">预警值</span>:&nbsp;<span class="info-content">{{ eamItem.warningValue }}</span>
                </el-col>
                <!-- <el-col :span="8">
                  <span class="info-title">创建人</span>:&nbsp;<span class="info-content">{{ eamItem.creationName }}</span>
                </el-col>
                <el-col :span="8">
                  <span class="info-title">创建时间</span>:&nbsp;<span class="info-content">{{ eamItem.creationTime }}</span>
                </el-col> -->
              </el-row>
            </div>
          </div>
          <!--设备参数-->
          <!-- <div class="item-block right">
            <div class="item-title">备件主要参数</div>
            <el-divider></el-divider>
            <params-table :paramsData="paramsData" :flag="flag"></params-table>
          </div> -->
          <!--备件修改信息-->
          <div class="item-block right">
            <div class="item-title">备件修改信息记录</div>
            <el-divider></el-divider>
            <modify-record></modify-record>
          </div>
          <div class="item-block">
            <div class="item-title">资料完整度</div>
            <el-row style="width:50%">
              <el-card shadow="never">
                <el-col :span="12">
                  <el-progress type="circle"
                               :color="customColors"
                               :percentage="eamItem.completePoint"
                               :stroke-width="7">
                  </el-progress>
                </el-col>
                <el-col :span="12">
                  <span>当前设备资料的完整度为{{eamItem.completePoint}}%，请尽快完善资料</span>
                </el-col>
              </el-card>
            </el-row>
          </div>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <div style="text-align: center;">
        <el-button :size="GlobalCss.controlSize"
                   type="primary"
                   @click="handleBack">返回</el-button>
        <el-button :size="GlobalCss.controlSize"
                   @click="handlePrint">打印</el-button>
      </div>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
