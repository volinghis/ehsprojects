<template>
  <div>
    <el-row>
      <el-col :span="12">
        <el-form ref="form"
                 :model="form"
                 :size="GlobalCss.buttonSize"
                 label-width="100px"
                 :rules="rules">
          <el-divider content-position="center"><span style="color:#409EFF">基本信息</span></el-divider>
          <el-form-item label="账 号："
                        prop="dataCode">
            <el-input v-model="form.dataCode"></el-input>
          </el-form-item>
          <el-form-item label="姓 名："
                        prop="name">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="职 务："
                        prop="position">
            <el-input v-model="form.position"></el-input>
          </el-form-item>
          <el-form-item label="部 门："
                        prop="orgName">
            <OrgSelect style="width:100%;"
                       v-model="form.orgName"></OrgSelect>
          </el-form-item>
          <el-form-item label="邮 箱："
                        prop="email">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="性 别："
                        prop="gender">
            <el-radio-group v-model="form.gender">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="联系电话："
                        prop="telephone">
            <el-input v-model="form.telephone"></el-input>
          </el-form-item>
          <el-divider content-position="center"><span style="color:#409EFF">辅助信息</span></el-divider>
          <el-form-item label="员工工号："
                        prop="jobNum">
            <el-input v-model="form.jobNum"></el-input>
          </el-form-item>
          <el-form-item label="员工学历："
                        prop="education">
            <el-input v-model="form.education"></el-input>
          </el-form-item>
          <el-form-item label="毕业院校："
                        prop="graduatedSchool">
            <el-input v-model="form.graduatedSchool"></el-input>
          </el-form-item>
          <el-form-item label="员工籍贯："
                        prop="homeTown">
            <el-input v-model="form.homeTown"></el-input>
          </el-form-item>
          <el-form-item label="员工专业："
                        prop="profession">
            <el-input v-model="form.profession"></el-input>
          </el-form-item>
          <el-form-item label="毕业时间："
                        prop="graduatedDate">
            <el-date-picker v-model="form.graduatedDate"
                            type="date"
                            placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary"
                       :size="GlobalCss.buttonSize"
                       @click="onSubmit('form')">保存</el-button>
            <el-button type="primary"
                       :size="GlobalCss.buttonSize"
                       @click="onSubmit('form')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <el-col :span="12"
              style="text-align: center;">
        <el-upload class="avatar-uploader"
                   action="https://jsonplaceholder.typicode.com/posts/"
                   :show-file-list="false"
                   :on-success="handleAvatarSuccess"
                   :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl"
               :src="imageUrl"
               class="avatar">
          <i v-else
             class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-col>

    </el-row>
    <div class="btn-group"
         style="margin-left:50px;">
    </div>
  </div>
</template>
<script>
import OrgSelect from '@components/org/org-selector/index.vue'
export default {
  components: {
    OrgSelect
  },
  data () {
    return {
      imageUrl: '',
      ruleForm: {
        pass: '',
        checkPass: ''
      },
      form: {
        dataCode: '',
        name: '',
        position: '',
        orgName: '',
        telephone: '',
        jobNum: '',
        education: '',
        graduatedSchool: '',
        homeTown: '',
        profession: '',
        graduatedDate: '',
        desc: ''
      },
      formLabelWidth: '80px',
      options: [{
        value: 'shannxi',
        label: '陕西',
        children: [{
          value: 'xiann',
          label: '西安',
          children: [{
            value: 'changan',
            label: '长安区'
          }, {
            value: 'yanta',
            label: '雁塔区'
          }, {
            value: 'beilin',
            label: '碑林区'
          }, {
            value: 'baqiao',
            label: '灞桥区'
          }]
        }]
      }],
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        telephone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { min: 11, max: 11, message: '11位手机号码', trigger: 'blur' }
        ],
        region: [
          { required: true, message: '请选择居住地', trigger: 'change' }
        ],
        desc: [
          { required: true, message: '请填写个人简介', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    this.initForm()
  },
  methods: {
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    initForm () {
      console.log(localStorage.getItem(this.GlobalVars.userLocal))
      const code = localStorage.getItem(this.GlobalVars.userLocal)
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgUser/findOrgUserByAccount', { params: { account: code } })
        .then((res) => {
          console.log(res.data)
          this.form = res.data
        }).catch(error => {
          console.log(error)
        })
    },
    onSubmit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          return false
        }
      })
    },
    handleChange (value) {
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.dialogFormVisible = false
          alert('submit!')
        } else {
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
      this.dialogFormVisible = false
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>
<style lang="scss" scoped>
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

/deep/.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
/deep/.avatar-uploader .el-upload {
  border: 1px dashed #8c939d;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  margin-top: 20px;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
}
.el-divider {
  background-color: #3a8ee6;
}
</style>
