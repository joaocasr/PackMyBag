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
						<button v-bind:style="{ 'background-image': 'url(' + bellImage + ')' }" @click="changeBell()" class="button-bell"></button>
      			</div>
    		</div>
    		<div v-if="this.role==='Cliente'" @click="handleAddToCart()" class="addtocartbutton">
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
				 :currentUser="username"
				 :classificacao="review.classificacao"
				 :descricao="review.descricao"
				 :username="review.username"
				 :profileImg="review.profileImg"
				 :idItem="idItem"
				 :timestamp="review.timestamp"
				 @deleteReview="removeReview"></ReviewComponent>	
				 
			</div>
    		<div class="reviews1">REVIEWS</div>
    		<div class="parent">
				<div v-if="showPrevious==true" @click="handlePage('previous')" class="rectangle-parent1">
        				<img alt="" src="/DetailedItemIMG/previousbtn.png">
        				
      			</div>
				<div class="div3">{{ current_page+1 }}</div>
      			<div v-if="showNext==true" @click="handlePage('next')" class="rectangle-parent2">
        				<img alt="" src="/DetailedItemIMG/nextbtn.png">
        				
      			</div>
    		</div>
			<button v-if="role==='Estilista'" class="addrecommendation" @click="togglePopup">Associate To Recommendations</button>


				<!-- Pop-up -->
				<div v-if="isPopupVisible" class="popup">
				<div class="popup-content">
					<h3>Associate the item to one or more of the following requests:</h3>

					<input
					type="text"
					v-model="searchQuery"
					placeholder="Search..."
					class="search-bar"
					/>

				<!-- Lista de pedidos -->
				<div class="list-container">
				<ul>
					<li v-for="(pedido, index) in filteredPedidos" :key="index" @mouseenter="hoveredPedido = pedido" @mouse="hoveredPedido = null">
					<label>
						<input type="checkbox" :value="pedido.nome" v-model="selectedPedidos"/>
						{{ pedido.nome }}
					</label>
					</li>
				</ul>
				</div>

				<div v-if="hoveredPedido" class="info-extra">
				<p>INFO</p>
				<p>Client: {{ hoveredPedido.usernameCliente }}</p>
				<p>Styles: {{ hoveredPedido.estilos }}</p>
				<p>Colors: {{ hoveredPedido.cores }}</p>
				<p>Nro Outfits: {{ hoveredPedido.nrOutfits }}</p>
				<p>Budget: {{ hoveredPedido.orcamento }}</p>
				<p>Excluded: {{ hoveredPedido.peçasExcluidas }}</p>
				<p>Preferences: {{ hoveredPedido.fabricsPreferences }}</p>
				<p>Occassion: {{ hoveredPedido.occasions }}</p>
				</div>

				<div class="buttons">
				<button @click="confirmSelection">Confirm</button>
				<button @click="togglePopup">Close</button>
				</div>
			</div>
				</div>

    		<img class="fvrt-icon" @click="handleAddFavourite()" alt="" :src="heart">
    		<div class="myreview">
				<div class="star">
				</div>
				<div class="gabriel-r" v-if="username!=''" >{{ username }}</div>
				<div class="gabriel-r" v-if="username==''" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
				<img class="generic-user-icon-13-262266219" alt="" :src="profileImg">

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
import authHeader from '@/services/auth-header';
import authService from '@/services/auth-service';
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
		let token = authService.getToken();
		if(token!=null){
			this.token = token;
			this.username=token.username;
			if(token.profileImage!=="") this.profileImg = this.$apiGatewayUrl+ '/api/utilizadoresService/profileImg/' +this.username;
			this.nome=token.nome;
			this.role = token.role;
			this.email=token.email;
			if(this.role==="Estilista") this.getPedidos();
		}
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
			username:"",
			nome:"",
			email:"",
			role:"",
			token:null,
			profileImg: "/DetailedItemIMG/generic-user-icon-13-2622662197-removebg-preview 1.png",
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
			bellImage: "/DetailedItemIMG/bell.png",
			idLoja:0,
			isPopupVisible: false,
			pedidos: [],
			searchQuery: "",
			selectedPedidos: [],
			hoveredPedido: null
		}
	},	
	computed: {
		filteredPedidos() {
		return this.pedidos.filter((pedido) =>
			pedido.nome.toLowerCase().includes(this.searchQuery.toLowerCase())
		);
		}
	},
	methods:{
		getItemInfo(id){
			axios.get(this.$apiGatewayUrl+'/api/catalogoService/items/'+id).then(resp=>{
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
				if(this.disponibilidade!=="In Stock"){
					this.availabilityColor = "#f03c36"
				}else{
					this.availabilityColor = "#3de469"
				}
				let cores = this.cor.split("/");
				this.colors= cores.map((x) => colorMap[x.toLowerCase()]);
				console.log(item);
			}).catch(err=>{
				console.log(err);
			})
		},
		getReviews(id){
			axios.get(this.$apiGatewayUrl+'/api/catalogoService/items/'+id+'/reviews?page='+this.current_page+"&number=2")
			.then(resp=>{	
				if(resp.data.length==0) {
					this.showNext=false;
					return;
				}
				this.reviews = resp.data;
				console.log(this.reviews);
			}).catch(erro=>{
				console.log(erro)
			})
		},
		removeReview(){
			this.reviews = this.reviews.filter((r) => r.username !== this.username);
		},
		publishReview(){
			if(this.myrate==0) return;
			const header = authHeader();
			let config = {headers:header}
			header['Content-Type'] = 'application/json';

			var d = new Date,
			dformat = [d.getDate(),d.getMonth()+1,d.getFullYear()].join('-')+' '+[d.getHours(),d.getMinutes()].join(':');
			axios.post(this.$apiGatewayUrl+'/api/catalogoService/items/'+this.idItem+'/addreview',
				{
					"username" : this.username,
					"profileImg" : this.profileImg,
					"texto" : this.mydescription,
					"timestamp" : dformat,
					"rating" : this.myrate
				},
				config
			).then(resp=>{
				this.$swal({
				icon: "success",
				title: "Success!",
				text: resp.data
			});
				this.reviews = this.reviews.filter((r) => r.username !== this.username);
				this.reviews.push({classificacao:this.myrate,descricao:this.mydescription,username:this.username,profileImg:this.profileImg,timestamp:dformat});
				this.averageRating = ((this.averageRating * this.nrReviews) + this.myrate) / (this.nrReviews+1);
				this.nrReviews = this.nrReviews + 1;
				this.myrate=0;
				this.mydescription='';
			}).catch(error=>{
				let errmsg ="More details : "
				if(error.response.data.message){
					errmsg+=error.response.data.message;
				}
				let m = "Check if you are authenticated and try again. "+errmsg;
				this.myrate=0;
				this.mydescription='';
				this.$swal({
				icon: "error",
				title: "Erro!",
				text: m
				});
			})
			
		},
		handlePage(action){
			if(action=='previous' && this.current_page==0){
				this.showNext = true;
				this.showPrevious = false;
				return;
			}
			if(action=='previous' && this.current_page>0){
				this.showNext = true;
				this.showPrevious = true;
				this.current_page -=1
				this.getReviews(this.idItem);
				return;
			} 
			if(action=='next'){
				this.showPrevious = true;
				this.showNext = true;
			 	this.current_page +=1
				this.getReviews(this.idItem);
				return;
			}
		},
		async addToFavourites() {

			const header = authHeader();
			let config = {headers:header}
			header['Content-Type'] = 'application/json';

			let dimensao = this.tamanho;
			if (this.subclasse == "Calcado") dimensao = dimensao.toString();
			try {
				const resp = await axios.post(this.$apiGatewayUrl+'/api/favoritosService/addItem',
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
					config
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
					this.heart = "/DetailedItemIMG/FvrtFill.png";
				} else {
					let msg="";
					if(r.response) msg = r.response.data.message;
					this.$swal.fire("Something went wrong! "+msg, "", "error");
					this.heart = "/DetailedItemIMG/FvrtEmpty.svg";
				}
			}
		},
		async addInterested(){
			const header = authHeader();
			let config = {headers:header}
			header['Content-Type'] = 'application/json';
			try{
				const r = await axios.post(this.$apiGatewayUrl+ '/api/notificacoesService/addInterested',
					{
						"codigo":this.itemCode,
						"designacao":this.designacao,
						"disponibilidade":this.disponibilidade,
						"idLoja":this.idLoja,
						"username":this.username,
						"nomeuser":this.nome,
						"email":this.email
					},
					config
				)
				return r;
			}catch(err){
				console.log(err);
				return err;
			}
		},
		async changeBell(){
			const result = await this.$swal.fire({
				title: "Do you want to receive notifications of " + this.designacao.toLowerCase() + "'s availability?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Yes"
			});
			if(result.isConfirmed){
				console.log("confirmado");
				let r = await this.addInterested();
				console.log(r);
				this.bellImage="/DetailedItemIMG/bellactive.jpg";
				if (r && r.status == 200) {
					this.$swal.fire("You will receive notifications on this item soon.", "", "success");
				} else {
					let msg="";
					if(r.response) msg = r.response.data.message;
					this.$swal.fire("Something went wrong! "+msg, "", "error");
					this.bellImage="/DetailedItemIMG/bell.png";
				}
			} else if (result.isDenied) {
				this.bellImage="/DetailedItemIMG/bell.png";

			}			
		},
		async addToCart(){
			if(this.disponibilidade!=="In Stock"){
				this.$swal.fire("The current item is unavailable!", "", "error");
				return;
			}
			const header = authHeader();
			let config = {headers:header}
			header['Content-Type'] = 'application/json';
			try{
				let r = await axios.post(this.$apiGatewayUrl+'/api/cartService/addItem',
				{
					"codigo":this.itemCode,
					"idLoja":this.idLoja,
					"username":this.username,
					"nome":this.nome,
					"email":this.email,
					"designacao":this.designacao,
					"imagem":this.imgItem,
					"preco":this.preco,
					"quantidade":1
				},
				config
				);
				return r;
			}catch(err){
				return err;
			}
		},
		async handleAddToCart(){
			const result = await this.$swal.fire({
				title: "Do you want to add " + this.designacao.toLowerCase() + " to your cart?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Yes"
			});
			if(result.isConfirmed){
				let r = await this.addToCart();
				if (r && r.status == 200) {
					this.$swal.fire("The item was inserted successfully!", "", "success");
				} else {
					let msg="";
					if(r.response) msg = r.response.data.message;
					this.$swal.fire("Something went wrong! "+msg, "", "error");
				}
			}
		},
		togglePopup() {
			this.isPopupVisible = !this.isPopupVisible;
		},
		async confirmSelection() {
			console.log(this.selectedPedidos);
			
			const result = await this.$swal.fire({
				title: "Are you sure you want to save this item to the recommendation(s)?",
				showDenyButton: false,
				showCancelButton: true,
				confirmButtonText: "Yes"
			});
			if(result.isConfirmed){
				let p;
				const header = authHeader();
				let config = {headers:header}
				header['Content-Type'] = 'application/json';

				for(p=0;p<this.selectedPedidos.length;p+=1){
					try{
						let add = await axios.put(this.$apiGatewayUrl+'/api/recomendacoesService/addItem',
						{
							"nome": this.selectedPedidos[p],
							"item": {"codigo":this.itemCode,"designacao":this.designacao,"imagem":this.imgItem,"preco":this.preco,"identificador":this.idItem,"idLoja":this.idLoja}
						},
						config
						);
						console.log(add);
					}catch(err){
						this.$swal.fire("Something went wrong! "+err.data, "", "error");
					}
				}

			}
			
			this.togglePopup(); 
		},
		getPedidos(){
			axios.get(this.$apiGatewayUrl+'/api/recomendacoesService/pedidosEinfo/'+this.username)
            .then(requests=>{
                this.pedidos = requests.data;
				console.log("aquuuiiii")
                console.log(this.pedidos);
            }).catch(err=>{
                console.log(err);
            });
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
