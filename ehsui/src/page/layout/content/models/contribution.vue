  <template>
  <el-table :data="tableData"
            :show-header="false"
            :size="GlobalCss.buttonSize"
            style="width: 100%">
    <el-table-column prop="time">
    </el-table-column>
    <el-table-column prop="type">
    </el-table-column>
    <el-table-column prop="content">
    </el-table-column>
    <el-table-column prop="score"
                     width="80">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data () {
    return {
      tableData: [{
        time: '1分钟前',
        score: '+20',
        content: '电气办公室办公物品申请',
        type: '办公申请'
      }, {
        time: '30分钟前',
        score: '+20',
        content: '我的请假申请',
        type: '请假申请'
      }, {
        time: '1小时15分钟前',
        score: '+20',
        content: '1号机组检修申请',
        type: '检修申请'
      }, {
        time: '12.11 15:45',
        score: '+20',
        content: '电气办公室办公物品申请',
        type: '办公申请'
      }]
    }
  }
}
</script>
