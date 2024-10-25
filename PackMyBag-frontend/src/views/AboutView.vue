<template>

  <main>
  	  <NavBarComponent></NavBarComponent>
  	<div class="aboutpage">
    		<div class="myfooter">
      			<div class="footer">
        				<div class="underline-parent">
          					<img class="underline-icon" alt="" src="UnderLine.svg">
          					
          					<div class="frame-wrapper">
            						<div class="icon-copyright-parent">
              							<img class="icon-copyright" alt="" src="icon-copyright.svg">
              							
              							<div class="exclusivegmailcom">Copyright Rimel 2024. All right reserved</div>
            						</div>
          					</div>
        				</div>
        				<div class="frame-parent">
          					<div class="frame-container">
            						<div class="frame-div">
              							<div class="logo-parent">
                								<div class="logo">
                  									<b class="exclusive">PackMyBag</b>
                								</div>
                								<div class="subscribe">Subscribe</div>
              							</div>
            						</div>
          					</div>
          					<div class="logo-parent">
            						<div class="support">Support</div>
            						<div class="bijoy-sarani-dhaka-dh-1515-parent">
              							<div class="bijoy-sarani-dhaka">Braga, Portugal</div>
              							<div class="exclusivegmailcom">exclusive@gmail.com</div>
              							<div class="exclusivegmailcom">+88015-88888-9999</div>
            						</div>
          					</div>
          					<div class="logo-parent">
            						<div class="support">Quick Link</div>
            						<div class="bijoy-sarani-dhaka-dh-1515-parent">
              							<div class="exclusivegmailcom">Privacy Policy</div>
              							<div class="exclusivegmailcom">Terms Of Use</div>
              							<div class="exclusivegmailcom">FAQ</div>
              							<div class="exclusivegmailcom">Contact</div>
            						</div>
          					</div>
        				</div>
      			</div>
    		</div>
    		<div class="about-us">About us</div>
    		<div class="traveling-is-an-container">
      			<p class="traveling-is-an">Traveling is an exciting experience; however, carrying luggage can be one of the most inconvenient aspects,</p>
      			<p class="traveling-is-an">mainly due to financial reasons, both for you and the airlines.</p>
      			<p class="traveling-is-an">&nbsp;</p>
      			<p class="traveling-is-an">There are several problems associated with this issue, such as the physical effort of carrying luggage, the</p>
      			<p class="traveling-is-an">possibility of losing it, the additional costs of checked baggage, excess luggage that customers wish to bring on </p>
      			<p class="traveling-is-an">their trip, and the fact that people traveling to countries with very different climates are forced to buy clothes </p>
      			<p class="traveling-is-an">they may hardly use again or only need for very specific occasions or events. Additionally, there is an </p>
      			<p class="traveling-is-an">environmental aspect, as the heavier the checked baggage, the higher the fuel consumption, which leads to </p>
      			<p class="traveling-is-an">increased emissions of greenhouse gases, such as carbon dioxide (CO2).</p>
      			<p class="traveling-is-an">&nbsp;</p>
      			<p class="traveling-is-an">
        				<span>Our mission is to provide more freedom to </span>
        				<span class="you">you</span>
        				<span class="the-traveler-offering">, the traveler, offering a light, ecological, convenient and </span>
      			</p>
      			<p class="traveling-is-an">comfortable alternative, and, at the same time, help reduce the environmental impact of the fashion and tourism </p>
      			<p class="traveling-is-an">industry.</p>
      			<p class="traveling-is-an">&nbsp;</p>
      			<p class="we-currently-only">We currently only operate in Portugal.</p>
    		</div>
    		<div class="navbar">
      			<div class="header">
        				<div class="logo-group">
          					<div class="logo">
            						<b class="exclusive">
            						</b>
          					</div>
          					<div class="home-parent">
            						<div class="home">
              							<div class="sign-up">Home</div>
            						</div>
            						<div class="home">
              							<div class="stylists1">Stylists</div>
            						</div>
            						<div class="catalogue">
              							<div class="catalogue1">Catalogue</div>
            						</div>
            						<div class="home">
              							<div class="sign-up">About</div>
            						</div>
            						<div class="signup">
              							<div class="sign-up">Sign Up</div>
            						</div>
          					</div>
        				</div>
        				<div class="search-component-set-parent">
          					<div class="search-component-set">
            						<div class="what-are-you-looking-for-parent">
              							<div class="what-are-you">What are you looking for?</div>
              							<img class="component-2-icon" alt="" src="Component 2.svg">
              							
            						</div>
          					</div>
          					<div class="wishlist-parent">
            						<div class="wishlist" id="wishlistContainer">
              							<img class="vector-icon" alt="" src="Vector.svg">
              							
              							<div class="ellipse-parent">
                								<div class="group-child">
                								</div>
                								<div class="div1">4</div>
              							</div>
            						</div>
            						<div class="cart1-with-buy" id="cart1WithBuy">
              							<img class="cart1-icon" alt="" src="Cart1.svg">
              							
              							<div class="ellipse-group">
                								<div class="group-child">
                								</div>
                								<div class="div2">2</div>
              							</div>
            						</div>
            						<img class="accountsettings-icon" alt="" src="AccountSettings.svg" id="accountSettingsIcon">
            						
          					</div>
        				</div>
      			</div>
      			<img class="logo-sideways-icon" alt="" src="logo-sideways.png">
      			
    		</div>
			
  	</div>
  	
  	<div id="dropdownProfileContainer" class="popup-overlay" style="display:none">
    		
    		<div class="dropdownprofile">
      			<div class="log-out-wrapper">
        				<div class="log-out">Log Out</div>
      			</div>
      			<div class="my-notifications-wrapper" id="popupframeContainer1">
        				<div class="my-notifications">My notifications</div>
      			</div>
      			<div class="my-favorite-items-wrapper" id="popupframeContainer2">
        				<div class="my-favorite-items">My favorite items</div>
      			</div>
      			<div class="all-my-orders-wrapper">
        				<div class="all-my-orders">All my orders</div>
      			</div>
      			<div class="my-reviews-wrapper" id="popupframeContainer4">
        				<div class="my-reviews">My reviews</div>
      			</div>
    		</div>
    		
    		
  	</div>
  	
  	
  	
  	<script>
    		var popupframeContainer1 = document.getElementById("popupframeContainer1");
    		if(popupframeContainer1) {
      			popupframeContainer1.addEventListener("click", function (e) {
        				// Add your code here
      			});
    		}
    		
    		var popupframeContainer2 = document.getElementById("popupframeContainer2");
    		if(popupframeContainer2) {
      			popupframeContainer2.addEventListener("click", function (e) {
        				// Add your code here
      			});
    		}
    		
    		var popupframeContainer4 = document.getElementById("popupframeContainer4");
    		if(popupframeContainer4) {
      			popupframeContainer4.addEventListener("click", function (e) {
        				// Add your code here
      			});
    		}
    		
    		var wishlistContainer = document.getElementById("wishlistContainer");
    		if(wishlistContainer) {
      			wishlistContainer.addEventListener("click", function (e) {
        				// Add your code here
      			});
    		}
    		
    		var cart1WithBuy = document.getElementById("cart1WithBuy");
    		if(cart1WithBuy) {
      			cart1WithBuy.addEventListener("click", function (e) {
        				// Add your code here
      			});
    		}
    		
    		var accountSettingsIcon = document.getElementById("accountSettingsIcon");
    		if(accountSettingsIcon) {
      			accountSettingsIcon.addEventListener("click", function () {
        				var popup = document.getElementById("dropdownProfileContainer");
        				if(!popup) return;
        				var popupStyle = popup.style;
        				if(popupStyle) {
          					popupStyle.display = "flex";
          					popupStyle.zIndex = 100;
          					popupStyle.backgroundColor = "rgba(113, 113, 113, 0.3)";
          					popupStyle.alignItems = "center";
          					popupStyle.justifyContent = "center";
        				}
        				popup.setAttribute("closable", "");
        				
        				var onClick = popup.onClick || function(e) {
          					if(e.target === popup && popup.hasAttribute("closable")) {
            						popupStyle.display = "none";
          					}
        				};
        				popup.addEventListener("click", onClick);
      			});
    		}
        </script>
		<FooterComponent></FooterComponent>
  </main>

</template>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>

