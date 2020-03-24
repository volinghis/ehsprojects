<template>
  <div class="table-wrapper">
    <div>
      <el-input placeholder="根据设备名称查询"
                    v-model="queryParam.name" :size="GlobalCss.buttonSize" style="width:30%;float:left;margin-bottom:8px;">
            <template slot="append">
              <el-button type="primary"
                         :size="GlobalCss.buttonSize"
                         icon="el-icon-search"
                         @click="reloadTable">
              </el-button>
            </template>
          </el-input>
    </div>
    <div class="table-list">
      <el-table :data="tableData"
                style="width: 100%"
                highlight-current-row
                ref="leftTable"
                @current-change="handleCurrentChange"
                @selection-change="handleSelectionChange"
                border
                :size="GlobalCss.controlSize">
        <template v-if="flag!=='child'">
          <el-table-column width="50"
                           align="center">
            <template slot-scope="scope">
              <el-radio v-model="tableRadio"
                        :label="scope.row"><i></i></el-radio>
            </template>
          </el-table-column>
        </template>
        <template v-else>
          <el-table-column type="selection"
                           width="55">
          </el-table-column>
        </template>
        <el-table-column prop="deviceNum"
                         label="设备编号"
                         width="160"
                         align="center"></el-table-column>
        <el-table-column prop="deviceName"
                         label="设备名称"
                         align="center"></el-table-column>
        <el-table-column prop="installLocationName"
                         label="位置"
                         align="center"></el-table-column>
        <el-table-column prop="deviceModel"
                         label="型号"
                         align="center"></el-table-column>
        <el-table-column prop="deviceStatus"
                         label="状态"
                         align="center"></el-table-column>
      </el-table>
    </div>
    <div class="pagination"
         style="text-align:right;margin-top:12px;">
      <el-pagination background
                     layout="total, prev, pager, next"
                     @current-change="handleCurrentPageChange"
                     :current-page="queryParam.page"
                     :page-size="queryParam.size"
                     :total="totalCount">
      </el-pagination>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      queryParam: {
        size: 5,
        page: 1,
        name: ''
      },
      tableRadio: {},
      form: {},
      tableData: [],
      totalCount: 0
    }
  },
  props: {
    deviceKey: String,
    flag: String
  },
  mounted: function () {
    if (this.flag === 'child') {
      this.getLeftChildList()
    } else {
      this.getLedgerListNotInFlow()
    }
    this.$refs.leftTable.clearSelection()
  },
  methods: {
    getLeftChildList () { // 不属于当前设备子设备的其他设备
      let reqParam = this.queryParam
      reqParam['deviceKey'] = this.deviceKey ? this.deviceKey : ''
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getLeftEamLedgerList', reqParam).then(res => {
        this.tableData = res.data.dataList
        this.totalCount = res.data.totalCount
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    getLedgerListNotInFlow () { // 未报废的设备集合
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getEamLedgersNotInFlow', this.queryParam).then(res => {
        this.tableData = res.data.dataList
        this.totalCount = res.data.totalCount
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handleCurrentPageChange: function (val) {
      this.queryParam.page = val
      if (this.flag === 'child') {
        this.getLeftChildList()
      } else {
        this.getLedgerListNotScrap()
      }
    },
    reloadTable () {
      if (this.flag === 'child') {
        this.getLeftChildList()
      } else {
        this.getLedgerListNotScrap()
      }
    },
    handleCurrentChange (val) {
      this.$emit('handlerOneSelect', val)
    },
    handleSelectionChange (val) {
      this.$emit('handlerSelect', val)
    }
  }
}
</script>
<style lang="scss" scoped>
// 搜索按钮样式
/deep/.el-input-group__append {
  background-color: #409eff;
  border-color: #409eff;
  color: #ffffff;
}
</style>
