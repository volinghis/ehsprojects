<template>
  <div>
    <el-divider content-position="left">查看工作日志</el-divider>
    <el-form style="width:700px;" label-suffix="：" label-width="100px" class="demo-ruleForm">
      <el-form-item label="名称">
       <span>{{ruleForm.name}}</span>
      </el-form-item>
      <el-form-item label="执行结果">
        <span>{{transExecuteResult(ruleForm.result)}}</span>
      </el-form-item>
      <el-form-item label="描述">
         <span>{{ruleForm.description}}</span>
      </el-form-item>
    </el-form>
    <el-collapse v-model="activeNames" v-if="normalExecute()">
      <el-collapse-item title="检修记录" name="rep">

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

        </el-table>
      </el-collapse-item>
      <el-collapse-item title="缺陷记录" name="def">

        <el-table :data="handlesearch(this.ruleForm.eamCheckDefect)" ref="defectTable" border
          :size="GlobalCss.buttonSize">
          <el-table-column width="40" type="expand">
            <template slot-scope="scope">
              <defects :dataRow="scope.row"></defects>
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
