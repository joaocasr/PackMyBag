<template>  	
	<NavBarComponent></NavBarComponent>

    <div class="client">

        <div class="div">
            <img class="line" src="/SignUpIMG/Line 19.svg" />
            <div class="text-wrapper">Recommendations</div>
            <!-- <div class="text-wrapper-2">Recommendation</div> -->


            <div class="group">
                <div class="parent">
						<img v-if="current_page+1 > 1" @click="handlePage('previous')" class="group-item1" alt="" src="/CatalogueIMG/previousbtn.png">
                    <div v-if="recommendations.length>0" class="dividx">{{ current_page + 1}}</div>
                    <div class="rectangle-parent">
                            <img v-if="recommendations.length>0" @click="handlePage('next')" class="group-item2" alt="" src="/CatalogueIMG/nextbtn.png">
                    </div>
			    </div>
            </div>


            <!-- <div class="recommendation"> -->
            <div v-for="rec in recommendations">
                <div class="recommendation">
                    <UserRecomendationIndividual
                    :descricao="rec.designacao"
                    :items="rec.items">
                    </UserRecomendationIndividual>
                </div>
            </div>
            <!-- </div> -->

        </div>
    </div>

	<FooterComponent></FooterComponent>
</template>

<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import Slider from '@vueform/slider';
import VueSelect from "vue3-select-component";
import authService from '@/services/auth-service';
import axios from 'axios';

export default {
	components:{
		NavBarComponent,
		FooterComponent,
		Slider,
		VueSelect
	},
	data(){
		return {
			value: [0,1000],
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
		}
		this.getUserRecomendacoes();
	},
	methods:{
		getUserRecomendacoes(){
            // falta ir buscar as recomendaçoes do cliente

			axios.get('http://localhost:8888/api/favoritosService/'+this.username+"?page="+this.current_page+"&number=3")
			.then(resp=>{
				this.favourites = resp.data;
				if(this.favourites.length==0) this.showbtnnext=false;
				console.log(this.favourites);
			}).catch(error=>{
				console.log(error);
			})
		},
	
		handlePage(action){
			if(action=='next') this.current_page+=1;
			else this.current_page-=1;
			this.getItemsperTypeAndPrice(this.selectedOption,this.value[0],this.value[1])
		},
		
	}
}
</script>

<style lang="css" scoped>
@import '../assets/userRecomendations.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>