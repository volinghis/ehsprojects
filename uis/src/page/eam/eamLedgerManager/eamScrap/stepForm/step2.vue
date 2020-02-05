<template>
  <div class="step-container">
    <el-button type="primary"
               :size="GlobalCss.buttonSize"
               icon="el-icon-plus"
               @click="dialogTableVisible = true">选择设备</el-button>
    <template>
      <el-table :data="tableData"
                border
                ref="multipleTable"
                class="tableHeight"
                :size="GlobalCss.controlSize">
        <el-table-column type="index"> </el-table-column>
        <el-table-column prop="deviceNum"
                         label="设备编号"> </el-table-column>
        <el-table-column prop="deviceName"
                         label="设备名称"> </el-table-column>
        <el-table-column prop="deviceModel"
                         label="规格型号"> </el-table-column>
        <el-table-column prop="installLocation"
                         label="安装位置"> </el-table-column>
        <el-table-column prop="person"
                         label="负责人"> </el-table-column>
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
      <eam-list @handlerSelect="handlerSelect" flag="scrap"></eam-list>
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
  methods: {
    nextStep: function () {
      if (!this.tableData.length > 0) {
        this.$message({
          message: '请选择需要报废的设备',
          type: 'info'
        })
        return
      }
      this.$emit('nextStep', this.tableData)
    },
    prevStep: function () {
      this.$emit('prevStep')
    },
    handleDetermine () {
      this.dialogTableVisible = false
      this.tableData.push(this.selectRow)
    },
    handlerSelect (val) {
      this.selectRow = val
    }
  },
  data () {
    return {
      dialogTableVisible: false,
      selectRow: {},
      form: {
        deviceName: '',
        deviceNum: '',
        installLocation: '',
        person: '',
        deviceModel: ''
      },
      tableData: []
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
</style>
