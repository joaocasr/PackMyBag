<template>
  	<div class="navbar">
    		<div class="myheader">
      			<div class="logo-parent">
        				<div class="logo">
          					<b class="exclusive">
          					</b>
        				</div>
        				<div class="home-parent">
          					<div class="home">
            						<div class="home"><a href="/">Home</a></div>
          					</div>
          					<div class="stylists1">
            						<div class="stylists1"><a href="/stylists">Stylists</a></div>
          					</div>
          					<div class="catalogue">
            						<div class="catalogue1"><a href="/catalogue">Catalogue</a></div>
          					</div>
          					<div class="about">
            						<div class="about"><a href="/about">About</a></div>
          					</div>
          					<div class="signup">
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
        				</div>
    		</div>
    		<img class="logo-sideways-icon" alt="" src="/NavBarIMG/logo-sideways.png">
    		
  	</div>
</template>
<script>
export default {
    data(){
        return {
            a:1
        }
    },
	methods:{
		gotoFavourites(){
			this.$router.push({ path: '/favourites'})
		}
	}
}
</script>
<style lang="css" scoped>
@import '../assets/navbar.css';
</style>
