// import { Quill, quillEditor } from 'vue-quill-editor'
import { quillEditor } from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
// 引入font.css
import './styles.scss'

// 自定义字体大小
// let Size = Quill.import('attributors/style/size')
// Size.whitelist = ['10px', '12px', '14px', '16px', '18px', '20px']
// Quill.register(Size, true)

// // 自定义字体类型
// var fonts = ['SimSun', 'SimHei', 'Microsoft-YaHei', 'KaiTi', 'FangSong', 'Arial', 'Times-New-Roman', 'sans-serif', '宋体', '黑体']
// var Font = Quill.import('formats/font')
// Font.whitelist = fonts
// Quill.register(Font, true)

export default {
  components: { quillEditor },
  data () {
    return {
      flag: '',
      buttonDisable: true,
      inputDisable: false,
      infoForm: {
        dataCode: '',
        newsTitle: '',
        newsContent: ''
      },
      rules: {
        newsTitle: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ]
      },
      editorOption: {
        placeholder: '请输入',
        theme: 'snow',
        modules: {
          toolbar: {
            container: '#toolbar'
          }
        }
      }
    }
  },
  mounted: function () {
    var object = Object.assign(this.$route.params)
    this.infoForm.dataCode = object.code
    this.flag = this.$route.params.flag
    if (this.$route.params.flag === 'edit') {
      this.infoForm = this.$route.params.data
    } else if (this.$route.params.flag === 'view') {
      this.infoForm = this.$route.params.data
      this.inputDisable = true
      this.buttonDisable = false
    }
  },
  methods: {
    onSubmit () {
      this.$refs.infoForm.validate((valid) => {
        if (this.infoForm.newsContent === '') {
          this.$message.error('请输入详细内容')
        }
        this.$axios.post(this.GlobalVars.globalServiceServlet + '/web/news/saveNews', this.infoForm).then(res => {
          if (res.data.resultType === 'ok') {
            this.$message({
              message: res.data.message,
              type: 'success'
            })
            this.$router.push({ name: this.infoForm.dataCode })
          } else {
            this.$message.error(res.data.message)
          }
        })
      })
    },
    onEditorReady () {

    },
    reset () {
      this.infoForm = {}
    },
    goBack () {
      // this.$router.push({ name: 'MENU_COMP_NEWS' })
      this.$router.go(-1)
    }
  }
}
