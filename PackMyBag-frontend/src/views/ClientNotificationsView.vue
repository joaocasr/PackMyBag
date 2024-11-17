<template>
	<main>
    <NavBarComponent></NavBarComponent>
  	<div class="notificationspage">
    		<div class="notificationspage-inner">
      			<div class="component-child"></div>
    		</div>
    		<div class="notificationspage-child">Notifications</div>
    		<img class="notificationspage-item" alt="" src="bell-03.svg">

			<div class="new-notification">
				<div class="nonewnotsclass" v-if="notifications.length==0">No new notifications.</div>
				<div v-for="notification in notifications">
					<NewNotification
					:tipo="notification.tipo"
					:descricao="notification.descricao"
					:data="notification.data"
					:id="notification.id"
					@removeNotification="removeNotification">
				</NewNotification>
			</div>
    					
    		<div class="parent">
						<img v-if="current_page+1 > 1" @click="handlePage('previous')" class="group-item1" alt="" src="/CatalogueIMG/previousbtn.png">
				<div v-if="notifications.length>0" class="dividx">{{ current_page + 1}}</div>
      			<div class="rectangle-parent">
						<img v-if="items.length>0" @click="handlePage('next')" class="group-item2" alt="" src="/CatalogueIMG/nextbtn.png">
      			</div>
			</div>
  	</div>
	<FooterComponent></FooterComponent>
  	</div>
	</main>
</template>
  	
<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import authService from '@/services/auth-service';
import axios from 'axios';
import NewNotification from '@/components/NewNotification.vue';
import authService from '@/services/auth-service';
export default {
	components:{
		NavBarComponent,
		FooterComponent,
		NewNotification
	},
	data(){
		return {
			token:null,
			username:'',
			notifications:[],
			current_page:0
		}
	},
	created(){
		this.token = authService.getToken();
		if(this.token!==null){
			this.username = this.token.username;
		}
		else router.push({path:'/login'})
	},
	methods:{
		getNotifications(){
			axios.get('http://localhost:8888/api/notificacoesService/getmyNotifications/'+this.username+"?page="+this.current_page+"&number=6")
			.then(resp=>{
				this.notifications = resp.data;
			}).catch(err=>{
				console.log(err);
			})

		},
		removeNotification(myid){
			axios.delete('http://localhost:8888/api/notificacoesService/removeMyNotification/'+this.username+"/"+myid)
			.then(resp=>{
				this.notifications = this.notifications.filter(notification=>notification.id!==myid);
			}).catch(err=>{
				console.log(err);
			})
		},
		clearNotifications(){
			axios.delete('http://localhost:8888/api/notificacoesService/clearMyNotifications/'+this.username)
			.then(resp=>{
				this.notifications = [];
			}).catch(err=>{
				console.log(err);
			})
		},
		handlePage(){
			if(action=='next') this.current_page+=1;
			else this.current_page-=1;
		}
	}

}
</script>
<style lang="css" scoped>
	@import '../assets/allnotifications.css';
</style>