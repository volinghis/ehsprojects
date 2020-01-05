export default {
  data () {
    return {
    }
  },
  mounted () {
  },
  methods: {

    menuSelect (index) {
      this.$router.push({ name: index })
    }
  }
}
