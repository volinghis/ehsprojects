<template>
  <div class="step-container">
    <div class="operate"
         style="text-align: right;margin-bottom:10px;">
      <el-button type="primary"
                 :size="GlobalCss.buttonSize"
                 :disabled="!isSet"
                 icon="el-icon-plus"
                 @click="handleSelectDev">选择设备</el-button>
    </div>

    <template>
      <el-form :model="result.allocateForm"
               ref="allocateForm"
               :show-message="false"
               :rules="rules">
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
          <el-table-column prop="installLocationName"
                           align="center"
                           label="调出位置"></el-table-column>
          <el-table-column prop="targetPosition"
                           align="center"
                           label="调入位置">
            <template slot-scope="scope">
              <span v-if="isSet">
                <el-form-item prop="targetPosition">
                  <el-input :size="GlobalCss.controlSize"
                            v-model="result.allocateForm.targetPosition"
                            placeholder="请输入调入位置"></el-input>
                </el-form-item>
              </span>
              <span v-else>{{scope.row.targetPosition}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="amount"
                           align="center"
                           label="数量">
            <template slot-scope="scope">
              <span v-if="isSet">
                <el-form-item prop="amount">
                  <el-input :size="GlobalCss.controlSize"
                            v-model="result.allocateForm.amount"
                            placeholder="请输入数量"></el-input>
                </el-form-item>
              </span>
              <span v-else>{{scope.row.amount}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="unit"
                           align="center"
                           label="单位">
            <template slot-scope="scope">
              <span v-if="isSet">
                <el-form-item prop="unit">
                  <el-input :size="GlobalCss.controlSize"
                            v-model="result.allocateForm.unit"
                            placeholder="请输入单位"></el-input>
                </el-form-item>
              </span>
              <span v-else>{{scope.row.unit}}</span>
            </template>
          </el-table-column>
          <el-table-column fixed="right"
                           label="操作"
                           width="120"
                           align="center">
            <template slot-scope="scope">
              <el-button :size="GlobalCss.controlSize"
                         type="danger"
                         :disabled="!isSet"
                         @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
    </template>
    <div style="text-align:center;">
      <el-button @click="prevStep"
                 :size="GlobalCss.buttonSize"
                 style="margin:10px;">上一步</el-button>
      <el-button type="primary"
                 @click="nextStep()"
                 :disabled="!isSet"
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
export default {
  name: 'stepTwo',
  components: {
    EamList
  },
  data () {
    return {
      dialogTableVisible: false,
      isSet: true,
      selectRow: {},
      result: {
        tableData: [],
        allocateForm: {
          installLocation: '',
          targetPosition: '',
          amount: 1,
          unit: ''
        }
      },
      rules: {
        targetPosition: [
          { required: true, trigger: 'blur' }
        ],
        targetDept: [
          { required: true, trigger: 'blur' }
        ],
        amount: [
          { required: true, trigger: 'blur' }
        ],
        unit: [
          { required: true, trigger: 'blur' }
        ]
      }
    }
  },
  props: {
    businessKey: {
      type: String
    }
  },
  created () {
    if (this.businessKey) {
      this.isSet = false
      this.getRefDevice(this.businessKey)
    }
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
      this.$refs.allocateForm.validate((valid) => {
        if (valid) {
          this.$emit('nextStep', this.result)
        } else {
          return false
        }
      })
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
      this.result.tableData.splice(index, 1)
    },
    getRefDevice (key) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamAllocate/getEamLedgerByAllocateKey', { params: { key: key } }).then(res => {
        var resData = res.data
        var temp = {}
        Object.assign(temp, resData.allocate, resData.table)
        this.result.tableData.push(temp)
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handleSelectDev () { // 选择设备
      this.dialogTableVisible = true
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
