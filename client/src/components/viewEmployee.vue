<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-3">Employee</h1>
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
        <v-col cols="11">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1" :search="search">
        </v-data-table>
        </v-col>
    
             
            <v-col cols="3">
              <v-text-field
                outlined
                label="ต้องการลบ ID พนักงาน: "
                prepend-icon= "mdi mdi-delete-forever"
                v-model="employee.employeeid"
              ></v-text-field>
                 <p v-if="employeeCheck != ''">ID พนักงานที่จะทำการลบ : {{employeeid}}
                        <v-btn class="" @click="deleteemployee" color="#D50000" style="color:#FFFFFF" >ลบ</v-btn>
                 </p>
              </v-col>
            
            <v-col cols="2">
              <div class="">
                 <v-btn @click="findemployee" depressed large color="#000000" style="color:#FFFFFF;">ยืนยัน</v-btn>
              </div>
            </v-col>
          </v-row>
  </v-container>
</template>


<script>
import http from "../http-common";
export default {
  name: "viewemployee",
  data() {
    return {
      search: '',
      headers: [
        
        {
         text: "ID พนักงาน",
          align: "left",
          sortable: false,
          value: "employeeid"

        },
        { text: "ID พนักงาน", value: "employeeid" },
        { text: "ชื่อพนักงาน", value: "employeename" },
        { text: "เพศ", value: "genderid.sex" },
        { text: "เลขบัตรประชาชน", value: "idcardnumber" },
        { text: "ที่อยู่", value: "address" },
        { text: "จังหวัด", value: "provinceid.provincename" },
        { text: "โทร.", value: "telephone" },
        { text: "ตำแหน่ง", value: "positionid.positionlevel" },
        { text: "เลขที่บัญชี", value: "banknum" },
        
      ],
      items: [],
      employee: {
        employeeid: null,
      },
      valid: false,
      employeeCheck: false,
      employeeid: null,
    };
  },
  methods: {
    /* eslint-disable no-console */
     getemployee() {
      http
        .get("/employee")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
     findemployee() {
      http
        .get("/employee/" + this.employee.employeeid)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.employeeid = response.data.employeeid;
            this.employeeCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    deleteemployee() {
      http
        .delete("/employee/" + this.employee.employeeid)
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
      this.getemployee();
    }
    /* eslint-disable no-console */
  },
  mounted() {
      this.getemployee();
  }
};
</script>
