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
					<FavouriteComponent
					:nome="i.designacao"
					:imagem="i.imagem"
					:preco="i.preco"
					:idItem = "i.identificador"
					:disponibilidade="i.disponibilidade"
					></FavouriteComponent>
				</div>
    		</div>
    		<div class="parent">
      			<div class="divclass">1</div>
      			<div class="rectangle-parent">
        				<div class="group-child">
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
            			<button class="buttonbtn-basic">Apply</button>
        				
      			</div>
    		</div>
    		<div class="genderfilter">  	</div>
	</div>
	<FooterComponent></FooterComponent>
</template>
<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import Slider from '@vueform/slider';
import FavouriteComponent from '@/components/FavouriteComponent.vue';
import axios from 'axios';

export default {
	components:{
		NavBarComponent,
		FooterComponent,
		FavouriteComponent,
		Slider,
	},
	data(){
		return {
			value: [0,1000],
			current_page:0,
			username:'alpacino',
			favourites:[]
		}
	},
	created(){
		this.getFavourites();
	},
	methods:{
		getFavourites(){
			axios.get('http://localhost:8888/api/favoritosService/'+this.username+"?page="+this.current_page+"&number=3")
			.then(resp=>{
				this.favourites = resp.data;
				console.log(this.favourites);
			}).catch(error=>{
				console.log(error);
			})
		}
	}
}
</script>

<style lang="css" scoped>
@import '../assets/favourites.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>