<template >
  <div>
    <template v-for="(items,index) in datas">
      <el-row :key="index">
        <el-col :span="6">
          <el-avatar :size="size" :src="items.rankingUrl"></el-avatar>
          <!-- <i class="el-icon-medal" style="font-size: x-large; color:#d4d434;"></i> -->
        </el-col>
        <el-col :span="6">
          <el-avatar :size="size" :src="items.circleUrl"></el-avatar>
        </el-col>
        <el-col :span="6">
          <span style="color: darkmagenta;">{{items.name}}</span>
        </el-col>
        <el-col :span="6">
          <span style="color: hotpink;">{{items.contribution}}</span>
        </el-col>
      </el-row>
    </template>
  </div>
</template>

<script>
export default {
  data () {
    return {
      size: 'small',
      datas: [
        { rankingUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', name: '张德江', contribution: '25568' },
        { rankingUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', name: '赵凯', contribution: '19877' },
        { rankingUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', name: '王萌萌', contribution: '17988' },
        { rankingUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', name: '刘欣欣', contribution: '17575' },
        { rankingUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', name: '刘丹丹', contribution: '16929' },
        { rankingUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', name: '赵斌', contribution: '15324' },
        { rankingUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', name: '王凯', contribution: '15324' },
        { rankingUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', name: '何军', contribution: '15324' },
        { rankingUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', name: '王思达', contribution: '15324' },
        { rankingUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', name: '何欣鑫', contribution: '15324' }
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
