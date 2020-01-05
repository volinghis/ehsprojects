<template>
    <el-container >

        <el-header ><ehsheader></ehsheader></el-header>
        <el-container>
        <el-aside :style="{height:(this.$store.state.contentHeight)+parseInt(GlobalCss.tabHeight)+'px'}">
          <siders></siders>
        </el-aside>
         <el-container >
           <el-header class="tabHeader">
            <tabs></tabs>
           </el-header>
        <el-main :style="{height:this.$store.state.contentHeight+'px'}"><router-view></router-view></el-main>
        </el-container>

    </el-container>
  </el-container>

</template>
<script>

import container from './datas'
import siders from '../siders/index'
import tabs from '../tabs/index'
import ehsheader from '../header/index'
container.components = {
  'ehsheader': ehsheader,
  'siders': siders,
  'tabs': tabs
}
export default container

</script>
<style lang="scss" scoped>
@import './styles.scss';
</style>
