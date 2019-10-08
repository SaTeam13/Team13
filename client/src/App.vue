<template>
  <v-app>
  <div class="bg"> 
  <v-app-bar style = "background: white;" app>
      <v-toolbar-title  class="headline text-uppercase" >
        <span class="font-weight-light">SportStaduim</span>   
      </v-toolbar-title>
      <v-row justify="center">
      <v-btn-toggle group>
      <v-btn @click="Reserve" color="#000000" style="color:#FFFFFF">จองสนาม</v-btn>
      <v-btn @click="Borrow" color="#000000" style="color:#FFFFFF">ยืมอุปกรณ์</v-btn>
      <v-btn @click="Customer" color="#000000" style="color:#FFFFFF">สมัครสมาชิก</v-btn>
      <v-btn @click="Employee" color="#000000" style="color:#FFFFFF">ข้อมูลพนักงาน</v-btn>
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
    },
};

</script>