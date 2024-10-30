<template>
    <main>
        <NavBarComponent></NavBarComponent>
        
            
            <div class="stylistspage">
                    <div class="stylistspage-child">
                    </div>
                    <div class="stylists">Stylists</div>

                    <div v-for="s in 2">
						<StylistIndividualView></StylistIndividualView>
					</div>
                    
                    <div class="parent">
                        <div class="div1">1</div>
                        <div class="rectangle-parent">
                                <div class="group-child">
                                </div>
                                <img class="group-item" alt="" src="/CatalogueIMG/nextbtn.png">
                                
                        </div>
                    </div>
                    <div class="genderfilter">

                        <div class="text">Gender</div>
                        <img class="iconcontrolexpand-more" alt="" src="/SignUpIMG/Icon/control/expand_more.svg">
                            <VueSelect
                                v-model="selectedOption"
                                :options="typeOptions" placeholder="Gender"
                            />
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
        current_page:0
    }
}

}

</script>
<style lang="css" scoped>
@import '../assets/stylists.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>
