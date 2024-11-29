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
			<button type="button" @click= "addFootWear()" class="inventorybtn1">Add FootWear</button>
			<button type="button" @click= "addPart()"class="inventorybtn2">Add Part</button>
			<button type="button" @click= "addSet()" class="inventorybtn3">Add Set</button>
			<button type="button" @click= "getOrders()" class="inventorybtn4">Orders</button>


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
		if(token!=null){
			this.token = token;
			this.username=token.username;
			this.idloja=token.idloja; 
		}
		this.getTopItems();
	},
	methods:{
		addPart(){
			this.$router.push('/addpart');
		},
		addFootWear(){
			this.$router.push('/addfootwear');
		},
		addSet() {
			this.$router.push('/addset');
		},	
		getTopItems(){
			axios.get(this.$apiGatewayUrl+'/api/catalogoService/trending/'+this.idloja).then(items=>{
				this.topItems = items.data;
			}).catch(err=>{
				console.log(err);
			})
		},
		getOrders(){
			this.$router.push('/orders');
		}
	}
}
</script>

<style lang="css" scoped>
@import '../assets/ShopHome.css';
</style>