import FilesTable from '../../../../components/filesTable'
export default {
  components: {
    FilesTable
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
      form: {},
      innerVisible: false,
      multipleSelection: [],
      tableData: [],
      queryBean: {
        flag: '',
        query: '',
        page: 1,
        size: 10,
        totalCount: 0
      }
    }
  },
  props: {
    selectWareHouseFlag: String
  },
  mounted: function () {
    this.getAllParts(this.selectWareHouseFlag)
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
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?resoureMenuKey=ALL&fileId=' + partsImg, { responseType: 'blob' }).then(res => {
        var resData = res.data
        this.imageUrl = URL.createObjectURL(resData)
      }).catch(error => {
        this.$message({ message: error })
      })
    },

    handleRowClick (row) {
      this.innerVisible = true
      this.form = row
      // this.form = this.$route.params.data
      this.deviceKey = this.form.key
      this.fileId = this.form.fileId
      // this.flag = this.$route.params.flag
      // if (this.flag) {
      //   this.partFlag = true
      // }
      this.getDevicePicture(this.form.partsImg)
      this.key += 1
    },
    getAllParts: function (v) {
      this.queryBean.flag = v
      this.$axios.post(this.GlobalVars.globalServiceServlet + '/eam/partsAccount/getAllPartsAccount', this.queryBean)
        .then(res => {
          this.tableData = res.data.dataList
          this.queryBean.totalCount = res.data.totalCount
        })
    },
    changePage (v) {
      this.queryBean.page = v
      this.getAllParts()
    },
    handleSelectionChange: function (val) {
      this.multipleSelection = val
      this.$emit('partsData', this.multipleSelection)
    }
  }
}
