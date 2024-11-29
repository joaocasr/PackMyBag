<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <RouterView></RouterView>
   <NotificationComponent @closeNotification="closeNotif" :notifs="notificacoes"></NotificationComponent>
  
</template>

<script>
import axios from 'axios'
import NotificationComponent from './components/NotificationComponent.vue';
import authService from './services/auth-service';

export default {
  data(){
    return {
      username:'',
      token:null,
      notificacoes:[]
    }
  },
  created(){
    
    let token = authService.getToken();
		if(token!=null){
			this.token = token;
			this.username=token.username;
      this.getNotifications();
    }
	},
  methods: {
    getNotifications() {
      const eventSource = new EventSource(this.$apiGatewayUrl+ `/api/notificacoesService/notifications/retrieve/${this.username}`);
      let id = 1;
      eventSource.onopen = () => {
      };

      eventSource.onmessage = (event) => {
        try {
          const notification = JSON.parse(event.data);
          notification['id'] = id;
          this.notificacoes.push(notification);
          id += 1;
        } catch (error) {
          console.error("Error parsing notification data:", error);
        }
      };

      eventSource.onerror = (error) => {
        console.error("Error in EventSource connection:", error);
        eventSource.close();
      };
  },
  closeNotif(idx){
    this.notificacoes = this.notificacoes.filter( (n)=> n.id !== idx);

  }
}
}
</script>
