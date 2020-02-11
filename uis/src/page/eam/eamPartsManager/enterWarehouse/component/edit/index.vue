<template>
  <div :style="{height:$store.state.contentHeight+'px',padding:'0px 20px',background:'#fff'}"
       class="divHeight">
    <el-form ref="form"
             :model="form"
             :rules="rules"
             label-position="right"
             label-width="100px"
             :size="GlobalCss.buttonSize"
             class="formWidth">
      <div class="searchHeight">
        <el-row :gutter="24">
          <el-col :span="8">
            <el-form-item label="入库编码："
                          prop="warehouseCode">
              <el-input v-model="form.warehouseCode"
                        placeholder="不用填写，系统自动生成"></el-input>
            </el-form-item>
            <el-form-item label="入库类型："
                          prop="inboundType">
              <el-input v-model="form.inboundType"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="入库仓库："
                          prop="warehouseName">
              <el-input v-model="form.warehouseName"></el-input>
            </el-form-item>
            <el-form-item label="入库日期："
                          prop="inboundDate">
              <el-date-picker v-model="form.inboundDate"
                              type="date"
                              placeholder="请选择入库日期"
                              style="width:100%"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="创 建 人："
                          prop="founder">
              <el-input v-model="form.founder"
                        disabled
                        placeholder="不用填写，系统自动生成"></el-input>
            </el-form-item>
            <!-- <el-form-item label="供 应 商："
                          prop="supplier">
              <el-input v-model="form.supplier"></el-input>
            </el-form-item> -->
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="备注信息："
                        prop="remark">
            <el-input type="textarea"
                      :autosize="{ minRows: 2, maxRows: 6}"
                      placeholder="请输入内容"
                      v-model="form.remark"
                      maxlength="300"
                      show-word-limit></el-input>
          </el-form-item>
        </el-row>
      </div>
      <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.buttonSize" @click="dialogVisible = true" v-show="show">选择备件</el-button>
      <el-button type="primary" icon="el-icon-plus" class="buttonHeight" :size="GlobalCss.buttonSize" @click="handlerAdd" v-show="show">添加备件</el-button>
      <!--备件表格-->
      <div class="tableHeight"
           :style="{height:tableHeight}">
        <table-part ref="table"
                    :parts="parts"
                    :objPart="obj"
                    :showFlag="showFlag"
                    :partsTable="partsTable"
                    @tableParams="tableParams"
                    :totalCounts="totalCount"></table-part>
      </div>
      <!-- <div style="text-align: center; padding:10px"
           class="submitHeight">
        <el-button type="primary"
                   @click="submitForm"
                   :size="GlobalCss.buttonSize">提&nbsp;&nbsp;交</el-button>
        <el-button @click="resetForm"
                   :size="GlobalCss.buttonSize">重&nbsp;&nbsp;置</el-button>
      </div> -->
    </el-form>
    <el-dialog title="添加备件"
               :visible.sync="dialogVisible"
               width="50%"
               :before-close="handleClose"
               destroy-on-close>
      <div style="text-align:center;">
        <add-part @partsData="partsData"></add-part>
        <div style="margin-top:45px;">
          <span slot="footer"
                class="dialog-footer">
            <el-button type="primary"
                       @click="overSelect"
                       :size="GlobalCss.buttonSize">确 定</el-button>
            <el-button @click="resetSelect"
                       :size="GlobalCss.buttonSize">取 消</el-button>
          </span>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
