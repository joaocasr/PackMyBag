<template>
    <main>
        <NavBarComponent></NavBarComponent>
  	
  	<div class="background-card">
                    <div class="tabs">
                        <button v-bind:class="{ active: isActiveTab1 }" class="tab-button" @click="changeTab('tab1')">CLIENTS & STYLISTS</button>
                        <button v-bind:class="{ active: isActiveTab2 }" class="tab-button" @click="changeTab('tab2')">SHOPS</button>
                    </div>
            	<img class="logo-sideways-icon" alt="" src="/SignUpIMG/logo-sideways.png">
    		<div v-if="isActiveTab1==true">
      			<input class="forms-sign-up" v-model="user.nome" placeholder="Name" />

      			<input class="forms-sign-up1" v-model="user.username" placeholder="Username">
    		
      			<input class="forms-sign-up2" v-model="user.email" placeholder="Email"/>
    
      			<input class="forms-sign-up3" v-model="user.password" placeholder="Password" type="password"/>
    		
    		<div class="phone-number">Phone number</div>
                  <input class="your-number-wrapper"
                    type="text"
                    inputmode="numeric"
                    pattern="[0-9\s]{13,19}"
                    autocomplete="cc-number"
                    maxlength="19"
                    placeholder="xxxx xxxx xxxx xxxx"
                    />
                <div class="countryclass">
                    <country-select class="countrySelect" v-model="country" shortCodeDropdown="true" :country="country" topCountry="US" />
                </div>
    		<button class="vector-icon"  @click="doSignUp()" alt="" id="vector">SIGN UP</button>
    		
            <div class="genderfilter">

            <div class="text">Gender</div>
                <VueSelect
                    v-model="selectedOption"
                    :options="typeOptions" placeholder="Gender"
                />
            </div>
            <div class="alreadyAccount">
                <p>Already have an account? <a href="/login">Login here.</a></p>
            </div>
        </div>
        <div v-if="isActiveTab2==true">
      			<input class="forms-sign-up" v-model="user.nome" placeholder="Name" />

      			<input class="forms-sign-up1" v-model="user.username" placeholder="Username">
    		
      			<input class="forms-sign-up2" v-model="user.email" placeholder="Email"/>
    
      			<input class="forms-sign-up3" v-model="user.password" placeholder="Password" type="password"/>
    		
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
            country: '',
            region: '',
            user: new User('', '', '', ''),
            tecnico: new Tecnico('', '', '', '',''),
            isActiveTab1:true,
            isActiveTab2:false
		}
	},
    components:{
        NavBarComponent,
        FooterComponent,
        VueSelect
    },
    methods:{
        doSignUp(){
            console.log("entrou")
            if(this.isActiveTab1==true){
                this.$store.dispatch('auth/signUpUser', this.user).then(resp => {
                    console.log(this.user);
                    console.log(resp);
                    this.$swal({
                        icon: "success",
                        title: "Success!",
                        text: "Your account was successfully created."});
                },error => {
                    this.$swal({
                    icon: "error",
                    title: "Error!",
                    text: "That username already exists."});
                }
            );
            }
        },
        changeTab(tab){
            if(tab==="tab1"){
                this.isActiveTab1=true;
                this.isActiveTab2=false;
            }
            if(tab==="tab2"){
                this.isActiveTab1=false;
                this.isActiveTab2=true;
            }
        }
    }
}

</script>


<style lang="css" scoped>
@import '../assets/SignUp.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>

