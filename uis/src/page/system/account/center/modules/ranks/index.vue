<template>
  <div>
       <el-table :data="ranks"
                        :size="GlobalCss.buttonSize"
                        :height="370"
                        >

      <el-table-column prop="userName"
                        align="center"
                        min-width="35"
                        >
                         <template slot-scope="scope">
          <el-image :src="findUrl(scope.$index+1)" style="width:25px;height:25px"
                    fit="fill"  ></el-image>
      </template>
      </el-table-column>

      <el-table-column prop="userName"
                        align="center"
                        show-overflow-tooltip
                        >
      </el-table-column>
      <el-table-column prop="score"
                        min-width="50"
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
