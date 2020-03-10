<template>
  <el-autocomplete
    v-model="searchVal"
    :fetch-suggestions="querySearch"
    @select="handleSelect"
    ref="autocomplete"
    :size="GlobalCss.buttonSize"
    style="margin-bottom:10px;width:100%;">
    <template v-if="isShow">
     <el-button slot="append" type="primary" @click="handleQuery" icon="el-icon-search"></el-button>
    </template>
  </el-autocomplete>
</template>

<script>
export default {
  data () {
    return {
      searchVal: '',
      suggestions: []
    }
  },
  props: {
    isShow: { type: Boolean, default: true }
  },
  mounted () {
    this.loadSuggestions()
  },
  methods: {
    querySearch (queryString, cb) {
      var suggestions = this.suggestions
      var results = queryString ? suggestions.filter(this.createStateFilter(queryString)) : suggestions
      // 调用 callback 返回建议列表的数据
      clearTimeout(this.timeout)
      this.timeout = setTimeout(() => {
        cb(results)
      }, 1000 * Math.random())
    },
    createStateFilter (queryString) {
      return (suggestion) => {
        return (suggestion.value.toLowerCase().indexOf(queryString.toLowerCase()) !== -1)
      }
    },
    loadSuggestions () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/eam/eamLedgerLast/getLastSuggestions').then(res => {
        this.suggestions = res.data
      }).catch(error => {
        this.$message({ message: error })
      })
    },
    handleSelect (val) {
      this.$emit('handleSelect', val)
    },
    handleQuery () {
      this.$emit('handleQuery')
    }
  }
}
</script>
<style lang="less" scoped>

</style>
