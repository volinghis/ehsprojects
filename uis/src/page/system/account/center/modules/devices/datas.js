export default {
  methods: {
    findColor (v) {
      if (v <= 30) {
        return 'exception'
      } else if (v > 30 && v <= 70) {
        return 'warning'
      } else {
        return 'success'
      }
    }
  },
  data () {
    return {
      datas: [{ completePoint: 10, deviceName: '1号锅炉', person: '王五' },
        { completePoint: 15, deviceName: '变压器1号', person: '陈六' },
        { completePoint: 20, deviceName: '磨煤机3号', person: '张德江' },
        { completePoint: 25, deviceName: '传动轴4号', person: '玉玉' },
        { completePoint: 30, deviceName: '5号锅炉', person: '林东' },
        { completePoint: 35, deviceName: '9号变压机', person: '王五' },
        { completePoint: 40, deviceName: '1号电梯', person: '王五' },
        { completePoint: 50, deviceName: '4号传感器', person: '王五' },
        { completePoint: 60, deviceName: '6号升降机', person: '王五' },
        { completePoint: 80, deviceName: '11号输煤机', person: '王五' }
      ]
    }
  }
}
