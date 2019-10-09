<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
      <br />
      <br />
      <br />
      <br />
        <h1 style="margin-top: -80px;"  class="display-1 font-weight-bold mb-3">แบบฟอร์มยืมอุปกรณ์กีฬา</h1>
          </v-flex>
           </v-layout>

    
    <v-row justify="center">
      <v-col cols="5">
        <v-form >
         
         
          <v-row justify="center">  
            <!-- ชื่อ- นามสกุล -->
            <v-col cols="10">
              <v-text-field
                solo  
                label="ชื่อ-นามสกุล"
                v-model= "Borrow.nameborrow"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                
               prepend-icon= "mdi mdi-account"
              ></v-text-field>
            </v-col>
          </v-row>
         
    
     <v-row justify="center"> 
        <!-- อุปกรณ์กีฬา -->
            <v-col cols="10">
              <v-select
                label="อุปกรณ์กีฬา"
                solo
                v-model="Borrow.sportEquipment"
                :items="sportEquipment"
                item-text="equipment"
                item-value="sportequipmentid"
                
                :rules="[(v) => !!v || 'กรุณากรอกเลือกข้อมูล']"
                required
                
                prepend-icon="mdi mdi-soccer"
              ></v-select>
            </v-col>


            <v-col cols="10">
              <v-select
                label="ประเภทอุปกรณ์"
                solo
                v-model="Borrow.typeEquipment"
                :items="typeEquipment"
                item-text="typename"
                item-value="typeequipmentid"
                
                :rules="[(v) => !!v || 'กรุณากรอกเลือกข้อมูล']"
                required
                
                prepend-icon="mdi mdi-settings"
              ></v-select>
            </v-col>
         </v-row>

    <v-row justify="center">  
        <!-- จำนวนอุปกรณ์ -->
            <v-col cols="10">
              <v-text-field
                solo  
                label="จำนวนอุปกรณ์"
                v-model= "Borrow.numberequipment"
              
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
                
               prepend-icon= "mdi mdi-calculator"
              ></v-text-field>
            </v-col>
          
  <v-col cols="10">
      <v-menu
        v-model="menu2"
        :close-on-content-click="false"
        :nudge-right="40"
        transition="scale-transition"
        offset-y
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="date"
              solo  
            label="Picker without buttons"
            prepend-icon= "mdi mdi-calendar"
           
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" @input="menu2 = false"></v-date-picker>
      </v-menu>
     </v-col>
    

 
        <!-- พนักงาน -->
            <v-col cols="10">
              <v-select
                label="พนังงาน"
                solo
                v-model="Borrow.employee"
                :items="employee"
                item-text="employeename"
                item-value="employeeid"
                :rules="[(v) => !!v || 'กรุณากรอกเลือกข้อมูล']"
                required
                style="margin-top: -30px;"
                prepend-icon="mdi mdi-account-multiple-outline"
              ></v-select>
             </v-col>
          </v-row>


          <v-row justify="center">
                <v-col cols="6">
                <v-btn-toggle group >
                  <v-btn @click="saveBorrow" :class="{ red: !valid, green: valid }">บันทึก</v-btn> 
                  <v-btn @click="clear" color="#000000" style="color:#FFFFFF">ยกเลิก</v-btn>
                </v-btn-toggle>
                </v-col>
              </v-row>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

import http from "../http-common";

export default {
  name: "Borrow",
  data() {
    return {
      Borrow: {
        employee:null,
        sportEquipment: null,
        typeEquipment: null,
     },
        nameborrow : '',
        numberequipment: '',
       
        date: new Date().toISOString().substr(0, 10),
        menu2: false,
        employee: null,
        sportEquipment: null,
        typeEquipment: null,
        valid: false,
        };
  },



  methods: {
    
     /* eslint-disable no-console */

         getemployee() {
      http
        .get("/employee")
        .then(response => {
          this.employee = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // ดึงข้อมูล sportEquipment ใส่ combobox
    getsportEquipment() {
      http
        .get("/sportEquipment")
        .then(response => {
          this.sportEquipment = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
  // ดึงข้อมูล typeEquipment ใส่ combobox
    gettypeEquipment() {
      http
        .get("/typeEquipment")
        .then(response => {
          this.typeEquipment = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
        // ดึงข้อมูล employee ใส่ combobox

  
  ///Borrow/{nameborrow}/{numberequipment}/{empselect}/{eqmselect}/{typeeqmselect}



    // function เมื่อกดปุ่ม save
   saveBorrow() {
      http
        .post(
          "/Borrow/" +
            this.Borrow.nameborrow +
            "/" +
            this.Borrow.numberequipment +
            "/" + 
            this.date +
            "/" +
            this.Borrow.employee +
            "/" +
            this.Borrow.sportEquipment + 
            "/" + 
            this.Borrow.typeEquipment,
         
          this.Borrow
        )
       .then(response => {
         console.log(response);
       })
        .catch(e => {
          console.log(e);
        });
        
      this.submitted = true;
      alert("บันทึกสำเร็จ")
      },
      clear(){
            this.Borrow.nameborrow='';
            this.Borrow.numberequipment='';
            this.Borrow.date='';
            this.menu2= '';
            this.Borrow.employee= null;
            this.Borrow.sportEquipment= null;
            this.Borrow.typeEquipment= null;
      },

    refreshList() {
      this.getemployee();
      this.getsportEquipment();
      this.gettypeEquipment();
      
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getemployee();
    this.getsportEquipment();
    this.gettypeEquipment();
    
  }
};
</script>
