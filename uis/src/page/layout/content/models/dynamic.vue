<template >
  <div>
    <template v-for="(items,index) in datas">
      <el-row :key="index">
        <el-col :span="3">
          <el-avatar :size="size" :src="circleUrl"></el-avatar>
          <span >{{items.avatar}}</span>
        </el-col>
        <el-col :span="3">
          <span >{{items.name}}</span>
        </el-col>
        <el-col :span="4">
          <span style="color: #909399;">{{items.time}}</span>
        </el-col>
        <el-col :span="5">
          <span style="color: #909399;">{{items.team}}</span>
        </el-col>
        <el-col :span="4">
          <span >{{items.content}}</span>
        </el-col>
        <el-col :span="5">
          <a href="#"><span style="color: #409EFF">{{items.title}}</span></a>
        </el-col>
      </el-row>
    </template>
  </div>
</template>
<script>
export default {
  data () {
    return {
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      size: 'small',
      datas: [
        { name: '沈十三', time: '3分钟前', team: '设备部热控二班', content: '修改设备资料', title: '刀闸' },
        { name: '李小七', time: '10分钟前', team: '运维部燃料队', content: '发布文章', title: '电工高手维修经验总结' },
        { name: '王大拿', time: '30分钟前', team: '运维部脱硫检修队', content: '删除设备', title: '阀门001' },
        { name: '常大哲', time: '1小时前', team: '运维部汽机队', content: '发布文章', title: '伺服电机使用手册' },
        { name: '王思达', time: '1小时10分钟前', team: '检修班组', content: '新建设备', title: '8号电抗器中性点电抗器' },
        { name: '刘丹丹', time: '1小时30分钟前', team: '电气专业', content: '新建检修记录', title: '柴油发电机' }
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
    padding-left: 1px;
  }
  .el-col：first-child {
    float: right;
  }
</style>
