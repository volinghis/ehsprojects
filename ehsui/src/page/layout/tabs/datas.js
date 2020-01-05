export default {
  methods: {
    tabClick (e) {
      this.$router.push({ name: e.name })
    },
    tabRemove (name) {
      this.$store.dispatch(this.GlobalVars.removeTabsMethodName, name)
    }
  },
  computed: {
    activeTab () {
      return this.$store.state.resourceMenuKey
    }
  }
}
