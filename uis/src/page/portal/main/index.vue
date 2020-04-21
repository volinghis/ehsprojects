<template>
  <div class="pagePanel">
    <div class="lineDiv"></div>

    <el-container>
      <el-header>
        <div class="headerPanel">
        <div class="topleft">
          <div style="float:left;">
            <el-image :src="dhUrl" style="width: 40px; height: 40px; vertical-align: middle;"></el-image>
          </div>
          <div style="float:right;">
            <span class="titlespan"><b>&nbsp;大&nbsp;唐&nbsp;电&nbsp;力</b></span>
          </div>
        </div>
        <div class="topright">
          <el-menu  mode="horizontal" >
            <el-menu-item index="1">首页</el-menu-item>
            <el-menu-item index="2">公司新闻</el-menu-item>
            <el-menu-item index="3">公司公告</el-menu-item>
            <el-menu-item index="4">专题报道</el-menu-item>
            <el-menu-item index="5">行业新闻</el-menu-item>
            <el-menu-item index="6">招标公告</el-menu-item>
            <el-menu-item index="6">媒体聚焦</el-menu-item>
            <el-menu-item index="7">党政工作</el-menu-item>
            <el-menu-item index="8">公司活动</el-menu-item>

          </el-menu>
        </div>
      </div>
      </el-header>
      <el-main><router-view></router-view></el-main>
      <el-footer class="footerPanel">东恒鑫源软件开发有限公司版权所有Copyright © 2020 <br>京备pc1231212号</el-footer>
    </el-container>
  </div>
</template>
<script>import datas from './datas.js'
export default datas
</script>
<style lang="scss" scoped>
  @import './styles.scss';
</style>
