
export default {
  props: {
    'disabled': Boolean,
    'clearable': { type: Boolean, default: true },
    'show-all-levels': { type: Boolean, default: false },
    'propOrgValue': String,
    'props': Object
  },
  model: {
    prop: 'propOrgValue'
  },
  methods: {
    clearCheckedNodes () {
      this.$refs['orgCasCader'].clearCheckedNodes()
    },
    getCheckedNodes (leafOnly) {
      return this.$refs['orgCasCader'].getCheckedNodes(leafOnly)
    },
    change (v) {
      this.$emit('change', v)
    },
    expandChange (v) {
      this.$emit('expand-change', v)
    },
    blur (e) {
      this.$emit('blur', e)
    },
    focus (e) {
      this.$emit('focus', e)
    },
    visibleChange (v) {
      this.$emit('visible-change', v)
    },
    removeTag (v) {
      this.$emit('remove-tag', v)
    }
  },
  mounted () {
  },
  data () {
    let that = this
    return {
      orgValue: this.propOrgValue,

      defaultProps: Object.assign({ multiple: false,
        checkStrictly: true,
        emitPath: false,
        lazy: true,
        lazyLoad (node, resolve) {
          console.log(node)
          var parentKey = node.value ? node.value : ''
          that.$axios.get(that.GlobalVars.globalServiceServlet + '/auth/orgUser/getAllForTree?parentKey=' + parentKey)
            .then((res) => {
              resolve(res.data)
            })
          // const { level } = node
          // setTimeout(() => {
          //   const nodes = Array.from({ length: level + 1 })
          //     .map(item => ({
          //       value: ++id,
          //       label: `选项${id}`,
          //       leaf: level >= 2
          //     }))
          //   // 通过调用resolve将子节点数据返回，通知组件数据加载完成
          //   resolve(nodes)
          // }, 1000)
        } }, this.props),
      options: []
    }
  }
}
