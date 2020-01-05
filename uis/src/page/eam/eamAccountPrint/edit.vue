<template>
  <div style="padding:0px 20px;">
    <el-form
      ref="form"
      :model="form"
      :rules="rules"
      label-position="top"
      label-width="80px"
    >
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="item-block">
            <span>设备图片</span>
            <el-upload
              class="upload-demo"
              drag
              :on-success="handleAvatarSuccess"
              action="https://jsonplaceholder.typicode.com/posts/"
              multiple
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                将图片拖到此处，或<em>点击上传</em>
              </div>
              <div class="el-upload__tip" slot="tip">
                只能上传jpg/png文件，且不超过500kb
              </div>
            </el-upload>
          </div>
          <!--检修质量标准-->
          <div class="item-block">
            <span>检修质量标准</span>
            <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              multiple
              :limit="3"
              :on-exceed="handleExceed"
              :file-list="fileList"
            >
              <el-button size="mini" plain type="primary" icon="el-icon-upload"
                >上传文件</el-button
              >
            </el-upload>
          </div>
          <!--设备说明书-->
          <div class="item-block">
            <span>设备说明书</span>
            <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              multiple
              :limit="3"
              :on-exceed="handleExceed"
              :file-list="fileList"
            >
              <el-button size="mini" plain type="primary" icon="el-icon-upload"
                >上传文件</el-button
              >
            </el-upload>
          </div>
          <!--设备操作手册-->
          <div class="item-block">
            <span>设备操作手册</span>
            <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              multiple
              :limit="3"
              :on-exceed="handleExceed"
              :file-list="fileList"
            >
              <el-button size="mini" plain type="primary" icon="el-icon-upload"
                >上传文件</el-button
              >
            </el-upload>
          </div>

          <div class="item-block">
            <span>资料完整度</span>
            <el-row>
              <el-card>
                <el-col :span="12">
                  <el-progress
                    type="circle"
                    :percentage="75"
                    :stroke-width="10"
                  ></el-progress>
                </el-col>
                <el-col :span="12">
                  <span> 当前设备资料的完整度为75%，请尽快完善资料</span>
                </el-col>
              </el-card>
            </el-row>
          </div>
        </el-col>
        <el-col :span="18">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="设备名称" prop="deviceName">
                <el-input v-model="form.deviceName"></el-input>
              </el-form-item>
              <el-form-item label="设备型号" prop="deviceModel">
                <el-input v-model="form.deviceModel"></el-input>
              </el-form-item>
              <el-form-item label="出厂日期" prop="leaveDate">
                <el-date-picker
                  v-model="form.leaveDate"
                  type="date"
                   style="width:400px;"
                  placeholder="选择日期"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="设备树" prop="deviceTree">
               <el-input v-model="form.deviceTree"></el-input>
              </el-form-item>
              <el-form-item label="生产厂家" prop="factoryName">
                <el-input v-model="form.factoryName"></el-input>
              </el-form-item>
              <el-form-item label="投运日期" prop="runDate">
                <el-date-picker
                  v-model="form.runDate"
                  type="date"
                  style="width:400px;"
                  placeholder="选择日期"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="设备编码" prop="deviceName">
                <el-input v-model="form.deviceName" placeholder="不用填写，系统自动生成"></el-input>
              </el-form-item>
              <el-form-item label="出厂编号" prop="factoryName">
                <el-input v-model="form.factoryName"></el-input>
              </el-form-item>
              <el-form-item label="创建人" prop="person">
                <el-input v-model="form.person"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <!--设备参数-->
          <div class="item-block right"><span>设备主要参数</span></div>
          <params-table></params-table>
          <div class="item-block right"><span>定期工作标准</span></div>
          <el-input
            type="textarea"
            :rows="5"
            maxlength="300"
            show-word-limit
            placeholder="请输入定期工作标准"
            v-model="form.textarea"
          >
          </el-input>
          <!--历任点检员-->
          <div class="item-block right"><span>历任点检员</span></div>
          <past-inspectors></past-inspectors>
        </el-col>
      </el-row>
      <el-form-item style="text-align: center;">
        <el-button  size="small" type="primary" @click="submitForm('form')">提交</el-button>
        <el-button  size="small"  @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import ParamsTable from './componet/paramsTable.vue'
import PastInspectors from './componet/pastInspectors.vue'
export default {
  name: 'eamAccountPrintEdit',
  components: {
    paramsTable: ParamsTable,
    pastInspectors: PastInspectors
  },
  data () {
    return {
      form: {
        deviceName: '',
        deviceNum: '',
        deviceTree: '',
        runDate: '',
        factoryName: '',
        person: '',
        textarea: ''
      },
      rules: {
        deviceName: [
          { required: true, message: '请输入设备名称', trigger: 'blur' }
        ],
        deviceModel: [
          { required: true, message: '请输入设备型号', trigger: 'change' }
        ],
        person: [
          { required: true, message: '请输入创建人', trigger: 'change' }
        ]
      },
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
  methods: {
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    submitForm (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert('submit!')
        } else {
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs['form'].resetFields()
    },
    handleRemove (file, fileList) {
    },
    handlePreview (file) {
    },
    handleExceed (files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      )
    },
    beforeRemove (file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    }
  }
}
</script>
<style lang="scss">
.item-block {
  margin-bottom: 22 px;
  span {
    font-size: 14px;
  }
  .upload-demo {
    margin: 20px 0px;
  }
}
.el-form--label-top .el-form-item__label{
    padding:0 0 0px;
}
.el-form-item {
  margin-bottom: 10px;
}
.right{
  margin:10px 0;
}
</style>
