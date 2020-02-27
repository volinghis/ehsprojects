<template>
  <div class="divStyle">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px" label-position="right" :size="GlobalCss.controlSize">
      <el-row>
        <el-divider content-position="left"><span style="color:#409EFF">基础信息</span></el-divider>
        <el-col :span="12">
          <el-form-item label="工号：" prop="dataCode">
              <el-input v-model="form.dataCode" :disabled="inputShow"></el-input>
              <!-- <el-input v-model="form.dataCode" @blur.prevent="userCodeValidation(form)"></el-input> -->
          </el-form-item>
          <el-form-item label="手机：" prop="telephone">
            <el-input v-model="form.telephone" :disabled="inputShow"></el-input>
          </el-form-item>
          <el-form-item label="所属部门：" prop="orgName">
            <el-input v-model="form.orgName" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="姓名：" prop="name">
            <el-input v-model="form.name" :disabled="inputShow"></el-input>
          </el-form-item>
          <el-form-item label="性别：" prop="gender">
            <el-select v-model="form.gender" placeholder="请选择性别" style="width:100%;" :disabled="inputShow">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="当前职务：" prop="position">
            <el-input v-model="form.position" :disabled="inputShow"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-divider content-position="left"><span style="color:#409EFF">辅助信息</span></el-divider>
        <el-col :span="12">
          <el-form-item label="学历：" prop="education" >
            <el-input v-model="form.education" :disabled="inputShow"></el-input>
          </el-form-item>
          <el-form-item label="专业：" prop="profession">
            <el-input v-model="form.profession" :disabled="inputShow"></el-input>
          </el-form-item>
          <el-form-item label="毕业院校：" prop="graduatedSchool">
            <el-input v-model="form.graduatedSchool" :disabled="inputShow"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮箱：" prop="email">
            <el-input v-model="form.email" :disabled="inputShow"></el-input>
          </el-form-item>
          <el-form-item label="籍贯：" prop="homeTown">
            <el-input v-model="form.homeTown" :disabled="inputShow"></el-input>
          </el-form-item>
          <el-form-item label="毕业时间：" prop="graduatedDate">
            <el-date-picker style="width:100%;" v-model="form.graduatedDate" type="date" placeholder="选择日期" :disabled="inputShow"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col>
          <el-form-item label="备注：" prop="remark">
            <el-input type="textarea" style="width:660px;" v-model="form.remark" placeholder="请输入内容" maxlength="300"
              :disabled="inputShow" show-word-limit>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import datas from './datas.js'
export default datas
</script>

<style lang="scss" scoped>
  @import "./styles.scss";
</style>
