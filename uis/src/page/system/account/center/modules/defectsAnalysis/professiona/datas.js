import 'echarts/lib/chart/bar'
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
      return { width: (document.body.offsetWidth - parseInt(this.GlobalCss.siderWidth) - 60) / 2, height: document.body.offsetHeight / 3 }
    }
  },

  mounted () {
    document.querySelector('.echarts').style.height = this.chartSize().height + 'px'
    document.querySelector('.echarts').style.width = this.chartSize().width + 'px'
    var that = this
    this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/defectLedger/defectAnalysisForIndexPage?type=deviceProfessiona').then(response => {
      that.orgOptions = {
        legend: {
        },

        tooltip: {},
        dataset: {
          source: response.data
        },
        xAxis: { type: 'category',
          axisLabel: {
            interval: 0,
            rotate: 70
          } },
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: response.data[0].map((s, idx) => {
          return idx > 0 ? { type: 'bar' } : null
        })
      }
    })
  }
}
