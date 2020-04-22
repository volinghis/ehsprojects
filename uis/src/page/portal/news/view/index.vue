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
export default {
  data () {
    return {
      data: {
        newsTitle: '',
        newsContent: '',
        creationTime: ''
      }
    }
  },
  mounted () {
    this.data = this.$router.currentRoute.query.data
    console.log(this.$router.currentRoute.query.data)
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
  .creationTime{
    font-size: 12px;
    text-align: right;
    color: #a9a9a9;
  }
</style>
