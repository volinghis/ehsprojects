<template>
  <div>
       <el-table :data="rates"
                        :size="GlobalCss.buttonSize"
                        :height="(($store.state.contentHeight-10)/3-30)"
                        >

      <el-table-column prop="label"
                        align="center"
                        >
      </el-table-column>
      <el-table-column prop="score"
                        align="center"
                        >
                         <template slot-scope="scope">
    <span class="ratescore">{{scope.row.score}}</span>
      </template>
      </el-table-column>
      <el-table-column prop="rate"
                        width="200"
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
