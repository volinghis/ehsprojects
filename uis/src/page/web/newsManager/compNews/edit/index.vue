<template>
  <div>
      <el-row>
       <el-col :span="24" class="warp-main">
        <el-form ref="infoForm" :model="infoForm" :rules="rules" label-suffix="：" label-width="80px" >
          <el-form-item label="标题" prop="newsTitle">
            <el-input v-model="infoForm.newsTitle" :disabled="inputDisable" size="samll"></el-input>
          </el-form-item>
          <el-form-item label="是否重大">
            <el-switch v-model="infoForm.major" active-text="重大" inactive-text="不重大" :disabled="disabled"></el-switch>
          </el-form-item>
          <el-form-item label="详细" prop="newsContent">
            <div class="edit_container">
              <quill-editor :disabled="inputDisable" v-model="infoForm.newsContent" ref="myQuillEditor" class="editer"
                  :options="editorOption" @ready="onEditorReady($event)" style="height: 400px;margin-bottom:40px;">
              </quill-editor>
            </div>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <div style="margin-left:80px;margin-top: 10px;">
          <el-button type="primary" @click="onSubmit" size="small" v-show="buttonDisable">发布</el-button>
          <el-button @click="reset" size="small" v-show="buttonDisable">重置</el-button>
          <el-button type="primary" @click="goBack" size="small" v-show="inputDisable" style="margin-left:0px;">返回</el-button>
        </div>
      </el-col>
    </el-row>
    </div>
</template>

<script>
import datas from './datas'
export default datas
</script>
<style lang="sass" scoped>
  @import './styles.scss'
</style>
