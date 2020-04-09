<template>
  <div class="pageClass">
    <el-row :gutter="10">
      <el-col :span="8" style="border-right-style: ridge;border-right-width: 1px;border-right-color: #E4E7ED;">
        <!-- <el-card> -->
          <div class="item-block" style="text-align:center;">
            <div class="item-title">设备详情</div>
            <el-image v-if="imageUrl" :src="imageUrl" class="avatar"></el-image>
          </div>
          <div class="ehs_form_item_message" style="text-align:center;">
           <el-row style="width:50%;margin-left:32%;">
             <el-form label-suffix="：" label-width="80px">
               <el-form-item label="备件编码">
                 <span class="info-content">{{ form.deviceCode }}</span>
               </el-form-item>
               <el-form-item label="备件名称">
                 <span class="info-content">{{ form.deviceName }}</span>
               </el-form-item>
               <el-form-item label="规格型号">
                 <span class="info-content">{{ form.norm }}</span>
               </el-form-item>
               <el-form-item label="物资类型">
                 <span class="info-content">{{ form.materialType }}</span>
               </el-form-item>
               <el-form-item label="物资编码">
                 <span class="info-content">{{ form.materialCode }}</span>
               </el-form-item>
               <el-form-item label="生产厂家">
                 <span class="info-content">{{ form.manufacturer }}</span>
               </el-form-item>
               <el-form-item label="出厂编号">
                 <span class="info-content">{{ form.leaveFactoryCode }}</span>
               </el-form-item>
               <el-form-item label="出厂日期">
                 <span class="info-content">{{ form.leaveFactoryDate }}</span>
               </el-form-item>
             </el-form>
            </el-row>
          </div>
        <!-- </el-card> -->
      </el-col>
      <el-col :span="16">
        <el-row>
          <el-col :span="24" class="grid-content bg-purple">
            <!-- <el-card> -->
              <div style="margin-left:20px;">
                <FilesTable :fileId="fileId" :deviceKey="deviceKey" @getFileId="allFileId" :isDisable="partFlag" :key="key"></FilesTable>
              </div>
            <!-- </el-card> -->
          </el-col>
        </el-row>
        <!-- <el-row>
          <el-col :span="24">
            <el-card>
              asdajdjaldkajklj
            </el-card>
          </el-col>
        </el-row> -->
      </el-col>
    </el-row>
    <el-row>
      <el-divider></el-divider>
      <el-col style="text-align:center;">
        <el-button type="primary" @click="$router.go(-1)" :size="GlobalCss.buttonSize" >返回</el-button>
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
