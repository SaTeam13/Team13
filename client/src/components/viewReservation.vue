<template>
<div class="bg">
  <v-card style="width:60%; margin:auto; background-color:white">
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Reservation</h1>
      </v-flex>
    </v-layout>
    <v-row justify="center">
      <v-col cols="8">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
  </v-card>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "viewReservation",
  data() {
    return {
      headers: [
        {
          text: "ผู้ใช้บริการ",
          align: "left",
          sortable: false,
          value: "customer.customername"
        },
        { text: "ID ผู้ใช้บริการ", value: "customer.customerid" },
        { text: "สนามที่จอง", value: "fieldcategory.field" },
        { text: "วันที่จอง", value: "reservedate" },
        { text: "เวลาที่จอง", value: "timetable.timeString" },
        { text: "ทำรายการโดย", value: "employee.employeename" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getReservations() {
      http
        .get("/reservation")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getReservations();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getReservations();
  }
};
</script>
