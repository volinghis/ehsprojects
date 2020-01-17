<template>
  <div class="step-container">
    <div class="operate"
         style="text-align: right;margin-bottom:10px;">
      <el-button type="primary"
                 :size="GlobalCss.buttonSize"
                 icon="el-icon-plus"
                 @click="handleSelectDev">选择设备</el-button>
    </div>

    <template>
      <el-table :data="result.tableData"
                border
                ref="multipleTable"
                class="tableHeight"
                :size="GlobalCss.controlSize">
        <el-table-column prop="deviceNum"
                         align="center"
                         label="设备编号"> </el-table-column>
        <el-table-column prop="deviceName"
                         align="center"
                         label="设备名称"> </el-table-column>
        <el-table-column prop="deviceModel"
                         align="center"
                         label="规格型号"> </el-table-column>
        <el-table-column prop="profession"
                         align="center"
                         label="调出部门"></el-table-column>
        <el-table-column prop="installLocation"
                         align="center"
                         label="调出位置"></el-table-column>
        <el-table-column prop="targetDept"
                         align="center"
                         label="调入部门">
            <OrgSelect @change="handleChange" ref="orgSelect"></OrgSelect>
        </el-table-column>
        <el-table-column prop="targetPosition"
                         align="center"
                         label="调入位置">
          <template slot-scope="scope">
            <el-input :size="GlobalCss.controlSize"
                      v-model="result.allocateForm.targetPosition"
                      placeholder="请输入数量"
                      @change="handleEdit(scope.$index, scope.row)"></el-input>
          </template></el-table-column>
        <el-table-column prop="amount"
                         align="center"
                         label="数量">
          <template slot-scope="scope">
            <el-input :size="GlobalCss.controlSize"
                      v-model="result.allocateForm.amount"
                      placeholder="请输入数量"
                      @change="handleEdit(scope.$index, scope.row)"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="unit"
                         align="center"
                         label="单位">
          <template slot-scope="scope">
            <el-input :size="GlobalCss.controlSize"
                      v-model="result.allocateForm.unit"
                      placeholder="请输入单位"
                      @change="handleEdit(scope.$index, scope.row)"></el-input>
          </template>
        </el-table-column>
        <el-table-column fixed="right"
                         label="操作"
                         width="120"
                         align="center">
          <template slot-scope="scope">
            <el-button :size="GlobalCss.controlSize"
                       type="danger"
                       @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
    <div style="text-align:center;">
      <el-button @click="prevStep"
                 :size="GlobalCss.buttonSize"
                 style="margin:10px;">上一步</el-button>
      <el-button type="primary"
                 @click="nextStep()"
                 :size="GlobalCss.buttonSize"
                 style="margin:10px;">下一步</el-button>
    </div>
    <!--设备选择弹窗-->
    <el-dialog title="设备台账"
               :visible.sync="dialogTableVisible">
      <eam-list @handlerSelect="handlerSelect"
                flag="allocate"></eam-list>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialogTableVisible = false"
                   :size="GlobalCss.buttonSize">取 消</el-button>
        <el-button type="primary"
                   @click="handleDetermine"
                   :size="GlobalCss.buttonSize">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import EamList from '../../../components/eamList'
import OrgSelect from '@/components/org/org-selector'
export default {
  name: 'stepTwo',
  components: {
    EamList,
    OrgSelect
  },
  methods: {
    nextStep: function () {
      if (!this.result.tableData.length > 0) {
        this.$message({
          message: '请选择需要调拨的设备',
          type: 'info'
        })
        return
      }
      this.$emit('nextStep', this.result)
    },
    prevStep: function () {
      this.$emit('prevStep')
    },
    handleDetermine () { // 确定所选设备
      this.dialogTableVisible = false
      this.result.tableData.push(this.selectRow)
    },
    handlerSelect (val) { // 获取弹窗内所选择的设备
      this.selectRow = val
    },
    handleDelete (index, row) {
      console.log(index)
      this.result.tableData.splice(index, 1)
    },
    handleEdit (index, row) {
    },
    handleSelectDev () { // 选择设备
      this.dialogTableVisible = true
    },
    handleChange (node) { // 获取部门
      console.log(node)
    }
  },
  data () {
    return {
      dialogTableVisible: false,
      selectRow: {},
      orgValue: '',
      result: {
        tableData: [],
        allocateForm: {
          profession: '',
          installLocation: '',
          targetDept: '',
          targetPosition: '',
          amount: 0,
          unit: ''
        }
      },
      rules: {
        targetPosition: [
          { required: true, message: '请输入调入位置', trigger: 'blur' }
        ],
        targetDept: [
          { required: true, message: '请输入调入部门', trigger: 'blur' }
        ]
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.stepFormText {
  margin-bottom: 24px;

  .ant-form-item-label,
  .ant-form-item-control {
    line-height: 22px;
  }
}
/deep/.el-dialog__footer {
  text-align: center;
}
.tb-edit .el-input {
  display: none;
}
.tb-edit .current-row .el-input {
  display: block;
}
.tb-edit .current-row .el-input + span {
  display: none;
}
</style>
