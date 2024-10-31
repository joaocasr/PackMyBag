<template>
  	<div class="navbar">
    		<div class="myheader">
      			<div class="logo-parent">
        				<div class="logo">
          					<b class="exclusive">
          					</b>
        				</div>
        				<div class="home-parent">
          					<div @click="gotoHome()" class="home">
            						<div class="home">Home</div>
          					</div>
          					<div @click="gotoStylists()" class="stylists1">
            						<div class="stylists1">Stylists</div>
          					</div>
          					<div @click="gotoCatalogue()" class="catalogue">
            						<div class="catalogue1">Catalogue</div>
          					</div>
          					<div @click="gotoAbout()" class="about">
            						<div class="about">About</div>
          					</div>
          					<div @click="gotoSignUp()" class="signup">
            						<div class="signup"><a href="/signup">Sign Up</a></div>
          					</div>
        				</div>
      			</div>
      			<input placeholder="What are you looking for?" class="search-component-set-parent"/>            						
          					
        			
        				<div class="wishlist-parent">
          					<div @click="gotoFavourites()" class="wishlist" id="wishlistContainer">
            						<img class="vector-icon" alt="" src="/NavBarIMG/Vector.svg">
            						
          					</div>
          					<div class="cart1-with-buy" id="cart1WithBuy">
            						<img class="cart1-icon" alt="" src="/NavBarIMG/Cart1.svg">
            						
          					</div>
							<div>

								<select class="selectClass" v-model="selected">
									<option @click="logout()">Logout</option>
									<option>My Notifications</option>
									<option>All My Orders</option>
									<option>Profile</option>
								</select>
							</div>
							<div v-if="token!=null" class="welcomeclass">Hello, {{username}}!
							</div>
        				</div>
    		</div>
    		<img class="logo-sideways-icon" alt="" src="/NavBarIMG/logo-sideways.png">
    		
  	</div>
</template>
<script>
import authService from '@/services/auth-service';

export default {
    data(){
        return {
            selected:"",
			username:"",
			token:null
        }
    },
	created(){
		let token = authService.getToken();
		console.log(token);
		if(token!=null){
			this.token = token;
			this.username=token.username;
		}
	}
	,methods:{
		gotoHome(){
			this.$router.push({ path: '/'})
		},
		gotoStylists(){
			this.$router.push({ path: '/stylists'})
		},
		gotoFavourites(){
			this.$router.push({ path: '/favourites'})
		},
		gotoCatalogue(){
			this.$router.push({ path: '/catalogue'})
		},
		gotoAbout(){
			this.$router.push({path:'/about',name:'about'})
		},
		gotoSignUp(){
			this.$router.push({path:'/signup'})
		},	
		logout(){
			this.$router.dispatch('auth/logout').then(()=>{
				this.$router.push({path:'/'})
			},error=>{
				console.log(error);
			})
		}
	}
}
</script>
<style lang="css" scoped>
@import '../assets/navbar.css';
</style>
