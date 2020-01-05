<template>
  <div>
    <el-divider content-position="left"><span style="font-size:xx-small;color:#409EFF;">备件信息</span></el-divider>
    <el-form inline="inline" ref="form" :model="form" :rules="rules" label-position="right" label-width="90px" :size="GlobalCss.buttonSize">
      <el-form-item label="备件名称:" prop="name">
        <el-input v-model="form.name" placeholder="请填写备件名称" ></el-input>
      </el-form-item>
      <el-form-item label="备件编号:" prop="code">
        <el-input v-model="form.code" placeholder="请填写备件名称" ></el-input>
      </el-form-item>
      <el-form-item label="备件类型:" prop="type">
        <el-input v-model="form.type" placeholder="请填写备件名称" ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')" style="margin-left: 15px;">搜索</el-button>
      </el-form-item>
    </el-form>
    <template>
      <el-table :data="tableData" border ref="multipleTable" class="tableHeight" :size="GlobalCss.controlSize">
        <el-table-column type="selection" align="center"></el-table-column>
        <el-table-column prop="code" label="备件编号" align="center"></el-table-column>
        <el-table-column prop="name" label="备件名称" align="center"></el-table-column>
        <el-table-column prop="type" label="备件类型" align="center"></el-table-column>
        <el-table-column prop="model" label="备件型号" align="center"></el-table-column>
        <el-table-column prop="norm" label="备件规格" align="center"></el-table-column>
      </el-table>
    </template>
    <el-divider></el-divider>
    <div style="text-align:center;">
      <el-button @click="prevStep" style="margin:10px;" :size="GlobalCss.buttonSize">上一步</el-button>
      <el-button type="primary" @click="nextStep(form)" style="margin:10px;" :size="GlobalCss.buttonSize">下一步</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'partStepTwo',
  methods: {
    nextStep: function (form) {
      this.$refs.form.validate((valid) => {
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
    prevStep: function () {
      this.$emit('prevStep')
    }
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
      },
      tableData: [{
        code: 'FM-0001',
        name: '阀门',
        completion: 80,
        type: '阀门',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }, {
        code: 'GL-0001',
        name: '锅炉',
        completion: 20,
        type: '锅炉',
        model: '阀门',
        norm: 'FM_0001_01',
        brand: '上海市普陀牌',
        warehouse: '1号仓库',
        unit: '个',
        price: '100',
        amount: '56',
        warningValue: '10'
      }]
    }
  }
}
</script>

<style lang="scss" scoped>
  .stepFormText {
    margin-bottom: 24px;
    .ant-form-item-label, .ant-form-item-control {
        line-height: 22px;
      }
  }
</style>
