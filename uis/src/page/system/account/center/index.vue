<template>

  <div class="panel">

      <el-row  :gutter="10" >
      <el-col :span="16" >
        <el-card>
          <div slot="header" class="clearfix">
            <span>我的待办</span>
          </div>
          <Approves></Approves>
        </el-card>
      </el-col>
      <el-col :span="8"  >
        <!-- <div class="block-title"
                 style="background: #66b1ff;">
              <label>上月榜单（仅展示前10名）</label>
            </div> -->
         <el-card >
          <div slot="header" class="clearfix">

            <span>我的绩效</span>
          </div>
          <Rates></Rates>
        </el-card>
      </el-col>

    </el-row>
    <el-row  :gutter="10" >
      <el-col :span="12" >
       <el-card>
          <div slot="header" class="clearfix">
            <span>设备资料完整度</span>
          </div>
          <Devices></Devices>
        </el-card>
      </el-col>

      <el-col :span="7" class="paddingStyle">
        <!-- <div class="block-title"
                 style="background: #67C23A;">
              <label>设备资料完整度</label>
            </div> -->
      <el-card>
          <div slot="header" class="clearfix">
            <span>消息提醒</span>
          </div>
          <Notices></Notices>
        </el-card>
      </el-col>
       <el-col :span="5"  class="paddingStyle">

        <!-- <div class="block-title"
                 style="background: #66b1ff;">
              <label>上月榜单（仅展示前10名）</label>
            </div> -->
        <el-card>
          <div slot="header"  class="clearfix">
            <span>上月榜单（仅展示前8名）</span>
          </div>
          <Ranks></Ranks>
        </el-card>
      </el-col>
    </el-row>
<!--
    <el-row  :gutter="10" >
      <el-col :span="8" >
        <el-card >
          <div slot="header" class="clearfix">
            <span>我的绩效</span>
          </div>
          <Rates></Rates>
        </el-card>
      </el-col>
      <el-col :span="8"  class="paddingStyle">

        <el-card>
          <div slot="header"  class="clearfix">

            <span>上月榜单（仅展示前10名）</span>
          </div>
          <Ranks></Ranks>
        </el-card>
      </el-col>
      <el-col :span="8" class="paddingStyle">

        <el-card>
          <div slot="header" class="clearfix">
            <span>设备资料完整度</span>
          </div>
          <Devices></Devices>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="12">

        <el-card>
          <div slot="header" class="clearfix">
            <span>我的待办</span>
          </div>
          <Approves></Approves>
        </el-card>
      </el-col>
      <el-col :span="8" class="paddingStyle">

        <el-card>
          <div slot="header" class="clearfix">
            <span>任务统计</span>
          </div>
          <Tasks></Tasks>
        </el-card>
      </el-col>
      <el-col :span="4" class="paddingStyle">

        <el-card>
          <div slot="header" class="clearfix">
            <span>消息提醒</span>
          </div>
          <Notices></Notices>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="10">

      <el-col :span="12">

        <el-card>
          <div slot="header" class="clearfix">
            <span>缺陷统计-按专业</span>
          </div>
          <Professiona />
        </el-card>
      </el-col>
      <el-col :span="12" class="paddingStyle">

        <el-card>
          <div slot="header" class="clearfix">
            <span>缺陷统计-按系统</span>
          </div>
          <Systems />
        </el-card>
      </el-col>
    </el-row>

    -->

  </div>
</template>
<script>
import Professiona from './modules/defectsAnalysis/professiona/index.vue'
import Systems from './modules/defectsAnalysis/systems/index.vue'
import Tasks from './modules/tasksAnalysis/index.vue'

// import Applys from './modules/applys/index'
// import Dones from './modules/dones/index.vue'
import Approves from './modules/approves/index.vue'
import Notices from './modules/notices/index.vue'
import Rates from './modules/rates/index'
import Ranks from './modules/ranks/index'
import Devices from './modules/devices/index'
import datas from './datas'
export default datas
datas.components = {
  // Dones,
  Systems,
  Approves,
  Notices,
  Tasks,
  // Applys,
  Professiona,
  Rates,
  Devices,
  Ranks
}

</script>

<style lang="scss" scoped>
  @import "@/styles/mixin.scss";
  @import "./styles.scss";

</style>
