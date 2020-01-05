<template>
  <div>
    <el-col>
      <div>
        <!-- <div style="float:right;"><i class="el-icon-date"></i></div> -->
        <el-tabs v-model="activeName" @tab-click="handleClick" style="text-align:center;" stretch="stretch">
          <el-tab-pane label="今日" name="first" style="width:100%;">
            <chart :options="optionDay" :autoresize="true" theme="ehsecharts" style="width:100%;"></chart>
          </el-tab-pane>
          <el-tab-pane label="本周" name="second" style="width:100%;">
            <chart :options="optionWeek" :autoresize="true" theme="ehsecharts" style="width:100%;"></chart>
          </el-tab-pane>
          <el-tab-pane label="本月" name="third" style="width:100%;">
            <chart :options="optionMonth" :autoresize="true" theme="ehsecharts" style="width:100%;"></chart>
          </el-tab-pane>
        </el-tabs>
        <!-- <chart :options="optionDay" style="width:100%"></chart> -->
      </div>
    </el-col>
  </div>
</template>
<script>
require('echarts/lib/chart/line')
// 引入提示框和标题组件
require('echarts/lib/component/title')
// 引入legend组件
require('echarts/lib/component/legend')
export default {
  data () {
    return {
      stretch: 'false',
      activeName: 'second',
      optionDay: {},
      optionWeek: {},
      optionMonth: {}
    }
  },
  methods: {
    handleClick (tab, event) {
    }
  },
  mounted () {
    // eslint-disable-next-line no-unused-expressions
    this.optionDay = {
      title: {
        text: '今日折线图'
      },
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        x: 'center'
      },
      grid: {
        top: '15%',
        bottom: '25%',
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['11.4', '11.5', '11.6', '11.7', '11.8', '11.9', '11.10']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: '故障量',
          type: 'line',
          stack: '总量',
          data: [7, 9, 15, 20, 10, 25, 10]
        },
        {
          name: '已修复',
          type: 'line',
          stack: '总量',
          data: [2, 4, 11, 13, 12, 9, 5]
        }
      ]
    // eslint-disable-next-line no-sequences
    },
    this.optionWeek = {
      title: {
        text: '本周折线图'
      },
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        x: 'center'
      },
      grid: {
        top: '15%',
        bottom: '25%',
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['11.4', '11.5', '11.6', '11.7', '11.8', '11.9', '11.10']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: '故障量',
          type: 'line',
          stack: '总量',
          data: [17, 19, 115, 120, 110, 125, 110]
        },
        {
          name: '已修复',
          type: 'line',
          stack: '总量',
          data: [12, 14, 111, 113, 112, 19, 15]
        }
      ]
    },
    this.optionMonth = {
      title: {
        text: '本月折线图'
      },
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        x: 'center'
      },
      grid: {
        top: '15%',
        bottom: '25%',
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['11.4', '11.5', '11.6', '11.7', '11.8', '11.9', '11.10']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: '故障量',
          type: 'line',
          stack: '总量',
          data: [7, 9, 15, 20, 10, 25, 10]
        },
        {
          name: '已修复',
          type: 'line',
          stack: '总量',
          data: [2, 4, 11, 13, 12, 9, 5]
        }
      ]
    }
  }
}
</script>
