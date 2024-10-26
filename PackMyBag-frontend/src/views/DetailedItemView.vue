<template>
	<main>
	<NavBarComponent></NavBarComponent>
  	<div class="itempage">
    		<div class="image-8-wrapper">
      			<img class="image-8-icon" alt="" :src="imgItem">
      			
    		</div>
    		<div class="itempage-inner">
      			<div class="b1-parent">
					<div v-for="color in colors" class="b1">
          					<div :style="{backgroundColor:color}" class="b1-child">
          					</div>
        				</div>
      			</div>
    		</div>
    		<div class="itempage-child">
    		</div>
    		<div class="itempage-item">
    		</div>
    		<div class="cutwork-poplin-dress">{{ designacao }}</div>
    		<div class="size-guide-xxl-container">
      			<p class="size-guide">SIZE: {{ tamanho }}</p>
    		</div>
    		<div class="colors">COLORS:</div>
    		<div class="frame-parent">
      			<div class="frame-group">
        				<div class="star-group">
          					<img class="bag-outline-icon" alt="" src="/DetailedItemIMG/Star.svg">
          					
          					<img class="bag-outline-icon" alt="" src="/DetailedItemIMG/Star.svg">
          					
          					<img class="bag-outline-icon" alt="" src="/DetailedItemIMG/Star.svg">
          					
          					<img class="bag-outline-icon" alt="" src="/DetailedItemIMG/Star.svg">
          					
          					<img class="bag-outline-icon" alt="" src="/DetailedItemIMG/Star.svg">
          					
        				</div>
        				<div class="div">4</div>
      			</div>
      			<div class="messageicon-parent">
        				<img class="messageicon" alt="" src="/DetailedItemIMG/Icon.svg">
        				
        				<div class="div">{{ nrReviews }} Reviews</div>
      			</div>
    		</div>
    		<div class="addtocartbutton">
				<img class="bag-outline-icon" alt="" src="/DetailedItemIMG/Bag.svg">
      			<div class="div">Add to cart</div>
      			<div class="arrowright">
      			</div>
    		</div>
    		<div class="priceitem">
      			<div class="arrowright">
      			</div>
      			<div class="div">{{ preco }} €</div>
      			<div class="arrowright">
      			</div>
    		</div>
    		<div class="availability" :style = "{backgroundColor: availabilityColor}">
      			<div class="arrowright">
      			</div>
      			<div class="in-stock">{{ disponibilidade }}</div>
      			<div class="arrowright">
      			</div>
    		</div>
			<div class="reviewsection" v-for="review in reviews">
				<ReviewComponent></ReviewComponent>	
			</div>
    		<div class="reviews1">REVIEWS</div>
    		<div class="parent">
      			<div class="div3">1</div>
      			<div class="rectangle-parent">
        				<div class="group-child">
        				</div>
        				<img class="group-item" alt="" src="/DetailedItemIMG/nextbtn.png">
        				
      			</div>
    		</div>
    		<img class="fvrt-icon" alt="" src="/DetailedItemIMG/Fvrt.svg">
    		<div class="myreview">
				<div class="star">
				</div>
				<div class="gabriel-r">Alex R</div>
				<img class="generic-user-icon-13-262266219" alt="" src="/DetailedItemIMG/generic-user-icon-13-2622662197-removebg-preview 1.png">

				<div class="star-parent">
					<img class="star-icon" alt="" src="/DetailedItemIMG/Star.svg">
					<img class="star-icon" alt="" src="/DetailedItemIMG/Star.svg">
					<img class="star-icon" alt="" src="/DetailedItemIMG/Star.svg">
					<img class="star-icon" alt="" src="/DetailedItemIMG/Star.svg">
					<img class="star-icon" alt="" src="/DetailedItemIMG/Star.svg">
				</div>
				<div class="itempage-child1">
				</div>
				<div class="publishbtn">
					<div class="publish">PUBLISH</div>
				</div>
			</div>
 
    </div>
	<FooterComponent></FooterComponent>
	</main>
