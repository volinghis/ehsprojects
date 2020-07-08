<template>
  <div style="margin-left:10px;">
    <el-row>
      <el-card>
      <el-col :span="24">
        <div class="container">
          <div class="search-wrapper">
            <div style="float:left;">
              <el-input :size="GlobalCss.controlSize" v-model="queryParam.query" placeholder="请输入商品编码或名称">
                <el-button slot="append" icon="el-icon-search" @click="init"></el-button>
              </el-input>
            </div>
            <div class="operation" style="float:right">
              <el-button type="primary" icon="fa fa-plus pull-left" @click="handlAdd" :size="GlobalCss.buttonSize">添加</el-button>
            </div>
          </div>
          <div class="refRoleTable" style="margin-left:10px;">
            <template>
              <el-table :data="commodityTable" :size="GlobalCss.buttonSize" border>
                <el-table-column type="index" show-overflow-tooltip min-width="45" align="center"> </el-table-column>
                <el-table-column prop="deviceCode" label="商品编号" min-width="100" sortable show-overflow-tooltip align="center"></el-table-column>
                <el-table-column prop="deviceName" label="商品名称" min-width="100" sortable show-overflow-tooltip align="center"></el-table-column>
                <el-table-column prop="norm" label="规格型号" min-width="110" sortable show-overflow-tooltip align="center"></el-table-column>
                <el-table-column prop="leaveFactoryCode" label="出厂编号" min-width="100" sortable show-overflow-tooltip align="center"></el-table-column>
                <el-table-column prop="leaveFactoryDate" label="出厂日期" width="100" sortable show-overflow-tooltip align="center"></el-table-column>
                <el-table-column prop="price" label="单价" min-width="80" sortable show-overflow-tooltip align="center"></el-table-column>
                <el-table-column prop="unit" label="单位" min-width="80" sortable show-overflow-tooltip align="center"></el-table-column>
                <el-table-column show-overflow-tooltip min-width="120" align="center" label="操作">
                  <template slot-scope="scope">
                    <el-button type="primary" :size="GlobalCss.buttonSize" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button type="danger" :size="GlobalCss.buttonSize" @click="handleRemove(scope.row)">删除
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination @current-change="handleCurrentChange" style="text-align: right;"
                :current-page.sync="queryParam.page" :page-size="queryParam.size" layout="total, prev, pager, next"
                :total="totalCount">
              </el-pagination>
            </template>
          </div>
          <!-- </el-card> -->
          <!--添加组织-->
          <el-dialog title="编辑页面" :visible.sync="dialogTableVisible" :close-on-click-modal="false" width="40%" :before-close="handleClose">
            <el-divider></el-divider>
            <div>
              <el-form label-position="right" label-width="80px" ref="commodityForm" :rules="rules" :model="commodityForm" :size="GlobalCss.buttonSize">
                <el-form-item label="仓库选择:" prop="wareHouse">
                  <el-select v-model="commodityForm.wareHouse" style="width:100%" placeholder="请选择所在仓库">
                    <el-option v-for="item in wareHouses" :key="item.key" :label="item.text" :value="item.key"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="商品编码:" prop="deviceCode">
                  <el-input v-model="commodityForm.deviceCode"></el-input>
                </el-form-item>
                <el-form-item label="商品名称:" prop="deviceName">
                  <el-input v-model="commodityForm.deviceName"></el-input>
                </el-form-item>
                <el-form-item label="规格型号:" prop="norm">
                  <el-input v-model="commodityForm.norm"></el-input>
                </el-form-item>
                <el-form-item label="出厂编号:" prop="leaveFactoryCode">
                  <el-input v-model="commodityForm.leaveFactoryCode"></el-input>
                </el-form-item>
                <el-form-item label="出厂日期:" prop="leaveFactoryDate">
                   <el-date-picker v-model="commodityForm.leaveFactoryDate" type="date" style="width:100%;" placeholder="选择日期" ></el-date-picker>
                </el-form-item>
                <el-form-item label="单价:" prop="price">
                  <el-input v-model="commodityForm.price"></el-input>
                </el-form-item>
                <el-form-item label="单位:" prop="unit">
                  <el-input v-model="commodityForm.unit"></el-input>
                </el-form-item>
              </el-form>
            </div>
            <el-divider></el-divider>
            <div slot="footer" class="dialog-footer">
              <el-button @click="handleReset" :size="GlobalCss.buttonSize">取 消</el-button>
              <el-button type="primary" @click="handleSubmit" :size="GlobalCss.buttonSize">保 存</el-button>
            </div>
          </el-dialog>
        </div>
        <!-- </el-card> -->
      </el-col>
      </el-card>
    </el-row>
  </div>
</template>
<script>
import datas from './datas.js'
export default datas

</script>

<style lang="scss" scoped>
  @import "./styles.scss";
</style>
