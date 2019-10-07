<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-3">ข้อมูลสมาชิก</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="8">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "ViewCustomer",
  data() {
    return {
       headers: [
        {text: "Customer",value: "customername" },
        {
          text: "Gender",
          align: "left",
          sortable: false,
          value: "genderid.sex"
        },
        { text: "Address", value: "address" },
        {
          text: "Province",
          align: "left",
          sortable: false,
          value: "provinceid.provincename"
        },
        { text: "Telephone", value: "telephone" },
        {
          text: "Category",
          align: "left",
          sortable: false,
          value: "categoryid.type"
        }
       ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล Customer ใส่ combobox
    getCustomer() {
      http
        .get("/customer")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getCustomer();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getCustomer();
  }
};
</script>
