<template>
  <div>
    <div class="topleft"><i class="fa fa-paper-plane-o fa-3x"></i><span>设备管理系统</span></div>
    <div class="topright">
      <i class='fa fa-user fa-1x'></i>
      <span>admin{{sessionUser.username}}({{sessionUser.account}})</span>
      <br>
      <span @click="logout()" title="安全退出"><i class='fa el-icon-warning logout'></i></span></div>
  </div>
</template>
<script>
import datas from './datas.js'
export default datas
</script>

<style lang="scss" scoped>
@import "./styles.scss";
</style>
