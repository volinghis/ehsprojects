<template>
  <div>
    <el-divider content-position="left"><span style="font-size:initial;color:#409EFF;">备件基本信息</span></el-divider>
    <el-form :inline="true" :model="emaPartLibrary" :rules="rules" ref="emaPartLibrary" status-icon class="demo-form-inline" label-width="200px" >
      <el-form-item label="备件编号：" prop="code">
        <el-input v-model="emaPartLibrary.code" placeholder="请填写备件编号"  prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="备件名称：" prop="name">
        <el-input v-model="emaPartLibrary.name" placeholder="请填写备件名称" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="备件类型：" prop="type">
        <el-input v-model="emaPartLibrary.type" placeholder="请填写备件类型" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="备件型号：" prop="model">
        <el-input v-model="emaPartLibrary.model" placeholder="请填写备件型号" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="备件类别：">
        <el-input v-model="emaPartLibrary.category" placeholder="请填写备件类别" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="存放位置：">
        <el-input v-model="emaPartLibrary.location" placeholder="请填写备件存放位置" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="规格：">
        <el-input v-model="emaPartLibrary.norm" placeholder="请填写备件规格" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="品牌：">
        <el-input v-model="emaPartLibrary.brand" placeholder="请填写备件品牌" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="价格：" prop="price">
        <el-input-number v-model.number="emaPartLibrary.price" :precision="2" :step="0.01" :min="0" :max="1000000"></el-input-number>
      </el-form-item>
      <el-form-item label="数量：" prop="amount">
        <el-input v-model.number="emaPartLibrary.amount" placeholder="请输入备件数量" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="预警值：" prop="warningValue">
        <el-input v-model.number="emaPartLibrary.warningValue" placeholder="请输入预警值" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="单位：">
        <el-input v-model="emaPartLibrary.unit" placeholder="请输入单位" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="物资类型：">
        <el-input v-model="emaPartLibrary.mterialType" placeholder="请输入物资类型" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
      <el-form-item label="物资编码：" prop="mterialCode">
        <el-input v-model="emaPartLibrary.mterialCode" placeholder="请输入物资编码" prefix-icon="el-icon-edit-outline"></el-input>
      </el-form-item>
    </el-form>
    <el-divider></el-divider>
    <el-button type="primary" @click="nextStep(emaPartLibrary)" style="float:right;margin:10px;" icon="el-icon-d-arrow-right">下一步</el-button>
  </div>
</template>
<script>
export default {
  name: 'partStepOne',
  data () {
    return {
      emaPartLibrary: {
        code: '',
        name: '',
        type: '',
        model: '',
        category: '',
        location: '',
        norm: '',
        brand: '',
        price: '',
        amount: '',
        unit: '',
        warningValue: '',
        mterialType: '',
        mterialCode: ''
      },
      rules: {
        code: [
          { required: true, message: '请输入备件编号', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入备件名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 2 到 8 个字符', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请输入备件类型', trigger: 'change' }
        ],
        model: [
          { required: true, message: '请输入备件型号', trigger: 'change' }
        ],
        price: [
          { type: 'number', required: true, message: '请输入备件价格', trigger: 'change' }
        ],
        amount: [
          { type: 'number', required: true, message: '请输入备件数量', trigger: 'change' }
        ],
        warningValue: [
          { required: true, message: '预警值不能为空', trigger: 'blur' },
          { type: 'number', message: '只能输入数字', trigger: 'change' }
        ],
        mterialCode: [
          { required: true, message: '请输入备件物资编码', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    nextStep (emaPartLibrary) {
      this.$refs.emaPartLibrary.validate((valid) => {
        if (valid) {
          this.$emit('nextStep')
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .el-input-number {
    width: 300px;
}
</style>
