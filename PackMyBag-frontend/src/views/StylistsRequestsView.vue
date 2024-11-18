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
                    :cliente="pedido.cliente"
                    :styles="pedido.styles"
                    :colors="pedido.colors"
                    :nroutfits="pedido.nroutfits"
                    :budget="pedido.budget"
                    :dontinclude="pedido.dontinclude"
                    :preferences="pedido.preferences"
                    :occasion="pedido.occasion"
                    :status="pedido.status"
                    :items="pedido.items"
                    :idx="index"
                    @expand="showPopUp"
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

export default {
    components:{
        NavBarComponent,
        FooterComponent,
        RequestRecomendation
    },
    data(){
        return {
            current_page:0,
            popUpRequest:0,
            isPopupVisible:false,
            pedidos:[
                {"cliente":"johnny1","styles":"Casual","colors":"Blue,Yellow","nroutfits":2,"budget":"20 Eur", "dontinclude": "Skirt, Jacket", "preferences":"Denim, Cotton" , "occasion":"Beach Vacation", "status":"pending", "items":[{"nome":"item 1"},{"nome":"item 2"},{"nome":"item 3"},{"nome":"item 4"},{"nome":"item 5"},{"nome":"item 6"},{"nome":"item 7"},{"nome":"item 8"},{"nome":"item 9"},{"nome":"item 10"},{"nome":"item 11"}]},
                {"cliente":"johnny2","styles":"Casual","colors":"Blue,Yellow","nroutfits":2,"budget":"20 Eur", "dontinclude": "Skirt, Jacket", "preferences":"Denim, Cotton" , "occasion":"Beach Vacation", "status":"pending", "items":[{"nome":"item 1"}]},
                {"cliente":"johnny3","styles":"Casual","colors":"Blue,Yellow","nroutfits":2,"budget":"20 Eur", "dontinclude": "Skirt, Jacket", "preferences":"Denim, Cotton" , "occasion":"Beach Vacation", "status":"pending", "items":[{"nome":"item 1"}]},
                {"cliente":"johnny4","styles":"Casual","colors":"Blue,Yellow","nroutfits":2,"budget":"20 Eur", "dontinclude": "Skirt, Jacket", "preferences":"Denim, Cotton" , "occasion":"Beach Vacation", "status":"pending", "items":[{"nome":"item 1"}]},
                {"cliente":"johnny5","styles":"Casual","colors":"Blue,Yellow","nroutfits":2,"budget":"20 Eur", "dontinclude": "Skirt, Jacket", "preferences":"Denim, Cotton" , "occasion":"Beach Vacation", "status":"pending", "items":[{"nome":"item 1"}]}
            ]
        }
    },
    methods:{
        showPopUp(i){
            console.log("showing the request:")
            console.log(i);
            this.popUpRequest = i;
            this.isPopupVisible = true;
        },
        togglePopup(){
            this.isPopupVisible = false;
        }
    }
}
</script>

<style lang="css" scoped>
@import '../assets/stylistsrequests.css';
</style>