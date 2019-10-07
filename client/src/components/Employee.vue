<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-3">ลงทะเบียนพนักงาน</h1>
      </v-flex>
    </v-layout>

     <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
            <v-col cols="10">
              <v-text-field
                v-model="employee.nameInfo"
                :rules="[(v) => !!v || 'Item is required']"
                label="Name"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
                <v-select
                  label="Gender"
                  v-model="employee.genderid"
                  :items="gender"
                  item-text="sex"
                  item-value="genderid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.idcard"
                :rules="[(v) => !!v || 'Item is required']"
                label="ID Card Number"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.address"
                :rules="[(v) => !!v || 'Item is required']"
                label="Address"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
                <v-select
                  label="Province"
                  v-model="employee.provinceid"
                  :items="province"
                  item-text="provincename"
                  item-value="provinceid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
            </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.phone"
                :rules="[(v) => !!v || 'Item is required']"
                label="Telephone"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
                <v-select
                  label="Position"
                  v-model="employee.positionid"
                  :items="position"
                  item-text="positionlevel"
                  item-value="positionid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
            </v-col>

            <v-col cols="10">
                <v-select
                  label="Bank"
                  v-model="employee.bankid"
                  :items="bank"
                  item-text="bankname"
                  item-value="bankid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
            </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.banknum"
                :rules="[(v) => !!v || 'Item is required']"
                label="Bank Number"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.user"
                :rules="[(v) => !!v || 'Item is required']"
                label="Username"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="10">
              <v-text-field
                v-model="employee.pass"
                :rules="[(v) => !!v || 'Item is required']"
                label="Password"
                required
              ></v-text-field>
            </v-col>

            <v-row justify="center">
              <v-col cols="12">
                <v-btn-toggle group >
                  <v-btn @click="saveEmployee" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
                  <v-btn style="margin-left: 15px;" @click="cancel">ยกเลิก</v-btn>
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
  name: "employee",
  data() {
    return {
      employee: {
        nameInfo: "",
        genderid: "",
        idcard: "",
        address: "",
        provinceid: "",
        phone: "",
        positionid: "",
        bankid: "",
        banknum: "",
        user: "",
        pass: ""
      },
       valid: false
    };
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Gender ใส่ combobox
    getgender() {
      http
        .get("/gender")
        .then(response => {
          this.gender = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Province ใส่ combobox
    getprovince() {
      http
        .get("/province")
        .then(response => {
          this.province = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Position ใส่ combobox
    getposition() {
      http
        .get("/position")
        .then(response => {
          this.position = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Bank ใส่ combobox
    getbank() {
      http
        .get("/bank")
        .then(response => {
          this.bank = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    // function เมื่อกดปุ่ม submit
    saveEmployee() {
      http
        .post(
          "/employee/" +
            this.employee.nameInfo +
            "/" +
            this.employee.genderid +
            "/" +
            this.employee.idcard +
            "/" +
            this.employee.address +
            "/" +
            this.employee.provinceid +
            "/" +
            this.employee.phone +
            "/" +
            this.employee.positionid +
            "/" +
            this.employee.bankid +
            "/" +
            this.employee.banknum +
            "/" +
            this.employee.user +
            "/" +
            this.employee.pass,
          this.employee
        )
        // .then(response => {
        //   console.log(response);
        //   this.$router.push("/viewemployee");
        // })
        // .catch(e => {
        //   console.log(e);
        // });
      this.submitted = true;
      alert("Complet")
    },
    cancel() {
      this.$refs.form.reset();
    },
    refreshList() {
      this.getgender();
      this.getprovince();
      this.getposition();
      this.getbank();
    }
    /* eslint-enable no-console */
  },
  mounted() {
       this.getgender();
       this.getprovince();
       this.getposition();
       this.getbank();
  }
};
</script>
