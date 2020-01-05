  <template>
  <el-table :data="tableData"
            :size="GlobalCss.buttonSize"
            style="width: 100%">
    <el-table-column prop="appName"
                     align="center"
                     width="180"
                     label="申请名称">
    </el-table-column>
    <el-table-column prop="appType"
                     align="center"
                     label="申请类型">
    </el-table-column>
    <el-table-column prop="date"
                     align="center"
                     label="时间">
    </el-table-column>
    <el-table-column prop="person"
                     align="center"
                     label="流转">
    </el-table-column>
    <el-table-column prop="status"
                     align="center"
                      width="80"
                     label="状态">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data () {
    return {
      tableData: [{
        date: '2016-05-02',
        person: '王小虎',
        status: '申请中',
        appName: '电气办公室办公物品申请',
        appType: '办公申请'
      }, {
        date: '2016-05-02',
        person: '王小虎',
        status: '申请中',
        appName: '我的请假申请',
        appType: '请假申请'
      }, {
        date: '2016-05-02',
        person: '王小虎',
        status: '申请中',
        appName: '1号机组检修申请',
        appType: '检修申请'
      }, {
        date: '2016-05-02',
        person: '王小虎',
        status: '申请中',
        appName: '电气办公室办公物品申请',
        appType: '办公申请'
      }]
    }
  }
}
</script>
