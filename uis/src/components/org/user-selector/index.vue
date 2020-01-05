<template>
  <el-cascader
    placeholder="请选择人员"
    ref="orgCasCader"
    :options="options" :size="GlobalCss.buttonSize" :disabled="disabled"
    :clearable="clearable"
    :show-all-levels="showAllLevels"
    transfer

    v-model="orgValue"
    @change="change"
    @blur="blur"
    @focus="focus"
    @visible-change="visibleChange"
    @remove-tag="removeTag"
    :props="defaultProps"
    >
<template slot-scope="{ node, data }">
  <span class="el-cascader-data-ehs-icon el-cascader-ehs-data-org" v-if="data.org"><i  class="fa fa-home"></i></span><span v-else class="el-cascader-data-ehs-icon"><i  class="fa fa-user"></i></span>
    <span class="el-cascader-ehs-data-label">{{ data.label }}</span>
  </template>
    </el-cascader>
</template>
<script>
import datas from './datas.js'
export default datas
</script>
<style lang="sass" scoped>
@import './styles.scss'
</style>
