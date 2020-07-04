<template>
  <div class="container my-5">
    <form action @submit.prevent>
      <p style="display: inline; color: white;">From:</p>
      <input type="date" style="display: inline;" value v-model="datesFilter.dateFrom" required />
      <p style="display: inline; color: white;">To:</p>
      <input style="display: inline;" type="date" value v-model="datesFilter.dateTo" required />
      <p style="display: inline;"></p>
      <button type="submit" @click="filterbills">Search</button>
      <p style="display: inline;"></p>
      <button id="x" type="reset" @click="getAllBills">x</button>
    </form>

    <div class="card-body text-center"></div>
    <div class="card">
      <table class="table table-hover">
        <thead class="miThead">
          <tr>
            <th scope="col text-center">Line Bill</th>
            <th scope="col text-center">Price</th>
            <th scope="col text-center">Date</th>
            <th scope="col text-center">Is Paid</th>
            <th scope="col text-center"></th>
          </tr>
        </thead>
        <tbody>
          <tr><td v-if="$store.state.bills.length == 0">No bills to show</td></tr>
          <tr v-for="bill in $store.state.bills " :key="bill.id">
            
            <td class="col-md-0 text-left">{{bill.line}}</td>
            <td class="col-md-0 text-left">{{bill.priceTotal}}</td>
            <td class="col-md-0 text-left">{{bill.date}}</td>
            <td class="col-md-0 text-left" v-if="bill.isPaid">Yes</td>
            <td class="col-md-0 text-left" v-if="!bill.isPaid">No</td>

            <!-- <td>
                    <a class="btn btn-sm btn-primary" href="#"><i class="far fa-edit"></i> edit</a>
                    <a class="btn btn-sm btn-danger" href="#"><i class="fas fa-trash-alt"></i> delete</a>    
            </td>-->
            <td>
              <button
                id="details"
                type="button"
                class="btn btn-success position-realtive"
                data-toggle="modal"
                data-target=".bd-example-modal-lg"
                @click="$store.state.bill = bill"
              >
                <i class="fas fa-plus"></i>Details
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Large modal -->
    <div
      class="modal fade bd-example-modal-lg"
      tabindex="-1"
      role="dialog"
      aria-labelledby="myLargeModalLabel"
      aria-hidden="true"
    >
      <div class="modal-center modal-lg">
        <div class="modal-content">
          <div id="mainDiv" class="container">
            <div class="row">
              <div
                class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3"
                 
              >
                <div class="row center"  >
                  <div class="col-xs-6 col-sm-6 col-md-6">
                    <address>
                      <strong>UTNPhones</strong>
                      <br />2135 Sunset Blvd
                      <br />Los Angeles, CA 90026
                      <br />
                      <abbr title="Phone"></abbr> (213) 484-6829
                    </address>
                  </div>
                  <div class="col-xs-6 col-sm-6 col-md-6 text-right" >
                    <p>
                      <em>Date: {{$store.state.bill.date}}</em>
                    </p>
                    <p>
                      <em>Expire date: {{$store.state.bill.dateExpiration}}</em>
                    </p>
                  </div>
                </div>
                
                  <table class="table table-hover" ref="content">
                    <thead>
                      <tr></tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td class="text-left">
                          <p>Number of calls made: {{$store.state.bill.quantityOfCalls}}</p>
                        </td>
                      </tr>
                      <tr>
                        <td class="text-right">
                          <p>
                            <strong>Subtotal: {{$store.state.bill.priceTotal}}</strong>
                          </p>
                          <p>
                            <strong>Tax: $50.94</strong>
                          </p>
                        </td>
                      </tr>
                      <tr>
                        <td class="text-right">
                          <h4>
                            <strong>Total:{{$store.state.bill.priceTotal + 50.94}}</strong>
                          </h4>
                        </td>
                       
                      </tr>
                    </tbody>
                  </table>
                  <button type="button" class="btn btn-success btn-lg btn-block" @click="download">Download</button>
                </div>
              </div>
            </div>
          </div>
          
        </div>
      </div>
    </div>
</template>

<script>
import store from "@/store";
import jspdf from "jspdf";
export default {
  name: "bills",
  data() {
    return {
      datesFilter: {
        dateFrom: "",
        dateTo: ""
      }
    };
  },
  methods: {
    prueba() {
      alert("hola");
    },
    filterbills() {
      if (this.datesFilter.dateFrom && this.datesFilter.dateTo) {
        console.log(this.datesFilter);
        store.dispatch("filterBillsByDate", this.datesFilter);
      }
    },
    getAllBills() {
      this.datesFilter.dateFrom = "";
      this.datesFilter.dateTo = "";
      store.dispatch("getBills");
    },
    download() {
      console.log("Llego al metodo");
      const doc = new jspdf();

      const html = this.$refs.content.innerHTML;
      console.log(html);

      console.log("Antes");
      doc.fromHTML(html, 15, 15, {
        width: 1000
      });
      console.log("pase");
      doc.save("bills.pdf");
    }
  }
};
</script>

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

footer  {
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