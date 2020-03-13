<template>
  <div style="padding:0px 20px;">
    <el-form ref="form"
             :size="GlobalCss.buttonSize"
             :model="form"
             :rules="rules"
             label-width="100px">
      <el-row>
        <el-col :span="4"
                :lg="4">
          <el-upload class="avatar-uploader"
                     :action="GlobalVars.globalServiceServlet + '/data/file/fileUpload'+ '?tt=' + Math.random()+ '&resoureMenuKey=' + $store.state.resourceMenuKey"
                     :show-file-list="false"
                     :on-success="handleAvatarSuccess"
                     :before-upload="beforeAvatarUpload">
            <img v-if="imgUrl"
                 :src="imgUrl"
                 class="avatar">
            <i v-else
               class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-col>
        <el-col :span="20"
                :lg="20">
          <el-row>
            <el-col :span="8">
              <el-form-item label="设备名称:"
                            prop="deviceName">
                <el-input v-model="form.deviceName"></el-input>
              </el-form-item>
              <el-form-item label="设备型号:"
                            prop="deviceModel">
                <el-input v-model="form.deviceModel"></el-input>
              </el-form-item>
              <el-form-item label="出厂日期:"
                            prop="leaveDate">
                <el-date-picker v-model="form.leaveDate"
                                type="date"
                                style="width:100%;"
                                placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="设备专业:"
                            prop="profession">
                <el-input v-model="form.profession"></el-input>
              </el-form-item>
              <el-form-item label="投运日期:"
                            prop="runDate">
                <el-date-picker v-model="form.runDate"
                                type="date"
                                style="width:100%;"
                                placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="生产厂家:"
                            prop="factoryName">
                <el-input v-model="form.factoryName">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="负责人:"
                            prop="person">
                <UserSelector v-model="form.person"
                              :propOrgValue="form.person"
                              @change="userSelectChange"
                              ref="userSelect"
                              style="width:100%;"></UserSelector>
              </el-form-item>
              <el-form-item label="出厂编号:"
                            prop="leaveNum">
                <el-input v-model="form.leaveNum"></el-input>
              </el-form-item>
              <el-form-item label="安装位置:"
                            prop="installLocation">
                <el-input v-model="form.installLocation"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="定期工作标准:">
            <el-input type="textarea"
                      :rows="5"
                      maxlength="300"
                      show-word-limit
                      placeholder="请输入定期工作标准"
                      v-model="form.remarks">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <!--设备参数-->
    <div class="item-block right">
      <params-table @getParamsTable="getParamsTable"
                    :deviceKey=deviceKey
                    :isDisable="false"/>
    </div>
    <!--历任点检员-->
    <div class="item-block right">
      <past-inspectors @getInspectors="getInspectors"
                       :deviceKey=deviceKey
                       :isDisable="false"/>
    </div>
    <!--子设备-->
    <div class="item-block right">
      <ChildEamTable @getRelatedKeys="getRelatedKeys"
                     :deviceKey="deviceKey" />
    </div>
    <!--关联文件-->
    <div class="item-block right">
      <FilesTable :fileId="form.fileId" :deviceKey="deviceKey" @getFileId="allFileId"></FilesTable>
    </div>
  </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="scss">
@import "./styles.scss";
</style>
