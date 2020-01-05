<template>
  <div>
    <el-table :data="tableData" style="width: 100%" size="mini">
      <el-table-column prop="date" label="日期" width="110"></el-table-column>
      <el-table-column prop="deviceName" label="检修设备" width="170"></el-table-column>
      <el-table-column prop="nature" label="检修性质" width="120"></el-table-column>
      <el-table-column prop="team" label="检修班组" width="150"></el-table-column>
      <el-table-column prop="name" label="负责人" width="120"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data () {
    return {
      tableData: [{
        date: '2016-05-02',
        deviceName: '氨区2号废水泵',
        nature: '临时检修',
        team: '运维部汽机队',
        name: '王小虎',
        status: '检修中'
      }, {
        date: '2016-05-02',
        deviceName: '柴油发电机',
        nature: '日常检修',
        team: '运维部汽机队',
        name: '王小虎',
        status: '已结束'
      }, {
        date: '2016-05-02',
        deviceName: '2C循环水泵',
        nature: 'C级检修',
        team: '运维部汽机队',
        name: '王小虎',
        status: '检修中'
      }, {
        date: '2016-05-02',
        deviceName: '一号炉煤气系统',
        nature: 'A级检修',
        team: '运维部汽机队',
        name: '王小虎',
        status: '检修中'
      }, {
        date: '2016-05-02',
        deviceName: '一号炉煤气系统',
        nature: 'A级检修',
        team: '运维部汽机队',
        name: '王小虎',
        status: '检修中'
      }, {
        date: '2016-05-02',
        deviceName: '2A给煤机',
        nature: '临时检修',
        team: '运维部汽机队',
        name: '王小虎',
        status: '检修中'
      }]
    }
  }
}
</script>
