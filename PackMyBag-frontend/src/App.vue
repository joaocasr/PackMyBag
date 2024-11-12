<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <RouterView></RouterView>
   <NotificationComponent></NotificationComponent>
  
</template>

<script>
import axios from 'axios'
import NotificationComponent from './components/NotificationComponent.vue';
import authService from './services/auth-service';

export default {
  data(){
    return {
      username:'',
      token:null
    }
  }
/*
  created(){
    
    let token = authService.getToken();
		if(token!=null){
			this.token = token;
			this.username=token.username;
      this.getNotifications();
    }
	},
  methods:{
    getNotifications(){
      axios.get('http://localhost:8888/api/notificacoesService/notifications/retrieve/:username').then(resp=>{

      })
    
    }
  }*/
}
</script>
