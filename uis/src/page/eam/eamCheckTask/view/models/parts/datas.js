export default {
  props: {
    'dataRow': Object
  },
  data () {
    return {
      partsData: {
        wareHouse: '',
        wareHouseName: '',
        deviceName: '',
        deviceCode: '',
        amount: '',
        receivePerson: '',
        receivePersonName: '',
        receiveOrg: ''
      }
    }
  },
  mounted () {
    var that = this
    that.partsData = Object.assign(that.dataRow)
  }
}
