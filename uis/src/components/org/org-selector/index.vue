<template>
  <el-cascader
    placeholder="请选择组织"
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
  <span class="dataIcon"><i class="fa fa-home"></i></span>
    <span class="dataLabel">{{ data.label }}</span>
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
