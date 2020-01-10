<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="流程" name="first">
      <el-table :data="tableData" size="mini">
        <el-table-column type="index" width="20"> </el-table-column>
        <el-table-column prop="process" width="80" label="流程"> </el-table-column>
        <el-table-column prop="date" width="100" label="日期"> </el-table-column>
        <el-table-column prop="name" width="80"  label="流转"> </el-table-column>
        <el-table-column prop="status" width="80"  label="状态"> </el-table-column>
      </el-table>
    </el-tab-pane>
    <el-tab-pane label="任务" name="second">任务管理</el-tab-pane>
    <el-tab-pane label="计划" name="third">计划管理</el-tab-pane>
  </el-tabs>
</template>
<script>
export default {
  data () {
    return {
      activeName: 'first',
      tableData: [
        {
          date: '2016/05/02',
          process: '办公申请',
          name: '张工',
          status: '申请中'
        },
        {
          date: '2016/05/04',
          process: '请假申请',
          name: '王主任',
          status: '驳回'
        },
        {
          date: '2016/05/01',
          process: '报废申请',
          name: '刘主任',
          status: '完结'
        },
        {
          date: '2016/05/03',
          process: '转移申请',
          name: '赵班长',
          status: '申请中'
        },
        {
          date: '2019/11/11',
          process: '检修申请',
          name: '赵班长',
          status: '完结'
        }
      ]
    }
  },
  methods: {
    handleClick (tab, event) {
    }
  }
}
</script>
