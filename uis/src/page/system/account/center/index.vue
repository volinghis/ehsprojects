<template>

  <div >
            <el-row >
          <el-col :span="8">
             <div class="block-title"
                 style="background:#f2bb5e;">
              <label>我的战绩</label>
            </div>
            <Rates></Rates>
          </el-col>
          <el-col :span="8" class="paddingStyle" >
            <div class="block-title"
                 style="background: #66b1ff;">
              <label>上月榜单（仅展示前10名）</label>
            </div>
            <Ranks></Ranks>
          </el-col>
           <el-col :span="8" class="paddingStyle" >
            <div class="block-title"
                 style="background: #67C23A;">
              <label>设备资料完整度</label>
            </div>

            <Devices></Devices>
          </el-col>
        </el-row>
        <el-row >
          <el-col :span="12">
            <div class="block-title"
                 style="background: #40c9c6;">
              <label>我的待办</label>

            </div>
            <Approves></Approves>
          </el-col>
          <el-col :span="8" class="paddingStyle" >
            <div class="block-title"
                 style="background: #f4516c;">
              <label>任务统计</label>

            </div>

            <Tasks></Tasks>
          </el-col>
                    <el-col :span="4" class="paddingStyle" >
            <div class="block-title"
                 style="background: #f4516c;">
              <label>消息提醒</label>

            </div>

            <Notices></Notices>
          </el-col>
        </el-row>
        <el-row >

          <el-col :span="12">
            <div class="block-title"
                 style="background: #5d75f2;">
              <label>缺陷统计-按专业</label>

            </div>

            <Professiona/>
          </el-col>
          <el-col :span="12" class="paddingStyle" >
            <div class="block-title"
                 style="background:#36a3f7;">
              <label>缺陷统计-按系统</label>

            </div>
            <Systems />
          </el-col>
        </el-row>
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
