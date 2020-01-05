<template>
  <div class="page-header-wrapper-grid-content-main">
    <el-row :gutter="24">
      <el-col :md="24"
              :lg="24">
        <div class="account-center-avatarHolder">
          <div class="avatar">
            <img src='@/assets/Avatar.svg' />
          </div>
          <div class="username">东恒测试</div>
          <div class="bio">海纳百川，有容乃大</div>
          <div class="rate-wrapper">
            <el-rate v-model="value"
                     :icon-classes="['el-icon-medal', 'el-icon-medal', 'el-icon-medal']"
                     void-icon-class="el-icon-medal"
                     :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
            </el-rate>
          </div>
        </div>
        <div class="account-center-detail">
          <p>
            <i class="el-icon-user"></i><span>高级工程师</span>
          </p>
          <p>
            <i class="el-icon-office-building"></i><span>设备部－电气专业</span>
          </p>
          <p>
            <i class="el-icon-location-outline"></i><span>陕西省</span><span>西安市</span>
          </p>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data () {
    return {
      inputVisible: false,
      inputValue: '',
      value: null
    }
  },
  methods: {

  }
}
</script>

<style scoped>
.page-header-wrapper-grid-content-main {
  width: 100%;
  height: 100%;
}
.account-center-detail {
  text-align: center;
}
.username {
  color: rgba(0, 0, 0, 0.85);
  font-size: 20px;
  line-height: 28px;
  font-weight: 500;
  margin-bottom: 4px;
}

.avatar {
  margin: 0 auto;
  width: 104px;
  height: 104px;
  margin-bottom: 20px;
  border-radius: 50%;
  overflow: hidden;
}
img {
  height: 100%;
  width: 100%;
}
.account-center-avatarHolder {
  text-align: center;
  margin-bottom: 24px;
}
.rate-wrapper{
  margin-top: 10px;
}
</style>
