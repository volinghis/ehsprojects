<template>
  <div>
    <div class="topleft">
      <div style="float:left;">
        <el-image :src="dhUrl" style="width: 40px; height: 40px; vertical-align: middle;margin-left: 5px;"></el-image>
      </div>
      <div style="float:right;">
        <span>东恒设备智能管控平台</span><br>
      </div>
    </div>
    <div class="topright">
      <div style="float:left">
        <el-avatar style="margin-right:10px;margin-top:12px;" :size="34" :src="avatarUrl"></el-avatar>
      </div>
      <div style="float:right;padding-top: 15px;">
        <span style="margin-right:25px;">欢迎您，{{sessionUser.username}}({{sessionUser.account}})</span>
        <span @click="logout()" title="安全退出"><i class='fa fa-sign-out logout'> 退出登录</i></span>
      </div>
    </div>
  </div>
</template>
<script>
import datas from './datas.js'
export default datas
</script>

<style lang="scss" scoped>
@import "./styles.scss";
</style>
