<template>
  <div class="container my-5">
     <form action @submit.prevent>
      <p style="display: inline; color: white;">From:</p>
      <input type="date" style="display: inline;" value v-model="filters.from" required />
      <p style="display: inline; color: white;">To:</p>
      <input style="display: inline;" type="date" value v-model="filters.to" required />
      <p style="display: inline;"></p>
      <button type="submit" @click="filterCalls">Search</button>
      <p style="display: inline;"></p>
      <button id="x" type="reset" @click="getCalls">x</button>
     </form>
    <div class="card-body text-center"></div>
    <div class="card">
      <table class="table table-hover">
        <thead class="miThead">
          <tr>
            <th scope="col text-center">Origin line</th>
            <th scope="col text-center">Destination line</th>
            <th scope="col text-center">Duration</th>
            <th scope="col text-center">Price</th>
            <th scope="col text-center">Date</th>
            <th scope="col text-center"></th>
          </tr>
        </thead>
        <tbody>
          <tr><td v-if="$store.state.calls.length == 0">No calls to show</td></tr>
          <tr v-for="call in $store.state.calls " :key="call.id">
            
            <td class="col-md-0 text-left">{{call.originLine}}</td>
            <td class="col-md-0 text-left">{{call.destinationLine}}</td>
            <td class="col-md-0 text-left">{{call.duration}}</td>
            <td class="col-md-0 text-left">{{call.price}}</td>
            <td class="col-md-0 text-left">{{call.dateCall}}</td>

          </tr>
        </tbody>
      </table>
    </div>    
    </div>

</template>

<script>
import store from "@/store";

export default {
  name: "calls",
  /*props: {
        msg: String
      }*/
  data(){
    return {
      filters:{
          from: '',
          to:''
      }
    }
  },    
  methods:{

     getCalls() {
       this.filters.from='';
       this.filters.to='';
      store.dispatch("getCalls");
    },
    filterCalls(){
        if (this.filters.from && this.filters.to){
            store.dispatch("filterCallsByDate", this.filters);
        }
        
    }
    
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
