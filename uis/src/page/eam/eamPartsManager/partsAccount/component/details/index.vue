<template>
  <div style="margin-left:10px;">
    <el-row :gutter="20">
      <el-col :span="10">
        <el-card>
           <div class="item-block" style="text-align:center;">
            <div class="item-title">设备图片</div>
            <img style="width: 370px; height: 370px;" v-if="imageUrl" :src="imageUrl">
          </div>
          <div class="ehs_form_item_message" style="text-align:center;">
           <el-row>
             <el-form>
               <el-form-item label="备件名称">
                 <span class="info-content">{{ eamItem.deviceName }}</span>
               </el-form-item>
             </el-form>
              <!-- <el-col :span="24">
                <span class="info-title">备件名称</span>:&nbsp;<span class="info-content">{{ eamItem.deviceName }}</span>
              </el-col>
              <el-col :span="24">
                <span class="info-title">备件编码</span>:&nbsp;<span class="info-content">{{ eamItem.deviceCode }}</span>
              </el-col>
              <el-col :span="24">
                <span class="info-title">规格型号</span>:&nbsp;<span class="info-content">{{ eamItem.norm }}</span>
              </el-col>
              <el-col :span="24">
                <span class="info-title">物资类型</span>:&nbsp;<span class="info-content">{{ eamItem.materialType }}</span>
              </el-col>
              <el-col :span="24">
                <span class="info-title">物资编码</span>:&nbsp;<span class="info-content">{{ eamItem.materialCode }}</span>
              </el-col>
              <el-col :span="24">
                <span class="info-title">生产厂家</span>:&nbsp;<span class="info-content">{{ eamItem.manufacturer }}</span>
              </el-col>
              <el-col :span="24">
                <span class="info-title">出厂编号</span>:&nbsp;<span class="info-content">{{ eamItem.leaveFactoryCode }}</span>
              </el-col>
              <el-col :span="24">
                <span class="info-title">出厂日期</span>:&nbsp;<span class="info-content">{{ eamItem.leaveFactoryDate }}</span>
              </el-col>
              <el-col :span="24">
                <span class="info-title">预警值</span>:&nbsp;<span class="info-content">{{ eamItem.warningValue }}</span>
              </el-col> -->
              <!-- <el-col :span="24">
                <span class="info-title">创建人</span>:&nbsp;<span class="info-content">{{ eamItem.creationName }}</span>
              </el-col>
              <el-col :span="24">
                <span class="info-title">创建时间</span>:&nbsp;<span class="info-content">{{ eamItem.creationTime }}</span>
              </el-col> -->
            </el-row>
          </div>
        </el-card>
      </el-col>
      <el-col :span="14">
        <el-row>
          <el-col :span="24" class="grid-content bg-purple">
            <el-card>
              asdajdjaldkajklj
            </el-card>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-card>
              asdajdjaldkajklj
            </el-card>
          </el-col>
        </el-row>
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
