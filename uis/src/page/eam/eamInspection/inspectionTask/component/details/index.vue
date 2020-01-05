<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'0px 20px',background:'#fff'}" class="divHeight">
    <el-form ref="form" :model="form" :rules="rules" label-position="right" label-width="100px" :size="GlobalCss.buttonSize" class="formWidth">
      <div class="searchHeight">
        <el-row :gutter="24">
          <el-col :span="8">
            <el-form-item label="计划名称：" prop="warehouseName">
              <el-input v-model="form.warehouseName"></el-input>
            </el-form-item>
            <el-form-item label="责任部门：" prop="supplier">
              <!-- <el-input v-model="form.supplier"></el-input> -->
              <org style="width:100%"></org>
            </el-form-item>
             <el-form-item label="开始日期：" prop="runDate">
              <el-date-picker v-model="form.runDate" type="date" placeholder="请选择入库日期" style="width:100%"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划类型：" prop="inboundType">
              <el-input v-model="form.inboundType"></el-input>
            </el-form-item>
            <el-form-item label="责任人：" prop="principal">
              <!-- <el-input v-model="form.principal"></el-input> -->
              <user style="width:100%"></user>
            </el-form-item>
            <el-form-item label="结束日期：" prop="leaveFactoryDate">
              <el-date-picker v-model="form.leaveFactoryDate" type="date" placeholder="请选择出厂日期" style="width:100%"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="计划编码：" prop="warehouseCode">
              <el-input v-model="form.warehouseCode" placeholder="不用填写，系统自动生成"></el-input>
            </el-form-item>
            <el-form-item label="执行周期：" prop="factoryCode">
              <el-input v-model="form.factoryCode"></el-input>
            </el-form-item>
            <el-form-item label="有 效 期：" prop="factoryCode">
              <el-input v-model="form.factoryCode"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="工作标准：" prop="remark">
            <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 6}" placeholder="请输入内容" v-model="form.remark" maxlength="300" show-word-limit></el-input>
          </el-form-item>
        </el-row>
        <el-row>
            <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.buttonSize" @click="dialogVisible = true">添加设备</el-button>
        </el-row>
      </div>
      <!--设备表格-->
      <div class="tableHeight" :style="{height:tableHeight}">
        <params-table></params-table>
      </div>
      <div style="text-align: center; padding:10px" class="submitHeight">
        <el-button type="primary" @click="submitForm('form')" :size="GlobalCss.buttonSize">提&nbsp;&nbsp;交</el-button>
        <el-button @click="resetForm('form')" :size="GlobalCss.buttonSize">重&nbsp;&nbsp;置</el-button>
      </div>
    </el-form>
    <el-dialog title="添加巡检设备" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
      <div style="text-align:center;">
        <!-- <el-form :model="partForm" inline="inline" :size="GlobalCss.controlSize">
          <el-row>
            <el-form-item label="设备名称：" prop="name">
              <el-input v-model="partForm.name"></el-input>
            </el-form-item>
            <el-form-item label="设备编号：" prop="code">
              <el-input v-model="partForm.code"></el-input>
            </el-form-item>
            <el-form-item label="设备类型：" prop="type">
              <el-input v-model="partForm.type"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="search('partForm')" style="margin-left: 5px;">搜索</el-button>
            </el-form-item>
          </el-row>
          <template>
            <el-table :data="tableData" border ref="multipleTable" :size="GlobalCss.controlSize">
              <el-table-column type="selection"> </el-table-column>
              <el-table-column prop="code" label="设备编号" align="center"> </el-table-column>
              <el-table-column prop="name" label="设备名称" align="center"> </el-table-column>
              <el-table-column prop="type" label="设备类型" align="center"> </el-table-column>
              <el-table-column prop="model" label="设备型号" align="center"> </el-table-column>
              <el-table-column prop="norm" label="设备规格" align="center"> </el-table-column>
            </el-table>
          </template>
        </el-form> -->
        <eamlist></eamlist>
        <div style="margin:10px;">
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false" :size="GlobalCss.buttonSize">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false" :size="GlobalCss.buttonSize">确 定</el-button>
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
@import './styles.scss';
</style>
