<template>
  <div style="text-align:center;">
    <el-form style="margin: 40px auto 0;">
      <div style="max-width: 100%;" >
        <i class="el-icon-circle-check" style="color:#67C23A;font-size:58px;"></i><br><br><br>
        <span style="font-size:24px;">操作成功</span>
      </div><br>
    </el-form>
    <el-divider></el-divider>
    <div >
      <el-button type="primary" @click="finish" icon="el-icon-refresh-right">继续添加</el-button>
      <el-button type="primary" @click="over" icon="el-icon-check">完成</el-button>
      <el-button style="margin-left: 8px" @click="toOrderList" icon="el-icon-tickets">查看详情</el-button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'partStepThree',
  data () {
    return {
      loading: false
    }
  },
  methods: {
    finish () {
      this.$emit('finish')
    },
    over () {
      this.$emit('finish')
      this.$router.push({ name: 'partLibraryIndex' })
    },
    toOrderList () {
      this.$router.push({ name: 'partDetails' })
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
