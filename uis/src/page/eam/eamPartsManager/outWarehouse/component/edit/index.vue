<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'0px 20px',background:'#fff'}" class="divHeight">
    <el-form ref="form" :model="form" :rules="rules" label-position="right" label-width="100px" :size="GlobalCss.buttonSize" class="formWidth">
      <div class="searchHeight">
        <el-row :gutter="24">
          <el-col :span="6">
            <el-form-item label="出库仓库：" prop="outWarehouseName">
              <el-input v-model="form.outWarehouseName"></el-input>
            </el-form-item>
            <el-form-item label="领用部门：" prop="receivDepart">
              <!-- <el-input v-model="form.receivDepart"></el-input> -->
              <orgSelect v-model="form.receivDepart" style="width:100%;"></orgSelect>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出库类型：" prop="outBoundType">
              <el-input v-model="form.outBoundType"></el-input>
            </el-form-item>
            <el-form-item label="领用人：" prop="receivEmp">
              <!-- <el-input v-model="form.receivEmp"></el-input> -->
              <userSelect  v-model="form.receivEmp" style="width:100%;"></userSelect>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出库编码：" prop="outWarehouseCode">
              <el-input v-model="form.outWarehouseCode" placeholder="不用填写，系统自动生成"></el-input>
            </el-form-item>
             <el-form-item label="创建人：" prop="founder">
              <el-input v-model="form.founder" disabled prefix-icon="el-icon-edit" placeholder="不用填写，系统自动生成"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出库日期：" prop="outBoundDate">
              <el-date-picker v-model="form.outBoundDate" type="date" placeholder="请选择时间" style="width:100%"></el-date-picker>
            </el-form-item>
            <!-- <el-form-item label="创建时间：" prop="creatDate">
              <el-input v-model="form.creatDate" disabled></el-input>
            </el-form-item> -->
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="备注信息：" prop="remark">
            <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 6}" placeholder="请输入内容"
                      v-model="form.remark" maxlength="300" show-word-limit></el-input>
          </el-form-item>
        </el-row>
      </div>
      <el-button type="primary"  icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.buttonSize" @click="dialogVisible = true" v-show="show">添加备件</el-button>
      <!--添加备件-->
      <div class="tableHeight" :style="{height:tableHeight}">
        <tablePart ref="table"
                   :flag="showFlag"
                   :parts="parts"
                   :partsTable="partsTable"
                   @tableParams="tableParams"
                   :totalCounts="totalCount"></tablePart>
      </div>
    </el-form>
    <el-dialog title="添加备件" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
      <div style="text-align:center;">
        <addPart @partsData="partsData"></addPart>
        <div style="margin: 10px;text-align:center;">
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false" :size="GlobalCss.buttonSize">取 消</el-button>
            <el-button type="primary" @click="overSelect" :size="GlobalCss.buttonSize">确 定</el-button>
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
