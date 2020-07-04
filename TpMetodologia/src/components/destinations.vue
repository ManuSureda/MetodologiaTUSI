<template>
  <div class="container my-5">
    <div class="card-body text-center"></div>
    <div class="card">
      <table class="table table-hover">
        <thead class="miThead">
          <tr>
            <th scope="col text-center">Destination Name</th>
            <th scope="col text-center">Prefix</th>
            <th scope="col text-center">QuantityOfCalls</th>
            <th scope="col text-center"></th>
          </tr>
        </thead>
        <tbody>
          <tr><td v-if="$store.state.destination.length == 0">No destination to show</td></tr>
          <tr v-for="destination in $store.state.destination " :key="destination.id">
            
            <td class="col-md-0 text-left">{{destination.city.nameDestination}}</td>
            <td class="col-md-0 text-left">{{destination.city.prefix}}</td>
            <td class="col-md-0 text-left">{{destination.quantityOfCalls}}</td>
            

          </tr>
        </tbody>
      </table>
    </div>    
    </div>

</template>

<script>
import store from "@/store";

export default {
  name: "destination",
  /*props: {
        msg: String
      }*/
  methods:{
     getDestination() {
      store.dispatch("getDestination");
    },
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body {
  background-image: url(https://static.pexels.com/photos/371633/pexels-photo-371633.jpeg);
  background-repeat: no-repeat;
  background-size: 100%;
}

.miThead {
  background-color: rgba(0, 0, 0, 0.856);
  color: rgb(255, 255, 255);
}

footer {
  margin-top: 20px;
  padding-top: 20px;
}
.bg__card__navbar {
  background-color: #000000;
}
.bg__card__footer {
  background-color: #000000 !important;
}
#add__new__list {
  top: -38px;
  right: 0px;
}
</style>
