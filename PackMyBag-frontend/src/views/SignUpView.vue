<script setup>
  import MazPhoneNumberInput from 'maz-ui/components/MazPhoneNumberInput'
  import { ref } from 'vue'
  const countryCode = ref('FR')
  const results = ref()
</script>
<template>
    <main>
        <NavBarComponent></NavBarComponent>
  	
  	<div class="background-card">
                    <div class="tabs">
                        <button v-bind:class="{ active: isActiveTab1 }" class="tab-button" @click="changeTab('tab1')">CLIENTS</button>
                        <button v-bind:class="{ active: isActiveTab2 }" class="tab-button" @click="changeTab('tab2')">STYLISTS</button>
                        <button v-bind:class="{ active: isActiveTab3 }" class="tab-button" @click="changeTab('tab3')">SHOPS</button>
                    </div>
            	<img class="logo-sideways-icon" alt="" src="/SignUpIMG/logo-sideways.png">
    		<div v-if="isActiveTab1==true">
      			<input class="forms-sign-up" v-model="user.nome" placeholder="Name" />

      			<input class="forms-sign-up1" v-model="user.username" placeholder="Username">
    		
      			<input class="forms-sign-up2" v-model="user.email" placeholder="Email"/>
    
      			<input class="forms-sign-up3" v-model="user.password" placeholder="Password" type="password"/>
      			
                <input class="forms-sign-up4" v-model="user.address" placeholder="Address" type="text"/>
                <div class="your-number-wrapper">
                  <MazPhoneNumberInput
                        v-model="user.nrTelemovel"
                        v-model:country-code="countryCode"
                        show-code-on-list
                        :preferred-countries="['FR', 'BE', 'DE', 'US', 'GB']"
                        @update="results = $event"
                    />
                </div>
               
    		<button class="vector-icon"  @click="doSignUp()" alt="" id="vector">SIGN UP</button>
    		
            <div class="genderfilter">

            <div class="text">Gender</div>
                <VueSelect
                    v-model="user.genero"
                    :options="typeOptions" placeholder="Gender"
                />
            </div>
            <div class="alreadyAccount">
                <p>Already have an account? <a href="/login">Login here.</a></p>
            </div>
            </div>
            <div v-if="isActiveTab2==true">
      			<input class="forms-sign-up" v-model="estilista.nome" placeholder="Name" />

      			<input class="forms-sign-up1" v-model="estilista.username" placeholder="Username">
    		
      			<input class="forms-sign-up2" v-model="estilista.email" placeholder="Email"/>
    
      			<input class="forms-sign-up3" v-model="estilista.password" placeholder="Password" type="password"/>
      			
                <input class="forms-sign-up4" v-model="estilista.bio"  placeholder="Tell us more about your experience..." type="text"/>

               
    		<button class="vector-icon"  @click="doSignUp()" alt="" id="vector">SIGN UP</button>
    		
            <div class="genderfilter">

            <div class="text">Gender</div>
                <VueSelect
                    v-model="estilista.genero"
                    :options="typeOptions" placeholder="Gender"
                />
            </div>
            <div class="alreadyAccount2">
                <p>Already have an account? <a href="/login">Login here.</a></p>
            </div>
            </div>
            <div v-if="isActiveTab3==true">
      			<input class="forms-sign-up" v-model="tecnico.nome" placeholder="Name" />

      			<input class="forms-sign-up1" v-model="tecnico.username" placeholder="Username">
    		
      			<input class="forms-sign-up2" v-model="tecnico.email" placeholder="Email"/>
    
      			<input class="forms-sign-up3" v-model="tecnico.password" placeholder="Password" type="password"/>
    		
                <input class="forms-sign-up4" v-model="tecnico.nomeLoja" placeholder="Shop's name" type="text"/>

    		<button class="vector-icon"  @click="doSignUp()" alt="" id="vector">SIGN UP</button>
    		<div class="alreadyAccount2">
                <p>Already have an account? <a href="/login">Login here.</a></p>
            </div>
            
            </div>

            
  	</div>
        <FooterComponent></FooterComponent>
    </main>
</template>

<script>
import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import VueSelect from "vue3-select-component";
import User from "@/models/user"
import Estilista from "@/models/estilista"
import Tecnico from '@/models/tecnico';
export default {

    data(){
		return{
			value: [0,1000],
			selectedOption:'',
			typeOptions: [{ label: 'Male', value: 'Male' },{ label: 'Female', value: 'Female' }],
			items: [],
			filtered: [],
			current_page:0,
            user: new User('', '', '', '', '', '',''),
            estilista: new Estilista('', '', '', '', '', ''),
            tecnico: new Tecnico('', '', '', '',''),
            isActiveTab1:true,
            isActiveTab2:false,
            isActiveTab3:false,

		}
	},
    components:{
        NavBarComponent,
        FooterComponent,
        VueSelect
    },
    methods:{
        doSignUp(){
            if(this.isActiveTab1==true){
                this.$store.dispatch('auth/signUpUser', this.user).then(resp => {
                    console.log(this.user);
                    this.$swal({
                        icon: "success",
                        title: "Success!",
                        text: "Your account was successfully created."});
                },error => {
                    if(error.response){
                        this.$swal({
                        icon: "error",
                        title: "Error!",
                        text: error.response.data.message});
                    }
                }
            );
            };
            if(this.isActiveTab2==true){
                this.$store.dispatch('auth/signUpEstilista', this.estilista).then(resp => {
                    console.log(this.estilista);
                    this.$swal({
                        icon: "success",
                        title: "Success!",
                        text: "Your account was successfully created."});
                },error => {
                    if(error.response){
                        this.$swal({
                        icon: "error",
                        title: "Error!",
                        text: error.response.data.message});
                    }
                }
            );
            };
            if(this.isActiveTab3==true){
                this.$store.dispatch('auth/signUpTecnico', this.tecnico).then(resp => {
                    console.log(this.tecnico);
                    this.$swal({
                        icon: "success",
                        title: "Success!",
                        text: "Your account was successfully created."});
                },error => {
                    if(error.response){
                        this.$swal({
                        icon: "error",
                        title: "Error!",
                        text: error.response.data.message});
                    }
                }
            );
            }
        },
        changeTab(tab){
            if(tab==="tab1"){
                this.isActiveTab1=true;
                this.isActiveTab2=false;
                this.isActiveTab3=false;
            }
            if(tab==="tab2"){
                this.isActiveTab1=false;
                this.isActiveTab2=true;
                this.isActiveTab3=false;
            }
            if(tab==="tab3"){
                this.isActiveTab1=false;
                this.isActiveTab2=false;
                this.isActiveTab3=true;
            }
        }
    }
}


</script>


<style lang="css" scoped>
@import '../assets/SignUp.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>

