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
            <el-submenu index="2">
              <template slot="title">公司概况</template>
              <el-menu-item index="2-1">公司简介</el-menu-item>
              <el-menu-item index="2-2">自然地理</el-menu-item>
              <el-menu-item index="2-3">历史沿革</el-menu-item>
              <el-menu-item index="2-4">组织人员</el-menu-item>
              <el-menu-item index="2-5">综合实力</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">行政咨讯</template>
              <el-menu-item index="3-1">行政体制</el-menu-item>
              <el-menu-item index="3-2">政策法规</el-menu-item>
            </el-submenu>
            <el-submenu index="4">
              <template slot="title">信息公开</template>
              <el-menu-item index="4-1">政府公文</el-menu-item>
              <el-menu-item index="4-2">规划计划</el-menu-item>
              <el-menu-item index="4-3">项目核准</el-menu-item>
            </el-submenu>
            <el-submenu index="5">
              <template slot="title">解读回应</template>
              <el-menu-item index="5-1">政策解读</el-menu-item>
              <el-menu-item index="5-2">回应资讯</el-menu-item>
            </el-submenu>
            <el-menu-item index="6">党政工作</el-menu-item>
            <el-menu-item index="7">公司活动</el-menu-item>
            <!-- <el-menu-item index="8">后台管理</el-menu-item> -->
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
