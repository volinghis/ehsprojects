<template>
  <div class="tableClass">
    <span style="display:inline-block;float:left">关联子设备</span>
    <div class="operate">
      <template v-if="!isDisable">
        <el-button type="primary"
                   icon="fa fa-plus pull-left"
                   @click="dialogTableVisible = true"
                   :size="GlobalCss.buttonSize">选择设备</el-button>
      </template>
    </div>
    <div class="tableContainer">
      <el-table :data="tableData"
                ref="table"
                border
                size="small"
                style="width: 100%">
        <el-table-column type="index"  align="center">
        </el-table-column>
        <el-table-column prop="deviceNum"
                         align="center"
                         label="设备编号"></el-table-column>
        <el-table-column prop="deviceName"
                         align="center"
                         label="设备名称"></el-table-column>
        <el-table-column prop="installLocationName"
                         align="center"
                         label="位置"></el-table-column>
        <el-table-column prop="deviceModel"
                         align="center"
                         label="型号"></el-table-column>
                         <el-table-column fixed="right"
                         align="center"
                         width="160"
                         label="操作">
          <template slot-scope="scope">
            <el-button type="danger"
                       v-if="!isDisable"
                       :size="GlobalCss.buttonSize"
                       @click="handleDeleteClick(scope.row,scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--设备选择弹窗-->
    <el-dialog title="设备台账"
               :visible.sync="dialogTableVisible" destroy-on-close>
      <eam-list @handlerSelect="handlerSelect"
                :deviceKey="deviceKey"
                flag="child"></eam-list>
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
  mounted () {
    this.getRelatedDeviceByKey()
  },
  methods: {
    // 移除
    handleDeleteClick (row, index) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/removeEamLedger', { params: { deviceKey: this.deviceKey, keys: row.key } }).then(res => {
        if (res.data.resultType === 'ok') {
          this.$message({
            message: res.data.message,
            type: 'success'
          })
          this.tableData.splice(index, 1)
          this.$emit('removedEamKey', row.key)
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
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getChildDevByKey', { params: { key: this.deviceKey } }).then(res => {
        this.tableData = res.data
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handlerConfirm (val) {
      var d = this.tableData
      d.push.apply(d, this.selectDatas)
      var keysStr = ''
      d.forEach(e => {
        keysStr += e.refKey + ','
      })
      this.dialogTableVisible = false
      this.$emit('getRelatedKeys', keysStr.substring(0, keysStr.length - 1))
    },
    handlerSelect (data) { // 获取子组件eamList传递的参数
      this.selectDatas = data
    }
  }
}
</script>
<style scoped>
.operate {
  margin-bottom: 8px;
  float: right;
}
.tableClass {
  margin-top: 10px;
  text-align: center;
}
</style>
