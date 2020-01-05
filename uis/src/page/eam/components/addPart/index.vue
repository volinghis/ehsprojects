<template>
  <div>
    <el-form :model="partForm" inline="inline" :size="GlobalCss.controlSize">
      <el-row>
        <el-form-item label="备件名称：" prop="name">
          <el-input v-model="partForm.name"></el-input>
        </el-form-item>
        <el-form-item label="备件编号：" prop="code">
          <el-input v-model="partForm.code"></el-input>
        </el-form-item>
        <el-form-item label="备件类型：" prop="type">
          <el-input v-model="partForm.type"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search('partForm')" style="margin-left: 5px;">搜索</el-button>
        </el-form-item>
      </el-row>
    </el-form>
    <template>
      <el-table :data="tableData" border ref="multipleTable" :size="GlobalCss.controlSize">
        <el-table-column type="selection"> </el-table-column>
        <el-table-column prop="code" label="备件编号" align="center"> </el-table-column>
        <el-table-column prop="name" label="备件名称" align="center"> </el-table-column>
        <el-table-column prop="type" label="备件类型" align="center"> </el-table-column>
        <el-table-column prop="model" label="备件型号" align="center"> </el-table-column>
        <el-table-column prop="norm" label="备件规格" align="center"> </el-table-column>
      </el-table>
    </template>
  </div>
</template>
<script>
export default {
  data () {
    return {
      partForm: {
        name: '',
        code: '',
        type: ''
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

</style>>
