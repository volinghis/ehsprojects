
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
    return {
      orgValue: this.propOrgValue,
      defaultProps: Object.assign({ multiple: false, checkStrictly: true, emitPath: false }, this.props),
      options: [{
        value: 'zhinan',
        label: 'MISS组织',
        disabled: 'disabled',
        org: true,
        children: [{
          value: 'shejiyuanze',
          label: '行政部',
          disabled: 'disabled',
          org: true,
          children: [{
            value: 'yizhi',
            label: '人力'
          }, {
            value: 'fankui',
            label: '出纳'
          }, {
            value: 'xiaolv',
            label: '采购部'
          }, {
            value: 'kekong',
            label: '合同管理部'
          }]
        }, {
          value: 'daohang',
          label: '财务部',
          disabled: 'disabled',
          org: true,
          children: [{
            value: 'b0c2046f-82b5-4f84-bd63-782083136fee',
            label: 'zhaol'
          }, {
            value: 'bffca95f-d70d-48b1-8530-a5b2b639e68c',
            label: 'test2'
          }]
        }]
      }]
    }
  }
}
