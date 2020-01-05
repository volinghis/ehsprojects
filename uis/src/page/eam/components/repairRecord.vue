<template>
  <div class="repair-record-container">
    <el-table :data="tableData"
              border
              size="mini"
              style="width: 100%">
      <el-table-column type="index"
                       width="50"></el-table-column>
      <el-table-column prop="repairName"
                       label="检修名称">
      </el-table-column>
      <el-table-column prop="repairTeam"
                       label="检修班组">
      </el-table-column>
      <el-table-column prop="repairTime"
                       label="检修开始时间">
      </el-table-column>
      <el-table-column prop="repairNature"
                       label="检修性质">
      </el-table-column>
      <el-table-column prop="founder"
                       label="创建人">
      </el-table-column>
      <el-table-column prop="personLiable"
                       label="责任人">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  props: ['tableId'],
  created () {
    // this.initTable()
  },
  data () {
    return {
      tableData: []
    }
  },
  watch: {
    tableId: {
      handler (val, oldVal) {
        // this.initTable()
      }
    }
  },
  methods: {
    initTable () {
      if (this.tableId) {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getRepairRecord').then(res => {
          this.tableData = res.data.filter(data => data.refDevice === this.tableId)
        })
      } else {
        this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedger/getRepairRecord').then(res => {
          this.tableData = res.data
        })
      }
    }
  }
}
</script>
