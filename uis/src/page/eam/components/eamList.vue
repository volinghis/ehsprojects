<template>
  <div class="table-wrapper">
    <div>
      <el-form :inline="true" label-width="80px" :size="GlobalCss.buttonSize">
        <el-form-item label="设备编号:">
          <el-input v-model="queryParam.deviceNum"/>
        </el-form-item>
        <el-form-item label="设备名称:">
          <el-input v-model="queryParam.deviceNum"/>
        </el-form-item>
        <el-button type="primary" :size="GlobalCss.buttonSize"
                   @click="$refs.table.refresh(true)"
                   icon="el-icon-search">查询</el-button>
        <el-button :size="GlobalCss.buttonSize"
                   @click="() => (queryParam = {})">重置</el-button>
      </el-form>
    </div>
    <div class="table-list">
      <el-table :data="tableData"
                style="width: 100%"
                border
                :size="GlobalCss.controlSize">
        <el-table-column type="selection"
                         width="50" align="center"> </el-table-column>
        <el-table-column prop="deviceNum"
                         label="设备编号" align="center"></el-table-column>
        <el-table-column prop="deviceName"
                         label="设备名称" align="center"></el-table-column>
        <el-table-column prop="installLocation"
                         label="类型" align="center"></el-table-column>
        <el-table-column prop="deviceModel"
                         label="型号" align="center"></el-table-column>
        <el-table-column prop="brand"
                         label="品牌" align="center"></el-table-column>
        <el-table-column prop="position"
                         label="位置" align="center"></el-table-column>
      </el-table>
    </div>
    <div class="pagination"
         style="text-align:right;margin-top:12px;">
      <el-pagination background
                     layout="total, prev, pager, next"
                     :current-page="1"
                     :page-size="10"
                     :total="100"
                     @current-change="handlePageChange">
      </el-pagination>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      queryParam: {},
      form: {},
      tableData: [
        {
          deviceNum: '2019100001',
          deviceName: '锅炉',
          installLocation: '锅炉专业',
          deviceModel: '6314-2RS',
          purchaseTime: '2016-05-02',
          brand: '西安东恒',
          position: '一号机组'
        },
        {
          deviceNum: '2019100001',
          deviceName: '锅炉',
          installLocation: '锅炉专业',
          deviceModel: '6314-2RS',
          purchaseTime: '2016-05-02',
          brand: '西安东恒',
          position: '一号机组'
        },
        {
          deviceNum: '2019100001',
          deviceName: '锅炉',
          installLocation: '锅炉专业',
          deviceModel: '6314-2RS',
          purchaseTime: '2016-05-02',
          brand: '西安东恒',
          position: '一号机组'
        },
        {
          deviceNum: '2019100001',
          deviceName: '锅炉',
          installLocation: '锅炉专业',
          deviceModel: '6314-2RS',
          purchaseTime: '2016-05-02',
          brand: '西安东恒',
          position: '一号机组'
        }
      ]
    }
  },
  mounted: function () {
  },
  methods: {
    customColorMethod: function (percentage) {
      if (percentage < 30) {
        return '#909399'
      } else if (percentage < 70) {
        return '#e6a23c'
      } else {
        return '#67c23a'
      }
    },
    handlePageChange: function () {
    },
    handConfirmClick: function () {
    },
    handleEditClick: function () {
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
