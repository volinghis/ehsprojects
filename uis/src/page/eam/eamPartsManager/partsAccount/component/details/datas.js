import FilesTable from '../../../../components/filesTable'
export default {
  components: {
    FilesTable
  },
  computed: {
    tableHeight: function () {
      return this.$store.state.contentHeight - 20
    }
  },
  data () {
    return {
      key: '',
      deviceKey: '',
      fileId: '',
      imageUrl: '',
      flag: '',
      partFlag: false,
      paramsData: [],
      form: {}
    }
  },
  mounted: function () {
    this.form = this.$route.params.data
    this.deviceKey = this.form.key
    this.fileId = this.form.fileId
    this.flag = this.$route.params.flag
    if (this.flag) {
      this.partFlag = true
    }
    this.getDevicePicture(this.form.partsImg)
    this.key += 1
  },
  methods: {
    allFileId (v) {
      this.form.fileId = v
    },
    handlePrint: function () {
    },
    handleBack: function () {
      this.$router.go(-1)
    },
    getDevicePicture: function (partsImg) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + partsImg, { responseType: 'blob' }).then(res => {
        var resData = res.data
        this.imageUrl = URL.createObjectURL(resData)
      }).catch(error => {
        this.$message({ message: error })
      })
    }
  }
}
