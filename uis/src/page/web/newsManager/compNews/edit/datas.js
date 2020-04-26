import { quillEditor } from 'vue-quill-editor'
import * as Quill from 'quill'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
// 引入font.css
import './styles.scss' // 引入编辑器
import ImageResize from 'quill-image-resize-module'
Quill.register('modules/imageResize', ImageResize)

// 自定义字体大小
let Size = Quill.import('attributors/style/size')
Size.whitelist = [false, '10px', '12px', '14px', '16px', '20px', '24px', '36px']
Quill.register(Size, true)

// // 自定义字体类型
var fonts = ['SimSun', 'SimHei', 'Microsoft-YaHei', 'KaiTi', 'FangSong', 'Arial', 'Times-New-Roman', 'sans-serif']
var Font = Quill.import('formats/font')
Font.whitelist = fonts
Quill.register(Font, true)

var Align = Quill.import('attributors/style/align')
Align.whitelist = ['right', 'center', 'justify']
Quill.register(Align, true)

const toolbarOptions = [
  ['bold', 'italic', 'underline', 'strike'], // 加粗，斜体，下划线，删除线
  [{ 'header': 1 }, { 'header': 2 }], // 标题，键值对的形式；1、2表示字体大小
  [{ 'list': 'ordered' }, { 'list': 'bullet' }], // 列表
  [{ 'indent': '-1' }, { 'indent': '+1' }], // 缩进
  [{ 'font': fonts }], // 字体
  [{ 'size': Size.whitelist }], // 字体大小
  [{ 'header': [1, 2, 3, 4, 5, false] }], // 几级标题
  [{ 'color': [] }, { 'background': [] }], // 字体颜色，字体背景颜色
  [{ 'align': [] }], // 对齐方式
  ['image'], // 上传图片
  ['clean'] // 清除字体样式
]
const handlers = {
  image: function image () {
    let self = this
    var fileInput = this.container.querySelector('input.ql-image[type=file]')
    if (fileInput === null) {
      fileInput = document.createElement('input')
      fileInput.setAttribute('type', 'file')
      // 可设置上传图片的格式
      fileInput.setAttribute('accept', 'image/png, image/gif, image/jpeg')
      fileInput.classList.add('ql-image')
      // 监听选择文件
      fileInput.addEventListener('change', function () {
        // console.log(fileInput.files[0])
        let file = fileInput.files[0]
        if (!/image\/\w+/.test(file.type)) {
          // console.log('图片格式不正确')
          return false
        }
        let reader = new FileReader()
        reader.readAsDataURL(file)
        reader.onload = function () {
          let img = new Image()
          img.src = this.result
          img.onload = function () {
            let that = this
            let scale = 1000 / that.width
            let w = 1000
            let h = that.height * scale
            let canvas = document.createElement('canvas')
            let ctx = canvas.getContext('2d')
            let anw = document.createAttribute('width')
            anw.nodeValue = w
            let anh = document.createAttribute('height')
            anh.nodeValue = h
            canvas.setAttributeNode(anw)
            canvas.setAttributeNode(anh)
            ctx.drawImage(that, 0, 0, w, h)
            let base64 = canvas.toDataURL('image/jpeg', 0.5)
            // console.log(base64)
            let length = self.quill.getSelection(true).index
            self.quill.insertEmbed(length, 'image', base64)
            self.quill.setSelection(length + 1)
          }
        }
      })
      this.container.appendChild(fileInput)
    }
    fileInput.click()
  }
}
export default {
  components: { quillEditor },
  data () {
    return {
      flag: '',
      disabled: false,
      buttonDisable: true,
      inputDisable: false,
      infoForm: {
        dataCode: '',
        newsTitle: '',
        newsContent: '',
        major: false
      },
      rules: {
        newsTitle: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ]
      },
      editorOption: {
        modules: {
          toolbar: {
            container: toolbarOptions,
            handlers: handlers
          },
          imageResize: {
            // displaySize: true,
            displayStyles: { // 添加
              backgroundColor: 'black',
              border: 'none',
              color: 'white'
            },
            modules: ['Resize', 'DisplaySize', 'Toolbar']
          }
        },
        placeholder: '请输入内容 ...',
        theme: 'snow'
      }
    }
  },
  mounted: function () {
    var object = Object.assign(this.$route.params)
    this.infoForm.dataCode = object.code
    this.flag = this.$route.params.flag
    if (this.$route.params.flag === 'edit') {
      this.getNews(this.$route.params.key)
    } else if (this.$route.params.flag === 'view') {
      this.disabled = true
      this.getNews(this.$route.params.key)
      this.inputDisable = true
      this.buttonDisable = false
    }
  },
  computed: {
    editor () {
      return this.$refs.myQuillEditor.quill
    }
  },
  methods: {
    onSubmit () {
      this.$refs.infoForm.validate((valid) => {
        if (this.infoForm.newsContent === '') {
          this.$message.error('请输入详细内容')
          return
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
    getNews (key) {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/web/news/getNewsByKey', { params: { key: key } }).then(res => {
        this.infoForm = res.data
      })
    },
    reset () {
      this.infoForm = {}
    },
    goBack () {
      this.$router.go(-1)
    }
  }
}
