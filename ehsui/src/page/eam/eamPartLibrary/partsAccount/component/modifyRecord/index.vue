<template>
  <el-table :data="tableData" size="mini" class="tableClass">
    <el-table-column type="index" width="50" align="center"> </el-table-column>
    <el-table-column prop="date" label="修改时间" align="center"></el-table-column>
    <el-table-column prop="name" label="修改人员" align="center"></el-table-column>
    <el-table-column prop="department" label="所在部门" align="center"></el-table-column>
    <el-table-column prop="operating" label="操作" align="center"></el-table-column>
  </el-table>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss';
</style>
