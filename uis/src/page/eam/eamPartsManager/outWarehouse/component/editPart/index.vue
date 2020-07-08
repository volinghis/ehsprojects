<template>
  <div style="margin-right:20px;">
    <el-form ref="form" :model="form" :rules="rules" label-suffix="：" label-position="right" label-width="90px" :size="GlobalCss.controlSize">
      <el-row style="margin-left:20px;">
        <el-col :span="6">
          <div class="el-upload avatar-uploader">
            <!-- <div class="item-title">设备图片</div> -->
            <img v-if="imageUrl" :src="imageUrl" class="avatar" :key=key>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </div>
        </el-col>
        <el-col :span="18">
          <div class="item-block">
            <el-row>
              <el-col :span="8">
                <el-form-item label="商品编码" prop="deviceCode">
                  <el-input v-model="form.deviceCode" placeholder="系统自动生成" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="物资编码" prop="materialCode">
                  <el-input  v-model="form.materialCode" placeholder="请填写物资编码" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="单价" prop="price">
                  <el-input v-model="form.price" @blur="priceBlur($event)" placeholder="请输入单价" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="预警值" prop="warningValue">
                  <el-input v-model="form.warningValue" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="供应商">
                  <el-input v-model="form.supplier" placeholder="请填写供应商" :disabled="partFlag"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="商品名称" prop="deviceName">
                  <el-input v-model="form.deviceName" placeholder="请填写商品名称" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="物资类别" prop="materialType">
                  <el-input v-model="form.materialType" placeholder="请填写物资类别" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="单位" prop="unit">
                  <el-input v-model="form.unit" placeholder="请输入单位" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="数量" prop="amount">
                  <el-input v-model="form.amount" placeholder="请输入数量" @blur="amountBlur($event)" :disabled="amountFlag"></el-input>
                  <template v-if="amountMessage">
                    <div class="ehs_form_item_message">剩余库存数量为:{{oldAmount}}</div>
                  </template>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="规格型号" prop="norm">
                  <el-input v-model="form.norm" placeholder="请填写商品型号" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="生产厂家" prop="manufacturer">
                  <el-input v-model="form.manufacturer" placeholder="请输入生产厂家" :disabled="partFlag" ></el-input>
                </el-form-item>
                <el-form-item label="出厂编号" prop="leaveFactoryCode">
                  <el-input v-model="form.leaveFactoryCode" placeholder="请输入生产编号" :disabled="partFlag"></el-input>
                </el-form-item>
                <el-form-item label="出厂日期" prop="leaveFactoryDate">
                  <el-date-picker v-model="form.leaveFactoryDate" type="date" style="width:100%;" placeholder="选择日期" :disabled="partFlag"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
          </div>
          <!-- <el-divider></el-divider> -->
          <!--设备参数-->
          <!-- <el-row>
            <div class="item-block">
              <span class="item-title">商品主要参数</span>
              <params-table  ref="partParams" :flag="flag" :paramsData="paramsData" ></params-table>
            </div>
          </el-row>
          <el-divider></el-divider> -->
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="备 注" prop="remark">
            <el-input  v-model="form.remark" style="width:100%;" type="textarea" :rows="2" maxlength="300" show-word-limit placeholder="请输入内容" :disabled="partFlag"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <div class="item-block right"  style="margin-left:20px;">
          <FilesTable :fileId="form.fileId" :deviceKey="deviceKey" @removedFileId="removedFileId" @getFileId="allFileId" :isDisable="partFlag" :key=key></FilesTable>
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
