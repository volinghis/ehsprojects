export default {

  methods: {
    loginUser () {
      var currUser = JSON.parse(sessionStorage.getItem(this.GlobalVars.userToken))

      return currUser.username
    }
  }
}
