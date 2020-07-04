<template>
  <div id="login">
    <div class="container">
      <div id="login-row" class="row justify-content-center align-items-center">
        <div id="login-column" class="col-md-6">
          <div id="login-box" class="col-md-12">
            <form @submit.prevent id="login-form" class="form" action method="post">
              <h3 class="text-center text-info">Login {{this.$store.state.authorization}}</h3>
              <div class="form-group">
                <label for="username" class="text-info">Username:</label>
                <br />
                <input
                  type="text"
                  id="username"
                  v-model="loginInfo.username"
                  required
                  class="form-control"
                />
              </div>
              <div class="form-group">
                <label for="password" class="text-info">Password:</label>
                <br />

                <input
                  type="password"
                  id="password"
                  v-model="loginInfo.password"
                  required
                  class="form-control"
                />
              </div>
              <button class="btn btn-info btn-md" type="submit" @click="login">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import store from "@/store";

export default {
  name: "login",

  data() {
    return {
      loginInfo: {
        username: "",
        password: ""
      }
    };
  },

  methods: {
    login() {
      if (this.loginInfo.username && this.loginInfo.password) {
        store.dispatch("login", this.loginInfo);
        
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body {
  margin: 0;
  padding: 0;
  background-color: #000000;
  height: 100vh;
  /* background-color: ; */
}
#login .container #login-row #login-column #login-box {
  margin-top: 120px;
  max-width: 600px;
  height: 320px;
  border: 1px solid #9c9c9c;
  background-color: #eaeaea;
}
#login .container #login-row #login-column #login-box #login-form {
  padding: 20px;
}
#login
  .container
  #login-row
  #login-column
  #login-box
  #login-form
  #register-link {
  margin-top: -85px;
}
</style>
