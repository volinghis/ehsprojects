import 'echarts/lib/chart/pie'
import 'echarts/lib/component/tooltip'
import 'echarts/lib/component/legend'
import 'echarts/lib/component/title'
export default {
  data () {
    return {
      orgOptions: {}
    }
  },
  computed: {

  },
  methods: {
    chartSize: function () {
      return { height: 360 }
    }
  },

  mounted () {
    var that = this
    this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/checks/task/taskAnalysisForIndexPage').then(response => {
      that.orgOptions = {
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },

        series: [
          {
            name: '任务数量',
            type: 'pie',

            data: response.data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]

      }

      that.$refs.chart3.resize({ height: that.chartSize().height })
    })
  }
}
