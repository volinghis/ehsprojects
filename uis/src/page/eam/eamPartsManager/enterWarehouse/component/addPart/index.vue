<template>
  <div>
    <el-form :model="queryBean">
      <el-input :size="GlobalCss.controlSize" v-model="queryBean.query" placeholder="请输入名称、编号、类型搜索" style="margin:5px 0px; width:30%;float:left;">
        <el-button slot="append" @click="getAllParts" icon="el-icon-search"></el-button>
      </el-input>
    </el-form>
    <template>
      <el-table :data="tableData" border ref="multipleTable" @selection-change="handleSelectionChange" :size="GlobalCss.controlSize">
        <el-table-column type="selection" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="wareHouseCode" label="入库编号" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="wareHouseName" label="所在仓库" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="deviceCode" label="备件编号" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="deviceName" label="备件名称" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="norm" label="规格型号" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="materialType" label="物资类别" show-overflow-tooltip min-width="100" align="center"></el-table-column>
        <el-table-column prop="amount" label="数量" show-overflow-tooltip min-width="70" align="center"></el-table-column>
        <el-table-column prop="warningValue" label="预警值" show-overflow-tooltip min-width="70" align="center">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag type="danger" size="small">{{ scope.row.warningValue }}</el-tag>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination style="text-align:right;" :current-page.sync="queryBean.page" :page-size="queryBean.size"
        @current-change="changePage" layout="total, prev, pager, next" :total="queryBean.totalCount">
      </el-pagination>
    </template>
  </div>
</template>
<script>
export default {
  props: {
    selectWareHouseFlag: String
  },
  data () {
    return {
      multipleSelection: [],
      tableData: [],
      queryBean: {
        flag: '',
        query: '',
        page: 1,
        size: 10,
        totalCount: 0
      }
    }
  },
  mounted: function () {
    this.getAllParts(this.selectWareHouseFlag)
  },
  methods: {
    getAllParts: function (v) {
      this.queryBean.flag = v
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/partsAccount/getAllPartsAccount', this.queryBean)
        .then(res => {
          this.tableData = res.data.dataList
          this.queryBean.totalCount = res.data.totalCount
        })
    },
    changePage (v) {
      this.queryBean.page = v
      this.getAllParts()
    },
    handleSelectionChange: function (val) {
      this.multipleSelection = val
      this.$emit('partsData', this.multipleSelection)
    }
  }
}

</script>
<style lang="scss" scoped>
  /deep/.el-input-group__append {
    background-color: #409eff;
    border: 1px solid #409eff;
    color: #fff;
  }

</style>>
