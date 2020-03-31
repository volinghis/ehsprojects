<template>
  <div class="pageClass">
    <el-row :gutter="10">
      <el-col :span="7">
        <el-card>
          <div class="item-block" style="text-align:center;">
            <div class="item-title">设备详情</div>
            <el-image style="width: 150px; height: 150px"
                      :src="imgUrl"
                      fit="fill"></el-image>
          </div>
          <div class="ehs_form_item_message">
            <el-row style="width:60%;margin-left:32%;">
              <el-form label-suffix="：" label-width="100">
                <el-form-item label="设备名称">
                  <span class="info-content">{{ eamInfos.deviceName }}</span>
                </el-form-item>
                <el-form-item label="设备编码">
                  <span class="info-content">{{eamInfos.deviceNum }}</span>
                </el-form-item>
                <el-form-item label="设备型号">
                  <span class="info-content">{{ eamInfos.deviceModel }}</span>
                </el-form-item>
                <el-form-item label="设备专业">
                  <span class="info-content">{{eamInfos.profession }}</span>
                </el-form-item>
                <el-form-item label="设备系统">
                  <span class="info-content">{{eamInfos.deviceSystem }}</span>
                </el-form-item>
                <el-form-item label="生产厂家">
                  <span class="info-content">{{ eamInfos.factoryName }}</span>
                </el-form-item>
                <el-form-item label="出厂日期">
                  <span class="info-content">{{ eamInfos.leaveDate }}</span>
                </el-form-item>
                <el-form-item label="投运日期">
                  <span class="info-content">{{ eamInfos.runDate}}</span>
                </el-form-item>
                <el-form-item label="安装位置">
                  <span class="info-content">{{ eamInfos.installLocationName }}</span>
                </el-form-item>
                <el-form-item label="负责人">
                  <span class="info-content">{{ eamInfos.personName }}</span>
                </el-form-item>
              </el-form>
            </el-row>
          </div>
        </el-card>
      </el-col>
      <el-col :span="17">
        <el-row>
          <el-col :span="24">
            <el-card>
              <past-inspectors :deviceKey="deviceKey"
                               :isDisable="true"></past-inspectors>
            </el-card>
          </el-col>
        </el-row>
        <el-row class="offset-row">
          <el-col :span="24">
            <el-card>
              <ChildEamTable :deviceKey="deviceKey"
                             :isDisable="true"></ChildEamTable>
            </el-card>
          </el-col>
        </el-row>
        <el-row class="offset-row">
          <el-col :span="24">
            <el-card>
              <params-table :deviceKey="deviceKey"
                            :isDisable="true"></params-table>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <el-row :gutter="10"
            style="margin-top:10px;">
      <el-col :span="7">
        <el-card>
          <div class="item-title">定期工作标准</div>
          <div>
            <el-input type="textarea"
                      :rows="5"
                      maxlength="300"
                      show-word-limit
                      placeholder="请输入定期工作标准"
                      v-model="eamInfos.remarks">
            </el-input>
          </div>
        </el-card>
      </el-col>
      <el-col :span="17">
        <el-card>
          <FilesTable :fileId="eamInfos.fileId"
                      :deviceKey="deviceKey"
                      :isDisable="true"></FilesTable>
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
