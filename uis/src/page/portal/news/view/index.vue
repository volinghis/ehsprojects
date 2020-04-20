<template>
  <div>
    <el-row class="title">{{data.newsTitle}}</el-row>
    <el-divider></el-divider>
    <el-row>
      <div class="content" v-html="data.newsContent"></div>
    </el-row>
  </div>
</template>
<script>
export default {
  data () {
    return {
      data: {
        newsTitle: '',
        newsContent: ''
      }
    }
  },
  mounted () {
    this.data = this.$router.currentRoute.query.data
  }
}
</script>
<style scoped>
  .title{
    text-align: center;
    font-size: 30px;
    margin-bottom: 20px;
  }
  .content{
    text-align: left;
  }
</style>
