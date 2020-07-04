
<template>
  <div id="app">
     <login v-if="!existToken"></login>  
     <div v-if="existToken"> 
      <div id="nav">
        <nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
            <span class="navbar-brand">UTN Phones</span>
            
            <ul class="navbar-nav mr-auto ">
                           
              <router-link to="/bills"  style="color: white; font-size: medium; margin:center;">
                <button class="btn btn-primary ml-4" >Bills 
                </button>
              </router-link>

              <router-link to="/calls"   style="color: white;  font-size: medium; margin:center;"> 
                <button class="btn btn-primary ml-4">Calls 
                </button>
              </router-link>

              <router-link
                to="/destinations" 
                style="color: white;  font-size: medium; margin:center;"> 
                <button class="btn btn-primary ml-4">
                Top Destinations 
                </button>
              </router-link>

              
            </ul>
            <ul class="nav navbar-nav navbar-right">
              
              <button class="btn btn-danger ml-auto" @click="logout"> 
                Log Out 
              </button>
             
              
            </ul>
        </nav>
      </div>
      <router-view />
      
     </div> 
   </div> 
</template>

<script>
import login from "@/components/login.vue";

export default {
  components: {
    login
  },
  computed: {
    existToken: function() {
      return this.$store.state.authorization != "";
    }
  },
  methods : {
    logout(){
       this.$store.dispatch("logOut");
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
