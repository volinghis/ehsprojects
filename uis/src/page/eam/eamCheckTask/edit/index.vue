<template>
  <div>
    <el-divider content-position="left">添加工作日志</el-divider>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" style="width:700px;" label-width="100px"
      :inline-message="true" :status-icon="true" class="demo-ruleForm">
      <el-form-item label="任务名称" prop="name">
        <el-input v-model="ruleForm.name" placeholder="请选择计划名称"></el-input>
        <div class="ehs_form_item_message">任务名称必填，且要求3到20个字符</div>
      </el-form-item>
      <el-form-item label="执行结果">
        <el-radio-group :size="GlobalCss.buttonSize" v-model="ruleForm.result">
          <el-radio border label="NORMAL">正常执行</el-radio>
          <el-radio border label="NOTEXECUTE_NOWORK">不执行-缺工</el-radio>
          <el-radio border label="NOTEXECUTE_PERSON_CHANGE">不执行-人员调离</el-radio>
          <el-radio border label="NOTEXECUTE_OTHER">不执行-其他</el-radio>
        </el-radio-group>
        <div class="ehs_form_item_message">请根据实际情况填写，正常执行的情况下，可以填写检修记录，缺陷，备件使用等信息</div>
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" v-model="ruleForm.description"></el-input>
      </el-form-item>
    </el-form>
    <el-collapse v-model="activeNames" v-if="normalExecute()">
      <el-collapse-item title="检修记录" name="rep">
        <el-button type="primary" size="mini" style="float:right" @click="repairsMethod()">添加</el-button>
        <repairs v-if="repairsAdd"></repairs>
        <el-table :data="handlesearch(this.ruleForm.eamCheckRepair)" ref="repairTable" border
          :size="GlobalCss.buttonSize">
          <el-table-column width="40" type="expand">
            <template slot-scope="props">
              <repairs :dataRow="props.row"></repairs>
            </template>
          </el-table-column>
          <el-table-column type="index" align="center" width="50" label="序号"></el-table-column>

          <el-table-column align="center" show-overflow-tooltip prop="deviceAddress" width="140" label="设备位置">
            <template slot-scope="scope">
              <span>{{addressNameGet(scope)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="objectKey" width="140" label="检修对象">
            <template slot-scope="scope">
              <span>{{objectNameGet(scope)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="question" label="问题描述"></el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="userName" label="检修执行人">
            <template slot-scope="scope">
              <span>{{transExecutor(scope.row)}}</span>
            </template>

          </el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="result" width="140" label="检修结论">
            <template slot-scope="scope">
              <span>{{transResult(scope.row)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" show-overflow-tooltip width="100" label="操作">
            <template slot-scope="scope">
              <el-button type="danger" @click="remove(scope)" size="mini" title="删除" icon="el-icon-delete" circle>
              </el-button>
            </template>

          </el-table-column>
        </el-table>
      </el-collapse-item>
      <el-collapse-item title="缺陷记录" name="def">
        <el-button type="primary" size="mini" style="float:right" @click="defectsMethod()">添加</el-button>
        <defects v-if="defectsAdd"></defects>
        <el-table :data="handlesearch(this.ruleForm.eamCheckDefect)" ref="defectTable" border
          :size="GlobalCss.buttonSize">
          <el-table-column width="40" type="expand">
            <template slot-scope="props">
              <defects :dataRow="props.row"></defects>
            </template>
          </el-table-column>
          <el-table-column type="index" align="center" width="50" label="序号"></el-table-column>

          <el-table-column align="center" show-overflow-tooltip prop="deviceAddress" width="140" label="设备位置">
            <template slot-scope="scope">
              <span>{{addressNameGet(scope)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="objectKey" width="140" label="缺陷对象">
            <template slot-scope="scope">
              <span>{{objectNameGet(scope)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="question" label="缺陷描述"></el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="devices" label="问题设备">
          </el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="level" width="140" label="缺陷等级">
            <template slot-scope="scope">
              <span>{{transDefectLevel(scope)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" show-overflow-tooltip prop="status" width="140" label="缺陷状态">
            <template slot-scope="scope">
              <span>{{transDefectStatus(scope)}}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" show-overflow-tooltip width="100" label="操作">
            <template slot-scope="scope">
              <el-button type="danger" @click="remove(scope)" size="mini" title="删除" icon="el-icon-delete" circle>
              </el-button>
            </template>

          </el-table-column>
        </el-table>
      </el-collapse-item>

    </el-collapse>
  </div>
</template>
<script>
import datas from './datas'
import defects from './models/defects/index.vue'
import repairs from './models/repairs/index.vue'

export default datas
datas.components = {
  'defects': defects,
  'repairs': repairs
}

</script>
<style lang="scss" scoped>
  @import "./styles.scss";

</style>
