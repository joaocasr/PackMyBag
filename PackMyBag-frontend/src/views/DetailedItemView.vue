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
          					<Rating id="avrgestrelas" :modelValue="averageRating" @update:modelValue="averageRating = $event" readonly/>
        				</div>
        				<div class="div">{{ averageRating }}</div>
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
				<ReviewComponent 
				 :classificacao="review.classificacao"
				 :descricao="review.descricao"
				 :nome="review.nome"
				 :profileImg="review.profileImg"
				 :timestamp="review.timestamp"></ReviewComponent>	
				 
			</div>
    		<div class="reviews1">REVIEWS</div>
    		<div class="parent">
				<div v-if="showPrevious==true" @click="handlePage('previous')" class="rectangle-parent1">
        				<img alt="" src="/DetailedItemIMG/previousbtn.png">
        				
      			</div>
				<div class="div3">{{ current_page + 1}}</div>
      			<div v-if="showNext==true" @click="handlePage('next')" class="rectangle-parent2">
        				<img alt="" src="/DetailedItemIMG/nextbtn.png">
        				
      			</div>
    		</div>
    		<img class="fvrt-icon" @click="handleAddFavourite()" alt="" :src="heart">
    		<img class="fvrt-icon-del" @click="handleRemoveFavourite()" alt="" src="/DetailedItemIMG/FvrtDel.png">
    		<div class="myreview">
				<div class="star">
				</div>
				<div class="gabriel-r">{{ nome }}</div>
				<img class="generic-user-icon-13-262266219" alt="" src="/DetailedItemIMG/generic-user-icon-13-2622662197-removebg-preview 1.png">

				<div class="star-parent">
					<Rating id="myestrelas" :modelValue="myrate" @update:modelValue="myrate = $event" :cancel="false" />
				</div>
				<input v-model="mydescription" class="itempage-child1"/>
				<div @click="publishReview" class="publishbtn">
					<div class="publish">PUBLISH</div>
				</div>
			</div>
			<div>
                <div class="item-row-detail">
					<div v-if="relacionados.length>0" class="relatedTitle"><u>RELATED ITEMS</u></div>	
					<div v-for="item in relacionados">
						<ItemRelacionadoView
						:imgSrc="item.imagem"
						:descricao="item.designacao"
						:idItem="item.itemID"></ItemRelacionadoView>
					</div>
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
import ItemRelacionadoView from '@/components/ItemRelacionadoView.vue';
import Rating from 'primevue/rating';

