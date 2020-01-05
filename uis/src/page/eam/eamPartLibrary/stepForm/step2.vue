<template>
  <div>
    <el-divider content-position="left"><span style="font-size:initial;color:#409EFF;">厂家基本信息</span></el-divider>
    <el-form :inline="true" :model="formInline" :rules="rules" ref="formInline" status-icon label-width="200px" >
      <el-form-item label="生产厂家：" prop="manufacturer">
        <el-input v-model="formInline.manufacturer" placeholder="请填写生产厂家"></el-input>
      </el-form-item>
      <el-form-item label="供应商：" prop="supplier">
        <el-input v-model="formInline.supplier" placeholder="请填写供应商"></el-input>
      </el-form-item>
      <el-form-item label="出厂日期：" prop="manufactureTime">
        <el-date-picker v-model="formInline.manufactureTime" type="date" placeholder="请选择出厂日期" style="width: 300px;"></el-date-picker>
      </el-form-item>
      <el-form-item label="使用寿命：" prop="useTime">
        <el-input v-model="formInline.useTime" placeholder="请填写使用寿命"></el-input>
      </el-form-item>
      <el-form-item label="购买时间：" prop="buyTime">
        <el-date-picker v-model="formInline.buyTime" type="date" placeholder="请选择购买时间" style="width: 300px;"></el-date-picker>
      </el-form-item>
      <el-form-item label="报废时间：" prop="scrappedTime">
        <el-date-picker v-model="formInline.scrappedTime" type="date" placeholder="请选择报废时间" style="width: 300px;"></el-date-picker>
      </el-form-item>
      <el-form-item label="保修期：" prop="warrantyTime">
        <el-input v-model="formInline.warrantyTime" placeholder="请填写保修期"></el-input>
      </el-form-item>
      <el-form-item label="备注：" prop="remark">
        <el-col>
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" maxlength="100"
          show-word-limit placeholder="请输入内容" v-model="formInline.remark"></el-input>
        </el-col>
      </el-form-item>
    </el-form>
    <el-divider></el-divider>
    <el-button :loading="loading" type="primary" @click="nextStep" style="float:right;margin:10px;" icon="el-icon-finished">提交</el-button>
    <el-button @click="prevStep" style="float:right;margin:10px;" icon="el-icon-d-arrow-left">上一步</el-button>
  </div>
</template>

<script>
export default {
  name: 'partStepTwo',
  data () {
    return {
      loading: false,
      formInline: {
        manufacturer: '',
        supplier: '',
        manufactureTime: '',
        useTime: '',
        buyTime: '',
        scrappedTime: '',
        warrantyTime: '',
        remark: ''
      },
      rules: {
        manufactureTime: [
          { type: 'date', required: true, message: '请选择出厂日期', trigger: 'change' }
        ],
        buyTime: [
          { type: 'date', required: true, message: '请选择购买时间', trigger: 'change' }
        ],
        scrappedTime: [
          { type: 'date', required: true, message: '请选择报废时间', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    nextStep (formInline) {
      const that = this
      that.loading = true
      this.$refs.formInline.validate((valid) => {
        if (valid) {
          that.timer = setTimeout(function () {
            that.loading = false
            that.$emit('nextStep')
          }, 1200)
        } else {
          that.loading = false
          return false
        }
      })
    },
    prevStep () {
      this.$emit('prevStep')
    }
  },
  beforeDestroy () {
    clearTimeout(this.timer)
  }
}
</script>

<style lang="scss" scoped>
  .stepFormText {
    margin-bottom: 24px;

    .ant-form-item-label,
    .ant-form-item-control {
      line-height: 22px;
    }
  }

</style>
