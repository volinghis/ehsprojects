<template>
  <div class="table-wrapper">
    <div>
      <el-autocomplete class="inline-input"
                       v-model="queryParam.query"
                       :size="GlobalCss.buttonSize"
                       style="margin-bottom:10px;width:40%;"
                       placeholder="可查询设备名称设备编号">
        <el-button slot="append"
                   type="primary"
                   icon="el-icon-search">搜索</el-button>
      </el-autocomplete>
    </div>
    <div class="table-list">
      <el-table :data="tableData"
                style="width: 100%"
                ref="leftTable"
                @current-change="handleSelectionChange"
                border
                :size="GlobalCss.controlSize">
          <el-table-column width="50"
                           align="center">
             <template slot-scope="scope">
                <el-radio  v-model="tableRadio" :label="scope.row"><i></i></el-radio>
            </template>
          </el-table-column>
        <el-table-column prop="deviceNum"
                         label="设备编号"
                         align="center"></el-table-column>
        <el-table-column prop="deviceName"
                         label="设备名称"
                         align="center"></el-table-column>
        <el-table-column prop="installLocation"
                         label="类型"
                         align="center"></el-table-column>
        <el-table-column prop="deviceModel"
                         label="型号"
                         align="center"></el-table-column>
        <el-table-column prop="supplier"
                         label="品牌"
                         align="center"></el-table-column>
        <el-table-column prop="installLocation"
                         label="位置"
                         align="center"></el-table-column>
      </el-table>
    </div>
    <div class="pagination"
         style="text-align:right;margin-top:12px;">
      <el-pagination background
                     layout="total, prev, pager, next"
                     @current-change="handleCurrentChange"
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
        query: ''
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
    if (this.flag === 'scrap') {
      this.getLedgerListNotScrap()
    } else if (this.flag === 'allocate') {
      // this.getLedgerListNotAllocate()
      this.getLedgerListNotScrap()// 暂时与报废获取的设备一致
    } else {
      this.getLeftChildList()
    }
    this.$refs.leftTable.clearSelection()
  },
  methods: {
    getLeftChildList () { // 部署当前设备的其他设备
      let reqParam = this.queryParam
      reqParam['deviceKey'] = this.deviceKey ? this.deviceKey : ''
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getLeftEamLedgerList', reqParam).then(res => {
        this.tableData = res.data.dataList
        this.totalCount = res.data.totalCount
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    getLedgerListNotScrap () { // 未报废的设备集合
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getLeftEamLedgerForScrap', this.queryParam).then(res => {
        this.tableData = res.data.dataList
        this.totalCount = res.data.totalCount
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    getLedgerListNotAllocate () { // 未调拨设备，暂时有问题
    },
    handleCurrentChange: function (val) {
      this.queryParam.page = val
      if (this.flag === 'scrap') {
        this.getLedgerListNotScrap()
      } else if (this.flag === 'allocate') {
        // this.getLedgerListNotAllocate()
        this.getLedgerListNotScrap()
      } else {
        this.getLeftChildList()
      }
    },
    handleEditClick: function () {
    },
    handleSelectionChange (val) {
      console.log(val)
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
