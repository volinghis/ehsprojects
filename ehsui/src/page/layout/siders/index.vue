<template>
  <div class="siders">
 <el-menu
        class="el-menu-vertical-demo"
        :default-active="$router.currentRoute.name"
        :background-color="GlobalCss.menuBg"
        :text-color="GlobalCss.menuText"
        :active-text-color="GlobalCss.menuActiveText"
        @select="menuSelect"
    >
          <template v-for="item in $store.state.menuDatas">

            <el-menu-item :index="item.key" :key="item.key"
                    v-if="item.leaf&&!item.business"><i :class="'fa '+(item.icon?item.icon:'')+'  ' "></i> {{ item.label }}</el-menu-item>
            <template v-else>
              <el-submenu :key="item.key" :index="item.key" v-if="(!item.leaf)&&(!item.business)&&item.children">
                  <template slot="title">
                    <i :class="'fa '+(item.icon?item.icon:'')+' ' "></i>
                   {{ item.label }}
                  </template>
                  <template v-for="itemSub in item.children">
                  <el-menu-item  :key="itemSub.key" :index="itemSub.key" v-if="itemSub.leaf&&!itemSub.business">
                  <i :class="'fa '+(itemSub.icon?itemSub.icon:'')+' sub-icon-size' "></i>&nbsp;{{ itemSub.label }}
                   </el-menu-item>
                </template>
              </el-submenu>
            </template>
          </template>
    </el-menu>

  </div>
</template>
<script>
import datas from './datas.js'
export default datas
</script>

<style lang="scss" >
@import "./styles.scss";
</style>
