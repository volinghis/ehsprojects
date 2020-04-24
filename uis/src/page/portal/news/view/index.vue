<template>
  <div>
    <el-row class="title">{{data.newsTitle}}<br><div class="creationTime">{{data.creationTime}}</div></el-row>
    <el-divider></el-divider>
    <el-row>
      <div class="content" v-html="data.newsContent"></div>
    </el-row>
  </div>
</template>

<script>
import datas from './datas.js'
export default datas
</script>

<style lang="scss" scoped>
@import "./styles.scss";
</style>
