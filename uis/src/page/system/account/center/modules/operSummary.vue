<template>
  <div style="height:100%;width:100%">
    <el-card shadow="never">
      <el-row :gutter="10"
              type="flex"
              class="row-bg"
              justify="center">
        <el-col :span="8">
          <div class="icon-content">
            <el-tooltip content="添加设备"
                        placement="top"><i class="fa fa-gear fa-3x"></i></el-tooltip>
            <div class="count">12台</div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="icon-content"><i class="fa fa-edit fa-3x"></i>
            <div class="count">12台</div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="icon-content"><i class="fa fa-file-text fa-3x"></i>
            <div class="count">12台</div>
          </div>

        </el-col>
      </el-row>
      <br>
      <el-row :gutter="10"
              type="flex"
              class="row-bg"
              justify="center">
        <el-col :span="8">
          <div class="icon-content"><i class="fa fa-bug fa-3x"></i>
            <div class="count">12台</div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="icon-content"><i class="fa fa-shield fa-3x"></i>
            <div class="count">12台</div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="icon-content"><i class="fa fa-link fa-3x"></i>
            <div class="count">12台</div>
          </div>
        </el-col>
      </el-row>
      <br>
      <el-row :gutter="10"
              type="flex"
              class="row-bg"
              justify="center">
        <el-col :span="8">
          <div class="icon-content">
            <el-tooltip content="6666666666666"
                        placement="top">
              <i class="fa fa-wrench fa-3x"></i>
            </el-tooltip>
            <div class="count">12台</div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="icon-content"><i class="fa fa-diamond fa-3x"></i>
            <div class="count">12台</div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="icon-content"><i class="fa fa-clock-o fa-3x"></i>
            <div class="count">12台</div>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {

}
</script>
<style lang="scss" scoped>
i {
  color: #36b8f9;
}
.icon-content {
  text-align: center;
}
.count{
 margin-top:5px;
}
</style>
