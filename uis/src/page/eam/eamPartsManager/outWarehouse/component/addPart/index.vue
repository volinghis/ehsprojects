<template>
  <div>
    <el-form :model="form">
      <el-input :size="GlobalCss.controlSize" v-model="form.query" placeholder="请输入名称、编号、类型搜索" style="margin:5px 0px; width:30%;float:left;">
        <el-button slot="append" @click="getAllParts" icon="el-icon-search"></el-button>
      </el-input>
    </el-form>
    <template>
      <el-table :data="tableData" border ref="multipleTable" @selection-change="handleSelectionChange" :size="GlobalCss.controlSize">
        <el-table-column type="selection" align="center"></el-table-column>
        <el-table-column prop="deviceCode" label="备件编号" align="center"></el-table-column>
        <el-table-column prop="deviceName" label="备件名称" align="center"></el-table-column>
        <el-table-column prop="norm" label="规格型号" align="center"></el-table-column>
        <el-table-column prop="materialType" label="物资类别" align="center"></el-table-column>
        <el-table-column prop="amount" label="数量" align="center"></el-table-column>
        <el-table-column prop="dummyAmount" label="虚拟数量" align="center"></el-table-column>
        <el-table-column prop="warningValue" label="预警值" align="center"></el-table-column>
      </el-table>
      <el-pagination style="text-align:right;" background :current-page.sync="form.page" :page-size="form.size" layout="total, prev, pager, next" :total="totalCount"></el-pagination>
    </template>
  </div>
</template>
<script>
export default {
  data () {
    return {
      multipleSelection: [],
      totalCount: 0,
      tableData: [],
      form: {
        query: '',
        page: 1,
        size: 20
      }
    }
  },
  mounted: function () {
    this.getAllParts()
  },
  methods: {
    getAllParts: function () {
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/partsAccount/getPartsAccountAll', this.form)
        .then(res => {
          this.tableData = res.data.dataList
          this.totalCount = res.data.totalCount
        })
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
