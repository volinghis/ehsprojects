<template>
  <div>
       <el-table :data="rates"
                        :size="GlobalCss.buttonSize"
                        :height="210"
                        >

      <el-table-column prop="label"
      min-width="60"
                        align="center"
                        >
      </el-table-column>
      <el-table-column prop="score"
                        align="center"
                         min-width="40"
                        >
                         <template slot-scope="scope">
    <span class="ratescore">{{scope.row.score}}</span>
      </template>
      </el-table-column>
      <el-table-column prop="rate"
                        min-width="100"
                        align="center"
                        >
                         <template slot-scope="scope">
       <el-rate
  :value="transNum(scope.row.rate)"
  disabled
  show-score
  text-color="#ff9900"
  >
</el-rate>
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
