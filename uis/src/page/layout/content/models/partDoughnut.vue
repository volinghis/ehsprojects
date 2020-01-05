<template>
  <chart :options="options" :autoresize="true" theme="ehsecharts"></chart>
</template>
<script>
require('echarts/lib/chart/pie')
// 引入提示框和标题组件
require('echarts/lib/component/title')
// 引入legend组件
require('echarts/lib/component/legend')
require('echarts/lib/component/tooltip')
export default {
  data () {
    return {
      options: {}
    }
  },
  mounted () {
    this.options = {
      title: {
        text: '备件概况',
        x: 'left'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        y: 'top',
        x: 'right',
        icon: 'circle',
        data: ['电气专业', '锅炉专业', '汽机专业', '热工专业', '热控专业']
      },
      series: [
        {
          name: '专业来源',
          type: 'pie',
          radius: ['45%', '65%'],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: false,
              position: 'center'
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: '30',
                fontWeight: 'bold'
              }
            }
          },
          labelLine: {
            normal: {
              show: false
            }
          },
          data: [
            { value: 435, name: '电气专业' },
            { value: 310, name: '锅炉专业' },
            { value: 334, name: '汽机专业' },
            { value: 435, name: '热工专业' },
            { value: 348, name: '热控专业' }
          ]
        }
      ]
    }
  }
}
</script>
<style scoped>
.echarts{
  width:100%;
}
</style>
