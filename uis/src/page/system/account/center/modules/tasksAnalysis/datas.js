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
      return { width: (document.body.offsetWidth - parseInt(this.GlobalCss.siderWidth) - 60) / 3, height: document.body.offsetHeight / 3 }
    }
  },

  mounted () {
    document.querySelector('.echarts').style.height = this.chartSize().height + 'px'
    document.querySelector('.echarts').style.width = this.chartSize().width + 'px'
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
            radius: '55%',
            center: ['50%', '50%'],
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
    })
  }
}
