<template>
  <div>
       <el-table :data="datas"
                        :size="GlobalCss.buttonSize"
                        :height="(($store.state.contentHeight-10)/3-30)"
                        >

      <el-table-column prop="deviceName"
                        align="center"
                        label="设备名称"
                         width="100"
                        show-overflow-tooltip
                        >

      </el-table-column>
      <el-table-column prop="completePoint"
                        align="center"
                         label="完整度"
                        >
 <template slot-scope="scope">
            <el-progress
                   :status="findColor(scope.row.completePoint)"
                   :stroke-width="14"
                   :percentage="scope.row.completePoint"></el-progress>
      </template>
      </el-table-column>
           <el-table-column prop="person"
                        align="center"
                        label="责任人"
                          width="60"
                        >

      </el-table-column>
      <el-table-column prop="completePoint"
                        align="center"
                         label="操作"
                         width="60"
                        >
      <template slot-scope="scope">
     <span class="processNames" @click="deviceClick(scope.row.completePoint)">去补全</span>
      </template>
      </el-table-column>
        </el-table>

  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss'
</style>
