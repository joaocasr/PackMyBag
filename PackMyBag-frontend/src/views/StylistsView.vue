<template>
    <main>
        <NavBarComponent></NavBarComponent>
        
            
            <div class="stylistspage">
                    <div class="stylistspage-child">
                    </div>
                    <div class="stylists">Stylists</div>

                    <div v-for="s in estilistas">
						<StylistIndividualView
                        :nome="s.nome"
                        :username="s.username"
                        :profileImage="s.profileImage"
                        :bio="s.bio"
                        :rating="s.rating"
                        ></StylistIndividualView>
					</div>
                    
                    <div class="parent">
                        <div class="div1">{{current_page + 1}}</div>
                                <img v-if="showbtnprevious==true" @click="handlePage('previous')" class="group-item1" alt="" src="/CatalogueIMG/previousbtn.png">
                               
                                <img v-if="showbtnnext==true" @click="handlePage('next')" class="group-item2" alt="" src="/CatalogueIMG/nextbtn.png">
                                

                    </div>
                    <div class="genderfilter">

                        
                    </div>
            </div>
  	
  	
  	
        <FooterComponent></FooterComponent>
    </main>
</template>

<script>

import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import StylistIndividualView from '@/components/StylistIndividualView.vue';
import VueSelect from "vue3-select-component";
import axios from 'axios';
import authService from '@/services/auth-service';
import authHeader from '@/services/auth-header';

export default {

    components:{
        NavBarComponent,
        FooterComponent,
        StylistIndividualView,
        VueSelect
    },
    data(){
        return{
            value: [0,1000],
            selectedOption:'',
            typeOptions: [{ label: 'Male', value: 'Male' },{ label: 'Female', value: 'Female' }],
            items: [],
            filtered: [],
            current_page:0,
            token: null,
            username:'',
            estilistas: [],
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
		this.getStylists();
    }, 
    methods:{
        getStylists(){
            const header = authHeader();
			let config = {headers:header}
			header['Content-Type'] = 'application/json';
            axios.get(this.$apiGatewayUrl+'/api/utilizadoresService/estilistas?page='+this.current_page+"&number=2",config).then(stylists=>{
                this.estilistas = stylists.data;
                if(this.estilistas.length==0) this.showbtnnext=false;
                console.log(this.estilistas);
            }).catch(err=>{
                console.log(err);
            })
        },
        handlePage(action){
            if(action=='previous' && this.current_page==0){
				this.showbtnprevious=false;
				return;
			}
			if(action=='previous' && this.current_page>0) {
				this.current_page-=1;
				this.showbtnnext=true;
                this.showbtnprevious=true;
				this.getStylists();
				return;
			}
			else {
				this.current_page+=1;
				this.showbtnprevious=true;
				this.getStylists();
			}
        }
    }

}

</script>
<style lang="css" scoped>
@import '../assets/stylists.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>
