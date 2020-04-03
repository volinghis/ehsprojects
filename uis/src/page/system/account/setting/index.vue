<template>
  <div class="container">
    <div class="account-settings-info-left">
      <el-tabs tab-position="left"
               v-model="activeName"
              @tab-click="handleClick"
               style="border-right:1px solid #DCDFE6;margin-right:15px;"
               :style="{height:(this.$store.state.contentHeight-15)+'px'}">
        <el-tab-pane label="个人资料"
                     name="first">
          <BaseSetting v-if="isFirst" />
        </el-tab-pane>
        <el-tab-pane label="修改密码"
                     name="second">
          <ChangePassword  v-if="isSecond"/>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import BaseSetting from './modules/baseSetting'
import ChangePassword from './modules/changePassword'
export default {
  data () {
    return {
      activeName: 'first',
      isFirst: true,
      isSecond: false
    }
  },
  components: {
    BaseSetting,
    ChangePassword
  },
  methods: {
    handleClick (tab) {
      if (tab.name === 'first') {
        this.isFirst = true
        this.isSecond = false
      } else if (tab.name === 'second') {
        this.isFirst = false
        this.isSecond = true
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/mixin.scss";
@import "./styles.scss";
</style>
