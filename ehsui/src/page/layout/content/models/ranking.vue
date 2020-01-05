<template >
  <div>
    <el-table :data="datas"
              :size="GlobalCss.buttonSize"
              style="width: 100%">
      <el-table-column prop="rankingUrl"
                       align="center"
                       width="50"
                       label="排名">
        <template slot-scope="scope">
          <el-image :src="scope.row.rankingUrl"
                    fit="fill"  :size="GlobalCss.buttonSize"></el-image>
        </template>
      </el-table-column>
      <!-- <el-table-column prop="circleUrl"
                       align="center"
                       width="50"
                       label="头像">
        <template slot-scope="scope">
          <el-avatar :size="40" :src="scope.row.circleUrl"></el-avatar>
        </template>
      </el-table-column> -->
      <el-table-column prop="name"
                       align="center"
                       label="姓名">
      </el-table-column>
      <el-table-column prop="contribution"
                       align="center"
                       label="贡献度">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data () {
    return {
      datas: [
        { rankingUrl: require('@/assets/images/medals/medal-1.svg'), circleUrl: require('@/assets/Avatar.svg'), name: '张德江', contribution: '25568' },
        { rankingUrl: require('@/assets/images/medals/medal-2.svg'), circleUrl: require('@/assets/Avatar.svg'), name: '赵凯', contribution: '19877' },
        { rankingUrl: require('@/assets/images/medals/medal-3.svg'), circleUrl: require('@/assets/Avatar.svg'), name: '王萌萌', contribution: '17988' },
        { rankingUrl: require('@/assets/images/medals/medal-4.svg'), circleUrl: require('@/assets/Avatar.svg'), name: '刘欣欣', contribution: '17575' },
        { rankingUrl: require('@/assets/images/medals/medal-5.svg'), circleUrl: require('@/assets/Avatar.svg'), name: '刘丹丹', contribution: '16929' },
        { rankingUrl: require('@/assets/images/medals/medal-6.svg'), circleUrl: require('@/assets/Avatar.svg'), name: '赵斌', contribution: '15324' },
        { rankingUrl: require('@/assets/images/medals/medal-7.svg'), circleUrl: require('@/assets/Avatar.svg'), name: '王凯', contribution: '15324' },
        { rankingUrl: require('@/assets/images/medals/medal-8.svg'), circleUrl: require('@/assets/Avatar.svg'), name: '何军', contribution: '15324' },
        { rankingUrl: require('@/assets/images/medals/medal-9.svg'), circleUrl: require('@/assets/Avatar.svg'), name: '王思达', contribution: '15324' },
        { rankingUrl: require('@/assets/images/medals/medal-10.svg'), circleUrl: require('@/assets/Avatar.svg'), name: '何欣鑫', contribution: '15324' }
      ]
    }
  }
}
</script>
<style lang="scss" scoped>
/deep/.el-table__row > td {
  border: none;
}
.el-table::before {
  height: 0px;
}
</style>
