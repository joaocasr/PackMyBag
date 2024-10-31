<template>
    <main>
        <NavBarComponent></NavBarComponent>
  	
        <div class="background-card">
            	<img class="logo-sideways-icon" alt="" src="/SignUpIMG/logo-sideways.png">

      			<input class="forms-sign-up1" v-model="user.username" placeholder="Username">
      			<input class="forms-sign-up3" v-model="user.password" placeholder="Password" type="password"/>
    		
   
    		<button class="vector-icon"  @click="doLogin()" alt="" id="vector">LOGIN</button>
    		
            
            <div class="alreadyAccount">
                <p>Don't have an account? <a href="/signup">Sign Up here.</a></p>
            </div>            
        </div>
        <FooterComponent></FooterComponent>
    </main>
</template>

<script>

import NavBarComponent from '@/components/NavBarComponent.vue';
import FooterComponent from '@/components/FooterComponent.vue';
import User from "@/models/user"
export default {

    data(){
		return{
            user: new User('', '', '', '')
		}
	},
    components:{
        NavBarComponent,
        FooterComponent
    },
    methods:{
        doLogin(){
            console.log(this.user);
               this.$store.dispatch('auth/login',this.user).then(resp=>{
                console.log(resp);
                this.$swal({
                    icon: "success",
                    title: "Success!",
                    text: "You are logged in."});
                this.$router.push({path:'/'})
           }, errr=>{
                this.$swal({
                        icon: "error",
                        title: "Error!",
                        text: "Something went wrong. Try again."});
                    }
                );
        }   
    }
}

</script>


<style lang="css" scoped>
@import '../assets/login.css';
</style>
<style src="@vueform/slider/themes/default.css"></style>

