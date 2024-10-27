<template>
    <main>
        <NavBarComponent></NavBarComponent>
        	
            <div class="signuppage2">
                    
                    <div class="an-email-was">An email was sent to you</div>
                    <div class="please-follow-the-container">
                        <p class="please-follow-the">Please follow the instructions on the email we sent you to verify your account and</p>
                        <p class="allow-to-to">allow you to rent clothes.</p>
                    </div>
                    <!-- <img class="vector-icon" alt="" src="/SignUpIMG/Vector.svg" id="vector"> -->
                    <a href="/">
                        <img class="vector-icon" alt="" src="/SignUpIMG/Vector.svg" id="vector">
                    </a>
                       
            </div>

        <FooterComponent></FooterComponent>
    </main>
</template>

<script>

import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
export default {
components:{
    NavBarComponent,
    FooterComponent
}
}

</script>


<style lang="css" scoped>
@import '../assets/SignUp2.css';
</style>