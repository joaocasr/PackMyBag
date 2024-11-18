<template>
    <NavBarComponent></NavBarComponent>
     <div class="stylist-requests">
        <div class="div">
        <img class="line" src="/CartIMG/Line 17.png"/>
        <div class="text-wrapper">My Requests</div>
        <div class="requestscontainer">
            <RequestRecomendation></RequestRecomendation>
            <RequestRecomendation></RequestRecomendation>
            <RequestRecomendation></RequestRecomendation>
            <RequestRecomendation></RequestRecomendation>
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

export default {
    components:{
        NavBarComponent,
        FooterComponent,
        RequestRecomendation
    },
    data(){
        return {
            current_page:0,
            pedidos:[]
        }
    }
}
</script>

<style lang="css" scoped>
@import '../assets/stylistsrequests.css';
</style>