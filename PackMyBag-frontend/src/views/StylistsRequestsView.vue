<template>
    <NavBarComponent></NavBarComponent>
     <div class="stylist-requests">
        <div class="div">
        <img class="line" src="/CartIMG/Line 17.png"/>
        <div class="text-wrapper">My Requests</div>

        <div v-if="isPopupVisible" class="popup">
        <div class="popup-content">
            <h3>List of Items - Recomendation for {{ pedidos[popUpRequest].cliente }}</h3>
            <ul>
            <li v-for="(item, index) in pedidos[popUpRequest].items" :key="index">{{ item.nome }}</li>
            </ul>
            <button @click="togglePopup">Close</button>
        </div>
        </div>
        <div class="requestscontainer">
            <div v-for="(pedido, index) in pedidos" :key="index">
                <RequestRecomendation
                    :nome="pedido.nome"
                    :descricao="pedido.descricao"
                    :cliente="pedido.usernameCliente"
                    :styles="pedido.estilos"
                    :colors="pedido.cores"
                    :nroutfits="pedido.nrOutfits"
                    :budget="pedido.orcamento"
                    :dontinclude="pedido.peçasExcluidas"
                    :preferences="pedido.fabricsPreferences"
                    :occasion="pedido.occasions"
                    :status="pedido.status"
                    :items="pedido.conjunto"
                    :idx="index"
                    @expand="showPopUp"
                    @recommendation_completed="removeRecommendation"
                    >
                </RequestRecomendation>
            </div>
        </div>
        <div class="parent">
						<img v-if="current_page+1 > 1" @click="handlePage('previous')" class="group-item1" alt="" src="/CatalogueIMG/previousbtn.png">
				<div v-if="pedidos.length>0" class="dividx">{{ current_page + 1}}</div>
      			<div class="rectangle-parent">
						<img v-if="pedidos.length>0" @click="handlePage('next')" class="group-item2" alt="" src="/CatalogueIMG/nextbtn.png">
      			</div>
			</div>
        </div>
    </div>
    <FooterComponent></FooterComponent>

</template>

<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import RequestRecomendation from '@/components/RequestRecomendation.vue';
import authService from '@/services/auth-service';
import axios from 'axios';
export default {
    components:{
        NavBarComponent,
        FooterComponent,
        RequestRecomendation
    },
    created(){
		let token = authService.getToken();
		if(token!=null){
			this.token = token;
			this.usernameEstilista=token.username;
            this.getRequests();
		}
	},
    data(){
        return {
            token:null,
            usernameEstilista:'',
            current_page:0,
            popUpRequest:0,
            isPopupVisible:false,
            pedidos:[]
        }
    },
    methods:{
        showPopUp(i){
            this.popUpRequest = i;
            this.isPopupVisible = true;
        },
        togglePopup(){
            this.isPopupVisible = false;
        },
        getRequests(){
            axios.get(this.$apiGatewayUrl+'/api/recomendacoesService/pedidos/estilistas/'+this.usernameEstilista+'?page='+this.current_page+'&number=5')
            .then(requests=>{
                this.pedidos = requests.data;
            }).catch(err=>{
                console.log(err);
            });
        },
        removeRecommendation(idx){
            this.pedidos.splice(idx,1);
        }
    }
}
</script>

<style lang="css" scoped>
@import '../assets/stylistsrequests.css';
</style>