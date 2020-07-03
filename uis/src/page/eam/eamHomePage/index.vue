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
      <!-- <el-col :span="12" >
       <el-card>
          <div slot="header" class="clearfix">
            <span>设备资料完整度</span>
          </div>
          <Devices></Devices>
        </el-card>
      </el-col> -->

      <el-col :span="16" class="paddingStyle">
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
       <el-col :span="8"  class="paddingStyle">
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
  </div>
</template>
<script>
import Approves from './modules/approves/index.vue'
import Notices from './modules/notices/index.vue'
import Rates from './modules/rates/index'
import Ranks from './modules/ranks/index'
import Devices from './modules/devices/index'
import datas from './datas'
export default datas
datas.components = {
  Approves,
  Notices,
  Rates,
  Devices,
  Ranks
}
</script>

<style lang="scss" scoped>
  @import "@/styles/mixin.scss";
  @import "./styles.scss";

</style>
