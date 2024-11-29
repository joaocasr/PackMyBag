<template>  	
	<NavBarComponent></NavBarComponent>
	<div class="favoritespage">
    		<div class="favorites">Favorites</div>
    		<img class="favoritespage-child" alt="" src="/FavouritesIMG/Line 17.png">
    		
    		<div class="shop-cart-frame">
      			<div class="cart-main-section">
        				<div class="product-parent">
          					<div class="product">  Product</div>
          					<div class="price">Price</div>
          					<div class="price">Availability</div>
        				</div>
      			</div>
				<!--colocar aqui-->
				<div class="container-favourite" v-for="i in favourites">
					<FavouriteComponent @removedFavourite="updateFavourite"
					:nome="i.designacao"
					:username=this.username
					:codigo="i.codigo"
					:idloja="i.idloja"
					:imagem="i.imagem"
					:preco="i.preco"
					:idItem = "i.identificador"
					:disponibilidade="i.disponibilidade"
					></FavouriteComponent>
				</div>
    		</div>
    		<div class="parent">
      			<div class="divclass">{{ current_page + 1}}</div>
      			<div class="rectangle-parent">
					<img @click="handlePage('previous')" v-if="showbtnprevious" class="previousclass" alt="" src="/CatalogueIMG/previousbtn.png">
					<div @click="handlePage('next')" v-if="showbtnnext" class="group-child">
							<img alt="" src="/CatalogueIMG/nextbtn.png">
        				</div>
        				
      			</div>
    		</div>
    		<div class="layoutcollablse-heading-parent">
      			<div class="layoutcollablse-heading">
        				<div class="text">Price range</div>
      			</div>
      			<div class="formrange-parent">
					<Slider :max="1000" v-model="value" />

        				<div class="formlabelinput">
          					<div class="formbaselabel-title">
            						<div class="label1">Min</div>
          					</div>
          					<div class="forminput-basic">
          					</div>
        				</div>
        				<div class="formlabelinput1">
          					<div class="formbaselabel-title">
            						<div class="label2">Max</div>
          					</div>
          					<div class="forminput-basic">
          					</div>
        				</div>
            			<button @click="getFavouritesPerPrice" class="buttonbtn-basic">Apply</button>
        				
      			</div>
    		</div>
    		<div class="genderfilter">
				<VueSelect v-model="selectedGenderOption" :options="genderOptions" placeholder="Select a type"/>
			</div>
			<div class="sizefilter">
				<VueSelect v-model="selectedSizeOption" :options="sizeOptions" placeholder="Select a size"/>
			</div>
	</div>
	<FooterComponent></FooterComponent>
</template>
<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import Slider from '@vueform/slider';
import VueSelect from "vue3-select-component";
import FavouriteComponent from '@/components/FavouriteComponent.vue';
import authService from '@/services/auth-service';
import axios from 'axios';

export default {
	components:{
		NavBarComponent,
		FooterComponent,
		FavouriteComponent,
		Slider,
		VueSelect
	},
	data(){
		return {
			value: [0,1000],
			current_page:0,
			username:'',
			token:null,
			favourites:[],
			selectedGenderOption:'',
			selectedSizeOption:'',
			genderOptions: [{ label: 'Male', value: 'Male' },{ label: 'Female', value: 'Female' },{ label: 'Child', value: 'Child' }],
			sizeOptions: [{ label: 'S', value: 'S' },{ label: 'M', value: 'M' },{ label: 'L', value: 'L' },{ label: 'XL', value: 'XL' }],
			showbtnprevious:false,
			showbtnnext:true
		}
	},
	created(){
		let token = authService.getToken();
		if(token!=null){
			this.token = token;
			this.username=token.username;
		}
		this.getFavourites();
	},
	methods:{
		getFavourites(){
			axios.get(this.$apiGatewayUrl+'/api/favoritosService/'+this.username+"?page="+this.current_page+"&number=3")
			.then(resp=>{
				this.favourites = resp.data;
				if(this.favourites.length==0) this.showbtnnext=false;
			}).catch(error=>{
				console.log(error);
			})
		},
		updateFavourite(id){
			this.favourites = this.favourites.filter((x)=> x.identificador !== id)
		},
		handlePage(action){
			if(action=='previous' && this.current_page==0){
				this.showbtnprevious=false;
				return;
			}
			if(action=='previous' && this.current_page>0) {
				this.current_page-=1;
				this.showbtnnext=true;
				this.getFavourites();
				return;
			}
			else {
				this.current_page+=1;
				this.showbtnprevious=true;
				this.getFavourites();
			}
		},
		getFavouritesPerPrice(){
			axios.get(this.$apiGatewayUrl+'/api/favoritosService/price/'+this.username+"?min="+this.value[0]+"&max="+this.value[1]+"&page="+this.current_page+"&number=3")
			.then(resp=>{
				this.favourites = resp.data;
			}).catch(err=>{
				console.log(err);
			})
		},
		getFavouritesByGender(tipo){
			axios.get(this.$apiGatewayUrl+'/api/favoritosService/genero/'+this.username+"?gender="+tipo+"&page="+this.current_page+"&number=3")
			.then(resp=>{
				this.favourites = resp.data;
			}).catch(err=>{
				console.log(err);
			})
		},
		getFavouritesBySize(size){
			axios.get(this.$apiGatewayUrl+'/api/favoritosService/size/'+this.username+"?size="+size+"&page="+this.current_page+"&number=3")
			.then(resp=>{
				this.favourites = resp.data;
			}).catch(err=>{
				console.log(err);
			})
		}
		
	},
	watch:{
		selectedGenderOption:function(newvalue,oldvalue){
			this.getFavouritesByGender(newvalue);
		},
		selectedSizeOption:function(newvalue,oldvalue){
			this.getFavouritesBySize(newvalue);
		}
	}
}
</script>

<style lang="css" scoped>
@import '../assets/favourites.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>
