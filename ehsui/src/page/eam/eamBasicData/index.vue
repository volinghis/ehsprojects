<template>
<el-row type="flex" class="row-bg" justify="center">
  <el-col :span="12" >
  <div class="eamBasicData-wrapper" style="margin-top:30%;">
    <div class="header">设备基础信息数据库</div>
    <div class="container">
      <!-- <el-button-group>
        <el-button size="mini">设备</el-button>
        <el-button size="mini">备件</el-button>
        <el-button size="mini">资料</el-button>
      </el-button-group> -->
      <div class="search">
        <el-input
          placeholder="请输入搜索内容"
          v-model="input"
          class="input-with-select"
        >
          <el-select v-model="select" slot="prepend" placeholder="选项">
            <el-option label="设备" value="1"></el-option>
            <el-option label="备件" value="2"></el-option>
            <el-option label="资料" value="3"></el-option>
          </el-select>
        </el-input>
      </div>
      <div class="tags">
        <span>电抗器</span><span>凝结水输送泵电动机</span
        ><span>EHC油泵电动机</span><span>辅助油泵电动机</span>
      </div>
    </div>
    <div class="footer">
      <el-button type="primary">搜索</el-button>
      <el-button type="primary" @click="handAddClick">添加信息</el-button>
    </div>
  </div>
  </el-col>
</el-row>
</template>
<script>
export default {
  data () {
    return {
      input: '',
      select: ''
    }
  },
  methods: {
    handAddClick () {
      this.$router.push({ name: '51' })
    }
  }
}
</script>
<style lang="scss">
.header {
  font-weight: 700;
  text-align: center;
  font-size: 48px;
  letter-spacing: 6px;
  color: #2db7f5;
  margin-bottom: 50px;
}
.el-select {
  width: 80px;
}

.footer {
  margin-top: 30px;
  text-align: center;
}
.tags {
  margin-top: 15px;
  span {
    margin-right: 10px;
  }
}
.el-button-group > .el-button:not(:last-child) {
  margin-right: 2px;
}
</style>