</template>
<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import ReviewComponent from '@/components/ReviewComponent.vue';
import axios from 'axios';
export default {
	components: {
		NavBarComponent,
		FooterComponent,
		ReviewComponent
	},
	created(){
		let itemid = this.$route.params.id;
		this.getItemInfo(itemid);
		this.getReviews(itemid);
	},
	data(){
		return{
			imgItem:String,
			designacao:String,
			preco:Number,
			nrReviews:Number,
			disponibilidade:String,
			cor:String,
			tamanho:String,
			availabilityColor:"#ffff",
			colors:[],
			username:"alej22",
			current_page:0,
			reviews:[]
		}
	},	
	methods:{
		getItemInfo(id){
			axios.get('http://localhost:8888/api/catalogoService/items/'+id).then(resp=>{
				let item = resp.data;
				this.imgItem = item.imagem;
				this.designacao = item.designacao;
				this.preco = item.preco;
				this.nrReviews = item.nrReviews;
				this.disponibilidade = item.disponibilidade;
				this.cor = item.cor;
				this.tamanho = item.tamanho;
				if(this.disponibilidade!="Not Available"){
					this.availabilityColor = "#3de469"
				}else{
					this.availabilityColor = "#f03c36"
				}

				let cores = this.cor.split("/");
				this.colors= cores.map((x) => colorMap[x.toLowerCase()]);
				console.log(this.colors);
			}).catch(err=>{
				console.log(err);
			})
		},
		getReviews(id){
			axios.get('http://localhost:8888/api/catalogoService/items/'+id+'/reviews?page='+this.current_page+"&number=3")
			.then(resp=>{	
				this.reviews = resp.data;
			}).catch(erro=>{
				console.log(erro)
			})
		}
	}

}
const colorMap={
			"castanho":"#563c1c",
			"branco":"white",
			"cinzento": "gray",
			"preto":"black",
			"amarelo":"yellow",
			"cinzento-vigoré":"#A9A9A9",
			"branco-marfim":"#FFFFF0",
			"azul-celeste":"#87CEEB",
			"antracite-escuro":"#36454F",
			"cimento":"#BFBFBF",
			"lilás":"#C8A2C8",
			"laranja":"#f23916",
			"azul-elétrico":"#0070FF",
			"manteiga": "#FFF4B1", 
			"linho": "#FAF0E6",  
			"vermelho": "#FF0000",
			"mostarda": "#FFDB58", 
			"azul":"#0000FF",
			"púrpura":"#800080",
			"torrado":"#8B4513",
			"vinho":"#722F37",
			"cru":"#FAF0E6",
			"bege": "#F5F5DC",        
			"azul-celeste": "#87CEEB",  
			"azul-marinho": "#000080",  
			"cinzento-antracito": "#2F2A29",
			"magenta": "#FF00FF",       
			"malva": "#B784A7", 
			"viscose": "#D6D5E0",  
			"lavanda": "#E6E6FA",  
			"azul-lavado": "#A3C1DA",    
			"verde": "#008000",           
			"verde-claro": "#90EE90",    
			"sea":"#C2DFFF",
			"borgonha": "#800020",
			"azul-marinho-escuro": "#001F3F",  
			"verde-garrafa": "#005B5B",
			"verde-água" : "#20B2AA",   
			"caqui": "#C3B091",         
			"grená": "#7E1F3D",          
			"castanho-claro":"#D2B48C",
			"telha":"#B65C36",
			"gelo":"#E0F7FA","vison": "#A99C8D",     
			"chumbo": "#7E7B7A",      
			"rosa-pálido": "#FFB6C1", 
			"carvão": "#333333",     
			"areia": "#D1B29D",       
			"rosa": "#FF69B4",       
			"leopardo": "#D4B94B",   
			"listrado": "#22395c",    
			"cava": "#E6B24E" ,
			"framboesa": "#E30B5C",
			"cinzento-pérola": "#C0C0C0",
			"turquesa": "#40E0D0",
			"baunilha": "#F3E5AB",
			"camel-escuro": "#C19A6B",
			"azul-médio": "#007BFF"
}
</script>
<import>

</import>
<style scoped src="../assets/detaileditem.css"></style>
