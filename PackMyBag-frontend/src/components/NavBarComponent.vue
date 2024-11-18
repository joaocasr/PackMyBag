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
          					<div v-if="this.role=='Cliente'" @click="gotoStylists()" class="stylists1">
            						<div class="stylists1">Stylists</div>
          					</div>
          					<div v-if="this.role=='Tecnico'" class="stylists1">
            						<div class="stylists1">Store</div>
          					</div>

							<div v-if="this.role!='Tecnico'" @click="gotoCatalogue()" class="catalogue">
            						<div class="catalogue1">Catalogue</div>
          					</div>
							<div v-if="this.role=='Tecnico'" class="catalogue">
            						<div class="catalogue1">Orders</div>
          					</div>


							<div @click="gotoAbout()" class="about">
            						<div class="about">About</div>
          					</div>
          					<div @click="gotoSignUp()" class="signup">
            						<div class="signup"><a href="/signup">Sign Up</a></div>
          					</div>
        				</div>
      			</div>
				  <form @submit.prevent="handleSearch">
					<input v-model="itemName" placeholder="What are you looking for?" class="search-component-set-parent"/>            						
				  </form>
        			
        				<div class="wishlist-parent">
          					<div v-if="this.role=='Cliente' && token!==null" @click="gotoFavourites()" class="wishlist" id="wishlistContainer">
            						<img class="vector-icon" alt="" src="/NavBarIMG/Vector.svg">
            						
          					</div>
          					<div v-if="this.role=='Cliente' && token!==null" @click="gotoCart()" class="cart1-with-buy" id="cart1WithBuy">
            						<img class="cart1-icon" alt="" src="/NavBarIMG/Cart1.svg">
            						
          					</div>
							<div class="selectwrapper">

								<select class="selectClass" v-bind:style="{backgroundImage:profileImg}" @change="onChange($event)" v-model="selected">
									<option v-if="token!=null" value="logout">Logout</option>
									<option v-if="token==null" value="login">Login</option>
									<option v-if="this.role=='Cliente'" value="notifications">My Notifications</option>
									<option v-if="this.role=='Estilista'" value="requests">My Requests</option>
									<option v-if="this.role=='Cliente'" value="payments">Payments</option>
									<option v-if="token!==null" value="profile">Profile</option>
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
			profileImg:"url(/NavBarIMG/user-icon.png)",
			role:"",
			token:null,
			itemName:""
        }
    },
	created(){
		let token = authService.getToken();
		console.log("nav");
		console.log(token);
		if(token!=null){
			this.token = token;
			this.username = token.username;
			if(token.profileImage!=="") this.profileImg = "url(http://localhost:8888/api/utilizadoresService/profileImg/"+this.username+")";
			this.role = token.role;
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
		gotoLogin(){
			this.$router.push({path:'/login'})
		},
		gotoCart(){
			this.$router.push({path:'/cart'})
		},
		goToNotifications(){
			this.$router.push({path:'/notifications'})
		},
		goToRequests(){
			this.$router.push({path:'/requests'})
		},
		gotoPayments(){
			this.$router.push({path:'/payments'})
		},
		gotoprofile(){
			this.$router.push({path:'/profile'})
		},
		logout(){
			this.$store.dispatch('auth/logout').then(()=>{
				this.token=null;
				this.profileImg = 'url(/NavBarIMG/user-icon.png)';
				this.$router.push({path:'/'})
			},error=>{
				console.log(error);
			})
		},
		onChange(event){
			let val = event.target.value;
			if(val==='login') this.gotoLogin();
			if(val==='logout') this.logout();
			if(val==='profile') this.gotoprofile();
			if(val==='notifications') this.goToNotifications();
			if(val==='requests') this.goToRequests();
			if(val==='payments') this.gotoPayments();
		},
		handleSearch(){
			if(this.itemName!==''){
				this.$router.push({name:'catalogue', query: { q : this.itemName }})
			}
		}
	}
}
</script>
<style lang="css" scoped>
@import '../assets/navbar.css';
</style>
