<template>
  <div style="margin-right:20px;">
        <el-form :model="form" :rules="rules" :size="GlobalCss.buttonSize" ref="form" label-width="100px">
          <el-form-item label="备件编号：">
            <el-input v-model="form.code"></el-input>
          </el-form-item>
          <el-form-item label="备件名称：">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="备件型号：">
            <el-input v-model="form.type"></el-input>
          </el-form-item>
          <el-form-item label="备件类型：">
            <el-input v-model="form.type"></el-input>
          </el-form-item>
          <el-form-item label="数量：" prop="amount">
            <el-input v-model="form.amount"></el-input>
          </el-form-item>
          <el-form-item label="单价：" prop="price">
            <el-input v-model="form.price"></el-input>
          </el-form-item>
          <el-form-item label="单位：" prop="unit">
            <el-input v-model="form.unit"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('form')">提交</el-button>
            <el-button @click="handleClose">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
</template>
<script>
export default {
  methods: {
    handleClose: function (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    }
  },
  data () {
    return {
      form: {
        name: '',
        code: '',
        type: '',
        price: '',
        amount: '',
        unit: ''
      },
      rules: {
        price: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        unit: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        amount: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ]
      }
    }
  }
}
</script>
<style lang="scss" scoped>

</style>
