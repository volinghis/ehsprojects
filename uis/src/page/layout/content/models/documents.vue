<template>
<div>
  <template v-for="items in datas">
     <a  :key="items.title" href="#"><span>{{items.title}}</span></a>
     <div :key="items.key">
       <el-rate :value="items.rate" disabled show-score text-color="#ff9900"></el-rate>
        <span><i class="el-icon-date"></i>发布日期:{{items.createTime}}</span>
        <el-divider direction="vertical"></el-divider>
        <span><i class="el-icon-view"></i>浏览量:{{items.browseCount}}</span>
        <el-divider direction="vertical"></el-divider>
        <span><i class="el-icon-thumb"></i>点赞数量:{{items.upCount}}</span>
     <br><br>
     </div>
  </template>
 </div>
</template>
<script>
export default {
  data () {
    return {
      datas: [{ title: '输煤机在零度以下工作时长判断标准以及动力标准', browseCount: 120, upCount: 100, createTime: '2015-12-13', rate: 4.7 },
        { title: '液压机标准液压调整', browseCount: 150, upCount: 80, createTime: '2016-02-13', rate: 4.5 },
        { title: '循环供水系统常用故障处理方案', browseCount: 130, upCount: 90, createTime: '2017-10-13', rate: 4.2 },
        { title: '磨煤机性能提升方案', browseCount: 110, upCount: 70, createTime: '2018-02-03', rate: 4.1 },
        { title: '高温作业常备知识以及关键操作注意事项', browseCount: 100, upCount: 75, createTime: '2019-05-04', rate: 4.0 }
      ]
    }
  }
}
</script>
<style scoped>
  .el-rate{
    margin-left: 0px;
  }
  a span{
    font-size: 14px;
    color:black
  }
  span{
    margin-left: 0px;
    color:#909399
  }
</style>
