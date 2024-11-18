<template>
		<main>
			<NavBarComponent></NavBarComponent>
			<div class="homeimage">
				<img class="icon" alt="" src="/HomeViewIMG/1104046 1.png">
				<div class="clothing-rental-for-container">
				<p class="clothing-rental-for">Clothing rental for travel lovers</p>
				<p class="clothing-rental-for">Leave your bags behind and sign up now</p>
				</div>
				<div class="explore-collection-wrapper" id="frameContainer">
				</div>
				<img class="icon1" alt="" src="/HomeViewIMG/5787874 1.png">
			</div>
			<button class="explore-collection" @click="goToCatalogue()">EXPLORE COLLECTION</button>
			<div class="rowhomecatalogue">
				<!--colocar aqui-->
				<div class="itemclass" v-for="item in randomItems">
					<ItemCatalogueView
					:imgSrc="item.imagem" 
					:descricao="item.designacao"
					:preco="item.preco" 
					:cor="item.cor" 
					:iditem="item.iditem"
				></ItemCatalogueView>
				</div>
			</div>
			<button class="fullcataloguebtn" @click="goToCatalogue()">FULL CATALOGUE</button>
			<div class="howitworksbanner">
				<div class="worksbanner-v2">
				<b class="how-it-works"> How It Works</b>
				<b class="return">Return</b>
				<b class="browse">Browse</b>
				<b class="rent">Rent</b>
				<div class="explore-our-vast">Explore our vast collection of clothes for your travels.</div>
				<div class="return-the-items">Return the items at the hotel reception.</div>
				<div class="choose-your-favorite">Choose your favorite pieces/sets and rent them for your trip.</div>
				<img class="image-icon" alt="" src="/HomeViewIMG/image.png">
				<img class="image-1-icon" alt="" src="/HomeViewIMG/image 1.png">
				<img class="hotel-reception-1-icon" alt="" src="/HomeViewIMG/hotel_reception 1.png">
				<img class="image-26-icon" alt="" src="/HomeViewIMG/image 26.png">
				<div class="pick-up-at">Pick up at the hotel reception and enjoy the clothes.</div>
				<b class="pick-up">Pick Up</b>
			</div>
			</div>
			
			<div class="membershipplan">
				<b class="hire-a-stylist">Hire a stylist</b>
				<div class="send-a-form-for-just-parent">
					<b class="send-a-form">Send a form for just</b>
					<b class="b">7€</b>
				<div class="stylists-recommendations">stylist's recommendations</div>
					<img class="imagem-4-icon" alt="" src="/HomeViewIMG/imagem 4.png">
				<div class="discount-on-orders">discount on orders</div>
					<img class="imagem-5-icon" alt="" src="/HomeViewIMG/imagem 5.png">
				<div class="fill-the-form-wrapper" id="frameContainer">

				<a href="/stylists">
					<div class="fill-the-form">FILL THE FORM</div>
				</a>
				

				</div>
				</div>
			</div>
			<div class="sustainablebanner">
					<img class="imagem-1-icon" alt="" src="/HomeViewIMG/imagem 1.png">

					<img class="imagem-3-icon" alt="" src="/HomeViewIMG/imagem 3.png">

					<img class="imagem-2-icon" alt="" src="/HomeViewIMG/imagem 2.png">

					<b class="sustainable-fashion">Sustainable Fashion</b>
					<b class="circular-economy">Circular Economy</b>
					<b class="eco-friendly">Eco-Friendly</b>
					<b class="water-conservation">Water Conservation</b>
					<div class="reduce-clothing-waste">Reduce clothing waste and your carbon footprint.</div>
					<div class="participate-in-a">Participate in a more sustainable fashion cycle.</div>
					<div class="help-save-water">Help save water used in clothing production.</div>
			</div>
				<FooterComponent></FooterComponent>
		</main>
</template>

<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import ItemCatalogueView from '@/components/ItemCatalogueView.vue';
import axios from 'axios';
export default {
	components:{
		NavBarComponent,
		FooterComponent,
		ItemCatalogueView
	},
	data(){
		return {
			randomItems:[]
		}
	}
	,created(){
		this.getRandomItems();
	},
	methods:{
		getRandomItems(){
			axios.get(this.$apiGatewayUrl+'/api/catalogoService/random')
			.then(items =>{
				console.log(items.data)
				this.randomItems = items.data;
				console.log("aqiu")
			}).catch(error=>{
				console.log(error);
			})
		},
		goToCatalogue(){
			this.$router.push({ path: '/catalogue' });
		}
	}
}

</script>
<style lang="css" scoped>
@import '../assets/HomeView.css';
</style>
