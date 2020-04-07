<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'0px 20px',background:'#fff'}" class="divHeight">
    <el-form ref="form" :model="form" :rules="rules" label-suffix="：" label-position="right" label-width="100px"
      :size="GlobalCss.buttonSize" class="formWidth">
      <div class="searchHeight">
        <el-row :gutter="24">
          <el-col :span="6">
            <el-form-item label="出库仓库" prop="outWarehouse">
              <el-select v-model="form.outWarehouse" style="width:100%" placeholder="请选择所在仓库" @change="selectWareHouse" :disabled="show">
                <el-option v-for="item in wareHouse" :key="item.key" :label="item.text" :value="item.key"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="领用部门" prop="receiveDepartCode">
              <OrgSelector v-model="form.receiveDepartCode" style="width:100%;" :disabled="show"></OrgSelector>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出库类型" prop="outBoundType">
               <el-select v-model="form.outBoundType" style="width:100%" placeholder="请选择所在仓库" :disabled="show">
                <el-option v-for="item in outType" :key="item.key" :label="item.text" :value="item.key"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="领用人" prop="receiveEmpCode">
              <template v-if="form.receiveEmp">
                <el-input v-model="form.receiveEmp" :disabled="show"></el-input>
              </template>
              <template v-else>
                <UserSelector v-model="form.receiveEmpCode" :propOrgValue="form.receiveEmpCode" style="width:100%;">
                </UserSelector>
              </template>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出库编码" prop="outWarehouseCode">
              <el-input v-model="form.outWarehouseCode" placeholder="请填写出库编码" :disabled="show"></el-input>
            </el-form-item>
            <el-form-item label="创建人" prop="founder">
              <el-input v-model="form.founder" disabled placeholder="不用填写，系统自动生成"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出库日期" prop="outBoundDate">
              <el-date-picker v-model="form.outBoundDate" type="date" placeholder="请选择时间" style="width:100%"
                :disabled="show"></el-date-picker>
            </el-form-item>
            <!-- <el-form-item label="创建时间" prop="creatDate">
              <el-input v-model="form.creatDate" disabled></el-input>
            </el-form-item> -->
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="备注信息" prop="remark">
            <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 6}" placeholder="请输入内容" :disabled="show"
              v-model="form.remark" maxlength="300" show-word-limit></el-input>
          </el-form-item>
        </el-row>
      </div>
    </el-form>
    <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.buttonSize" @click="handlerSelect" v-show="showButton">添加备件</el-button>
    <!--添加备件-->
    <div class="tableHeight" :style="{height:tableHeight}">
      <TablePart ref="table" :flag="showFlag" :parts="parts" :partsTable="partsTable" @tableParams="tableParams" :totalCounts="totalCount"></TablePart>
    </div>
    <el-dialog title="选择备件" :visible.sync="dialogVisible" width="70%" :before-close="handleClose" destroy-on-close>
      <el-divider></el-divider>
      <div style="text-align:center;">
        <AddPart :selectWareHouseFlag="selectFlag" @partsData="partsData"></AddPart>
        <div style="margin: 10px;">
          <span slot="footer" class="dialog-footer">
            <el-button @click="resetSelect" :size="GlobalCss.buttonSize" v-show="showButton">取 消</el-button>
            <el-button type="primary" @click="overSelect" :size="GlobalCss.buttonSize" v-show="showButton">确 定
            </el-button>
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