import axios from 'axios';
export default {
	components: {
		NavBarComponent,
		FooterComponent,
		ReviewComponent,
		ItemRelacionadoView,
		Rating
	},
	created(){
		let itemid = this.$route.params.id;
		this.idItem = itemid;
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
			username:"alpacino",
			nome:"Al Pacino",
			profileImg: "xxx",
			current_page:0,
			reviews:[],
			myrate:0,
			mydescription:'',
			averageRating:0,
			idItem:0,
			tipo:'',
			subclasse:'',
			showNext:true,
			showPrevious : true,
			relacionados:[],
			itemCode:'',
			heart:"/DetailedItemIMG/FvrtEmpty.svg",
			idLoja:0

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
				this.averageRating = item.averageRating;
				this.relacionados = item.relacionados;
				this.tipo = item.tipo;
				this.subclasse = item.subclasse;
				this.itemCode = item.codigo;
				this.idLoja = item.loja.idloja;
				if(this.disponibilidade!="Not Available"){
					this.availabilityColor = "#3de469"
				}else{
					this.availabilityColor = "#f03c36"
				}
				let cores = this.cor.split("/");
				this.colors= cores.map((x) => colorMap[x.toLowerCase()]);
				console.log(item);
			}).catch(err=>{
				console.log(err);
			})
		},
		getReviews(id){
			axios.get('http://localhost:8888/api/catalogoService/items/'+id+'/reviews?page='+this.current_page+"&number=2")
			.then(resp=>{	
				if(resp.data.length==0) {
					this.current_page -=1;
					this.showNext=false;
					return;
				}
				this.reviews = resp.data;
				console.log(this.reviews);
			}).catch(erro=>{
				console.log(erro)
			})
		},
		publishReview(){
			if(this.myrate==0) return;
			const headers = {
				'Content-Type': 'application/json',
			} //in the future add token -> 'Authorization': 'JWT ...'
			var d = new Date,
			dformat = [d.getDate(),d.getMonth()+1,d.getFullYear()].join('-')+' '+[d.getHours(),d.getMinutes()].join(':');
			axios.post('http://localhost:8888/api/catalogoService/items/'+this.idItem+'/addreview',
				{
					"username" : this.username,
					"name" : this.nome,
					"profileImg" : this.profileImg,
					"texto" : this.mydescription,
					"timestamp" : dformat,
					"rating" : this.myrate
				},
				{headers}
			).then(resp=>{
				this.$swal({
				icon: "success",
				title: "Success!",
				text: resp.data
			});
				this.myrate=0;
				this.mydescription='';
			}).catch(error=>{
				this.$swal({
				icon: "error",
				title: "Oops...",
				text: error.data
				});
			})
			
		},
		handlePage(action){
			if(action=='previous' && this.current_page<=0){
				this.showNext = true;
				this.showPrevious = false;
				return;
			} 
			if(action=='next'){
				this.showPrevious = true;
			 this.current_page +=1
			}
			if(action=='previous') {
				this.current_page -=1;
				this.showNext = true;
			}
			this.getReviews(this.idItem);
		},
		async addToFavourites() {
			if (this.heart == "/DetailedItemIMG/FvrtEmpty.svg") this.heart = "/DetailedItemIMG/FvrtFill.png";
			else this.heart = "/DetailedItemIMG/FvrtEmpty.svg";

			const headers = {
				'Content-Type': 'application/json',
				// Future addition of Authorization token
			}
			let dimensao = this.tamanho;
			if (this.subclasse == "Calcado") dimensao = dimensao.toString();
			try {
				const resp = await axios.post('http://localhost:8888/api/favoritosService/addItem',
					{
						codigoItem: this.itemCode,
						idLoja: this.idLoja,
						clienteUsername: this.username,
						designacao: this.designacao,
						preco: this.preco,
						disponibilidade: this.disponibilidade,
						tipo: this.tipo,
						imagem: this.imgItem,
						subclasse: this.subclasse,
						dimensao: dimensao,
						identificador:this.idItem
					},
					{ headers }
				);
				console.log(resp);
				return resp;
			} catch (err) {
				console.log(err);
				return err;
			}
		},
		async handleAddFavourite() {
			const result = await this.$swal.fire({
				title: "Do you want to save the item '" + this.designacao.toLowerCase() + "' to your list of favourites?",
				showDenyButton: true,
				showCancelButton: true,
				confirmButtonText: "Save",
				denyButtonText: `Don't save`
			});
			if (result.isConfirmed) {
				let r = await this.addToFavourites();
				if (r && r.status == 200) {
					this.$swal.fire("Saved! The item was saved to your favourites.", "", "success");
				} else {
					this.$swal.fire("Something went wrong! The item already belongs to your favourites.", "", "error");
				}
			} else if (result.isDenied) {
				this.$swal.fire("Changes are not saved", "", "info");
			}
		},
		async handleRemoveFavourite() {
			const result = await this.$swal.fire({
				title: "Do you want to remove the item '" + this.designacao.toLowerCase() + "' from your list of favourites?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Remove"
			});
			if (result.isConfirmed) {
				let r = await this.removeFromFavourites();
				if (r && r.status == 200) {
					this.$swal.fire("Sucess! The item was removed from your favourites.", "", "success");
				} else {
					this.$swal.fire("Something went wrong! The item does not belong to your favourites.", "", "error");
				}
			} else if (result.isDenied) {
				this.$swal.fire("Changes are not saved", "", "info");
			}
		},
		async removeFromFavourites() {
			try {
				let data = {username: this.username,itemCode: this.itemCode,idLoja: this.idLoja} 
				let headers = {
					headers: {
						'Content-Type': 'application/json;charset=UTF-8',
					}
				}
				const resp = await axios.delete('http://localhost:8888/api/favoritosService/removeItem',
				{
					data,
					headers
				}
				);
				console.log(resp);
				return resp;
			} catch (err) {
				console.log(err);
				return err;
			}
		}
		
	},

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
