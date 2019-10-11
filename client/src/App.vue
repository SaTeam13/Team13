<template>
  <v-app>
  <div class="bg"> 
  <v-app-bar style = "background: white;" app>
      <v-toolbar-title  class="headline text-uppercase" >
        <span class="font-weight">SportStaduim</span>   
      </v-toolbar-title>
      <v-row justify="center" style="margin-right:190px">
      <v-btn-toggle group>
      <v-btn @click="Reserve" color="#00E676" style="color:#000000">จองสนาม</v-btn>
      <v-btn @click="Borrow" color="#76FF03" style="color:#000000">ยืมอุปกรณ์</v-btn>
      <v-btn @click="Customer" color="#00E676" style="color:#000000">สมัครสมาชิก</v-btn>
      <v-btn @click="Employee" color="#76FF03" style="color:#000000">ข้อมูลพนักงาน</v-btn>
      <v-btn @click="Payment" color="#00E676" style="color:#000000">ชำระเงินค่าสมาชิก</v-btn>
      <v-btn @click="SportEquipment" color="#76FF03" style="color:#000000">ข้อมูลอุปกรณ์</v-btn>
      </v-btn-toggle>
      
      </v-row>
  </v-app-bar> 
     <v-content>
    <v-card style="width:70%; margin:auto; background-color:#FFFFFF">
      <router-view />
    </v-card>
    </v-content>
    </div> 
  </v-app>
</template>
<script>
  export default {
  name: 'App',

  methods: { 
  Reserve(){
      this.$router.push("/reservation");
    },
  Borrow(){
      this.$router.push("/borrow");
    },
  Customer(){
      this.$router.push("/customer");
    },
  Employee(){
      this.$router.push("/employee");
    },
  Payment(){
      this.$router.push("/paymentmember");
    },
    SportEquipment(){
      this.$router.push("/sportequipment");
    },
    },
};

</script>