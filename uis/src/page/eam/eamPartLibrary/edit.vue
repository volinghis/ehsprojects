<template>
  <div>
    <el-form ref="form" :inline="true" :model="form" label-width="200px">
      <el-form-item label="备件名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="归属名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="规格型号">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="备件类型">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="备件类型">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="备件类别">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="备件类别">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="品牌">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="所在仓库">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="价格">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="数量">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="单位">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="生产厂商">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="供应商">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="物资类型">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="物资编码">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data () {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  methods: {
    onSubmit () {
    }
  }
}
</script>
<style>
  .el-form--inline .el-form-item__content{
    width: 300px;
  }
  .el-select {
    width: 300px;
  }
</style>
