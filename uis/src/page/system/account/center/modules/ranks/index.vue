<template>
  <div>
       <el-table :data="ranks"
                        :size="GlobalCss.buttonSize"
                        :height="(($store.state.contentHeight-10)/3-30)"
                        >

      <el-table-column prop="userName"
                        align="center"
                        >
                         <template slot-scope="scope">
          <el-image :src="findUrl(scope.$index+1)" style="width:35px;height:35px"
                    fit="fill"  size="large"></el-image>
      </template>
      </el-table-column>

      <el-table-column prop="userName"
                        align="center"
                        >
      </el-table-column>
      <el-table-column prop="score"
                        width="200"
                        align="center"
                        >
       <template slot-scope="scope">
    <span class="rankscore">{{scope.row.score}}</span>
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
