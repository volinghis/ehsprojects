<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'0px 20px',background:'#fff'}" class="divHeight">
    <el-form ref="form" :model="form" :rules="rules" label-position="right" label-width="100px" :size="GlobalCss.buttonSize" class="formWidth">
      <div class="searchHeight">
        <el-row :gutter="24">
          <el-col :span="6">
            <el-form-item label="计划编码：" prop="inspTaskCode">
              <el-input v-model="form.inspTaskCode" placeholder="不用填写，系统自动生成"></el-input>
            </el-form-item>
            <el-form-item label="责任部门：" prop="responsibleDept">
              <org style="width:100%" @change="changeSelectOrg"></org>
              <!-- <el-input v-model="form.responsibleDept"></el-input> -->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="计划名称：" prop="inspTaskName">
              <el-input v-model="form.inspTaskName"></el-input>
            </el-form-item>
            <el-form-item label="责任人：" prop="responsiblePerson">
              <user style="width:100%" @change="changeSelectUser"></user>
              <!-- <el-input v-model="form.responsiblePerson"></el-input> -->
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="计划类型：" prop="inspTaskType">
              <el-input v-model="form.inspTaskType"></el-input>
            </el-form-item>
               <el-form-item label="开始日期：" prop="startDate">
              <el-date-picker v-model="form.startDate" type="date" placeholder="请选择入库日期" style="width:100%"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="创建人：" prop="founder">
              <el-input v-model="form.founder" placeholder="不用填写，系统自动生成" disabled></el-input>
            </el-form-item>
            <el-form-item label="结束日期：" prop="overDate">
              <el-date-picker v-model="form.overDate" type="date" placeholder="请选择出厂日期" style="width:100%"></el-date-picker>
            </el-form-item>
            <!-- <el-form-item label="执行周期：" prop="factoryCode">
              <el-input v-model="form.factoryCode"></el-input>
            </el-form-item> -->
            <!-- <el-form-item label="有 效 期：" prop="factoryCode">
              <el-input v-model="form.factoryCode"></el-input>
            </el-form-item> -->
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="工作标准：" prop="workingStandard">
            <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 6}" placeholder="请输入内容" v-model="form.workingStandard" maxlength="300" show-word-limit></el-input>
          </el-form-item>
          <el-form-item label="备注：" prop="remark">
            <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 6}" placeholder="请输入内容" v-model="form.remark" maxlength="300" show-word-limit></el-input>
          </el-form-item>
        </el-row>
        <el-row>
            <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.buttonSize" @click="dialogVisible = true">添加巡检设备</el-button>
        </el-row>
      </div>
      <!--设备表格-->
      <div class="tableHeight" :style="{height:tableHeight}">
        <params-table :deviceSet="deviceSet"></params-table>
      </div>
      <!-- <div style="text-align: center; padding:10px" class="submitHeight">
        <el-button type="primary" @click="handerSubmit" :size="GlobalCss.buttonSize">提&nbsp;&nbsp;交</el-button>
        <el-button @click="resetForm" :size="GlobalCss.buttonSize">重&nbsp;&nbsp;置</el-button>
      </div> -->
    </el-form>
    <el-dialog title="添加巡检设备" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
      <div style="text-align:center;">
        <eamlist @handlerSelect="handlerSelect" flag="child"></eamlist>
        <div style="margin:10px;">
          <span slot="footer" class="dialog-footer">
            <el-button @click="overReset" :size="GlobalCss.buttonSize">取 消</el-button>
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
@import './styles.scss';
</style>
