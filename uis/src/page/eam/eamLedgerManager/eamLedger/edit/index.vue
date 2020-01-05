<template>
  <div style="padding:0px 20px;">
    <el-form ref="form"
             size="mini"
             :model="form"
             :rules="rules"
             label-width="80px">
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="item-block">
            <span>设备图片</span>
            <el-upload class="upload-demo"
                       drag
                       :on-success="handleAvatarSuccess"
                       action="https://jsonplaceholder.typicode.com/posts/"
                       multiple>
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                将图片拖到此处，或<em>点击上传</em>
              </div>
              <div class="el-upload__tip"
                   slot="tip">
                只能上传jpg/png文件，且不超过500
              </div>
            </el-upload>
          </div>
          <!--检修质量标准-->
          <div class="item-block">
            <span>检修质量标准</span>
            <el-upload class="upload-demo"
                       action="https://jsonplaceholder.typicode.com/posts/"
                       :on-remove="handleRemove"
                       :before-remove="beforeRemove"
                       multiple
                       :limit="3"
                       :on-exceed="handleExceed">
              <el-button size="mini"
                         type="primary"
                         icon="el-icon-upload">上传文件</el-button>
            </el-upload>
          </div>
          <!--设备说明书-->
          <div class="item-block">
            <span>设备说明书</span>
            <el-upload class="upload-demo"
                       action="https://jsonplaceholder.typicode.com/posts/"
                       :on-remove="handleRemove"
                       :before-remove="beforeRemove"
                       multiple
                       :limit="3"
                       :on-exceed="handleExceed"
                       :file-list="fileList">
              <el-button size="mini"
                         type="primary"
                         icon="el-icon-upload">上传文件</el-button>
            </el-upload>
          </div>
          <!--设备操作手册-->
          <div class="item-block">
            <span>设备操作手册</span>
            <el-upload class="upload-demo"
                       action="https://jsonplaceholder.typicode.com/posts/"
                       :on-remove="handleRemove"
                       :before-remove="beforeRemove"
                       multiple
                       :limit="3"
                       :on-exceed="handleExceed"
                       :file-list="fileList">
              <el-button :size="GlobalCss.buttonSize"
                         type="primary"
                         icon="el-icon-upload">上传文件</el-button>
            </el-upload>
          </div>

          <div class="item-block">
            <span>资料完整度</span>
            <el-row>
              <el-card>
                <el-col :span="12">
                  <el-progress type="circle"
                               :percentage="form.completePoint"
                               :stroke-width="10"></el-progress>
                </el-col>
                <el-col :span="12">
                  <span> 当前设备资料的完整度为75%，请尽快完善资料</span>
                </el-col>
              </el-card>
            </el-row>
          </div>
        </el-col>
        <el-col :span="18">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="设备名称"
                            prop="deviceName">
                <el-input v-model="form.deviceName"></el-input>
              </el-form-item>
              <el-form-item label="设备型号"
                            prop="deviceModel">
                <el-input v-model="form.deviceModel"></el-input>
              </el-form-item>
              <el-form-item label="出厂日期"
                            prop="leaveDate">
                <el-date-picker v-model="form.leaveDate"
                                type="date"
                                style="width:100%;"
                                placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="生产厂家"
                            prop="factoryName">
                <el-input v-model="form.factoryName"></el-input>
              </el-form-item>
              <el-form-item label="投运日期"
                            prop="runDate">
                <el-date-picker v-model="form.runDate"
                                type="date"
                                style="width:100%;"
                                placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="采购时间"
                            prop="purchaseTime">
                <el-input v-model="form.purchaseTime"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="设备编码"
                            prop="deviceNum">
                <el-input v-model="form.deviceNum"
                          placeholder="不用填写，系统自动生成"></el-input>
              </el-form-item>
              <el-form-item label="出厂编号"
                            prop="leaveNum">
                <el-input v-model="form.leaveNum"></el-input>
              </el-form-item>
              <el-form-item label="创建人"
                            prop="person">
               <el-input v-model="form.person"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <!--设备参数-->
          <div class="item-block right">
            <span>设备主要参数</span>
            <params-table @getParamsTable="getParamsTable" @deleteParamsTable="deleteParamsTable" :deviceKey=deviceKey></params-table>
          </div>

          <!--历任点检员-->
          <div class="item-block right">
            <span>历任点检员</span>
            <past-inspectors></past-inspectors>
          </div>
           <div class="item-block right">
            <span>定期工作标准</span>
            <el-input type="textarea"
                      :rows="5"
                      maxlength="300"
                      show-word-limit
                      placeholder="请输入定期工作标准"
                      v-model="form.textarea">
            </el-input>
          </div>
          <!--子设备-->
          <div class="item-block right">
            <span>关联子设备</span>
            <el-button type="primary"
                       icon="fa fa-plus pull-left"
                       @click="dialogTableVisible = true"
                       style="float:right;"
                       :size="GlobalCss.buttonSize">选择设备</el-button>
            <el-table :data="tableData"
                      size="small"
                      style="width: 100%">
              <el-table-column prop="deviceNum"
                               label="设备编号"></el-table-column>
              <el-table-column prop="deviceName"
                               label="设备名称"></el-table-column>
              <el-table-column prop="installLocation"
                               label="类型"></el-table-column>
              <el-table-column prop="deviceModel"
                               label="型号"></el-table-column>
              <el-table-column prop="brand"
                               width="100"
                               label="品牌"></el-table-column>
              <el-table-column align="center"
                               width="200"
                               label="操作">
                <template slot-scope="scope">
                  <el-button type="text"
                             @click="handleEditClick(scope.row)"
                             icon="el-icon-edit"
                             :size="GlobalCss.buttonSize">编辑</el-button>
                  <el-button @click="handleDeleteClick(scope.row)"
                             type="text"
                             icon="el-icon-delete"
                             :size="GlobalCss.buttonSize">删除</el-button>
                </template>
              </el-table-column>
            </el-table>

            <!--设备选择弹窗-->
            <el-dialog title="设备台账"
                       :visible.sync="dialogTableVisible">
              <eam-list></eam-list>
              <div slot="footer"
                   class="dialog-footer">
                <el-button @click="dialogTableVisible = false">取 消</el-button>
                <el-button type="primary"
                           @click="dialogTableVisible = false">确 定</el-button>
              </div>
            </el-dialog>
          </div>
        </el-col>
      </el-row>
      <el-form-item style="text-align: center;">
        <el-button :size="GlobalCss.buttonSize"
                   type="warning"
                   @click="$router.go(-1)">返回</el-button>
        <el-button :size="GlobalCss.buttonSize"
                   type="primary"
                   @click="submitForm('form')">提交</el-button>
        <!-- <el-button size="small"
                   @click="resetForm('form')">重置</el-button> -->
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss">
@import './styles.scss'
</style>
