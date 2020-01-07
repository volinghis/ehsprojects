 <template>
  <el-table :data="tableData"
    :max-height="($store.state.contentHeight/2-40-30)"
            :size="GlobalCss.buttonSize"
            style="width: 100%">
    <el-table-column prop="taskNum"
                     align="center"
                     label="流程名称">
    </el-table-column>
    <el-table-column prop="taskName"
                     align="center"
                     label="当前环节">
    </el-table-column>
    <el-table-column prop="startTime"
                     align="center"
                     label="申请人">
    </el-table-column>
    <el-table-column prop="endTime"
                     align="center"
                     label="申请时间">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data () {
    return {
      tableData: [{
        startTime: '2016-05-02',
        endTime: '2016-05-02',
        taskName: '压力管道巡检',
        taskNum: 'XJRW20191202001',
        status: '进行中'
      }, {
        startTime: '2016-05-02',
        endTime: '2016-05-02',
        taskName: '压力管道巡检',
        taskNum: 'XJRW20191202001',
        status: '进行中'
      }, {
        startTime: '2016-05-02',
        endTime: '2016-05-02',
        taskName: '压力管道巡检',
        taskNum: 'XJRW20191202001',
        status: '进行中'
      }, {
        startTime: '2016-05-02',
        endTime: '2016-05-02',
        taskName: '压力管道巡检',
        taskNum: 'XJRW20191202001',
        status: '进行中'
      }]
    }
  }
}
</script>
