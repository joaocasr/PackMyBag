<template>  	
	<NavBarComponent></NavBarComponent>

    <div class="client">

        <div class="div">
            <img class="line" src="/SignUpIMG/Line 17.png" />
            <div class="text-wrapper">Recommendations</div>


            <!-- <div class="recommendation"> -->
            <div v-for="rec in recommendations">
                <div class="recommendation">
                    <UserRecomendationIndividual
                    :descricao="rec.descricao"
                    :items="rec.conjunto"
					:username="username"
					:email="email"
					:nome="nome">
                    </UserRecomendationIndividual>
                </div>
            </div>
            <!-- </div> -->

			<div class="group">
                <div class="parent">
						<img v-if="current_page+1 > 1" @click="handlePage('previous')" class="group-item1" alt="" src="/CatalogueIMG/previousbtn.png">
                    <div v-if="recommendations.length>0" class="dividx">{{ current_page + 1}}</div>
                    <div class="rectangle-parent">
                            <img v-if="recommendations.length>0" @click="handlePage('next')" class="group-item2" alt="" src="/CatalogueIMG/nextbtn.png">
                    </div>
			    </div>
            </div>

        </div>
    </div>

	<FooterComponent></FooterComponent>
</template>

<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import UserRecomendationIndividual from '@/components/UserRecomendationIndividual.vue';
import authService from '@/services/auth-service';
import axios from 'axios';

export default {
	components:{
		NavBarComponent,
		FooterComponent,
		UserRecomendationIndividual
	},
	data(){
		return {
			current_page:0,
			username:'',
			token:null,
			
			recommendations: [],
			isquery:false,
			queryname:"",

			showbtnprevious:false,
			showbtnnext:true
		}
	},
	created(){
		let token = authService.getToken();
		console.log(token);
		if(token!=null){
			this.token = token;
			this.username=token.username;
			this.nome=token.nome;
			this.email=token.email;
			this.getUserRecomendacoes();
		}
	},
	methods:{
		getUserRecomendacoes(){
            // falta ir buscar as recomendaçoes do cliente

			axios.get('http://localhost:8888/api/recomendacoesService/pedidos/cliente/'+this.username+"?page="+this.current_page+"&number=3")
			.then(resp=>{
				if (resp.status != 400 || resp.status != 404 || resp.status != 500) {
					this.recommendations = resp.data;
					if(this.recommendations.length==0) this.showbtnnext=false;
					console.log(this.recommendations);
					console.log("Sucess getting recommendations");
				}
				
			}).catch(error=>{
				console.log(error);
				console.log("No recommendations found");
				this.recommendations = [];
				this.showbtnnext=false;
			
			})
		},
	
		handlePage(action){
			if(action=='next') this.current_page+=1;
			else this.current_page-=1;
			this.getUserRecomendacoes();
		},
		
	}
}
</script>

<style lang="css" scoped>
@import '../assets/userRecomendations.css';
</style>