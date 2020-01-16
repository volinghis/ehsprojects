// import ParamsTable from '../../../../components/paramsTable.vue'
import ParamsTable from '../paramsTable/index.vue'
import ModifyRecord from '../modifyRecord/index.vue'
export default {
  components: {
    paramsTable: ParamsTable,
    modifyRecord: ModifyRecord
  },
  data () {
    return {
      eamItem: {},
      flag: '',
      paramsData: [],
      customColors: [
        { color: '#f56c6c', percentage: 20 },
        { color: '#e6a23c', percentage: 40 },
        { color: '#5cb87a', percentage: 60 },
        { color: '#1989fa', percentage: 80 },
        { color: '#6f7ad3', percentage: 100 }
      ],
      fileList: [
        {
          name: 'food.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        },
        {
          name: 'food2.jpeg',
          url:
            'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }
      ]
    }
  },
  mounted: function () {
    this.eamItem = this.$route.params.data
    this.paramsData = this.$route.params.pData
    this.flag = this.$route.params.flag
  },
  methods: {
    handlePrint: function () {
    },
    handleBack: function () {
      this.$router.go(-1)
    },
    handleExceed: function (files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      )
    }
  }
}
