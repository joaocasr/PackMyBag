<template>
    <main>
        <NavBarComponent></NavBarComponent>
        
            <div class="signuppage">
                    
                    <div class="signuppage-child">
                    </div>
                    <div class="forms-sign-up">
                        <!-- 
                        <div class="name">Name:</div>
                        <img class="forms-sign-up-child" alt="" src="/SignUpIMG/Line 19.svg">
                        -->
                        <input type="text" placeholder="Name" style="border: none; border-bottom: 1px solid #000; outline: none; width: 540px; font-size: 18px;">

                        
                    </div>
                    <img class="generic-user-icon-13-262266219" alt="" src="/SignUpIMG/generic-user-icon-13-2622662197-removebg-preview 1.png">
                    
                    <div class="import-profile-picture">Import profile picture (optional)</div>
                    <div class="forms-sign-up1">
                        <!--
                        <div class="name">Email:</div>
                        <img class="forms-sign-up-child" alt="" src="/SignUpIMG/Line 19.svg">
                        -->

                        <input type="text" placeholder="Email" style="border: none; border-bottom: 1px solid #000; outline: none; width: 540px; font-size: 18px;">
                        
                    </div>
                    <div class="forms-sign-up2">
                        <!--
                        <div class="name">Password:</div>
                        <img class="forms-sign-up-child" alt="" src="/SignUpIMG/Line 19.svg">
                        -->

                        <input type="text" placeholder="Password" style="border: none; border-bottom: 1px solid #000; outline: none; width: 540px; font-size: 18px;">
                        
                    </div>
                    <div class="phone-number">Phone number:</div>
                    <div class="your-number-wrapper">
                        <!-- <div class="your-number">your number</div> -->
                        <input type="text" placeholder="your number" style="border: none; outline: none; width: 900px; font-size: 18px;">
                    </div>
                    <div class="country-code-wrapper">
                        <!-- <div class="country-code">country code </div> -->
                        <input type="text" placeholder="country code" style="border: none; outline: none; width: 900px; font-size: 18px;">
                    </div>

                    <div class="country-code-help">
                        <a href="https://www.countrycode.org/" target="_blank">Check all countries codes here!</a>
                    </div>

                    <!-- <img class="vector-icon" alt="" src="/SignUpIMG/Vector.svg" id="vector"> -->
                    <a href="/signup/signup2">
                        <img class="vector-icon" alt="" src="/SignUpIMG/Vector.svg" id="vector">
                    </a>
                    
                    <img class="image-35-icon" alt="" src="/SignUpIMG/image 35.png">
                    
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
import VueSelect from "vue3-select-component";
import axios from 'axios';

export default {

    data(){
		return{
			value: [0,1000],
			selectedOption:'',
			typeOptions: [{ label: 'Male', value: 'Male' },{ label: 'Female', value: 'Female' }],
			items: [],
			filtered: [],
			current_page:0
		}
	},


components:{
    NavBarComponent,
    FooterComponent,
    VueSelect
}
}

</script>


<style lang="css" scoped>
@import '../assets/SignUp.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>

