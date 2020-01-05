<template>
  <div class="container">
    <div class="account-settings-info-left">
      <el-tabs tab-position="left"
               v-model="activeName"
               @tab-click="handleSelect"
               :style="{height:(this.$store.state.contentHeight -30)+'px'}">
        <el-tab-pane label="个人首页"
                     name="first"></el-tab-pane>
        <el-tab-pane label="修改密码"
                     name="second">
          <ChangePassword />
        </el-tab-pane>
        <el-tab-pane label="消息管理"
                     name="third"></el-tab-pane>
        <el-tab-pane label="个人资料"
                     name="fourth">
          <BaseSetting />
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
      activeName: 'fourth'
    }
  },
  components: {
    BaseSetting,
    ChangePassword
  },
  mounted () {
  },
  methods: {
    handleSelect (tab) {
      console.log(tab)
      if (tab.name === 'first') {
        this.$router.push({ name: 'userCenter' })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/mixin.scss";
@import "./styles.scss";
</style>
