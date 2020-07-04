import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    authorization: "",
    bills: [ {
      "line": "2235684083",
      "quantityOfCalls" : 10,
      "priceTotal": 50.71,
      "date": "2019-01-01",
      "dateExpiration": "2019-01-15",
      "user": {
          "id": 1,
          "username": "Abulzomi",
          "password": "1234"
      },
      "paid": true
  },
  {
      "line": "2235684083",
      "priceTotal": 50.02,
      "quantityOfCalls" : 11,
      "date": "2019-02-01",
      "dateExpiration": "2019-02-15",
      "user": {
          "id": 1,
          "username": "Abulzomi",
          "password": "1234"
      },
      "paid": false
  },
  {
      "line": "2235684083",
      "quantityOfCalls" : 10,
      "priceTotal": 50.84,
      "date": "2019-03-01",
      "dateExpiration": "2019-03-15",
      "user": {
          "id": 1,
          "username": "Abulzomi",
          "password": "1234"
      },
      "paid": true
    }],
    bill:{},
    calls:[{
      "originLine": "2235684083",
      "destinationLine" : "2235684082",
      "duration": 10,
      "price": 100,
      "dateCall": "2019-07-02"
    },{
      "originLine": "2235684083",
      "destinationLine" : "223625175",
      "duration": 11,
      "price": 101,
      "dateCall": "2019-07-02"
    },{
      "originLine": "2235241571",
      "destinationLine" : "223524175",
      "duration": 12,
      "price": 102,
      "dateCall": "2019-07-02"
    }],
    destination:[{"city":{"nameDestination":"Cordoba","prefix":543},"quantityOfCalls":42},{"city":{"nameDestination":"Mar del Plata","prefix":223},"quantityOfCalls":25}]
  
  },
  
  mutations: {
    setAuthorization(state, token) {
      state.authorization = token;
      console.log(state.authorization);
    },
    setLogOut(state){
      state.authorization = "";

    },
    setBills(state, bills) {
      state.bills = bills;
      
    },
    setCalls(state, callList){
      state.calls = callList;
    },
    setDestination(state, destinationss){
      state.destination = destinationss;
    }
    
  },
  actions: {
    async login ({ commit , dispatch}, loginInfo) {
      axios
        .post(`http://localhost:8082/login/`, loginInfo, {
          headers: {
            "content-type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
          },
        })
        .then(
          function(response) {
            dispatch('getAllLists');
            commit("setAuthorization", response.data.autorization);
            
            
          },
          function() {
            
            alert("Usuario o contraseña incorrecto");
          }
        );
    },
    async getAllLists({  dispatch }) {
        dispatch("getBills");
        dispatch("getCalls");
        dispatch("getDestination");
    },

    async getBills({ commit }) {
      axios
      .get(`http://localhost:8082/api/bill`,  {
        headers: {
          "content-type": "application/json;charset=UTF-8",
          "Access-Control-Allow-Origin": "*",
        }, })
        .then(
          function(response) {
            
            commit("setBills", response.data);
          },
          function() {
            console.log("Problema al recuperar bills fechas");
          }
        );
    },
    async filterBillsByDate({ commit }, datesFilter) {
      axios
        .get(`http://localhost:8082/api/bill`, {
          params :{
            from : datesFilter.dateFrom,
            to: datesFilter.dateTo 
          } 
        }, {
          headers: {
            "content-type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
          },
        })
        .then(
          function(response) {
            
            commit("setBills", response.data);
          },
          function() {
            console.log("Problema al filtrar fechas");
          }
        );
    },
    async getCalls({ commit }) {
      axios
        .get(`http://localhost:8082/api/call`, {
          headers: {
            "content-type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
          }, })
          .then(
            function(response) {
              
              commit("setCalls", response.data);
            },
            function() {
              console.log("Problema al recuperar calls");
            }
          );
    }, 
    async filterCallsByDate({ commit }, filters ) {
      axios
        .get(`http://localhost:8082/api/call`, {
          params :{
            from : filters.from,
            to: filters.to 
          } 
        },{
          headers: {
            "content-type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
          }, })
          .then(
            function(response) {
             
              commit("setCalls", response.data);
            },
            function() {
              console.log("Problema filtrar calls");
            }
          );
    }, 
    async getDestination({ commit }) {
        axios
          .get(`http://localhost:8082/api/call/destinations`, {
            headers: {
              "content-type": "application/json;charset=UTF-8",
              "Access-Control-Allow-Origin": "*",
            }, })
            .then(
              function(response) {
                console.log(response);
                commit("setDestination", response.data);
              },
              function() {
                console.log("Problema al recuperar destinations");
              }
            );
    },
    logOut({commit}){
      commit("setLogOut");
    }
  },
});

