<template>
  <div>
    <el-table :data="tableData" resizable highlight-current-row border :max-height="tableHeight" :size="GlobalCss.buttonSize" style="width: 100%;">
      <el-table-column fixed="left" prop="id" label="设备编号" align="center"></el-table-column>
      <el-table-column fixed="left" prop="name" label="设备名称" align="center"></el-table-column>
      <el-table-column prop="type" label="设备型号" align="center"></el-table-column>
      <el-table-column prop="amount" label="设备类型" align="center"></el-table-column>
      <el-table-column prop="price" label="位置" align="center"></el-table-column>
      <el-table-column fixed="right" label="操作" width="160" align="center">
        <template slot-scope="scope">
          <el-button @click="drawer = true" type="primary" size="mini" style="margin-left: 16px;">编辑</el-button>
          <el-button @click="handleClickDel(scope.row)" size="mini"  type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination class="pageHeight" background style="text-align:right;"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      :page-sizes="[10, 20, 50]"
      :page-size="10"
      layout="total, sizes, prev, pager, next"
      :total="100">
    </el-pagination>
    <el-drawer title="设备编辑" :visible.sync="drawer" :direction="direction" :before-close="handleClose">
      <div style="margin-right:20px;">
        <el-form :model="form" :rules="rules" :size="GlobalCss.buttonSize" ref="form" label-width="100px">
          <el-form-item label="设备编号：">
            <el-input v-model="form.code"></el-input>
          </el-form-item>
          <el-form-item label="设备名称：">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="设备型号：">
            <el-input v-model="form.type"></el-input>
          </el-form-item>
          <el-form-item label="设备类型：">
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
            <el-button type="primary" @click="submitForm('form')" :size="GlobalCss.buttonSize">提 交</el-button>
            <el-button @click="handleClose" :size="GlobalCss.buttonSize">取 消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import './styles.scss';
</style>
