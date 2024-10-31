<template>
  <main>
  	  <NavBarComponent></NavBarComponent>
  	<div class="aboutpage">
    		
    		<div class="about-us">About us</div>
    		<div class="traveling-is-an-container">
      			<p class="traveling-is-an">Traveling is an exciting experience, but carrying luggage can be one of the most inconvenient aspects,</p>
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
        				<span class="you">YOU</span>
        				<span class="the-traveler-offering">, the traveler, by offering a light, ecological, convenient and </span>
      			</p>
      			<p class="traveling-is-an">comfortable alternative, and, at the same time, by reducing the environmental impact of the fashion and tourism </p>
      			<p class="traveling-is-an">industry.</p>
      			<p class="traveling-is-an">&nbsp;</p>
      			<p class="we-currently-only">We currently only operate in Portugal.</p>
    		</div>	
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
@import '../assets/about.css';
</style>

