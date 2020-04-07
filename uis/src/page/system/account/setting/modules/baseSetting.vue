<template>
  <div>
    <el-row>
      <el-col :span="12" style="margin-left:20px;">
        <el-form ref="form" :model="form" :size="GlobalCss.buttonSize" label-width="100px" :rules="rules">
          <el-divider content-position="center"><span style="color:#409EFF">基本信息</span></el-divider>
          <el-form-item label="账 号：" prop="dataCode">
            <el-input v-model="form.dataCode" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="姓 名：" prop="name">
            <el-input v-model="form.name" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="职 务：" prop="positionName">
            <el-input v-model="form.positionName" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="部 门：" prop="orgName">
            <OrgSelect style="width:100%;" :propOrgValue="form.orgKey" v-model="form.orgKey" :disabled="true">
            </OrgSelect>
          </el-form-item>
          <el-form-item label="邮 箱：" prop="email">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="性 别：" prop="gender">
            <el-radio-group v-model="form.gender">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="手机号码：" prop="telephone">
            <el-input v-model="form.telephone"></el-input>
          </el-form-item>
          <el-divider content-position="center"><span style="color:#409EFF">辅助信息</span></el-divider>
          <el-form-item label="员工学历：" prop="education">
            <el-input v-model="form.education"></el-input>
          </el-form-item>
          <el-form-item label="毕业院校：" prop="graduatedSchool">
            <el-input v-model="form.graduatedSchool"></el-input>
          </el-form-item>
          <el-form-item label="员工籍贯：" prop="homeTown">
            <el-input v-model="form.homeTown"></el-input>
          </el-form-item>
          <el-form-item label="员工专业：" prop="profession">
            <el-input v-model="form.profession"></el-input>
          </el-form-item>
          <el-form-item label="毕业时间：" prop="graduatedDate">
            <el-date-picker v-model="form.graduatedDate" type="date" style="width:100%;" placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :size="GlobalCss.buttonSize" @click="onSubmit('form')">保存</el-button>
            <el-button type="primary" :size="GlobalCss.buttonSize" @click="resetForm('form')">取消</el-button>
          </el-form-item>
        </el-form>
      </el-col>

      <el-col :span="10" style="text-align: center;">
        <el-upload class="avatar-uploader"
          :action="GlobalVars.globalServiceServlet + '/data/file/fileUpload'+ '?tt=' + Math.random()+ '&resoureMenuKey=' + $store.state.resourceMenuKey"
          :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
          <el-image v-if="form.avatar||account==='admin'" :src="imageUrl" class="avatar"></el-image>
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-col>

    </el-row>
    <div class="btn-group" style="margin-left:50px;">
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
      account: '',
      form: {
        dataCode: '',
        name: '',
        position: '',
        orgName: '',
        telephone: '',
        education: '',
        graduatedSchool: '',
        homeTown: '',
        profession: '',
        graduatedDate: '',
        desc: '',
        avatar: ''
      },
      formLabelWidth: '80px',
      options: [],
      rules: {
        telephone: [{
          required: true,
          message: '请输入手机号',
          trigger: 'blur'
        },
        {
          min: 11,
          max: 11,
          message: '11位手机号码',
          trigger: 'blur'
        }
        ],
        email: [{
          required: true,
          message: '请输入邮箱地址',
          trigger: 'blur'
        },
        {
          type: 'email',
          message: '请输入正确的邮箱地址',
          trigger: ['blur', 'change']
        }
        ]
      }
    }
  },
  mounted () {
    this.account = localStorage.getItem(this.GlobalVars.userLocal)
    this.initForm()
  },
  methods: {
    handleAvatarSuccess (res, file) {
      this.form.avatar = res.entityKey
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    initForm () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgUser/findOrgUserByAccount', {
        params: {
          account: this.account
        }
      })
        .then((res) => {
          var fileId = res.data.avatar
          if (this.account === 'admin') {
            this.imageUrl = require('@/assets/logo.svg')
          } else {
            this.$axios.get(this.GlobalVars.globalServiceServlet + '/data/file/downloadFile?fileId=' + fileId, {
              responseType: 'blob'
            }).then((res) => {
              this.imageUrl = URL.createObjectURL(res.data)
            })
          }
          this.form = res.data
        })
    },
    onSubmit (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post(this.GlobalVars.globalServiceServlet + '/auth/userManager/saveCurrentUser', this.form)
            .then((res) => {
              if (res.data.resultType === 'ok') {
                this.$message({
                  type: 'success',
                  message: res.data.message
                })
              }
            })
        } else {
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
      this.initForm()
    },
    beforeAvatarUpload (file) {
      const isPNG = file.type === 'image/png'
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG && !isPNG) {
        this.$message.error('上传头像图片只能是 JPG或PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return (isJPG || isPNG) && isLt2M
    }
  }
}

</script>
<style lang="scss" scoped>
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 170px;
    height: 170px;
    line-height: 170px;
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
    margin-top: 20px;
  }

  .avatar {
    width: 170px;
    height: 170px;
    display: block;
  }

  .el-divider {
    background-color: #3a8ee6;
  }

</style>
