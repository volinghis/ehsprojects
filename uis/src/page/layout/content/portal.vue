<template>
  <div class="account-center-index-wrapper">
    <el-row :gutter="20">
      <el-col :span="5">
        <el-card shadow="hover">
          <UserInfo></UserInfo>
        </el-card>
      </el-col>
      <el-col :md="19">
        <el-row :gutter="20"
                class="panel-group">
          <el-col :xs="12"
                  :sm="12"
                  :lg="6"
                  class="card-panel-col">
            <div class="card-panel task">
              <div class="card-panel-icon-wrapper task">
                <i class="fa fa-align-justify fa-5x"></i>
              </div>
              <div class="card-panel-description">
                <div class="card-panel-text">
                  任务
                </div>
                <div class="card-panel-num">1133</div>
              </div>
            </div>
          </el-col>
          <el-col :xs="12"
                  :sm="12"
                  :lg="6"
                  class="card-panel-col">
            <div class="card-panel approval">
              <div class="card-panel-icon-wrapper approval">
                <i class="fa fa-paperclip fa-5x"></i>
              </div>
              <div class="card-panel-description">
                <div class="card-panel-text">
                  审批
                </div>
                <div class="card-panel-num">1133</div>
              </div>
            </div>
          </el-col>
          <el-col :xs="12"
                  :sm="12"
                  :lg="6"
                  class="card-panel-col">
            <div class="card-panel contribution">
              <div class="card-panel-icon-wrapper contribution">
                <i class="fa fa-star-o fa-5x"></i>
              </div>
              <div class="card-panel-description">
                <div class="card-panel-text">
                  贡献度
                </div>
                <div class="card-panel-num">45</div>
              </div>
            </div>
          </el-col>
          <el-col :xs="12"
                  :sm="12"
                  :lg="6"
                  class="card-panel-col">
            <div class="card-panel notice">
              <div class="card-panel-icon-wrapper notice">
                <i class="fa fa-send-o fa-5x"></i>
              </div>
              <div class="card-panel-description">
                <div class="card-panel-text">
                  消息
                </div>
                <div class="card-panel-num">45</div>
              </div>
            </div>
          </el-col>
        </el-row>

        <el-row style="margin-top:10px;"
                :gutter="20">
          <el-col :span="15">
            <div class="block-title"
                 style="background: #40c9c6;">
              <label>我的任务</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <MyTask></MyTask>
          </el-col>
          <el-col :span="9">
            <div class="block-title"
                 style="background: #f4516c;">
              <label>消息提醒</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <template>
              <el-table :data="notices"
                        :size="GlobalCss.buttonSize"
                        :show-header="false">
                <el-table-column width="5">
                </el-table-column>
                <el-table-column prop="notice">
                </el-table-column>
              </el-table>
            </template>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <el-row style="margin-top:20px;"
            :gutter="20">
      <el-col :span="5">
        <OperSummary />
      </el-col>
      <el-col :span="19">
        <el-row :gutter="20">
          <el-col :span="12">
            <div class="block-title"
                 style="background: #5d75f2;">
              <label>我的申请</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <Application />
          </el-col>
          <el-col :span="12">
            <div class="block-title"
                 style="background:#36a3f7;">
              <label>我的审批</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <Approval />
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <el-row :gutter="20"
            style="margin-top:20px;">
      <el-col :span="5">
        <el-card shodow="never">
          <div slot="header">
            <span>贡&nbsp;献&nbsp;度&nbsp;季&nbsp;度&nbsp;排&nbsp;行</span>
          </div>
          <ranking></ranking>
        </el-card>
      </el-col>
      <el-col :span="19">
        <el-row :gutter="10">
          <el-col :span="15">
            <div class="block-title"
                 style="background:#f2bb5e;">
              <label>我的贡献度</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <Contribution />
          </el-col>
          <el-col :span="9">
            <div class="block-title"
                 style="background:#f4516c;">
              <label>资料完整度</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <DataIntegrity />
          </el-col>
        </el-row>
        <el-row style="margin-top:20px;">
          <el-col>
            <div class="block-title"
                 style="background:#66b1ff;">
              <label>我的日程</label>
              <i class="fa fa-angle-double-right fa-2x"></i>
            </div>
            <el-row>
              <el-col :sm="12"
                      :lg="12">
                <div class="calendar-wrapper">
                  <Calendar :sundayStart="true"
                            @choseDay="clickDay">
                  </Calendar>
                </div>
              </el-col>
              <el-col :sm="12"
                      :lg="12">
                <DataTimeLine />
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import UserInfo from './models/userInfo'
import MyTask from './models/myTask'
import Application from './models/application'
import Approval from './models/approval'
import OperSummary from './models/operSummary'
import Ranking from './models/ranking'
import Contribution from './models/contribution'
import DataIntegrity from './models/dataIntegrity'
import DataTimeLine from './models/dataTimeLine'
import Calendar from 'vue-calendar-component'
export default {
  data () {
    return {
      notices: [{
        notice: '1.管道巡检计划到达巡检时间'
      }, {
        notice: '2.管道巡检计划到达巡检时间'
      }, {
        notice: '3.管道巡检计划到达巡检时间'
      }, {
        notice: '4.管道巡检计划到达巡检时间'
      }, {
        notice: '4.管道巡检计划到达巡检时间'
      }]
    }
  },
  components: {
    UserInfo,
    MyTask,
    Application,
    Approval,
    OperSummary,
    Ranking,
    Contribution,
    DataIntegrity,
    DataTimeLine,
    Calendar
  },
  methods: {
    clickDay (data) {
      console.log(data)
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/mixin.scss";
@import "./styles.scss";
</style>
