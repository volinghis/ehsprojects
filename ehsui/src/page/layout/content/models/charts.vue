<template>
   <chart :options="options" :autoresize="true" theme="ehsecharts"></chart>
</template>
<script>
require('echarts/lib/chart/pie')
// 引入提示框和标题组件
require('echarts/lib/component/title')
// 引入legend组件
require('echarts/lib/component/legend')

export default {

  mounted: function () {
    this.options = {
      title: {
        text: '故障原因占比',
        subtext: '',
        x: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['外力破坏', '磨损', '损伤', '二次损伤', '操作不当', '润滑过多或过少', '老化']
      },
      series: [
        {
          name: '故障来源',
          type: 'pie',
          radius: '45%',
          center: ['50%', '70%'],
          data: [
            { value: 335, name: '磨损' },
            { value: 210, name: '外力破坏' },
            { value: 334, name: '损伤' },
            { value: 135, name: '二次损伤' },
            { value: 42, name: '操作不当' },
            { value: 55, name: '润滑过多或过少' },
            { value: 131, name: '老化' }
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 15,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    }
  },
  data () {
    return {
      options: {}
    }
  }
}
</script>
<style scoped>
.echarts{
  width:100%;
}
</style>
