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
                       :action="GlobalVars.globalServiceServlet + '/data/file/fileUpload'+ '?tt=' + Math.random()+ '&resoureMenuKey=' + $store.state.resourceMenuKey"
                       multiple>
              <el-image v-if="imgUrl" :src="imgUrl" fit="contain"></el-image>
              <div v-else class="uploadContent" >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">
                  将图片拖到此处，或<em>点击上传</em>
                </div>
              </div>
              <div class="el-upload__tip"
                   slot="tip">
                只能上传jpg/png文件，且不超过500kb
              </div>
            </el-upload>
          </div>
          <!--检修质量标准-->
          <div class="item-block">
            <span>检修质量标准</span>
            <file-upload></file-upload>
          </div>
          <!--设备说明书-->
          <div class="item-block">
            <span>设备说明书</span>
            <file-upload></file-upload>
          </div>
          <!--设备操作手册-->
          <div class="item-block">
            <span>设备操作手册</span>
            <file-upload></file-upload>
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
                  <span> 当前设备资料的完整度为{{form.completePoint}}%，请尽快完善资料</span>
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
              <el-form-item label="设备专业"
                            prop="profession">
                <el-input v-model="form.profession"></el-input>
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
                <el-date-picker v-model="form.purchaseTime"
                                type="date"
                                style="width:100%;"
                                placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
               <el-form-item label="负责人"
                            prop="person">
                <UserSelector v-model="form.person" :propOrgValue="form.person" @change="userSelectChange" ref="userSelect"  style="width:100%;"></UserSelector>
              </el-form-item>
              <el-form-item label="出厂编号"
                            prop="leaveNum">
                <el-input v-model="form.leaveNum"></el-input>
              </el-form-item>
              <el-form-item label="安装位置"
                            prop="installLocation">
                <el-input v-model="form.installLocation"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <!--设备参数-->
          <div class="item-block right">
            <span>设备主要参数</span>
            <params-table @getParamsTable="getParamsTable"
                          :deviceKey=deviceKey
                          :isDisable="false"></params-table>
          </div>

          <!--历任点检员-->
          <div class="item-block right">
            <span>历任点检员</span>
            <past-inspectors @getInspectors="getInspectors"
                             :deviceKey=deviceKey
                             :isDisable="false"></past-inspectors>
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
            <ChildEamTable @getRelatedKeys="getRelatedKeys"
                           :deviceKey="deviceKey" />
          </div>
        </el-col>
      </el-row>
      <!-- <el-form-item style="text-align: center;">
        <el-button :size="GlobalCss.buttonSize"
                   type="warning"
                   @click="$router.push({name:'eamLedger'})">返回</el-button>
        <el-button :size="GlobalCss.buttonSize"
                   type="primary"
                   @click="handerSubmit()">提交</el-button>
      </el-form-item> -->
    </el-form>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss">
@import "./styles.scss";
</style>
