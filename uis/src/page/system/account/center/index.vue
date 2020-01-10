<template>
  <div >
        <el-row >
          <el-col :span="15">
            <div class="block-title"
                 style="background: #40c9c6;">
              <label>我的代办</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <Approves></Approves>
          </el-col>
          <el-col :span="9" class="paddingStyle" >
            <div class="block-title"
                 style="background: #f4516c;">
              <label>消息提醒</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <Notices></Notices>
          </el-col>
        </el-row>
        <el-row >
          <el-col :span="12">
            <div class="block-title"
                 style="background: #5d75f2;">
              <label>我的申请</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <Applys/>
          </el-col>
          <el-col :span="12" class="paddingStyle" >
            <div class="block-title"
                 style="background:#36a3f7;">
              <label>我的已办</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <Dones />
          </el-col>
        </el-row>
  </div>
</template>
<script>
import Applys from './modules/applys/index'
import Dones from './modules/dones/index.vue'
import Approves from './modules/approves/index.vue'
import Notices from './modules/notices/index.vue'
import datas from './datas'
export default datas
datas.components = {
  Dones,
  Approves,
  Notices,
  Applys
}

</script>

<style lang="scss" scoped>
@import "@/styles/mixin.scss";
@import "./styles.scss";
</style>
