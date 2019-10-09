<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <span class="mdi mdi-account mdi-48px "></span>
        <h1 class="display-1 font-weight-bold mb-3">แบบฟอร์มการชำระเงินค่าสมาชิก</h1>
      </v-flex>
    </v-layout>
    
    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                outlined
                label="ID ผู้ใช้บริการ"
                prepend-icon= "mdi mdi-account"
                v-model="payment.customerId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
              <p v-if="customerCheck != ''">ยินดีตอนรับคุณ : {{customerName}}</p>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findCustomer" depressed large color="#000000" style="color:#FFFFFF">ยืนยัน</v-btn>
              </div>
            </v-col>
          </v-row>

          <v-row>
              <v-col cols="10">
                <v-select
                  label="พนักงานที่ทำรายการ"
                  prepend-icon= "mdi mdi-account-card-details"
                  outlined
                  v-model="payment.employeeId"
                  :items="employee"
                  item-text="employeename"
                  item-value="employeeid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="10">
                <v-select
                  label="เลือกประเภทลูกค้า"
                  prepend-icon= "mdi mdi-football"
                  outlined
                  v-model="payment.customertypeId"
                  :items="customerType"
                  item-text="customertypename"
                  item-value="customertypeid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
                <p>{{cusprice}}</p>
              </v-col>
            </v-row>
           
            <v-row>
              <v-col cols="10">
                <v-select
                  label="เลือกช่วงเวลา"
                  outlined
                  prepend-icon= "mdi mdi-clock"
                  v-model="payment.timerangeId"
                  :items="timeRange"
                  item-text="timerangename"
                  item-value="timerangeid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  style ="margin-top:-30px"
                ></v-select>
               
              </v-col>
            </v-row>
            
            <v-row justify="center">
              <v-col cols="10">
                <v-btn-toggle group >
                  <v-btn @click="savePayment" style="color:#FFFFFF" :class="{ black: !valid, green: valid } ">บันทึก</v-btn>
                  <v-btn @click="clear" color="#D50000" style="color:#FFFFFF">ยกเลิก</v-btn>
                  <v-btn @click="viewReserve" color="#000000" style="color:#FFFFFF">ดูบันทึก</v-btn>
                </v-btn-toggle>
              </v-col>
            </v-row>
            <br />
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";
export default {
  name: 'paymentMember',
  components: {
    },
   data () {
    return { 
      date: new Date().toISOString().substr(0, 10),
      menu: false,
       payment: {
        customerId: null,
        customertypeId: null,
        timerangeId: null,
        employeeId: null,
      },
      valid: false,
      customerCheck: false,
      customerName: null,
    }
  },
  methods: {
    /* eslint-disable no-console */
    customerType(){
      http
        .get("/customertype" )
        .then(response =>{
            this.customerType = response.data;
            console.log(response.data)
        })
        .catch(e => {
            console.log(e);
        })
    },
    employee(){
      http
        .get("/employee")
        .then(response =>{
            this.employee = response.data;
            console.log(response.data)
        })
        .catch(e => {
            console.log(e);
        })
    },
    timeRange() {
      http
        .get("/timerange")
        .then(response => {
          this.timeRange = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        })
    },
    
    findCustomer() {
      http
        .get("/customer/" + this.payment.customerId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.customerName = response.data.customername;
            this.customerCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
      savePayment() {
      http
        .post("/paymentmember/" +
            this.payment.customerId +
            "/" +
            this.payment.employeeId +
            "/" +
            this.payment.customertypeId +
            "/" +
            this.payment.timerangeId,
          this.payment
        )
        .then(response => {
          console.log(response);
          alert("ทำรายการสำเร็จ");
          this.$router.push("/viewpayment");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    viewReserve(){
      this.$router.push("/viewpayment");
    },
    clear() {
      this.$refs.form.reset();
     
     this.customerCheck = false;
    },

    refreshList() {
      this.customerType();
      this.timeRange();
      this.employee();
    }
    /* eslint-enable no-console */
  },
  mounted() {
      this.customerType();
      this.timeRange();
      this.employee();
  }
}
</script>
