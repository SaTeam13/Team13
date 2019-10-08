<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-3">Reservation</h1>
      </v-flex>
    </v-layout>
    <v-row justify="center">
      
        <v-col cols="5">
        <v-text-field v-model="search"
          label="ค้นหา"
          prepend-icon= "mdi mdi-file-find"
          outlined hide-details
        ></v-text-field>
        </v-col>
        <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1" :search="search">
        </v-data-table>
        </v-col>
    
 
            <v-col cols="3">
              <v-text-field
                outlined
                label="ต้องการลบ ID การจองที่: "
                prepend-icon= "mdi mdi-delete-forever"
                v-model="reservation.reservationId"
              ></v-text-field>
              <p v-if="reservationCheck != ''">ID การจองที่จะทำการลบ : {{reservationId}}
                <v-btn class="" @click="deleteReservation" color="#D50000" style="color:#FFFFFF" >ลบ</v-btn>
              </p>
            </v-col>
            <v-col cols="2">
              <div class="">
                <v-btn @click="findReservation" depressed large color="#000000" style="color:#FFFFFF;">ยืนยัน</v-btn>
              </div>
            </v-col>
          </v-row>
  </v-container>
</template>
<script>
import http from "../http-common";
export default {
  name: "viewReservation",
  data() {
    return {
      search: '',
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
        { text: "ทำรายการโดย", value: "employee.employeename" },
        { text: "ID การจอง", value: "reservationid" },
      ],
      items: [],
       reservation: {
        reservationId: null,
      },
      valid: false,
      reservationCheck: false,
      reservationId: null,
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
    findReservation() {
      http
        .get("/reservation/" + this.reservation.reservationId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.reservationId = response.data.reservationid;
            this.reservationCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    deleteReservation() {
      http
        .delete("/reservation/" + this.reservation.reservationId)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          alert("ทำการลบรายการจองเรียบร้อย");
          location.reload();
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
