
export default {
  props: {
    'disabled': Boolean,
    'clearable': Boolean,
    'size': { type: String },
    'show-all-levels': { type: Boolean, default: false },
    'propOrgValue': String,
    'props': Object
  },
  model: {
    prop: 'propOrgValue',
    event: 'change'
  },
  mounted () {
    this.loadDatas()
  },
  methods: {
    loadDatas () {
      this.$axios.get(this.GlobalVars.globalServiceServlet + '/auth/orgManager/getAllForTree')
        .then((res) => {
          this.options = res.data
        })
    },
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
  data () {
    return {
      normalsize: this.size ? this.size : this.GlobalCss.buttonSize,
      defaultProps: Object.assign({ multiple: false, checkStrictly: true, emitPath: false }, this.props),
      options: []
    }
  }
}
