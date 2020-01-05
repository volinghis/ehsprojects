<template>
  <div class="container">
    <el-row :gutter="10" style="margin-bottom:10px;">
      <el-col :span="6" :md="24" :lg="6" class="heightCol">
        <div>
          <el-card shadow='hover' v-bind:style="styleRowHeiheht" style="margin-left:5px;">
            <centers></centers>
          </el-card>
        </div>
      </el-col>
      <el-col :span="18">
        <div>
          <el-row :gutter="10" style="margin-bottom:10px;">
            <el-col :span="8">
              <el-card shadow='hover' v-bind:style="styleColHeiheht">
                <account-doughnut></account-doughnut>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card shadow='hover' v-bind:style="styleColHeiheht">
                <part-doughnut></part-doughnut>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card shadow='hover' v-bind:style="styleColHeiheht">
                <task></task>
              </el-card>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="16">
              <el-card shadow='hover' v-bind:style="styleColHeiheht">
                <linechart></linechart>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card shadow='hover' v-bind:style="styleColHeiheht">
                <charts></charts>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="6" class="elheight">
        <div >
          <el-card shadow='hover' v-bind:style="styleRowHeiheht" style="margin-left:5px;">
            <div slot="header" class="item-title" size="small" style="text-align:center;border-radius: 30px;">
              <el-button size="mini" round type="primary" >
                <span style="weight:100px;font-size: large; font-weight: bold;">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贡&nbsp;献&nbsp;度&nbsp;季&nbsp;度&nbsp;排&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
              </el-button>
            </div>
            <ranking></ranking>
          </el-card>
        </div>
      </el-col>
      <el-col :span="18">
        <div>
          <el-row :gutter="10" style="margin-bottom:10px;">
            <el-col :span="16">
              <el-card shadow='hover' v-bind:style="styleColHeiheht">
                <div slot="header" class="item-title" size="small">
                  <span class="mainTitle">新增检修记录</span>
                </div>
                <maintenance-record></maintenance-record>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card shadow='hover' v-bind:style="styleColHeiheht">
                <div slot="header" class="item-title" size="small">
                  <span class="mainTitle">资料完整度</span>
                </div>
                <devices></devices>
              </el-card>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="16">
              <el-card shadow='hover' v-bind:style="styleColHeiheht">
                <div slot="header" class="item-title" size="small">
                  <span class="mainTitle">动&nbsp;&nbsp;&nbsp;&nbsp;态</span>
                </div>
                <dynamic></dynamic>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card shadow='hover' v-bind:style="styleColHeiheht">
                <div slot="header" class="item-title" size="small">
                  <span class="mainTitle">热门关注</span>
                  <a href="#"><span style="float:right;color:#409EFF">更多</span></a>
                </div>
                <div>
                  <documents></documents>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import centers from './models/centers.vue'
import charts from './models/charts.vue'
import task from './models/task.vue'
import accountDoughnut from './models/accountDoughnut.vue'
import partDoughnut from './models/partDoughnut.vue'
import linechart from './models/linechart.vue'
import devices from './models/devices.vue'
import maintenanceRecord from './models/maintenanceRecord.vue'
import dynamic from './models/dynamic.vue'
import ranking from './models/ranking.vue'
import documents from './models/documents.vue'
export default {
  data () {
    return {
      styleRowHeiheht: {
        // height: this.nHeight
        height: ''
      },
      styleColHeiheht: {
        height: ''
      }
    }
  },
  components: {
    'centers': centers,
    'charts': charts,
    'task': task,
    'accountDoughnut': accountDoughnut,
    'partDoughnut': partDoughnut,
    'linechart': linechart,
    'devices': devices,
    'maintenanceRecord': maintenanceRecord,
    'dynamic': dynamic,
    'documents': documents,
    'ranking': ranking
  },
  mounted: function () {
    var h = document.querySelector('.heightCol').offsetWidth
    this.styleRowHeiheht.height = h * 2 + 10 + 'px'
    this.styleColHeiheht.height = h + 'px'
  }
}
</script>
<style scoped>
  .mainTitle{
    font-size: large;
    font-weight: bold;
    color: #646669
  }
</style>
<style>
  .el-card__header {
    padding: 10px 20px !important;
    height: 45px !important;
  }
</style>
