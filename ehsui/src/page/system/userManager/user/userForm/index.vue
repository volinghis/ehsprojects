<template>
  <div class="divStyle">
    <el-form ref="form"
             :model="form"
             :rules="rules"
             inline="inline"
             label-width="100px"
             label-position="right"
             :size="GlobalCss.controlSize">
      <el-row>
        <el-divider content-position="left"><span style="color:#409EFF">基础信息</span></el-divider>
        <el-col :span="12">
          <el-form-item label="工号："
                        prop="dataCode">
            <template slot-scope>
              <el-input v-model="form.dataCode" @blur.prevent="userCodeValidation(form)"></el-input>
            </template>
          </el-form-item>
          <el-form-item label="手机："
                        prop="telephone">
            <el-input v-model="form.telephone"></el-input>
          </el-form-item>
          <el-form-item label="所属部门："
                        prop="orgName">
            <el-input v-model="form.orgName" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="姓名："
                        prop="name">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="性别："
                        prop="gender">
            <!-- <el-input v-model="form.gender"></el-input> -->
             <el-select v-model="form.gender" placeholder="请选择性别" style="width:178px;">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="当前职务："
                        prop="position">
            <el-input v-model="form.position"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-divider content-position="left"><span style="color:#409EFF">辅助信息</span></el-divider>
        <el-col :span="12">
          <el-form-item label="学历："
                        prop="education">
            <el-input v-model="form.education"></el-input>
          </el-form-item>
           <el-form-item label="专业："
                        prop="profession">
            <el-input v-model="form.profession"></el-input>
          </el-form-item>
          <el-form-item label="毕业时间："
                        prop="graduatedDate">
            <el-date-picker
                style="width:178px;"
                v-model="form.graduatedDate"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮箱："
                        prop="email">
            <el-input v-model="form.address"></el-input>
          </el-form-item>
          <el-form-item label="籍贯："
                        prop="homeTown">
            <el-input v-model="form.homeTown"></el-input>
          </el-form-item>
         <el-form-item label="毕业院校："
                        prop="graduatedSchool">
            <el-input v-model="form.graduatedSchool"></el-input>
          </el-form-item>
        </el-col>
        <el-form-item label="备注："
                      prop="remark">
          <el-input type="textarea"
                    style="width:466px;"
                    v-model="form.remark"
                    placeholder="请输入内容"
                    maxlength="300"
                    show-word-limit>
          </el-input>
        </el-form-item>
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
