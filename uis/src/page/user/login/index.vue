<template>
  <div>
    <div class="logingPanel"></div>
    <div class="loginBody">
      <el-row>
        <el-col :span="12">
          <p><el-image :src="dhUrl" style="width: 100px; height: 100px"></el-image></p>
          <p>
            <span>仓储管理系统</span><br>
            <span>WareHouse Management System</span>
          </p>
        </el-col>
        <el-col :span="12">
          <p><span>用户登录 / </span><span>USER LOGIN</span></p>
          <p>{{result.message}}</p>
          <el-form ref="loginForm"
                   :rules="rules"
                   :model="loginForm">
            <el-form-item prop="account">
              <el-input v-model="loginForm.account"
                        placeholder="请输入账号"><template slot="prepend"><i class="el-icon-user-solid"></i></template></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="loginForm.password" @keyup.enter.native="login"
                        placeholder="请输入密码" type="password"><template slot="prepend"><i class="fa fa-key"></i></template></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary"
                         @click="login"
                         :loading="loading"
                         long>登录</el-button>
              <el-checkbox v-model="remeberAccount">记住账号</el-checkbox>
              <el-link type="primary" @click="forgetPassword">忘记密码?</el-link>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </div>
    <div class="footerBody">
      <p><span>Copyright © DongHengSoft, All Rights Reserved.<br>西安东恒鑫源软件开发有限公司</span></p>
    </div>
  </div>
</template>
<script>
import datas from './datas'
export default datas
</script>
<style lang="scss" scoped>
@import "./styles.scss";
</style>
