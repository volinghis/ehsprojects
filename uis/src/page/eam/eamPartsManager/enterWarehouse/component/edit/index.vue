<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'0px 20px',background:'#fff'}" class="divHeight">
    <el-form ref="form"  :model="form" :rules="rules" label-suffix="：" label-position="right" label-width="100px" :size="GlobalCss.buttonSize" class="formWidth">
      <div class="searchHeight">
        <el-row :gutter="24">
          <el-col :span="8">
            <el-form-item label="入库编码"  prop="warehouseCode">
              <el-input v-model="form.warehouseCode" placeholder="请输入入库编码"  :disabled="show"></el-input>
            </el-form-item>
            <el-form-item label="入库类型" prop="inboundType">
              <el-select v-model="form.inboundType" style="width:100%" placeholder="请选择所在仓库" :disabled="show">
                <el-option v-for="item in inType" :key="item.key" :label="item.text" :value="item.key"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="入库仓库" prop="warehouse">
              <el-select v-model="form.warehouse" style="width:100%" placeholder="请选择所在仓库" :disabled="show" @change="selectWareHouse">
                <el-option v-for="item in wareHouse" :key="item.key" :label="item.text" :value="item.key"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="入库日期" prop="inboundDate">
              <el-date-picker v-model="form.inboundDate" type="date" placeholder="请选择入库日期" style="width:100%" :disabled="show"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="创 建 人"  prop="founder">
              <el-input v-model="form.founder" disabled placeholder="不用填写，系统自动生成" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="备注信息"  prop="remark">
            <el-input type="textarea"  :autosize="{ minRows: 2, maxRows: 6}" placeholder="请输入内容" v-model="form.remark"
                      maxlength="300" show-word-limit :disabled="show"></el-input>
          </el-form-item>
        </el-row>
      </div>
      <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.buttonSize" @click="handlerSelect" v-show="showButton">选择备件</el-button>
      <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.buttonSize" @click="handlerAdd" v-show="showButton">添加备件</el-button>
      <!--备件表格-->
      <div class="tableHeight">
        <table-part ref="table" :parts="parts" :objPart="obj" :showFlag="showFlag" :partsTable="partsTable" @tableParams="tableParams" :totalCounts="totalCount">
        </table-part>
      </div>
    </el-form>
    <el-dialog title="选择备件" :visible.sync="dialogVisible" width="70%" :before-close="handleClose" destroy-on-close>
      <el-divider></el-divider>
      <div style="text-align:center;">
        <add-part :selectWareHouseFlag="selectFlag" @partsData="partsData" :key="key"></add-part>
        <div style="margin: 10px;">
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="overSelect" :size="GlobalCss.buttonSize">确 定</el-button>
            <el-button @click="resetSelect" :size="GlobalCss.buttonSize">取 消</el-button>
          </span>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
