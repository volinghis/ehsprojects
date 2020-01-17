<template>
  <div>
    <span>关联子设备</span>
    <div class="operate">
      <template v-if="!isDisable">
      <el-button type="primary"
                 icon="fa fa-plus pull-left"
                 @click="dialogTableVisible = true"
                 style="float:right;"
                 :size="GlobalCss.buttonSize">选择设备</el-button>
      <el-button type="danger"
                 icon="el-icon-delete"
                 style="float:right;margin-right:10px;"
                 @click="handleDeleteClick"
                 :size="GlobalCss.buttonSize">移除</el-button>
      </template>
    </div>
    <div class="tableContainer">
      <el-table :data="tableData"
                @selection-change="handleSelectionChange"
                border
                size="small"
                style="width: 100%">
        <el-table-column type="selection"
                         width="55">
        </el-table-column>
        <el-table-column prop="deviceNum"
                         label="设备编号"></el-table-column>
        <el-table-column prop="deviceName"
                         label="设备名称"></el-table-column>
        <el-table-column prop="installLocation"
                         label="位置"></el-table-column>
        <el-table-column prop="deviceModel"
                         label="型号"></el-table-column>
        <el-table-column prop="profession"
                         label="专业"></el-table-column>
      </el-table>
    </div>
    <!--设备选择弹窗-->
    <el-dialog title="设备台账"
               :visible.sync="dialogTableVisible">
      <eam-list @handlerSelect="handlerSelect"
                :deviceKey="deviceKey" flag="child"></eam-list>
      <div slot="footer"
           class="dialog-footer">
        <el-button @click="dialogTableVisible = false"
                   :size="GlobalCss.buttonSize">取 消</el-button>
        <el-button type="primary"
                   @click="handlerConfirm"
                   :size="GlobalCss.buttonSize">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import ConstructKeys from '../commom/utils.js'
import EamList from './eamList'
export default {
  components: {
    EamList
  },
  data () {
    return {
      dialogTableVisible: false,
      tableData: [],
      selectDatas: [],
      checkedDatas: []
    }
  },
  props: {
    deviceKey: String,
    isDisable: Boolean
  },
  watch: {
    deviceKey: function (val) {
      this.getRelatedDeviceByKey(val)
    }
  },
  methods: {
    // 移除
    handleDeleteClick () {
      const checked = this.checkedDatas
      console.log(checked)
      if (!checked.length > 0) {
        this.$message({
          message: '请选择要移除的子设备',
          type: 'warning'
        })
      } else {
        let keys = ConstructKeys.handlerArrayDatas(checked)
        this.handlerRemove(keys)
      }
    },
    handlerRemove (keys) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/removeEamLedger', { params: { deviceKey: this.deviceKey, keys: keys } }).then(res => {
        if (res.data.resultType === 'ok') {
          this.$message({
            message: res.data.message,
            type: 'success'
          })
          this.getRelatedDeviceByKey(this.deviceKey)
        } else {
          this.$message({
            message: res.data.message,
            type: 'info'
          })
        }
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    getRelatedDeviceByKey () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getChildDevByKey', { params: { key: this.deviceKey } }).then(res => {
        this.tableData = res.data
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handlerConfirm (val) {
      this.tableData = this.selectDatas
      this.dialogTableVisible = false
      this.$emit('getRelatedKeys', ConstructKeys.handlerArrayDatas(this.selectDatas))
    },
    handlerSelect (data) { // 传递给子组件的方法
      this.selectDatas = data
    },
    handleSelectionChange (val) {
      this.checkedDatas = val
    }
  }
}
</script>
