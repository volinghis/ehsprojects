
export default {
  props: {
    'disabled': Boolean,
    'clearable': { type: Boolean, default: true },
    'show-all-levels': { type: Boolean, default: false },
    'propOrgValue': String,
    'props': Object
  },
  model: {
    prop: 'propOrgValue',
    event: 'change'
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
    if (this.propOrgValue) {
      let that = this
      that.$axios.get(that.GlobalVars.globalServiceServlet + '/auth/orgUser/findUserByKey?key=' + this.propOrgValue)
        .then((res) => {
          if (res.data.name) {
            this.$refs['orgCasCader'].$el.querySelector('.el-input__inner').value = res.data.name
          }
        })
    }
  },
  data () {
    let that = this
    return {
      defaultProps: Object.assign({ multiple: false,
        checkStrictly: true,
        emitPath: false,
        lazy: true,
        lazyLoad (node, resolve) {
          var parentKey = node.value ? node.value : ''
          that.$axios.get(that.GlobalVars.globalServiceServlet + '/auth/orgUser/getAllForTree?parentKey=' + parentKey)
            .then((res) => {
              resolve(res.data)
            })
        } }, this.props),
      options: []
    }
  }
}
