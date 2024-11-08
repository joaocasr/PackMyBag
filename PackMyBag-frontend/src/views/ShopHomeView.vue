<template>  	
	<NavBarComponent></NavBarComponent>
	<div class="shophomepage">
    		<div class="homeimage">
    		</div>
    		<img class="icon" alt="" src="/HomeViewIMG/5787874 1.png">
    		
    		<img class="icon1" alt="" src="/HomeViewIMG/1104046 1.png">
    		
    		<b class="we-help-you">We help you renting clothes for your store.</b>
    		<b class="trending-items">Trending Items</b>

			<button class="inventorybtn">Inventory</button>
			<button class="inventorybtn1">Manage Items</button>

			<div class="rowhomecatalogue">
				<!--colocar aqui-->
				<div class="itemclass" v-for="item in topItems">
					<TrendItemComponent
					:imgSrc="item.imagem"
					:descricao="item.nome"
					:aquisicoes="item.nrAquisicoes"
					:idItem="item.iditem"></TrendItemComponent>
				</div>
			</div>

  	</div>
	<FooterComponent></FooterComponent>
</template>
<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import TrendItemComponent from '@/components/TrendItemComponent.vue';
import authService from '@/services/auth-service';
import axios from 'axios';
export default {
	components:{
		NavBarComponent,
		FooterComponent,
		TrendItemComponent
	},
	data(){
		return{
			token:null,
			username:String,
			idloja:Number,
			topItems:[]
		}
	},
	created(){
		let token = authService.getToken();
		console.log(token);
		if(token!=null){
			this.token = token;
			this.username=token.username;
			this.idloja=token.idloja; 
		}
		this.getTopItems();
	},
	methods:{
		
		getTopItems(){
			axios.get('http://localhost:8888/api/catalogoService/trending/'+this.idloja).then(items=>{
				console.log(items.data);
				this.topItems = items.data;
			}).catch(err=>{
				console.log(err);
			})
		}
	}
}
</script>

<style lang="css" scoped>
@import '../assets/homeshop.css';
</style>