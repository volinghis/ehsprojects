<template>
  <div style="margin-left:10px;">
    <el-row>
      <el-card>
        <el-col :span="24">
          <div class="container">
            <!-- <div class="operation">
              <el-button type="primary" :size="GlobalCss.buttonSize" icon="fa fa-plus" @click="handlAdd"> 新增</el-button>
            </div> -->
            <div class="search-wrapper">
              <div style="float:left;">
                <el-input :size="GlobalCss.controlSize" v-model="queryParam.query" placeholder="请输入供应商编码或名称">
                  <el-button slot="append" icon="el-icon-search" @click="init"></el-button>
                </el-input>
              </div>
              <div class="operation" style="float:right">
                <el-button type="primary" icon="fa fa-plus pull-left" @click="handlAdd" :size="GlobalCss.buttonSize">添加</el-button>
              </div>
            </div>
            <div class="refRoleTable">
              <template>
                <el-table :data="supplierTableData" :size="GlobalCss.buttonSize" border>
                  <el-table-column type="index" show-overflow-tooltip min-width="45" align="center"> </el-table-column>
                  <el-table-column prop="supplierCode" show-overflow-tooltip min-width="100" align="center" label="供应商编码"></el-table-column>
                  <el-table-column prop="supplierName" show-overflow-tooltip min-width="100" align="center" label="供应商名称"></el-table-column>
                  <el-table-column prop="supplierTypeName" show-overflow-tooltip min-width="100" align="center" label="供应商类别"></el-table-column>
                  <el-table-column prop="state" show-overflow-tooltip min-width="100" align="center" label="状态">
                    <template slot-scope="scope">
                      <el-tooltip :content="scope.row.state===0 ? '启用中':'停用中'" placement="left">
                        <el-switch @change="changeState($event,scope.row,scope.$index)" v-model="scope.row.state"
                          active-color="#13ce66" inactive-color="#ff4949" :active-value="0" :inactive-value="1">
                        </el-switch>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                  <el-table-column prop="sort" show-overflow-tooltip min-width="80" sortable align="center" label="排序"
                    width="120"></el-table-column>
                  <el-table-column show-overflow-tooltip min-width="120" align="center" label="操作">
                    <template slot-scope="scope">
                      <el-button type="primary" :size="GlobalCss.buttonSize" @click="handleEdit(scope.row)">编辑
                      </el-button>
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
            <el-dialog title="编辑页面" :visible.sync="dialogTableVisible" :close-on-click-modal="false" width="40%"
              :before-close="handleClose">
              <el-divider></el-divider>
              <div>
                <el-form label-position="right" label-width="100px" ref="supplierForm" :rules="rules"
                  :model="supplierForm" :size="GlobalCss.buttonSize">
                  <el-form-item label="供应商编码:" prop="supplierCode">
                    <el-input v-model="supplierForm.supplierCode"></el-input>
                  </el-form-item>
                  <el-form-item label="供应商名称:" prop="supplierName">
                    <el-input v-model="supplierForm.supplierName" placeholder="请填写供应商名称"></el-input>
                  </el-form-item>
                  <el-form-item label="供应商类型:" prop="supplierType">
                    <el-select v-model="supplierForm.supplierType" style="width:100%" placeholder="请选择供应商类型">
                        <el-option v-for="item in supplierTypes" :key="item.key" :label="item.text" :value="item.key"></el-option>
                     </el-select>
                  </el-form-item>
                  <!-- <el-form-item label="供应商等级:" prop="supplierLevel">
                    <el-select v-model="supplierForm.supplierLevel" style="width:100%" placeholder="请选择供应商类型" >
                        <el-option v-for="item in supplierlevels" :key="item.key" :label="item.text" :value="item.key"></el-option>
                     </el-select>
                  </el-form-item> -->
                  <el-form-item label="排序:" prop="sort">
                    <el-input v-model="supplierForm.sort"></el-input>
                  </el-form-item>
                  <el-form-item label="备注:" prop="remark">
                    <el-input v-model="supplierForm.remark"></el-input>
                  </el-form-item>
                  <template>
                    <el-button type="primary" :size="GlobalCss.buttonSize" icon="fa fa-plus" @click="add"
                      style="margin-bottom:5px;"> 新增</el-button>
                    <el-row>
                      <el-col :span="24">
                        <el-table :size="GlobalCss.controlSize" border ref="contarts" :data="contarts.data" style="width: 100%" highlight-current-row>
                          <el-table-column type="index" align="center"></el-table-column>
                          <el-table-column v-for="(item,index) in contarts.columns" :label="item.label"
                            :prop="item.prop" :width="item.width" :key="index" align="center">
                            <template slot-scope="scope">
                              <span v-if="scope.row.isSet">
                                <el-input :size="GlobalCss.controlSize" placeholder="请输入内容" v-model="contarts.sel[item.prop]"></el-input>
                              </span>
                              <span v-else>{{scope.row[item.prop]}}</span>
                            </template>
                          </el-table-column>
                          <template v-show="buttonFlag">
                            <el-table-column label="操作" width="150" align="center">
                              <template slot-scope="scope">
                                <template v-if="scope.row.isSet">
                                  <el-button :size="GlobalCss.controlSize" type="success" v-show="buttonFlag"
                                    style="cursor: pointer;" @click.stop="saveRow(scope.row,scope.$index)">保存
                                  </el-button>
                                </template>
                                <template v-else>
                                  <el-button :size="GlobalCss.controlSize" type="primary" v-show="buttonFlag"
                                    style="cursor: pointer;" @click.stop="editRow(scope.row,scope.$index)">编辑
                                  </el-button>
                                </template>
                                <el-button :size="GlobalCss.controlSize" type="danger" v-show="buttonFlag"
                                  style="cursor: pointer;" @click="deleteRow(scope.$index,contarts.data)">删除
                                </el-button>
                              </template>
                            </el-table-column>
                          </template>
                        </el-table>
                      </el-col>
                    </el-row>
                  </template>
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
