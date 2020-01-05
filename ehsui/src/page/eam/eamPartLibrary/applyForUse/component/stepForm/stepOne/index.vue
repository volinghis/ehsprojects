<template>
  <div>
    <el-divider content-position="left"><span style="font-size:xx-small;color:#409EFF;">申请信息</span></el-divider>
      <el-form :model="emaPartLibrary" :rules="rules" ref="emaPartLibrary" class="table" label-width="100px" :size="GlobalCss.buttonSize" >
        <!-- <el-row>
          <el-col :span="24"> -->
            <el-form-item label="申请名称：" prop="appayName">
              <el-input v-model="emaPartLibrary.appayName" placeholder="请填写本次申请名称"></el-input>
            </el-form-item>
            <el-form-item label="申请人：" prop="code">
              <el-input v-model="emaPartLibrary.code" placeholder="请填写申请人"></el-input>
            </el-form-item>
            <el-form-item label="申请部门：" prop="name">
              <el-input v-model="emaPartLibrary.name" placeholder="请填写申请部门"></el-input>
            </el-form-item>
            <el-form-item label="领用类型：" prop="type">
              <el-select v-model="emaPartLibrary.type" placeholder="请选择类型" style="width:100%">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" style="width:100%"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="申请日期：" prop="date">
              <el-date-picker v-model="emaPartLibrary.date" type="date" placeholder="选择日期" style="width:100%"></el-date-picker>
            </el-form-item>
            <el-form-item label="备注：">
              <el-input type="textarea" :rows="2" v-model="emaPartLibrary.remark" placeholder="请填写备注" maxlength="100"  show-word-limit></el-input>
            </el-form-item>
          <!-- </el-col>
        </el-row> -->
      </el-form>
    <el-divider></el-divider>
    <div style="text-align:center;">
      <el-button type="primary" @click="nextStep(emaPartLibrary)" style="margin:10px;" :size="GlobalCss.buttonSize">下一步</el-button>
      <el-button  @click="over" style="margin:10px;" :size="GlobalCss.buttonSize">撤销</el-button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'partStepOne',
  data () {
    return {
      value: '',
      options: [
        {
          value: '1',
          label: '维修'
        },
        {
          value: '2',
          label: '损耗'
        },
        {
          value: '3',
          label: '养护'
        },
        {
          value: '4',
          label: '借用'
        },
        {
          value: '5',
          label: '其他'
        }
      ],
      emaPartLibrary: {
        appayName: '',
        name: '',
        code: '',
        type: '',
        date: new Date()
      },
      rules: {
        appayName: [
          { required: true, message: '请填写申请名称', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择类型', trigger: 'change' }
        ],
        date: [
          { required: true, message: '请选择日期', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    nextStep: function (emaPartLibrary) {
      this.$refs.emaPartLibrary.validate((valid) => {
        if (valid) {
          this.$message({
            message: '恭喜你，这是一条成功消息',
            type: 'success'
          })
          this.$emit('nextStep')
        } else {
          this.$message.error('错了哦，这是一条错误消息')
          return false
        }
      })
    },
    over: function () {
      this.$emit('finish')
      this.$router.push({ name: '33' })
    }
  }
}
</script>

<style lang="scss" scoped>
.table{
  width: 60%;
  position: relative;
  left: 20%;
}
.el-textarea{
  padding: 0px;
}
</style>
