<template >
  <div>
    <template v-for="(items,index) in datas">
    <el-row  :key="index">
      <el-col :span="6">
        <span >{{items.name}}</span>
      </el-col>
      <el-col :span="14">
         <el-progress :key="items.name"
                   :status="items.color"
                   :text-inside="true"
                   :stroke-width="16"
                   :percentage="items.score"></el-progress>
      </el-col>
      <el-col :span="4">
        <a href="#"><span style="color: #409EFF">去补全</span></a>
      </el-col>
    </el-row>
    </template>
  </div>
</template>

<script>
export default {
  data () {
    return {
      datas: [{ color: 'exception', score: 10, name: '1号锅炉' },
        { color: 'exception', score: 15, name: '变压器1号' },
        { color: 'exception', score: 20, name: '磨煤机3号' },
        { color: 'exception', score: 25, name: '传动轴4号' },
        { color: 'exception', score: 30, name: '5号锅炉' },
        { color: 'warning', score: 35, name: '9号变压机' },
        { color: 'warning', score: 40, name: '1号电梯' },
        { color: 'warning', score: 50, name: '4号传感器' },
        { color: 'warning', score: 60, name: '6号升降机' },
        { color: 'success', score: 70, name: '11号输煤机' }
      ]
    }
  }
}
</script>
<style scoped>
  .el-row{
    margin-bottom: 20px;
  }
  .el-col{
    padding-left: 10px;
  }
  .el-col：first-child {
    float: right;
  }
</style>
